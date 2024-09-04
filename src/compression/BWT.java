package compression;

import path.FilePath;
import util.Reader;
import util.Writer;

import java.io.IOException;
import java.util.Arrays;


public class BWT {
    private static final int bufferSize = 1000;

    /**
     * Citanje stringa iz datoteke i primjena kompresije
     */
    public static void compress() throws IOException {
        Reader reader = new Reader(FilePath.BASE64.getPath());
        Writer writer = new Writer(FilePath.BWTEncoded.getPath());
        String buffer;
        String compressedString;
        while (!(buffer = reader.readFromFile(bufferSize)).isEmpty()) {
            compressedString = doBWTcompression(buffer);
            writer.write(compressedString);
        }
        reader.close();
        writer.close();
    }

    public static void decompress() throws IOException {
        Reader reader = new Reader(FilePath.RLEDecoded.getPath());
        Reader original_reader = new Reader(FilePath.BASE64.getPath());
        Writer writer = new Writer(FilePath.BWTDecoded.getPath());
        String buffer;
        String decompressedString;
        while (!(buffer = reader.readFromFile(bufferSize)).isEmpty()) {
            String original_string = original_reader.readFromFile(bufferSize);
            decompressedString = doBWTdecompression(buffer, original_string);
            writer.write(decompressedString);
        }
        reader.close();
        writer.close();
    }

    public static String doBWTcompression(String input){
        int length = input.length();
        String[] rotations = new String[length];

        // Generiranje svih rotacija izvornog teksta
        for (int i = 0; i < length; i++) {
            rotations[i] = input.substring(i) + input.substring(0, i);
        }

        // Sortiranje rotacija leksikografski
        Arrays.sort(rotations);

        StringBuilder sb = new StringBuilder();

        // Izgradnja komprimiranog teksta iz posljednjeg stupca sortiranih rotacija
        for (String rotation : rotations) {
            sb.append(rotation.charAt(length - 1));
        }

        return sb.toString();
    }

    public static String doBWTdecompression(String input, String original_string){
        int length = input.length();

        String[] rotations = new String[length];
        Arrays.fill(rotations, "");

        // Punjenje matrice rotacija
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                rotations[j] = input.charAt(j) + rotations[j];
            }
            Arrays.sort(rotations);
        }

        // Pronalazak izvornog teksta
        for (String row : rotations) {
            if (row.equals(original_string)) {
                return row;
            }
        }

        // Ako izvorni string nije pronađen, vraća se prazna vrijednost (ne bi se trebalo dogoditi)
        return "";
    }
}
