package com.cyyaw.jvm;

import java.io.*;

public class MyClassLoader extends ClassLoader {

    private String codePath;

    public MyClassLoader(String codePath) {
        this.codePath = codePath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        BufferedInputStream bis = null;
        ByteArrayOutputStream bos = null;
        /*
            File.separator代表的是//
            codePath是 C:\\Users\\19878\\IdeaProjects\\learn\\
            假如 测试时传过来的name是 com.TwoNum 是包名+类名
            codePath 拼接后是 C:\\Users\\19878\\IdeaProjects\\learn\\com\\TwoNum.class
            需要把编译后的TwoNum.class 文件放在项目下自己建个com包，把TwoNum.class放进去
        */
        codePath = codePath + name.replace(".", File.separator) + ".class";
        byte[] bytes = new byte[1024];
        int line = 0;
        try {
            //读取编译后的文件
            bis = new BufferedInputStream(new FileInputStream(codePath));
            bos = new ByteArrayOutputStream();
            while ((line = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, line);
            }
            bos.flush();
            bytes = bos.toByteArray();
            return defineClass(null, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return super.findClass(name);
    }


}
