/* InterfaceGraphique.java
 * Certains des commentaires dans ce fichier proviennent de la JavaDoc et du cours Programmation avancée et application / Jean-Guy Mailly
 * 
 * Groupe 3, TD 2 : DUBOUX Élie, NAMOLARU Leonard, ANDRIAMIHAJA Rhidine 
 * Projet 2020 - 2021 : Mesure du niveau du contenu d’un verre • UE Traitement des Images Numériques
 * Licence Informatique et Applications - UFR Maths-Info - Université de Paris
 */

import java.io.File; // Une représentation abstraite des chemins de fichiers et de répertoires.
import java.util.ArrayList; // Implémentation de tableau redimensionnable de l'interface List
import java.util.List; // Une collection ordonnée
import javax.imageio.ImageIO; // Une classe contenant des méthodes statiques pour localiser des ImageReaders et des ImageWriters, et effectuer un encodage et un décodage simples.

import javafx.application.Application; // Le point d'entrée des applications JavaFX est la classe Application.
import javafx.stage.Stage; // Stage est la scene (au sens « physique » du terme), l’endroit ou tout se passe
import javafx.scene.Scene; // Une scene : niveau intermediaire entre la fenetre et les panneaux
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.DirectoryChooser; // Fournit la prise en charge des boîtes de dialogue de sélection de répertoire.

import javafx.event.EventHandler; // EventHandler<T> est une interface parametree par un type T extends Event
import javafx.event.ActionEvent; // ActionEvent : clic sur un bouton, un item de menu, une case a cocher etc.

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Une interface graphique (une application JavaFX) pour le projet : Mesure du niveau du contenu d’un verre.
 */
public class InterfaceGraphique extends Application{
	/**
	 * La liste des photos qui seront incluses dans la galerie de photos.
	 */
    private static List<Image> galerieDePhotos = new ArrayList<>();
    
	/**
	 * La liste des URL des photos qui seront incluses dans la galerie de photos.
	 */
    private static List<String> galerieDePhotosURL = new ArrayList<>();
    
    public static List<String> resultats = new ArrayList<>();
    
