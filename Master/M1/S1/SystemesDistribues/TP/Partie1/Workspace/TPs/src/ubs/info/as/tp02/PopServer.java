package ubs.info.as.tp02;

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
 *
 */
public class PopServer {
	int validOps, keys;
	ServerSocketChannel serverSocket;
	SocketChannel socketChannel;
	Selector selector;
	SelectionKey selectionKey, selectionKey2;
	@SuppressWarnings("rawtypes")
	Set selectedKeys;
	@SuppressWarnings("rawtypes")
	Iterator iter;
	
	/**
	 * Démarre le serveur POP3
	 * @param port : port du serveur
	 * @param name : nom du fichier créé
	 * @throws IOException
	 */
	public void run(int port, String name) throws IOException {
		selector = Selector.open();
		serverSocket = ServerSocketChannel.open();
		serverSocket.bind(new InetSocketAddress(port));
		serverSocket.configureBlocking(false);
		validOps = serverSocket.validOps();
		selectionKey = serverSocket.register(selector, validOps, null);
		keys = selector.select();
		selectedKeys = selector.selectedKeys();
		iter = selectedKeys.iterator();
		while(iter.hasNext()) {
			selectionKey2 = (SelectionKey)iter.next();
			if(selectionKey2.isAcceptable()) {
				SocketChannel tmpClient = serverSocket.accept();
				tmpClient.configureBlocking(false);
				tmpClient.register(selector, SelectionKey.OP_READ);
			}
			else if(selectionKey2.isReadable()) {
				SocketChannel tmpClient = (SocketChannel)selectionKey2.channel();
				ByteBuffer buffer = ByteBuffer.allocate(256);
				tmpClient.read(buffer);
				String output = new String(buffer.array()).trim();
				if(output.equals(name)) {
					tmpClient.close();
				}
			}
			iter.remove();
		}
		socketChannel = SocketChannel.open();
	}
	
	/**
	 * Main de la classe PopServeur
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < (args.length-1); i++) {
			System.out.println(args[i]);
		}
		if(args.length > 2) {
			System.out.println("Error args");
			System.exit(-1);
		}
		if(args[0].equals("-h") || args[0].equals("--help")) {
			System.out.println("Help : Write a name for the file that will be created");
			System.exit(-1);
		}
		PopServer popServer = new PopServer();
		File file = new File("dataPop3");
		file.mkdir();
		file = new File("dataPop3/sameUserName");
		file.mkdir();
		file = new File("dataPop3/" + args[0]);
		File mailFile = new File("./mail.txt");
		mailFile.createNewFile();
		MailFile mail = new MailFile(mailFile);
		
		if(file.exists()) {
			System.out.println("Le fichier " + file.getName() + " existe déjà");
			file = new File("./dataPop3/sameUserName/" + mail.getFrom());
			System.out.println("Création du fichier " + mail.getFrom() + " : " + file.createNewFile());
			if(file.createNewFile() == false) {
				System.out.println("Le fichier " + mail.getFrom() + " a déjà été créé");
			}
			System.out.println("Ouverture du Serveur POP3");
			while(true) {
				popServer.run(5556, args[0]);
			}
		}
		else {
			System.out.println("Le fichier " + file.getName() + " n'existe pas encore");
			System.out.println("Création du fichier " + file.getName() + " : " + file.createNewFile());
			System.out.println("Ouverture du Serveur POP3");
			while(true) {
				popServer.run(5556, args[0]);
			}
		}
	}
}