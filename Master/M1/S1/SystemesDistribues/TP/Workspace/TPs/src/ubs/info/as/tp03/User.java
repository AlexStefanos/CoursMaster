package ubs.info.as.tp03;

import java.util.Scanner;

/**
 * TP03 Systeme Distribue : Gossiping
 * @author Alexandre Stefanos
 * 
 * Je n'ai pas compris comment mettre en place les connexions, j'ai tout de même fait la structure du programme que j'ai imaginé.
 *
 */
public class User {
    private String nom;
    private String ip;
    private String nextMsgToSend;
    private String lastMsgReceived;
    private int portUDP;
    private int portTCP;
    
    /**
     * Constructeur de la classe User
     * @param nom : Nom de l'utilisateur
     * @param ip : ip de l'utilisateur
     * @param portUDP : port UDP de l'utilisateur
     * @param portTCP : port TCP de l'utilisateur
     */
    public User(String nom, String ip, int portUDP, int portTCP) {
        this.nom = nom;
        this.ip = ip;
        this.portUDP = portUDP;
        this.portTCP = portTCP;
        this.nextMsgToSend = new String();
        this.lastMsgReceived = new String();
    }

    /**
     * Méthode permettant de démarrer le gossiping avec receivUser
     * @param receivUser : Utilisateur avec qui le gossiping est réalisé
     */
    public void startGossiping(User receivUser) {
    	if(receivUser.portUDP != 0) {
    		System.out.println("Démarrage du gossiping UDP de l'utilisateur : " + receivUser.nom + ", ip : " + receivUser.ip);
    	}
    	else if(receivUser.portTCP != 0) {
    		System.out.println("Démarrage du gossiping TCP de l'utilisateur : " + receivUser.nom + ", ip : " + receivUser.ip);
    	}
    	else {
    		System.out.println("Erreur démarrage gossiping");
    	}
    }

    /**
     * Méthode permettant d'arrêter le gossiping avec receivUser
     * @param receivUser : Utilisateur avec qui le gossiping est réalisé
     */
    public void stopGossiping(User receivUser) {
    	if(receivUser.portUDP != 0) {
    		System.out.println("Arrêt du gossiping UDP de l'utilisateur : " + receivUser.nom + ", ip : " + receivUser.ip);
    	}
    	else if(receivUser.portTCP != 0) {
    		System.out.println("Arrêt du gossiping TCP de l'utilisateur : " + receivUser.nom + ", ip : " + receivUser.ip);
    	}
    	else {
    		System.out.println("Erreur arrêt gossiping");
    	}
    }

    /**
     * Méthode permettant d'envoyer un message à receivUser
     * @param receivUser : Utilisateur avec qui le gossiping est réalisé
     */
    public void offerMessage(User receivUser) {
    	Scanner sc = new Scanner(System.in);
    	nextMsgToSend = sc.next();
        //Envoi de nextMsgToSend à receivUser
    	sc.close();
    }

    /**
     * Méthode permettant de demander le message de receivUser
     * @param receivUser : Utilisateur avec qui le gossiping est réalisé
     */
    public void requestMessage(User receivUser) {
    	//Appel de nextMsgToSend de receivUser
    }

    /**
     * Méthode permettant de supprimer le message de receivUser
     * @param receivUser : Utilisateur avec qui le gossiping est réalisé
     */
    public void deleteMessage(User receivUser) {
    	nextMsgToSend = "";
    	//Suppression de lastMsgReceived de receivUser
    	
    }

    /**
     * Méthode permettant de répondre au message de receivUser
     * @param receivUser : Utilisateur avec qui le gossiping est réalisé
     */
    public void answerMessage(User receivUser) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println(lastMsgReceived);
    	nextMsgToSend = sc.next();
    	//Envoi de nextMsgToSend à receivUser
    	sc.close();
    }

    /**
     * Main de la classe User
     * @param args : arguments du programme
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String username = new String();
        String ip = new String();
        int portUDP = 0, portTCP = 0, tmp2 = 2;
        User user = new User("localhost", "localhost", 0, 0);
        
        while(true) {
        	System.out.print("Veuillez insérer le nom de l'utilisateur : ");
        	username = sc.next();
        	System.out.print("Veuillez insérer l'ip de l'utilisateur : ");
        	ip = sc.next();
        	System.out.print("Voulez-vous un gossiping UDP (0) ou TCP (1) ? ");
        	int tmp = 5;
        	while((tmp != 0) && (tmp != 1)) {
        		tmp = sc.nextInt();
        		if((tmp != 0) && (tmp != 1)) {
        			System.out.print("Voulez-vous un gossiping UDP (0) ou TCP (1) ? ");
        		}
        	}
        	if(tmp == 0) {
        		System.out.print("Veuillez insérer le port TCP de l'utilisateur : ");
        		portUDP = sc.nextInt();
        		user.portUDP = portUDP;
        	}
        	if(tmp == 1) {
        		System.out.print("Veuillez insérer le port UDP de l'utilisateur : ");
        		portTCP = sc.nextInt();
        		user.portTCP = portTCP;
        	}
        	User receivUser = new User(username, ip, portUDP, portTCP);
        	user.startGossiping(receivUser);
        	System.out.println("Choix de la prochain action :");
        	System.out.println("offerMessage (1)");
        	System.out.println("requestMessage (2)");
        	System.out.println("deleteMessage (3)");
        	System.out.println("answerMessage (4)");
        	while((tmp2 != 1) && (tmp2 != 2) && (tmp != 3) && (tmp != 4)) {
            	tmp2 = sc.nextInt();
	        	if(tmp == 1)
	        		user.offerMessage(receivUser);
	        	else if(tmp == 2)
	        		user.requestMessage(receivUser);
	        	else if(tmp == 3)
	        		user.deleteMessage(receivUser);
	        	else if(tmp == 4)
	        		user.answerMessage(receivUser);
	        	else
	        		System.out.println("Veuillez choisir une des actions proposées");
        	}
        	System.out.print("Si vous voulez arrêter le gossiping, tapez 0 ");
        	tmp2 = sc.nextInt();
        	if(tmp2 == 0) {
        		user.stopGossiping(receivUser);
        		sc.close();
        		System.exit(0);
        	}
        }
    }
}
