package com.cyyaw.file;


import java.io.*;

/**
 * 文件
 */
public class FileTest {


    public static void main(String[] args) {

        //源文件路径
        File source = new File("G:\\CentOS-7-x86_64-DVD-2009.iso");
        //目标文件路径
        File target = new File("G:\\copy.iso");


        try {
            //实现文件的拷贝
            InputStream inputStream = new FileInputStream(source);
            OutputStream outputStream = new FileOutputStream(target);
            int temp = 0;
            //每次读取1024个字节
            byte[] data = new byte[1024];
            //将每次读取的数据保存到字节数组里面，并且返回读取的个数
            while ((temp = inputStream.read(data)) != -1){
                //输出数组
                outputStream.write(data,0,temp);
            }

            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }






    }


}


