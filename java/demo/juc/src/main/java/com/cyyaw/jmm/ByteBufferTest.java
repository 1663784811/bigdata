package com.cyyaw.jmm;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接内存实现两个文件之间的copy
 */
public class ByteBufferTest {


    public static void main(String[] args) {


    }


    /**
     * 通过JAVA NIO 非直接缓冲区拷贝文件
     *
     * @param sourcePath 源文件路径
     * @param targetPath 目标文件路径
     */
    public static void copyFileByChannel(String sourcePath, String targetPath) {
        FileChannel outChannel = null;
        FileChannel inChannel = null;

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(sourcePath);
            fos = new FileOutputStream(targetPath);
            //获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            //分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (inChannel.read(buf) != -1) {
                //转换为读取数据模式
                buf.flip();
                //写入到磁盘
                outChannel.write(buf);
                //清空缓冲区
                buf.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (outChannel != null) {
                    outChannel.close();
                }
                if (inChannel != null) {
                    inChannel.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
