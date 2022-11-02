package ubs.info.as.tp01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class Client {
	Socket socket;
	FileInputStream fI;
	FileOutputStream fO;
	
	public Client() throws IOException {
		socket = new Socket("localhost", 5555);
		System.out.println(socket.isConnected());
	}
	
	public void close() throws IOException {
		socket.close();
		System.out.println(socket.isClosed());
	}
	
	public void writeData() throws IOException {
		FileInputStream inputStream = new FileInputStream("binData");
		FileOutputStream outputStream = new FileOutputStream("binData");
		
		outputStream.write(inputStream.readAllBytes());
		inputStream.close();
		outputStream.close();
	}
	
	public static void main(String[] args) throws IOException {
		Client client = new Client();
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;

		client.writeData();
		System.out.println(client.socket.getReceiveBufferSize());
		while(elapsedTime < 2*60) {
			elapsedTime = (new Date()).getTime() - startTime;
		}
		client.close();
	}
}
