package sample;

import java.awt.*;

public class MyColor {

    final int MIN_GRAY = 10;
    final int MAX_GRAY = 240;

    int rgb;
    int r;
    int g;
    int b;
    int diapozone;
    int intensity;
    float diapozoneLengh;
    Color finalColor;
    boolean isBlackToWhite;

    public MyColor(int rgb , boolean isBlackToWhite) {

//        testColor();

        this.rgb = rgb;
        this.isBlackToWhite = isBlackToWhite;
        init();
        recomputeColor();
    }

    private void init(){
        r = (rgb >> 16) & 0xFF;
        g = (rgb >> 8) & 0xFF;
        b = (rgb & 0xFF);
        findColorDiapozone(r,g,b);
    }

    private int findColorDiapozone(int r,int g,int b) {
        if ((r == 0) && (b == 255)) {
            diapozone = 1;
            intensity = g;
        } else if ((r == 0) && (g == 255)) {
            diapozone = 2;
//            if (b == 0) {
            if (false) {

                intensity = 255;
            } else {intensity  = b;}
            intensity = Math.abs(intensity-255);
        } else if ((g == 255) && (b == 0)) {
            diapozone = 3;
            intensity = r;
        } else if ((r == 255) && (b == 0)) {
            diapozone = 4;
//            if (g ==0) {
                if (false) {
                intensity = 255;
            } else {
                intensity = g;
            }
            intensity = Math.abs(intensity-255);
        }

        if (diapozone == 0) {
            System.out.println("wrong color");
        }
        return diapozone;

    }

    private int recomputeColor() {
        int greyInv = 0;
        diapozoneLengh =Math.abs ((MAX_GRAY-MIN_GRAY)/4);
//        float greyColor = MIN_GRAY + diapozoneLengh*(diapozone-1)  +(intensity/255.f)*diapozoneLengh;
        float greyColor = MIN_GRAY + diapozoneLengh*(diapozone-1)  +(intensity/255.f)*diapozoneLengh;
        if (!isBlackToWhite) {
            greyInv = (int) (Math.abs(MAX_GRAY - greyColor));
        }
        try {
            if (!isBlackToWhite) {
                finalColor = new Color(greyInv, greyInv, greyInv);
            } else {
                finalColor = new Color((int)greyColor,(int)greyColor,(int)greyColor);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("error" + e);
        }

        return (int)greyColor;
    }

    public void testColor() {
//
//        Color color = new Color(0,0,255);
//        int rgb1 = color.getRGB();
//        int diap =  findColorDiapozone(color.getRed(),color.getGreen(),color.getBlue());
//        System.out.println("diap=" + diapozone+ " inten=" + intensity + " recop=" + recomputeColor());
//
//        color = new Color(0,127,255);
//        int rgb2 = color.getRGB();
//        int diap2 =  findColorDiapozone(color.getRed(),color.getGreen(),color.getBlue());
//        System.out.println("diap=" + diapozone+ " inten=" + intensity+ " recop=" + recomputeColor());
//
//        color = new Color(0,255,255);
//        int rgb3 = color.getRGB();
//        int diap3 =  findColorDiapozone(color.getRed(),color.getGreen(),color.getBlue());
//        System.out.println("diap=" + diapozone+ " inten=" + intensity+ " recop=" + recomputeColor());
//
//        color = new Color(0,255,127);
//        int rgb4 = color.getRGB();
//        int diap4 =  findColorDiapozone(color.getRed(),color.getGreen(),color.getBlue());
//        System.out.println("diap=" + diapozone+ " inten=" + intensity+ " recop=" + recomputeColor());
//
//        color = new Color(0,255,0);
//        int rgb5 = color.getRGB();
//        int diap5 =  findColorDiapozone(color.getRed(),color.getGreen(),color.getBlue());
//        System.out.println("diap=" + diapozone+ " inten=" + intensity+ " recop=" + recomputeColor());
//
//        color = new Color(127,255,0);
//        int rgb6 = color.getRGB();
//        int diap6 =  findColorDiapozone(color.getRed(),color.getGreen(),color.getBlue());
//        System.out.println("diap=" + diapozone+ " inten=" + intensity+ " recop=" + recomputeColor());
//
//        color = new Color(255,255,0);
//        int rgb7 = color.getRGB();
//        int diap7 =  findColorDiapozone(color.getRed(),color.getGreen(),color.getBlue());
//        System.out.println("diap=" + diapozone+ " inten=" + intensity+ " recop=" + recomputeColor());
//
//        color = new Color(255,127,0);
//        int rgb8 = color.getRGB();
//        int diap8 =  findColorDiapozone(color.getRed(),color.getGreen(),color.getBlue());
//        System.out.println("diap=" + diapozone+ " inten=" + intensity+ " recop=" + recomputeColor());
//
        Color color = new Color(255,0,0);
        int rgb9 = color.getRGB();
        int diap9 =  findColorDiapozone(color.getRed(),color.getGreen(),color.getBlue());
        System.out.println("diap=" + diapozone+ " inten=" + intensity+ " recop=" + recomputeColor());

    }
}
