package edu.eci;

import java.net.*;
import java.io.*;

public class HttpServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            System.out.println("Listo para recibir ...");
            while (true) {
            	clientSocket = serverSocket.accept();
            	
            	respuestaSolicitud(clientSocket, in, out);
            }            
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
    
    public static void respuestaSolicitud(Socket clientSocket, BufferedReader in, PrintWriter out) throws IOException {
    	out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        //Muestra la petición del cliente.
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            if (!in.ready()) {
                break;
            }   
        }
        
        outputLine = "<!DOCTYPE html>" + "<html>" + "<head>" + "<meta charset=\"UTF-8\">" + "<title>Title of the document</title>\n" + "</head>" + "<body>" + "My Web Site" + "</body>" + "</html>" + inputLine;
        out.println(outputLine);
    }
}
