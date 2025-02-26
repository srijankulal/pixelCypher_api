/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.srijan.pixelcypher.pixelCypher.ImageSteganography;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author srijan
 */
public class ImageSteganography {
    public static void cypher(BufferedImage img,String text,String outputPath)throws IOException{
        text += "\0";
        byte[] textBytes = text.getBytes();
        int bitIndex = 0, byteIndex = 0;

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (byteIndex >= textBytes.length) break;

                int pixel = img.getRGB(x, y);
                int newPixel = modifyPixel(pixel, textBytes[byteIndex], bitIndex);
                img.setRGB(x, y, newPixel);

                bitIndex += 1;
                if (bitIndex == 8) {
                    bitIndex = 0;
                    byteIndex += 1;
                }
            }
        }

        ImageIO.write(img, "png", new File(outputPath));
    }
    private static int modifyPixel(int pixel, byte character, int bitIndex) {
        int mask = 1 << (7 - bitIndex);
        int bitValue = (character & mask) >> (7 - bitIndex);

        // Modify the Least Significant Bit of the Blue channel
        return (pixel & 0xFFFFFFFE) | bitValue;
    }
    public static String decypher(BufferedImage image) {
        StringBuilder text = new StringBuilder();
        int bitIndex = 0, byteValue = 0;
    
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                int bit = pixel & 1; // Extract the LSB of the Blue channel
    
                byteValue = (byteValue << 1) | bit;
                bitIndex += 1;
    
                if (bitIndex == 8) {
                    if (byteValue == 0) return text.toString(); // End marker detected
                    text.append((char) byteValue);
                    bitIndex = 0;
                    byteValue = 0;
                }
            }
        }
        return text.toString();
    }
    public static void main(String[] args) throws IOException { 
        BufferedImage image = ImageIO.read(new File("C:/Codes/Projects/pixelCypher/src/main/java/com/srijan/pixelcypher/pixelCypher/ImageSteganography/input.png"));
        // cypher(image, "Hello, World!", "output.png");
        // System.out.println("Text embedded in image successfully!");
        String ans=decypher(image);
        System.out.println(ans);
    }
    
  
    
}
//C:/Codes/Projects/pixelCypher/src/main/java/com/srijan/pixelcypher/pixelCypher/ImageSteganography