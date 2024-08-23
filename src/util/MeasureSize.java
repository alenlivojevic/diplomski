package util;

import compression.LZW;
import path.FilePath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MeasureSize {
    public static void measure(){
        //ORIGINAL IMAGE SIZE
        double originalImageSize = (double) (128 * 128 * 3) / 1024;
        System.out.println("Uncompressed size: " + originalImageSize + "kB");

        //LZW SIZE  
        double compressedSize = (double) (LZW.getCompressed_length() * 4) /1024;
        System.out.println("Compressed size: " + compressedSize + "kB");

        //RATIO
        double ratio = 100 - (compressedSize/originalImageSize) * 100;
        System.out.println("Ratio: " + ratio + "%");

    }

}
