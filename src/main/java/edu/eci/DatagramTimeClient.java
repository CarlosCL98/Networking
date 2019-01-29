//no se recupera despeus de vovler a encender el servidor, sigue mostrando al misma 
package edu.eci;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramTimeClient {

	public static void main(String[] args) {
		String received = "";
		while (true) {
			try {
				DatagramSocket socket = new DatagramSocket();
				byte[] buf = new byte[256];
				InetAddress address = InetAddress.getByName("127.0.0.1");
				DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 45000);
				socket.send(packet);				
				packet = new DatagramPacket(buf, buf.length);
				try {
					socket.setSoTimeout(5000);					
					socket.receive(packet);
					received = new String(packet.getData(), 0, packet.getLength());
					System.out.println("Date: " + received);
				} catch (SocketTimeoutException ex) {
					System.out.println("Date-SinServer: " + received);
				}
				Thread.sleep(5000);
			} catch (SocketException ex) {
				Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
			} catch (UnknownHostException ex) {
				Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InterruptedException ex) {
				Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}