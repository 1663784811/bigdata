package com.cyyaw.netty;

import com.cyyaw.nio.RtspServer;

/**
 * Netty Application class for RTSP server
 * This class serves as the entry point for the RTSP server application using Netty.
 */
public class NettyApplication {

    /**
     * Main method to start the RTSP server using Netty
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        int port = 8554; // Default RTSP port

        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number provided. Using default port: " + port);
            }
        }

        try {
            System.out.println("Starting RTSP server on port " + port + "...");
            RtspServer rtspServer = new RtspServer(port);
            rtspServer.start();
        } catch (Exception e) {
            System.err.println("Failed to start RTSP server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}