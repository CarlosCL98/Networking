package edu.eci;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class URLReader {

    public static void main(String[] args) throws Exception {

        System.out.println("Ingrese la URL: ");
        Scanner sc = new Scanner(System.in);
        String urlScanner = sc.nextLine();
        URL url = new URL(urlScanner);

        String ruta = "src/main/resources/resultado.html";
        File file = new File(ruta);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                bw.write(inputLine);
            }
            bw.close();
            reader.close();
        } catch (IOException x) {
            System.err.println(x);
        }

    }

}
