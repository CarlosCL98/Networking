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
		byte[] sendBuf = new byte[256];
		String received = "";
		try {
			DatagramSocket socket = new DatagramSocket();
			byte[] buf = new byte[256];
			InetAddress address = InetAddress.getByName("127.0.0.1");
			DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 45000);
			socket.setSoTimeout(2000);
			while(true){				
				socket.send(packet);				
				packet = new DatagramPacket(buf, buf.length);		
				//System.out.println("1");
				try {					
					socket.receive(packet);
					//System.out.println("LA address: " + packet.getAddress());
				} catch (SocketTimeoutException ex) {
					while(packet.getAddress() == null) {
						//System.out.println("LA address: " + packet.getAddress());	
						System.out.println("LA MISMA: " + received);
						Thread.sleep(2000);
					}
					socket.send(packet);				
					packet = new DatagramPacket(buf, buf.length);	
				}
				//System.out.println(socket.getSoTimeout());
				received = new String(packet.getData(), 0, packet.getLength());
				System.out.println("Date: " + received);
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}catch (SocketException ex) {
			Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnknownHostException ex) {
			Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
		} 
	}
}