    public static void nouvelleFenetrePrincipale(Stage fenetrePrincipale, File repertoireSelectionne, Image image) {
    	
    	try {
    		 // Un panneau avec cinq enfants, qui peuvent etre places en haut, en bas, a gauche, a droite et au centre.
    		BorderPane panneauAvecCinqEnfants = new BorderPane();
    		
    		// javafx.geometry.Insets : Un ensemble de décalages intérieurs pour le 4 côté d'une zone rectangulaire.
    		Insets decalagesInterieursPanneau = new Insets(15, 20, 10, 10); // Insets(double top, double right, double bottom, double left)
    		panneauAvecCinqEnfants.setPadding( decalagesInterieursPanneau );
    		
    		// En haut (top) : les titres de la fenêtre
        	Label nomUE = new Label("UE Traitement des Images Numériques • Projet 2020 - 2021");
        	Label grandTitre = new Label("Mesure du niveau du contenu d’un verre");
        	
        	// javafx.scene.layout.FlowPane : Place ses enfants successivement, en ligne ou en colonne
        	FlowPane titresDeLaFenetre = new FlowPane() ; 
        	titresDeLaFenetre.getChildren().addAll( nomUE , grandTitre ) ;
        	
        	titresDeLaFenetre.setAlignment( Pos.CENTER ) ;
        	
        	Insets marginTitresDeLaFenetre = new Insets(10, 10, 10, 10);
    	    BorderPane.setMargin(titresDeLaFenetre, marginTitresDeLaFenetre);
    	    panneauAvecCinqEnfants.setTop(titresDeLaFenetre);

    	    // A gauche (left)
        	Label afficherRepertoireSelectionne = new Label(repertoireSelectionne.getAbsolutePath());
        	Label titreLesResultats = new Label( "L'historique des résultats");
        	Label afficherLesResultats =  new Label( resultats.size() != 0 ? resultats.toString() : "L'histoire est vide.");   
        	
        	FlowPane resultatsRepertoireSelectionne = new FlowPane(Orientation.VERTICAL); // Pour creer un FlowPane vertical
        	resultatsRepertoireSelectionne.getChildren().addAll( afficherRepertoireSelectionne ,titreLesResultats, afficherLesResultats ) ;
        	
        	resultatsRepertoireSelectionne.setAlignment( Pos.TOP_CENTER ) ;
        	resultatsRepertoireSelectionne.setPadding(new Insets(5, 5, 5, 5));
    	    BorderPane.setMargin(titresDeLaFenetre, new Insets(10, 10, 10, 10));
    	    panneauAvecCinqEnfants.setLeft(resultatsRepertoireSelectionne);
    	    
    	    // Au centre (center)
    	    if (image != null) {
            	ImageView iv2 = new ImageView() ;
            	
        		iv2.setImage( image ) ;
        		iv2.setFitWidth( 400 ) ;
        		iv2.setFitHeight( 500 ) ;
        		iv2.setPreserveRatio( true ) ;
        		
        	    panneauAvecCinqEnfants.setCenter(iv2);
        	    BorderPane.setAlignment(iv2, Pos.TOP_CENTER);
    	    } else {
    	    	Label messageErreur = new Label("Veuillez sélectionner une image dans la fenêtre \"Galerie de photos\" qui s'ouvre devant vous en parallèle avec cette fenêtre");
    	    	messageErreur.setPadding(new Insets(5, 5, 5, 5));
    	    	panneauAvecCinqEnfants.setCenter(messageErreur);
    	    	BorderPane.setAlignment(messageErreur, Pos.TOP_CENTER);
    	    }

    	
    	    // A droite (right)
    	    //Button label = new Button("label");
    	    //label.setPadding(new Insets(5, 5, 5, 5));
    	    //panneauAvecCinqEnfants.setRight(label);
    	    //BorderPane.setMargin(label, new Insets(10, 10, 10, 10));
    	
    	    // En bas (bottom)
        	Label groupe3Td2 = new Label("Groupe 3, TD 2 : DUBOUX Élie, NAMOLARU Leonard, ANDRIAMIHAJA Rhidine | Licence Informatique et Applications - UFR Maths-Info - Université de Paris");
        	
        	// javafx.scene.layout.FlowPane : Place ses enfants successivement, en ligne ou en colonne
        	FlowPane droitsAuteur = new FlowPane() ; 
        	droitsAuteur.getChildren().addAll( groupe3Td2 ) ;
        	titresDeLaFenetre.setAlignment( Pos.CENTER ) ;
        	
        	droitsAuteur.setPadding(new Insets(5, 5, 5, 5));
    	    panneauAvecCinqEnfants.setBottom(droitsAuteur);
    	    BorderPane.setMargin(droitsAuteur, new Insets(10, 10, 10, 10));
    	
    	    Scene scene = new Scene(panneauAvecCinqEnfants, 1200, 700);
    	    scene.getStylesheets().add("Stylesheet.css"); // Associer un fichier CSS
    	    grandTitre.setId("grandTitre"); // Associer un identifiant à un élément de texte
    	    nomUE.setId("nomUE"); // Associer un identifiant à un élément de texte
    	    titreLesResultats.setId("titreLesResultats");
    	    
    	    fenetrePrincipale.setTitle("Mesure du niveau du contenu d’un verre"); // Definit le titre de la fenetre.
    	    fenetrePrincipale.setScene(scene); // Place la scene dans la fenetre
    	    fenetrePrincipale.show(); // Affiche la fenetre
    	} catch (Exception e) {
    		e.printStackTrace();
    	} // catch
    }
    
