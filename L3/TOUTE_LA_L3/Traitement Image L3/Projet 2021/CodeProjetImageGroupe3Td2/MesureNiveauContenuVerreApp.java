/* MesureNiveauContenuVerreApp.java
 * Certains des commentaires de notre projet proviennent de la JavaDoc et du cours Programmation avancée et application / Jean-Guy Mailly
 * 
 * Groupe 3, TD 2 : DUBOUX Élie, NAMOLARU Leonard, ANDRIAMIHAJA Rhidine 
 * Projet 2020 - 2021 : Mesure du niveau du contenu d’un verre • UE Traitement des Images Numériques
 * Licence Informatique et Applications - UFR Maths-Info - Université de Paris
 */

import org.opencv.core.Core;

import javafx.application.Application;  // Le point d'entrée des applications JavaFX est la classe Application.

public class MesureNiveauContenuVerreApp {
    
    /**
     * Lancement de l'application.
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
    	
    	try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // OpenCV
	    	
	    	if (args.length == 0) {
	        	// Le point d'entrée des applications JavaFX est la classe Application.
	            Application.launch(InterfaceGraphique.class, args); // Lancement de l'application. Cette méthode est généralement appelée à partir de la méthode main().
	        
	            /* La méthode launch() ne revient pas tant que l'application n'est pas fermée, que ce soit via un appel à Platform.exit 
	             * ou que toutes les fenêtres de l'application ont été fermées.
	             */
    	}

    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}