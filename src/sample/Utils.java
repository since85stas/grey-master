package sample;

import sun.rmi.runtime.Log;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class Utils {

    public static BufferedImage readImage(String imageName) {

        BufferedImage image = null;
        File f = null;

        //read image
        try {
            f = new File(imageName); //image file path
//            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//            image = new BufferedImage(f)
            image = ImageIO.read(f);
            System.out.println("Reading complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return image;
    }

    public static boolean writeImage(BufferedImage image, File file) {

//        dir = "/sysroot/home/stas/my_apps";
//        origFileName = "French Puppy";
//        String
        boolean isSuccess = true;
        if (image == null) {
            throw new IllegalArgumentException();
        }
        try {
            String name = file.getName();
            String[] nameWithExt = name.split("\\.");
            String newName = "grey_" + nameWithExt[0] + ".jpg";
            String path = file.getParent();
            File newFile = new File(path + "/" + newName);
            ImageIO.write(image, "jpeg", newFile);
        } catch (Exception e) {
            System.out.println("err" + e);
        }
        return isSuccess;
    }

    public static BufferedImage transformImage(BufferedImage origImage) {

        for (int x = 0; x < origImage.getWidth(); ++x)
            for (int y = 0; y < origImage.getHeight(); ++y) {
                int rgb = origImage.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);

                // Normalize and gamma correct:
                double rr = Math.pow(r / 255.0, 2.2);
                double gg = Math.pow(g / 255.0, 2.2);
                double bb = Math.pow(b / 255.0, 2.2);

                // Calculate luminance:
                float lum = (float) (0.2126 * rr + 0.7152 * gg + 0.0722 * bb);

                // Gamma compand and rescale to byte range:
                int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
                origImage.setRGB(x, y, gray);
            }

        return origImage;
    }

//    public static BufferedImage transformImageDen(BufferedImage origImage) {
//
//        final int MIN_GRAY = 0;
//        final int MAX_GRAY = 255;
//
//        int gray = 0;
//
//        for (int x = 0; x < origImage.getWidth(); ++x)
//            for (int y = 0; y < origImage.getHeight(); ++y) {
//
//                int r = (rgb >> 16) & 0xFF;
//                int g = (rgb >> 8) & 0xFF;
//                int b = (rgb & 0xFF);
//
//                if ( b == 0 || g ==0) {
//                    gray = MIN_GRAY;
//                } else if ( (b == 255) && (g>1) && (g<255) ) {
//                    gray = (g+1)/4 -1;
//                } else if (  (b>0) && (b<255) && (g ==255) ) {
//                    gray = (MAX_GRAY-g)/4+63;
//                } else if (r==0 && g==0) {
//                    gray = 127;
//                } else if ((r>0) && (r<255) && (g==255)) {
//                    gray = (r+1)/4+127;
//                } else if ( (r==255) && (g <=255) && (b!=255)) {
//                    gray = (MAX_GRAY-g)/4 +191;
//                } else if ((r==255) && (g==0)) {
//                    gray =MAX_GRAY;
//                } else if ( (r==255)&&(b==255)&&(g==255) ){
//                    gray = 255;
//                }
//                Color greyColor = new Color(gray,gray,gray);
//                rgb = greyColor.getRGB();
//
//                origImage.setRGB(x, y, rgb );
//            }
//
//        return origImage;
//    }


//
    public static BufferedImage transformImageMy(BufferedImage origImage, boolean isBlackToWhite) {

        int gray = 0;

        for (int x = 0; x < origImage.getWidth(); ++x)
            for (int y = 0; y < origImage.getHeight(); ++y) {

                int rgb = origImage.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);

                if ((r==255)&&(b==255)&&(g==255)) {
                    Color color = new Color(255,255,255);
                    origImage.setRGB(x,y,color.getRGB());
                } else if ((r==0)&&(b==0)&&(g==0)) {
                    Color color = new Color(0,0,0);
                    origImage.setRGB(x,y,color.getRGB());
                } else {
                    MyColor color = new MyColor(rgb, isBlackToWhite);
                    origImage.setRGB(x, y, color.finalColor.getRGB() );
                }


            }

        return origImage;
    }





}