    /**
     * Le point d'entrée principal pour toutes les applications JavaFX (l'interface graphique).
     * La méthode start est appelée une fois que le système est prêt pour que l'application (l'interface graphique) commence à s'exécuter.
     * 
     * @param fenetrePrincipale l’objet Stage (la scene) correspondant a la fenetre principale. 
     *        Stage est la scene , c’est-a-dire : l’endroit ou tout se passe : la fenetre
     *        (delimitee par son cadre, et equipee de boutons pour la fermer, reduire, etc). 
     *        Dans le cas d’une application « simple », avec une seule fenetre, il 
     *        n’y a qu’un seul objet Stage qui est cree. Si le contenu de la fenetre doit changer, 
     *        ca veut dire qu’il faut remplacer la Scene.
     */
    public void start(Stage fenetrePrincipale) { // Overrides: start(...) in Application
    	// Les titres dans la fenêtre
    	Label nomUE = new Label("UE Traitement des Images Numériques • Projet 2020 - 2021");
    	Label grandTitre = new Label("Mesure du niveau du contenu d’un verre");
    	Label explicationDivisionBaseImages = new Label("Nous avons décidé de diviser notre base d'images en 2 parties: une partie pour la construction du système, une deuxième partie pour les tests. \n"
    													+ "Afin que chaque section comprenne des images qui ont été entrées dans la base de données par les différents groupes, nous avons décidé \n"
    													+ "de faire la division comme suit: Puisque le nom de toutes les images est un nombre, les images avec des nombres pairs seront utilisés pour construire le système. \n"
    													+ "Les autres : pour les tests .Par conséquent, avant de sélectionner le dossier avec les images, veuillez indiquer à quelle partie de la base vous souhaitez accéder. \n");
    	
        // Cree une zone d’affichage de texte + Cree un bouton
        Label label = new Label("Vous n'avez pas encore sélectionné un répertoire"); 
        Button button = new Button("Sélectionnez un répertoire"); 

        try {
        	// Definit le titre de la fenetre.
        	fenetrePrincipale.setTitle("Mesure du niveau du contenu d’un verre"); 
  
            DirectoryChooser selectionnezRepertoire = new DirectoryChooser();
            // Définit la valeur du titre de la boite de dialogue
            selectionnezRepertoire.setTitle("Sélectionnez un répertoire"); 
           
            ToggleGroup group = new ToggleGroup() ;
            RadioButton button1 = new RadioButton ("Base d’apprentissage") ;
            button1.setToggleGroup( group ) ;
            RadioButton button2 = new RadioButton("Base de validation") ;
            button2.setToggleGroup ( group ) ;
            button2.setSelected( true ) ;
            
            
            // Lorsque l’evenement a lieu, la methode handle de EventHandler est appelee.
            EventHandler<ActionEvent> gestionnaireEvenements = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                	
                	// Affiche une boîte de dialogue de sélection de répertoire.
                    File repertoireSelectionne = selectionnezRepertoire.showDialog(fenetrePrincipale); 
                    
                    // (repertoireSelectionne == null) si aucun répertoire n'a été sélectionné
                    if (repertoireSelectionne != null) { 
                        
                        File[] lesFichiersDuRepertoireSelectionne = repertoireSelectionne.listFiles();
                        for (int i = 0 ; i < lesFichiersDuRepertoireSelectionne.length ; i++) {
                        	String nomDuFichier = lesFichiersDuRepertoireSelectionne[i].getName();
                        	
                        	if (lesFichiersDuRepertoireSelectionne[i] != null) 
                        	{
                        		if(!nomDuFichier.contains("resultat")) {
                                    if ( nomDuFichier.toLowerCase().endsWith(".jpeg") || nomDuFichier.toLowerCase().endsWith(".jpg") || nomDuFichier.toLowerCase().endsWith(".png") ) {
                                    	int nomFichierSansTypeFichier = Integer.parseInt( nomDuFichier.split("\\.")[0] );
                                    	
                                    	if ( (button1.isSelected() && nomFichierSansTypeFichier % 2 == 0) || (button2.isSelected() && nomFichierSansTypeFichier % 2 != 0) )
                                    	try {
                                        	galerieDePhotos.add(SwingFXUtils.toFXImage(ImageIO.read(lesFichiersDuRepertoireSelectionne[i]), null));
                                        	galerieDePhotosURL.add(lesFichiersDuRepertoireSelectionne[i].getAbsolutePath());
                                        } catch (Exception exception) {
                                        	label.setText("Un dysfonctionnement s'est produit! \n"
                                        			+ " Veuillez vous assurer que le dossier que vous avez sélectionné contient au moins une image \n"
                                        			+ "et que les noms de toutes les images du dossier sont uniquement composés de chiffres.");
                                        } // catch
                                    } // if
                        		} // if
                        	} // if
                        } // for
                        
                        try {
                        	afficherGalerieDePhotos(fenetrePrincipale, new Stage(), repertoireSelectionne);
                        	nouvelleFenetrePrincipale(fenetrePrincipale, repertoireSelectionne, null);
						} catch (Exception exception3) {
				        	label.setText("Un dysfonctionnement s'est produit! \n"
				        			+ " Veuillez vous assurer que le dossier que vous avez sélectionné contient au moins une image \n"
				        			+ "et que les noms de toutes les images du dossier sont uniquement composés de chiffres.");
						} // catch
                        
                    } else { // repertoireSelectionne == null
                        label.setText("Aucun répertoire n'a été sélectionné.");
                    } // else
                } // handle()
            }; // EventHandler<ActionEvent> gestionnaireEvenements
  
