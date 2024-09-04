package util;

import compression.LZW;

public class MeasureSize {
    public static void measure(){
        //ORIGINAL IMAGE SIZE
        double originalImageSize = (double) (512 * 512 * 3) / 1024;
        System.out.println("Uncompressed size: " + originalImageSize + "kB");

        //LZW SIZE  
        double compressedSize = (double) (LZW.getCompressed_length() * 4) /1024;
        System.out.println("Compressed size: " + compressedSize + "kB");

        //RATIO
        double ratio = 100 - (compressedSize/originalImageSize) * 100;
        System.out.println("Ratio: " + ratio + "%");

    }

}
