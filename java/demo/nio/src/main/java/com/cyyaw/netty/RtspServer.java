package com.cyyaw.nio;

import com.cyyaw.netty.RtspRequest;
import com.cyyaw.netty.RtspResponse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

/**
 * RTSP Server implementation using Netty
 */
public class RtspServer {

    private final int port;

    public RtspServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new RtspServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("RTSP Server started on port: " + port);
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    /**
     * Channel initializer for RTSP server
     */
    private static class RtspServerInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new RtspRequestDecoder());
            pipeline.addLast(new RtspResponseEncoder());
            pipeline.addLast(new RtspServerHandler());
        }
    }

    /**
     * Decoder for RTSP requests
     */
    private static class RtspRequestDecoder extends ByteToMessageDecoder {

        private static final Pattern REQUEST_LINE_PATTERN =
            Pattern.compile("^(\\w+) (\\S+) (RTSP/\\d\\.\\d)$", Pattern.CASE_INSENSITIVE);

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            if (in.readableBytes() < 1) {
                return;
            }

            // Check if we have a complete request
            int indexOfCrlf = indexOfCrlf(in);
            if (indexOfCrlf == -1) {
                return; // Not enough data
            }

            // Read the request line
            int lineStart = in.readerIndex();
            int lineEnd = indexOfCrlf;
            String requestLine = readString(in, lineStart, lineEnd);

            // Parse request line
            Matcher matcher = REQUEST_LINE_PATTERN.matcher(requestLine);
            if (!matcher.matches()) {
                // Send error response
                DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.BAD_REQUEST,
                    Unpooled.copiedBuffer("Invalid RTSP Request", CharsetUtil.UTF_8)
                );
                ctx.writeAndFlush(response);
                return;
            }

            String method = matcher.group(1);
            String uri = matcher.group(2);
            String version = matcher.group(3);

            // Read headers
            StringBuilder headersBuilder = new StringBuilder();
            while (true) {
                int headerStart = lineEnd + 2; // Skip CRLF
                if (headerStart >= in.writerIndex()) {
                    return; // Wait for more data
                }

                int nextCrlf = indexOfCrlf(in, headerStart);
                if (nextCrlf == -1) {
                    return; // Wait for more data
                }

                String headerLine = readString(in, headerStart, nextCrlf);

                // Empty line indicates end of headers (assuming no body handling for now)
                if (headerLine.isEmpty()) {
                    // Create RTSP request object
                    RtspRequest request = new RtspRequest(method, uri, version);

                    // Move reader index past headers
                    in.readerIndex(nextCrlf + 2);

                    out.add(request);
                    break;
                } else {
                    // Add header to request
                    int colonIndex = headerLine.indexOf(':');
                    if (colonIndex != -1) {
                        String headerName = headerLine.substring(0, colonIndex).trim();
                        String headerValue = headerLine.substring(colonIndex + 1).trim();
                        request.addHeader(headerName, headerValue);
                    }
                    lineEnd = nextCrlf;
                }
            }
        }

        private int indexOfCrlf(ByteBuf buffer) {
            return indexOfCrlf(buffer, buffer.readerIndex());
        }

        private int indexOfCrlf(ByteBuf buffer, int fromIndex) {
            int toIndex = buffer.writerIndex();
            for (int i = fromIndex; i < toIndex - 1; i++) {
                if (buffer.getByte(i) == '\r' && buffer.getByte(i + 1) == '\n') {
                    return i;
                }
            }
            return -1;
        }

        private String readString(ByteBuf buffer, int start, int end) {
            byte[] bytes = new byte[end - start];
            buffer.getBytes(start, bytes);
            return new String(bytes, CharsetUtil.UTF_8);
        }
    }

    /**
     * Encoder for RTSP responses
     */
    private static class RtspResponseEncoder extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            if (msg instanceof RtspResponse) {
                RtspResponse response = (RtspResponse) msg;
                ByteBuf buffer = ctx.alloc().buffer();

                // Write status line
                String statusLine = response.getVersion() + " " +
                                   response.getStatusCode() + " " +
                                   response.getReasonPhrase() + "\r\n";
                buffer.writeBytes(statusLine.getBytes(CharsetUtil.UTF_8));

                // Write headers
                for (String headerName : response.getHeaderNames()) {
                    String headerValue = response.getHeader(headerName);
                    String headerLine = headerName + ": " + headerValue + "\r\n";
                    buffer.writeBytes(headerLine.getBytes(CharsetUtil.UTF_8));
                }

                // End of headers
                buffer.writeBytes("\r\n".getBytes(CharsetUtil.UTF_8));

                // Write content if available
                if (response.getContent() != null) {
                    buffer.writeBytes(response.getContent().getBytes(CharsetUtil.UTF_8));
                }

                super.write(ctx, buffer, promise);
            } else {
                super.write(ctx, msg, promise);
            }
        }
    }

    /**
     * RTSP request handler
     */
    private static class RtspServerHandler extends SimpleChannelInboundHandler<RtspRequest> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, RtspRequest request) throws Exception {
            System.out.println("Received RTSP request:");
            System.out.println("Method: " + request.getMethod());
            System.out.println("URI: " + request.getUri());
            System.out.println("Version: " + request.getVersion());

            for (String headerName : request.getHeaderNames()) {
                System.out.println("Header: " + headerName + " = " + request.getHeader(headerName));
            }

            RtspResponse response;

            switch (request.getMethod()) {
                case "DESCRIBE":
                    response = handleDescribe(request);
                    break;
                case "SETUP":
                    response = handleSetup(request);
                    break;
                case "PLAY":
                    response = handlePlay(request);
                    break;
                case "TEARDOWN":
                    response = handleTeardown(request);
                    break;
                case "OPTIONS":
                    response = handleOptions(request);
                    break;
                default:
                    response = createErrorResponse(HttpResponseStatus.NOT_IMPLEMENTED, "Method not implemented");
            }

            ctx.writeAndFlush(response);
        }

        private RtspResponse handleOptions(RtspRequest request) {
            RtspResponse response = new RtspResponse("RTSP/1.0", 200, "OK");
            response.addHeader("CSeq", request.getHeader("CSeq"));
            response.addHeader("Public", "OPTIONS, DESCRIBE, SETUP, TEARDOWN, PLAY, PAUSE");
            response.addHeader("Server", "Netty-RTSP-Server/1.0");
            return response;
        }

        private RtspResponse handleDescribe(RtspRequest request) {
            RtspResponse response = new RtspResponse("RTSP/1.0", 200, "OK");
            response.addHeader("CSeq", request.getHeader("CSeq"));
            response.addHeader("Content-Base", request.getUri());
            response.addHeader("Content-Type", "application/sdp");
            response.addHeader("Server", "Netty-RTSP-Server/1.0");

            // SDP content for the video stream
            String sdpContent = "v=0\r\n" +
                               "o=- 123456 1 IN IP4 127.0.0.1\r\n" +
                               "s=RTSP Session\r\n" +
                               "c=IN IP4 0.0.0.0\r\n" +
                               "t=0 0\r\n" +
                               "m=video 0 RTP/AVP 96\r\n" +
                               "a=control:trackID=0\r\n" +
                               "a=rtpmap:96 H264/90000\r\n" +
                               "a=fmtp:96 profile-level-id=42e01f; packetization-mode=1\r\n";

            response.setContent(sdpContent);
            return response;
        }

        private RtspResponse handleSetup(RtspRequest request) {
            RtspResponse response = new RtspResponse("RTSP/1.0", 200, "OK");
            response.addHeader("CSeq", request.getHeader("CSeq"));
            response.addHeader("Transport", "RTP/UDP;unicast;client_port=8000-8001;server_port=9000-9001");
            response.addHeader("Session", "12345678;timeout=60");
            response.addHeader("Server", "Netty-RTSP-Server/1.0");
            return response;
        }

        private RtspResponse handlePlay(RtspRequest request) {
            RtspResponse response = new RtspResponse("RTSP/1.0", 200, "OK");
            response.addHeader("CSeq", request.getHeader("CSeq"));
            response.addHeader("Range", "npt=0.000-");
            response.addHeader("Session", "12345678");
            response.addHeader("RTP-Info", request.getUri() + "/trackID=0;seq=0;rtptime=0");
            response.addHeader("Server", "Netty-RTSP-Server/1.0");
            return response;
        }

        private RtspResponse handleTeardown(RtspRequest request) {
            RtspResponse response = new RtspResponse("RTSP/1.0", 200, "OK");
            response.addHeader("CSeq", request.getHeader("CSeq"));
            response.addHeader("Server", "Netty-RTSP-Server/1.0");
            return response;
        }

        private RtspResponse createErrorResponse(HttpResponseStatus status, String message) {
            RtspResponse response = new RtspResponse("RTSP/1.0", status.code(), status.reasonPhrase());
            response.addHeader("Content-Type", "text/html");
            response.setContent("<html><body><h1>" + status.code() + " " + status.reasonPhrase() + "</h1><p>" + message + "</p></body></html>");
            return response;
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.err.println("Exception in RTSP server: " + cause.getMessage());
            cause.printStackTrace();
            ctx.close();
        }
    }
}