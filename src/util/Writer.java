package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {

    PrintWriter pw;


    public Writer(String filePath) throws IOException {
        pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath, false)));
    }

    public void write(String input){
        pw.write(input);
        //System.out.println("Upisujem u datoteku: " + input);
    }

    public void close() throws IOException {
        pw.close();
    }
}
