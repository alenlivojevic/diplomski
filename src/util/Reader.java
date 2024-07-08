package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reader cita određeni broj znakova (buffer) iz datoteke i vraća ih u obliku Stringa.
 * Reader bi trebao znati gdje je stao prilikom prethodnog citanja i svakim
 * pozivom funkcije readFromFile() nastaviti odakle je stao.
 */

public class Reader {
    String filePath;
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader br;

    public Reader(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        br = new BufferedReader(new FileReader(this.filePath));
    }


    public String readFromFile(int bufferSize) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[bufferSize];
        int bytesRead = br.read(buffer);

        if (bytesRead == -1) {
            return String.valueOf(new char[0]); // Vrati prazan niz ako nema više podataka za čitanje.
        }
        stringBuilder.append(buffer, 0, bytesRead);
        return stringBuilder.toString();
    }

    public void close() throws IOException {
        br.close();
    }

}
