package util;

import compression.LZW;
import path.FilePath;

import java.io.File;
import java.nio.file.Paths;

public class MeasureSize {
    public static void measure(){
        //ORIGINAL IMAGE SIZE
        File imageFile = new File(String.valueOf(Paths.get(FilePath.SLIKA_POCETNA.getPath())));
        double originalImageSize = imageFile.length()/1024.0;
        System.out.println("Original image size: " + originalImageSize + "kB");

        //LZW SIZE
        double compressedSize = (double) (LZW.getCompressed_length() * 4) /1024;
        System.out.println("Compressed size: " + compressedSize + "kB");

        //RATIO
        double ratio = 100 - (compressedSize/originalImageSize) * 100;
        System.out.println("Ratio: " + ratio + "%");

    }

}
