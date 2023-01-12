package com.cyyaw.netty.shorts.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.TooLongFrameException;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteOrder;
import java.util.List;

import static io.netty.util.internal.ObjectUtil.checkPositive;
import static io.netty.util.internal.ObjectUtil.checkPositiveOrZero;

/**
 * Byte To Message 解码器
 */
@Slf4j
public class MyDecoder extends ByteToMessageDecoder {
    // 大端、小端
    private final ByteOrder byteOrder;
    //
    private final int maxFrameLength;
    // 长度位偏移
    private final int lengthFieldOffset;
    // 长度字节宽度
    private final int lengthFieldLength;
    //
    private final int lengthFieldEndOffset;
    //
    private final int lengthAdjustment;
    //
    private final int initialBytesToStrip;
    //
    private final boolean failFast;
    //
    private boolean discardingTooLongFrame;
    //
    private long tooLongFrameLength;
    //
    private long bytesToDiscard;

    public MyDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        this(maxFrameLength, lengthFieldOffset, lengthFieldLength, 0, 0);
    }

    public MyDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        this(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, true);
    }

    public MyDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        this(ByteOrder.BIG_ENDIAN, maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
    }


    public MyDecoder(ByteOrder byteOrder, int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        if (byteOrder == null) {
            throw new NullPointerException("byteOrder");
        }
        checkPositive(maxFrameLength, "maxFrameLength");
        checkPositiveOrZero(lengthFieldOffset, "lengthFieldOffset");
        checkPositiveOrZero(initialBytesToStrip, "initialBytesToStrip");
        if (lengthFieldOffset > maxFrameLength - lengthFieldLength) {
            throw new IllegalArgumentException("maxFrameLength (" + maxFrameLength + ") " + "must be equal to or greater than " + "lengthFieldOffset (" + lengthFieldOffset + ") + " + "lengthFieldLength (" + lengthFieldLength + ").");
        }
        this.byteOrder = byteOrder;
        this.maxFrameLength = maxFrameLength;
        this.lengthFieldOffset = lengthFieldOffset;
        this.lengthFieldLength = lengthFieldLength;
        this.lengthAdjustment = lengthAdjustment;
        this.lengthFieldEndOffset = lengthFieldOffset + lengthFieldLength;
        this.initialBytesToStrip = initialBytesToStrip;
        this.failFast = failFast;
    }

    @Override
    protected final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object decoded = decode(ctx, in);
        if (decoded != null) {
            out.add(decoded);
        }
    }

    private void discardingTooLongFrame(ByteBuf in) {
        long bytesToDiscard = this.bytesToDiscard;
        int localBytesToDiscard = (int) Math.min(bytesToDiscard, in.readableBytes());
        in.skipBytes(localBytesToDiscard);
        bytesToDiscard -= localBytesToDiscard;
        this.bytesToDiscard = bytesToDiscard;
        failIfNecessary(false);
    }

    private static void failOnNegativeLengthField(ByteBuf in, long frameLength, int lengthFieldEndOffset) {
        in.skipBytes(lengthFieldEndOffset);
        throw new CorruptedFrameException("negative pre-adjustment length field: " + frameLength);
    }

    private static void failOnFrameLengthLessThanLengthFieldEndOffset(ByteBuf in, long frameLength, int lengthFieldEndOffset) {
        in.skipBytes(lengthFieldEndOffset);
        throw new CorruptedFrameException("Adjusted frame length (" + frameLength + ") is less " + "than lengthFieldEndOffset: " + lengthFieldEndOffset);
    }

    private void exceededFrameLength(ByteBuf in, long frameLength) {
        long discard = frameLength - in.readableBytes();
        tooLongFrameLength = frameLength;
        if (discard < 0) {
            in.skipBytes((int) frameLength);
        } else {
            discardingTooLongFrame = true;
            bytesToDiscard = discard;
            in.skipBytes(in.readableBytes());
        }
        failIfNecessary(true);
    }

    private static void failOnFrameLengthLessThanInitialBytesToStrip(ByteBuf in, long frameLength, int initialBytesToStrip) {
        in.skipBytes((int) frameLength);
        throw new CorruptedFrameException("Adjusted frame length (" + frameLength + ") is less " + "than initialBytesToStrip: " + initialBytesToStrip);
    }

    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (discardingTooLongFrame) {
            discardingTooLongFrame(in);
        }
        // in.readableBytes()  获取可读字节
        if (in.readableBytes() < lengthFieldEndOffset) {
            return null;
        }
        int actualLengthFieldOffset = in.readerIndex() + lengthFieldOffset;

        long frameLength = getUnadjustedFrameLength(in, actualLengthFieldOffset, lengthFieldLength, byteOrder);
        log.info("======== 数据大小: {}", frameLength);
        if (frameLength < 0) {
            failOnNegativeLengthField(in, frameLength, lengthFieldEndOffset);
        }

        byte[] arr = new byte[(int) frameLength + lengthFieldLength];
        in.getBytes(0, arr);
        log.info("数据：{}", new String(arr, "GBK"));


        frameLength += lengthAdjustment + lengthFieldEndOffset;
        if (frameLength < lengthFieldEndOffset) {
            failOnFrameLengthLessThanLengthFieldEndOffset(in, frameLength, lengthFieldEndOffset);
        }
        if (frameLength > maxFrameLength) {
            exceededFrameLength(in, frameLength);
            return null;
        }
        // never overflows because it's less than maxFrameLength
        int frameLengthInt = (int) frameLength;
        if (in.readableBytes() < frameLengthInt) {
            return null;
        }
        if (initialBytesToStrip > frameLengthInt) {
            failOnFrameLengthLessThanInitialBytesToStrip(in, frameLength, initialBytesToStrip);
        }
        in.skipBytes(initialBytesToStrip);
        // extract frame
        int readerIndex = in.readerIndex();

        int actualFrameLength = frameLengthInt - initialBytesToStrip;
        // 读取数据
        ByteBuf frame = extractFrame(ctx, in, readerIndex, actualFrameLength);
        // 移动下标
        in.readerIndex(readerIndex + actualFrameLength);
        return frame;
    }


    protected long getUnadjustedFrameLength(ByteBuf buf, int offset, int length, ByteOrder order) {
        buf = buf.order(order);
        long frameLength;
        switch (length) {
            case 1:
                frameLength = buf.getUnsignedByte(offset);
                break;
            case 2:
                frameLength = buf.getUnsignedShort(offset);
                break;
            case 3:
                frameLength = buf.getUnsignedMedium(offset);
                break;
            case 4:
                frameLength = buf.getUnsignedInt(offset);
                break;
            case 8:
                frameLength = buf.getLong(offset);
                break;
            default:
                throw new DecoderException("unsupported lengthFieldLength: " + lengthFieldLength + " (expected: 1, 2, 3, 4, or 8)");

        }
        return frameLength;
    }

    private void failIfNecessary(boolean firstDetectionOfTooLongFrame) {
        if (bytesToDiscard == 0) {
            long tooLongFrameLength = this.tooLongFrameLength;
            this.tooLongFrameLength = 0;
            discardingTooLongFrame = false;
            if (!failFast || firstDetectionOfTooLongFrame) {
                fail(tooLongFrameLength);
            }
        } else {
            if (failFast && firstDetectionOfTooLongFrame) {
                fail(tooLongFrameLength);
            }
        }
    }

    protected ByteBuf extractFrame(ChannelHandlerContext ctx, ByteBuf buffer, int index, int length) {
        /**
         * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
         * +   xxxxx   |  长度位  |     数据
         * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
         * ____________________index  -------------------------------  length ------------------------------------------------
         */
        return buffer.retainedSlice(index, length);
    }

    private void fail(long frameLength) {
        if (frameLength > 0) {
            throw new TooLongFrameException("Adjusted frame length exceeds " + maxFrameLength + ": " + frameLength + " - discarded");
        } else {
            throw new TooLongFrameException("Adjusted frame length exceeds " + maxFrameLength + " - discarding");
        }
    }
}
