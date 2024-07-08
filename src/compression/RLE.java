package compression;

import path.FilePath;
import util.Reader;
import util.Writer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RLE {
    private static final int bufferSize = 500;
    //static char[] bufferArray = new char[bufferSize];
    public static void compress() throws IOException {
        Reader reader = new Reader(FilePath.BWTEncoded.getPath());
        // Reader reader = new Reader(FilePath.BASE64.getPath());
        Writer writer = new Writer(FilePath.RLEEncoded.getPath());
        String buffer;
        String compressedString;
        while (!(buffer = reader.readFromFile(bufferSize)).isEmpty()) {
            compressedString = doRLEcompression(buffer);
            writer.write(compressedString);
        }
        reader.close();
        writer.close();
    }


    public static void decompress() throws IOException {
        //TODO osiguraj da idući znak nakon buffera nije znamenka ili *
        Reader reader = new Reader(FilePath.RLEEncoded.getPath());
        Writer writer = new Writer(FilePath.RLEDecoded.getPath());
        String buffer;
        String decompressedString;
        while (!(buffer = reader.readFromFile(bufferSize)).isEmpty()) {
            char lastChar = buffer.charAt(buffer.length() - 1);
            while(Character.isDigit(lastChar) || lastChar == '*'){
                String addedChar = reader.readFromFile(1);
                if(addedChar.isEmpty()) break;
                buffer += addedChar;
                lastChar = buffer.charAt(buffer.length() - 1);
            }
            //System.out.println(lastChar);
            decompressedString = doRLEdecompression(buffer);
            writer.write(decompressedString);
        }
        reader.close();
        writer.close();
    }

    private static String doRLEcompression(String buffer) {
        StringBuilder compressed = new StringBuilder();
        int length = buffer.length();
        for (int i = 0; i < length; i++) {
            char currentChar = buffer.charAt(i);
            if (Character.isDigit(currentChar)) {
                // If the current character is a digit treat it as part of the input
                compressed.append("*");
                compressed.append(currentChar);
                compressed.append("*");
            } else {
                int count = 1;
                // Count consecutive occurrences of the same character
                while (i + 1 < length && buffer.charAt(i + 1) == currentChar) {
                    count++;
                    i++;
                }
                //compressed.append(currentChar).append(count);
                compressed.append(count).append(currentChar);
            }
        }
        return compressed.toString();
    }

    private static String doRLEdecompression(String buffer) {
        StringBuilder decompressed = new StringBuilder();
        int length = buffer.length();
        int i = 0;
        while (i < length) {
            if(buffer.charAt(i) == '*'){
                i++;
                decompressed.append(buffer.charAt(i));
                i += 2;
                continue;
            }

            StringBuilder countStr = new StringBuilder();
            // Extract the count (which may be more than one digit)
            while (i < length && Character.isDigit(buffer.charAt(i))) {
                countStr.append(buffer.charAt(i));
                i++;
            }
            int count = Integer.parseInt(countStr.toString());

            char currentChar = buffer.charAt(i);
            // Append the character 'count' times to the decompressed string
            for (int j = 0; j < count; j++) {
                decompressed.append(currentChar);
            }
            i++; // Move to the next character
        }
        return decompressed.toString();
    }
}
