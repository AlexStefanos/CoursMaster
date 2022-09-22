package ubs.info.as.tp01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Client {
	Socket socket;
	FileInputStream fI;
	FileOutputStream fO;
	
	public Client() throws IOException {
		socket = new Socket("localhost", 2222);
	}
	
	public void run(SocketAddress socketAddress) throws IOException {
		socket.connect(socketAddress);
		System.out.println(socket.isConnected());
	}
	
	public void close() throws IOException {
		socket.close();
		System.out.println(socket.isClosed());
	}
	
	public static void main(String[] args) throws IOException {
		Client client = new Client();
		SocketAddress socketAddress = new InetSocketAddress("localhost", 2222);
		
		client.run(socketAddress);
		client.close();
	}
}
