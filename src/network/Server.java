package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private int port;
	
	public Server(int port) {
		this.port = port;
		create();
	}
	
	public Server() {
		port = 1337;
		create();
	}
	
	private void create() {
		try {
			ServerSocket ss = new ServerSocket(port);
			Socket sock = ss.accept();
			
			if(sock.isConnected()) {
				onClientConnect();
				PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				
				out.println("Hello there!");
				String input;
				while((input = in.readLine()) != null) {
					System.out.println(input);
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void onClientConnect() {
		
	}
	
	public void onClientDisconnect() {
		
	}
}
