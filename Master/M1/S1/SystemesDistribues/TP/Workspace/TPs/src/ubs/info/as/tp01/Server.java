package ubs.info.as.tp01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;

public class Server {
	ServerSocket serverSocket;
	
	public Server() throws IOException {
		serverSocket = new ServerSocket();
	}
	
	public void socketLocalAdress(SocketAddress address) {
		try{
			serverSocket.bind(address);
		} catch(IOException e) {
			System.err.println(e);
		}
	}
	
	public void acceptClientSocket() {
		try {
			serverSocket.accept();
		} catch(IOException e) {
			System.err.println(e);
		}
	}
	
	public void close() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		SocketAddress address = new InetSocketAddress("localhost", 2222);
		
		server.socketLocalAdress(address);
		server.acceptClientSocket();
		server.close();
	}
}
