package com.cyyaw.file;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesTest {


    public static void main(String[] args) throws IOException {
        int dstWidth = 100;
        int dstHeight = 100;
        int height_i =0;
        int width =0;
        int height =0;
        String type = "png";
        int[] bytArr = new int[10];
        File outFile = new File("aa.png");
        BufferedImage imageNew = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);
        imageNew.setRGB(0, height_i, width, height, bytArr, 0, width);
        ImageIO.write(imageNew, type, outFile);
    }


}
