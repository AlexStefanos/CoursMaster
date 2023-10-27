package ubs.info.as;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import fr.ubs.io.MailFile;

/**
 * TP02 Systeme Distribue : TCP POP3 Server
 * @author Alexandre Stefanos
 */
public class PopServer {
	/**
	 * Methode de gestion de message d'erreur
	 */
	private static void usage() {
		System.out.println("Usage : Write a port and a name for the file that will be created. Like this : java -jar ./TP02.jar 1234 example.txt");
		System.exit(-1);
	}

	/**
	 * Démarre le serveur POP3
	 * @param port : port du serveur
	 * @param fileName : nom du fichier 
	 * @throws IOException
	 */
	public static void run(int port, String fileName) {
		int validOps;
		SelectionKey selectionKey;
		Set<SelectionKey> selectedKeys;
		Iterator<SelectionKey> iter;
		String output;
		try(Selector selector = Selector.open(); ServerSocketChannel serverSocket = ServerSocketChannel.open();) {
			SocketChannel socketChannel;
			serverSocket.bind(new InetSocketAddress(port));
			serverSocket.configureBlocking(false);
			validOps = serverSocket.validOps();
			selectionKey = serverSocket.register(selector, validOps, null);
			selectedKeys = selector.selectedKeys();
			iter = selectedKeys.iterator();
			while(iter.hasNext()) {
				selectionKey = (SelectionKey)iter.next();
				if(selectionKey.isAcceptable()) {
					SocketChannel client = serverSocket.accept();
					client.configureBlocking(false);
					client.register(selector, SelectionKey.OP_READ);
				}
				else if(selectionKey.isReadable()) {
					SocketChannel client = (SocketChannel)selectionKey.channel();
					ByteBuffer buffer = ByteBuffer.allocate(256);
					client.read(buffer);
					output = new String(buffer.array()).trim();
					if(output.equals(fileName)) {
						client.close();
					}
					socketChannel = SocketChannel.open();
					buffer.reset();
					MailFile mail = new MailFile(new File(fileName));
					String dataMail = mail.getFrom();
					switch(dataMail) {
						case "AUTHQUIT":
							buffer.get("+OK POP3 Server signing off".getBytes());
							socketChannel.write(buffer);
							break;
						case "STAT":
							buffer.get("+OK".getBytes());
							socketChannel.write(buffer);
							break;
						case "LIST":
							if(dataMail.equals(null)) {
								buffer.get("-ERR no such message".getBytes());
								socketChannel.write(buffer);
							} else {
								buffer.get("+OK scan listing follows".getBytes());
								socketChannel.write(buffer);
							}
							break;
						case "RETR":
							buffer.get("+OK message follows".getBytes());
							socketChannel.write(buffer);
							break;
						case "DELE":
							dataMail = null;
							buffer.get("+OK message deleted".getBytes());
							socketChannel.write(buffer);
							break;
						case "NOOP":
							if(dataMail.contains("TRANSACTION")) {
								buffer.get("+OK".getBytes());
								socketChannel.write(buffer);
							}
							else {
								buffer.get("+NOOP".getBytes());
								socketChannel.write(buffer);
							}
							break;
						case "RSET":
							if(dataMail.contains("TRANSACTION")) {
								if(dataMail.contains("marked")) {
									buffer.get("+OK".getBytes());
									socketChannel.write(buffer);
								}
							}
							break;
						case "UPDQUIT":
							if(dataMail.contains("TRANSACTION")) {
								dataMail = null;
								buffer.get("+OK".getBytes());
								socketChannel.write(buffer);
							}
							else {
								buffer.get("+NOOP".getBytes());
								socketChannel.write(buffer);
							}
							break;
						case "TOP":
							if(dataMail.contains("TRANSACTION")) {
								buffer.get(dataMail.split("\n", 1).toString().getBytes());
								buffer.get("+OK top of message follows".getBytes());
								socketChannel.write(buffer);
							}
							else {
								buffer.get("+ERR no such message".getBytes());
								socketChannel.write(buffer);
							}
							break;
					}
					socketChannel.close();
				}
				iter.remove();
			}
		} catch(IOException e) {
			System.err.println(e.getMessage());
			usage();
		}
	}
	
	/**
	 * Main de la classe PopServeur
	 * @param args
	 */
	public static void main(String[] args) {
		if((args.length != 2) || (args[0].equals("-h")) || (args[0].equals("--help"))) {
			usage();
			System.exit(-1);
		}
		else {
			for(int i = 0; i < (args.length - 1); i++)
				System.out.println(args[i]);
			int port = Integer.parseInt(args[0]);
			String fileName = args[1];
			System.out.println("Opening POP3 server");
			while(true)
				PopServer.run(port, fileName);
		}
	}
}