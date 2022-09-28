package ubs.info.as.tp02;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.io.IOException;
import java.nio.channels.Selector;

public class popServer {
	String name;
	int port;
	ServerSocketChannel serverSocket;
	
	public popServer(String name, int port) {
		this.name = name;
		this.port = port;
	}
	
	public void run() throws IOException {
		//serverSocket.bind();
		//servSocket.open();
	}
	
	public static void main(String[] args) {
		//Selector close = new Selector();
		try {
			while(!close) {
				
			}
		}catch (IOException e) {
			System.err.print(0);
			System.exit(0);
		}
	}
}