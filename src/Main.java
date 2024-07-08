import compression.LZW;
import compression.RLE;
import convertImage.ConvertImage;
import compression.BWT;
import util.MeasureSize;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        long startTime;
        long endTime;
        long durationTime;

        long compressionTime ,compressionStart, compressionEnd;
        long decompressionTime, decompressionStart, decompressionEnd;

        try {
            compressionStart = System.currentTimeMillis();
            //CONVERT IMAGE
            System.out.println("Image converting started...");
            startTime = System.currentTimeMillis();
            ConvertImage.encode();
            endTime = System.currentTimeMillis();
            durationTime = endTime - startTime;
            System.out.println("Image converting finished in " + durationTime + "ms");

            //BWT COMPRESSION
            System.out.println("BWT compress started...");
            startTime = System.currentTimeMillis();
            BWT.compress();
            endTime = System.currentTimeMillis();
            durationTime = endTime - startTime;
            System.out.println("BWT compress finished in " + durationTime + "ms");

            //RLE COMPRESSION
            System.out.println("RLE compress started...");
            startTime = System.currentTimeMillis();
            RLE.compress();
            endTime = System.currentTimeMillis();
            durationTime = endTime - startTime;
            System.out.println("RLE compress finished in " + durationTime + "ms");

            //LZW COMPRESSION
            System.out.println("LZW compress started...");
            startTime = System.currentTimeMillis();
            LZW.compress();
            endTime = System.currentTimeMillis();
            durationTime = endTime - startTime;
            System.out.println("LZW compress finished in " + durationTime + "ms");
            compressionEnd = System.currentTimeMillis();
            compressionTime = compressionEnd - compressionStart;
            System.out.println("Compression finished in " + compressionTime + "ms");

            ////////////////////   DECOMPRESSION   /////////////////////////

            //LZW DECOMPRESSION
            decompressionStart = System.currentTimeMillis();
            System.out.println("LZW decompress started...");
            startTime = System.currentTimeMillis();
            LZW.reverseDictionary();
            LZW.decompress();
            endTime = System.currentTimeMillis();
            durationTime = endTime - startTime;
            System.out.println("LZW decompress finished in " + durationTime + "ms");

            //RLE DECOMPRESSION
            System.out.println("RLE decompress started...");
            startTime = System.currentTimeMillis();
            RLE.decompress();
            endTime = System.currentTimeMillis();
            durationTime = endTime - startTime;
            System.out.println("RLE decompress finished in " + durationTime + "ms");

            //BWT DECOMPRESSION
            System.out.println("BWT decompress started...");
            startTime = System.currentTimeMillis();
            BWT.decompress();
            endTime = System.currentTimeMillis();
            durationTime = endTime - startTime;
            System.out.println("BWT decompress finished in " + durationTime + "ms");

            //IMAGE CONVERTING
            System.out.println("Image converting started...");
            startTime = System.currentTimeMillis();
            ConvertImage.decode();
            endTime = System.currentTimeMillis();
            durationTime = endTime - startTime;
            System.out.println("Image converting finished in " + durationTime + "ms");
            decompressionEnd = System.currentTimeMillis();
            decompressionTime = decompressionEnd - decompressionStart;
            System.out.println("Decompression finished in " + decompressionTime + "ms");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MeasureSize.measure();
    }
}