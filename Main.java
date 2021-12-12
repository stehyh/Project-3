package com.project3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import static java.awt.Image.SCALE_SMOOTH;

public class Main {
    static String[] SYMBOLS = new String[]{".","+",":",";","1","2","3","?","5","*","#"};
    public static void main(String[] args) {

        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream("./1.png"));
            int scale = 30;
            float currW = bufferedImage.getWidth();
            float currH = bufferedImage.getHeight();

            int newH = (int)((currH) / (currW) * scale);
            Graphics g = bufferedImage.getGraphics();
            BufferedImage newImage = (BufferedImage)(bufferedImage.getScaledInstance((int)currW, newH, SCALE_SMOOTH));

            List<Integer> clr = new ArrayList<Integer>();
            for (int i = 0; i < (int)currW; i++) {
                for (int j = 0; j < newH; j++) {
                    clr.add(newImage.getRGB(i, j));
                }
            }

            BufferedImage grayImage = new BufferedImage((int)currW, newH, BufferedImage.TYPE_BYTE_GRAY);

            List<String> symbolRresult = symbolizer(grayImage);
            int length = symbolRresult.size();

            List<List<String>> newImage2 = new ArrayList<List<String>>();
            for(int i = 0; i < length; i+= scale){
                List<String> d = symbolRresult.subList(i, i + scale);
                newImage2.add(d);
            }

            List<List<String>> result = new ArrayList<List<String>>();
            for (int i = 0; i < currW; i++) {
                for (int j = 0; j < currH; j++) {
                    result.add(Arrays.asList(newImage2.get(i).get(j), String.valueOf(clr.get(0)), String.valueOf(clr.get(1)), String.valueOf(clr.get(2)), String.valueOf(clr.get(3))));
                    System.out.println(newImage2.get(i).get(j));
                }
                System.out.println("");
            }
        }catch (Exception e){

        }
    }

    public static List<String> symbolizer(BufferedImage src){
        int scaler = 25;
        List<String> newPixels = new ArrayList<String>();
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                newPixels.add(SYMBOLS[src.getRGB(i, j)/scaler]);
            }
        }
        return newPixels;
    }
    
}
