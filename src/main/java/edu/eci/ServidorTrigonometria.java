package edu.eci;

import java.net.*;
import java.io.*;

public class ServidorTrigonometria {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(35000);
			//serverSocket.close();
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
			System.exit(1);
		}
		
		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
		
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String inputLine, outputLine;
		String operacion = "cos";
		while ((inputLine = in.readLine()) != null) {
			System.out.println("Mensaje: " + inputLine);
			double rta = 0.0;
			try {
				double numero = Double.parseDouble(inputLine);
				numero = (numero*Math.PI)/180;
				if(operacion.equals("cos")) {
					rta = Math.cos(numero);
				}				
				else if(operacion.equals("sin")) {
					rta = Math.sin(numero);
				}
				else if(operacion.equals("tan")) {
					rta = Math.tan(numero);
				}
				outputLine = "Respuesta: " + rta;
			} catch (NumberFormatException e) {
				if(inputLine.contains("fun:")) operacion = inputLine.substring(inputLine.indexOf(":")+1);	
				outputLine = "Respuesta: " + inputLine;
				if (outputLine.equals("Respuesta: Bye.")) break;
			}
			out.println(outputLine);
		}
		
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}
}