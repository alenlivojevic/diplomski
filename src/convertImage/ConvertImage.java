package convertImage;

import path.FilePath;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ConvertImage {

    public static void encode() throws IOException {
        String imageString;
        Path imagePath = Paths.get(FilePath.SLIKA_POCETNA.getPath());
        byte[] imageBytes = Files.readAllBytes(imagePath);
        imageString = Base64.getEncoder().encodeToString(imageBytes);
        System.out.println("Duljina base64 stringa je " + imageString.length());


        FileWriter writer = new FileWriter(FilePath.BASE64.getPath());
        writer.write(imageString);
        writer.close();
        System.out.println("Tekst je uspješno spremljen u datoteku.");

    }

    public static void decode() throws IOException {
        Path path = Paths.get(FilePath.BWTDecoded.getPath());
        byte[] data = Base64.getDecoder().decode(Files.readAllBytes(path));
        Files.write(Paths.get(FilePath.SLIKA_VRACENA.getPath()), data);
        System.out.println("Image saved");
    }

}
