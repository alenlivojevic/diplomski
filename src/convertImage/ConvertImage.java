package convertImage;

import path.FilePath;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ConvertImage {

    public static void encode_Base64() throws IOException {
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

    public static void encode_hilbert() throws IOException, InterruptedException {
        String pythonCommand = "C:\\Users\\alenl\\AppData\\Local\\Programs\\Python\\Python312\\python.exe ./python/image2string.py";
        ProcessBuilder processBuilder = new ProcessBuilder(pythonCommand.split(" "));
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = process.waitFor();
        System.out.println("Exited with code: " + exitCode);
    }

    public static void decode_hilbert() throws IOException, InterruptedException {
        String pythonCommand = "C:\\Users\\alenl\\AppData\\Local\\Programs\\Python\\Python312\\python.exe ./python/string2image.py";
        ProcessBuilder processBuilder = new ProcessBuilder(pythonCommand.split(" "));
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = process.waitFor();
        System.out.println("Exited with code: " + exitCode);
    }

    public static void decode_base64() throws IOException {
        Path path = Paths.get(FilePath.BWTDecoded.getPath());
        byte[] data = Base64.getDecoder().decode(Files.readAllBytes(path));
        Files.write(Paths.get(FilePath.SLIKA_VRACENA.getPath()), data);
        System.out.println("Image saved");
    }

}
