package compression;

import path.FilePath;
import util.Reader;
import util.Writer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LZW {
    private static final int bufferSize = 500;
    private static int dictionarySize = 256;
    static Map<String, Integer> dictionary = new HashMap<>();
    private static int compressed_length;
    private static int uncompressed_length;
    static Map<Integer, String> reverseDictionary = new HashMap<>();
    public static void compress() throws IOException {
        for (int i = 0; i < dictionarySize; i++) {
            dictionary.put("" + (char)i, i);
        }
        compressed_length = 0;

        Reader reader = new Reader(FilePath.RLEEncoded.getPath());
        Writer writer = new Writer(FilePath.LZWEncoded.getPath());
        String buffer;
        String compressedString;
        while (!(buffer = reader.readFromFile(bufferSize)).isEmpty()) {
            compressedString = doLZWcompression(buffer);
            writer.write(compressedString);
        }
        reader.close();
        writer.close();
    }


    public static void decompress() throws IOException {
        File file = new File(FilePath.LZWEncoded.getPath());
        Scanner scanner = new Scanner(file);
        Writer writer = new Writer(FilePath.LZWDecoded.getPath());
        String decompressedString;
        while (scanner.hasNext()) {
            String buffer = scanner.next();
            decompressedString = doLZWdecompression(buffer);
            writer.write(decompressedString);
        }
        scanner.close();
        writer.close();
    }

    private static String doLZWcompression(String buffer) {
        uncompressed_length = buffer.length();
        String current = "";
        String compressed = "";

        for (char c : buffer.toCharArray()) {
            String next = current + c;
            if (dictionary.containsKey(next)) {
                current = next;
            } else {
                compressed += dictionary.get(current) + " ";
                compressed_length++;
                dictionary.put(next, dictionarySize++);
                current = "" + c;
            }
        }
        if (!current.equals("")) {
            compressed += dictionary.get(current) + " ";
            compressed_length++;
        }

        return compressed;
    }
    public static void reverseDictionary(){

        for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
            reverseDictionary.put(entry.getValue(), entry.getKey());
        }
    }
    private static String doLZWdecompression(String buffer){

        String[] compressedArray = buffer.split(" ");
        StringBuilder decompressed = new StringBuilder();
/*
        Map<Integer, String> reverseDictionary = new HashMap<>();
        for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
            reverseDictionary.put(entry.getValue(), entry.getKey());
        }
*/
        String current = reverseDictionary.get(Integer.parseInt(compressedArray[0]));
        decompressed.append(current);

        for (int i = 1; i < compressedArray.length; i++){
            int code = Integer.parseInt(compressedArray[i]);
            String entry;
            if (reverseDictionary.containsKey(code)){
                entry = reverseDictionary.get(code);
            } else if (code == dictionarySize){
                entry = current + current.charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid compressed code: " + code);
            }
            decompressed.append(entry);
            reverseDictionary.put(dictionarySize++, current + entry.charAt(0));
            current = entry;
        }

        return decompressed.toString();
    }

    public static int getCompressed_length() {
        return compressed_length;
    }
}
