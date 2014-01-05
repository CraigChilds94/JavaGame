package network;

public class Client {
	
	private String ip;
	private int port;
	
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public Client() {
		ip = "127.0.0.1";
		port = 1337;
	}
	
	public void onConnect() {
		
	}
	
	public void onDisconnect() {
		
	}
}