            button.setOnAction(gestionnaireEvenements); // ActionEvent : Associe a un  element par la methode setOnAction()
            
            VBox vbox = new VBox(30, nomUE, grandTitre, explicationDivisionBaseImages, button1, button2, label, button);
            vbox.setAlignment(Pos.CENTER);
            Scene scene = new Scene(vbox, 1200, 700); // Cree une scene (niveau intermediaire entre la fenetre et les panneaux)
        	
            scene.getStylesheets().add("Stylesheet.css"); // Associer un fichier CSS
        	grandTitre.setId("grandTitre"); // Associer un identifiant à un élément de texte
        	nomUE.setId("nomUE"); // Associer un identifiant à un élément de texte
        	explicationDivisionBaseImages.setId("explicationDivisionBaseImages"); // Associer un identifiant à un élément de texte
        	
        	fenetrePrincipale.setScene(scene); // Place la scene dans la fenetre
        	fenetrePrincipale.show(); // Affiche la fenetre
        }
  
        catch (Exception exception2) {
        	label.setText("Un dysfonctionnement s'est produit! \n"
        			+ " Veuillez vous assurer que le dossier que vous avez sélectionné contient au moins une image \n"
        			+ "et que les noms de toutes les images du dossier sont uniquement composés de chiffres.");
        } // catch
    } // start()
    
    /**
     * Afficher les photos du dossier sélectionné.
     * @param fenetrePrincipale l’objet Stage (la scene) correspondant a la fenetre principale.
     * @throws Exception
     */
    public void afficherGalerieDePhotos(Stage fenetrePrincipale, Stage fenetreGalerieDePhotos, File repertoireSelectionne) throws Exception {
    	Label galerieTitre = new Label("Mesure du niveau du contenu d’un verre • Galerie de photos");
    	Label afficherRepertoireSelectionne = new Label(repertoireSelectionne.getAbsolutePath());
    	
    	
    	
    	fenetreGalerieDePhotos.setHeight(400);
    	fenetreGalerieDePhotos.setWidth(1200);
    	
        HBox imagesStore = new HBox(4);
        int i;
        for (i = 0 ; i < galerieDePhotos.size() ; i++) {
            ImageView imageView = new ImageView();
            String url = galerieDePhotosURL.get(i);
            imageView.setOnMouseClicked(event -> MesureNiveau.f(  url, fenetrePrincipale, repertoireSelectionne ) );

            imageView.setImage(galerieDePhotos.get(i));
            imageView.setFitWidth(1200 / 4);
            imageView.setFitHeight(800 / 4 - 50);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);

            imagesStore.getChildren().add(imageView);
        } // for

        ScrollPane scrollPane = new ScrollPane(imagesStore);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        fenetreGalerieDePhotos.setScene(new Scene(scrollPane));
        VBox vbox = new VBox(30, galerieTitre, afficherRepertoireSelectionne, scrollPane);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 1200, 800); // Cree une scene (niveau intermediaire entre la fenetre et les panneaux)
    	
        scene.getStylesheets().add("Stylesheet.css"); // Associer un fichier CSS
        galerieTitre.setId("galerieTitre"); // Associer un identifiant à un élément de texte
    	
    	fenetreGalerieDePhotos.setScene(scene); // Place la scene dans la fenetre
    	fenetreGalerieDePhotos.show(); // Affiche la fenetre
    } // afficherGalerieDePhotos()
} // class InterfaceGraphique