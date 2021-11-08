import java.io.File;
import java.util.ArrayList;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MesureNiveau {
	final static int EXIT_FAILURE = 1;
	
	/**
	 * 
	 * @param dir
	 * @return
	 */
	static ArrayList<File> selectionImages(File dir){
		ArrayList<File> images = new ArrayList<File>();
		for(File file : dir.listFiles()) {
			if (!file.getName().contains("_"))
				images.add(file);
		}
		return images;
	}

	/**
	 * Méthode transformant une image en une matrice
	 * @param cheminImage indication du chemin où se trouve l'image à transformer dans l'ordinateur
	 * @return matrice de l'image passer en paramètre si elle existe
	 */
	static Mat transformerImageEnMatrice(String cheminImage) {
		return ( (cheminImage.isEmpty()) ? new Mat() : Imgcodecs.imread( cheminImage ) ); 
	}
	
	/**
	 * Méthode qui lisse une image représentée par une matrice 
	 * @param matriceImageOrigine 
	 * @param matriceImageResultat  
	 * @param tailleNoyau  
	 */
	static void lissage(Mat matriceImageOrigine, Mat matriceImageResultat, int tailleNoyau) {
		Size sizeNoyau = new Size(tailleNoyau, tailleNoyau);
		Imgproc.blur(matriceImageOrigine, matriceImageResultat, sizeNoyau); 
	}
	
	/**
	 * utilisation du medianBlur
	 * @param matriceImageOrigine
	 * @param matriceImageResultat
	 * @param tailleNoyau
	 */
	static void filtreMedian(Mat matriceImageOrigine, Mat matriceImageResultat, int tailleNoyau) {
		Imgproc.medianBlur(matriceImageOrigine, matriceImageResultat, tailleNoyau);
	}
	
	/**
	 * un brouillage avec le gaussianBlur
	 * @param matriceImageOrigine
	 * @param matriceImageResultat
	 * @param tailleNoyau
	 */
	static void gaussienne(Mat matriceImageOrigine, Mat matriceImageResultat, int tailleNoyau) {
		Size sizeNoyau = new Size(tailleNoyau, tailleNoyau);
		Imgproc.GaussianBlur(matriceImageOrigine, matriceImageResultat, sizeNoyau, 0,0, Core.BORDER_DEFAULT);
	}
	
	
	/**
	 * Méthode qui transforme les couleurs d'une image en nuance de gris 
	 * @param matriceImageOrigine
	 * @param matriceImageResultat
	 */
	static void transformerImageEnNiveauxDeGris(Mat matriceImageOrigine, Mat matriceImageResultat) {
		Imgproc.cvtColor(matriceImageOrigine, matriceImageResultat, Imgproc.COLOR_RGB2GRAY);
	}
	
	/**
	 * Méthode qui dessine les contours des objets dans une image réprésentée par une matrice 
	 * @param matriceImageOrigine 
	 * @param matriceImageResultat 
	 * @param seuil1
	 * @param seuil2
	 */
	static void detectionContoursCanny(Mat matriceImageOrigine, Mat matriceImageResultat, int seuil1, int seuil2) {
		Imgproc.Canny(matriceImageOrigine, matriceImageResultat, seuil1, seuil2);
	}
	
	/**
	 * Seuillage avec kmeans
	 * @param matriceImageOrigine
	 * @param k
	 * @return
	 */
	static ArrayList<Mat> seuillage(Mat matriceImageOrigine, int k) {
		int n = matriceImageOrigine.rows() * matriceImageOrigine.cols();
		Mat data = matriceImageOrigine.reshape(1, n);
		data.convertTo(data, CvType.CV_32F, 1.0 / 255.0);
		
		Mat labels = new Mat();
		
		TermCriteria criteria = new TermCriteria(TermCriteria.COUNT, 100, 1);
		Mat centers = new Mat();
		Core.kmeans(data, k, labels, criteria, 1, Core.KMEANS_PP_CENTERS, centers);
		
		centers.convertTo(centers, CvType.CV_8UC1, 255.0);
		centers.reshape(3);
		
		ArrayList<Mat> clusters = new ArrayList<Mat>();
		for(int i = 0; i < centers.rows(); i++) {
			clusters.add(Mat.zeros(matriceImageOrigine.size(), matriceImageOrigine.type()));
		}		
		
		int rows = 0;
		for(int y = 0; y < matriceImageOrigine.rows(); y++) {
			for(int x = 0; x < matriceImageOrigine.cols(); x++) {
				int label = (int)labels.get(rows, 0)[0];
				
				int r = (int)centers.get(label, 2)[0];
				int g = (int)centers.get(label, 1)[0];
				int b = (int)centers.get(label, 0)[0];
				clusters.get(label).put(y, x, b, g, r);
				rows++;
			}
		}
		return clusters;
	}
	
	/**
	 * Utilisation de sobel ou scharr
	 * @param matriceImageOrigine
	 * @param matriceImageResultat
	 */
	static void detectionContoursSobelScharr(Mat matriceImageOrigine, Mat matriceImageResultat) {
		Imgproc.Scharr( matriceImageOrigine, matriceImageResultat, CvType.CV_16S, 0, 1);
		//Imgproc.Sobel(matriceImageOrigine, matriceImageResultat, CvType.CV_16S, 0, 1);
	}
	
	/**
	 * Erosion des par l'element structuant par défaut :
	 *  [ 1, 1, 1, 1, 1, 1, 1]
	 * @param matriceImageOrigine
	 * @param matriceImageResultat
	 * @param taille
	 * @param rep
	 */
	static void erosion(Mat matriceImageOrigine, Mat matriceImageResultat, int taille) {
		Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(taille,1));
		Point origine = new Point((int)taille/2 +1,-1);
		Imgproc.erode(matriceImageOrigine, matriceImageResultat, element, origine);
	}

	
	/**
	 * Méthode pour afficher une image
	 * @param matriceImageResultat Matrice qui représente une image
	 */
	static void afficherImage(Mat matriceImageResultat) {
		HighGui.imshow("Resultat", matriceImageResultat);
		HighGui.waitKey();
	}
	
	/**
	 * 
	 * @param matriceImageResultat
	 * @param iter
	 * @return
	 */
	static ArrayList<Integer> calculLignes(Mat matriceImageResultat, int iter){
				
		ArrayList<Integer> contoursHorizon = new ArrayList<Integer>();
		
		int ligneDansMatriceImage = matriceImageResultat.height()/30;
		while(ligneDansMatriceImage < matriceImageResultat.height() -matriceImageResultat.height()/30) {
			int nombreDePixelsPasNoirsDansLaLigne = 0;
			for(int j = 0 ; j < matriceImageResultat.width() -1 ; j++) {
				if(matriceImageResultat.get(ligneDansMatriceImage, j) != null)
				{
					if(matriceImageResultat.get(ligneDansMatriceImage, j)[0] != 0) {
						nombreDePixelsPasNoirsDansLaLigne++;
						if(nombreDePixelsPasNoirsDansLaLigne > 20 - iter*10) {
							contoursHorizon.add(ligneDansMatriceImage);
							ligneDansMatriceImage+= matriceImageResultat.height()/20;
							j = 0;
							nombreDePixelsPasNoirsDansLaLigne = 0;
						}
					}
					else
						nombreDePixelsPasNoirsDansLaLigne = 0;
				}
			}
			ligneDansMatriceImage++;
		}
		return contoursHorizon;
	}
	
	/**
	 * renvoie les lignes au dessus ou en dessous du milieu donné
	 * @param lignes
	 * @param haut
	 * @param milieu
	 * @return
	 */
	static ArrayList<Integer> lignesHautBas(ArrayList<Integer> lignes, boolean haut, int milieu) {
		ArrayList<Integer> count = new ArrayList<>();

		for (int i = 0; i < lignes.size(); i++) {
			if (haut) {
				if (lignes.get(i) < milieu)
					count.add(lignes.get(i));
			}
			else {
				if (lignes.get(i) > milieu)
					count.add(lignes.get(i));
			}
		}
		
		return count;		
	}
	/**
	 * renvoie le nombre le plus proche de c
	 * @param a
	 * @param b
	 * @param c
	 * @param i
	 * @return
	 */
	static boolean plusProche(int a, int b, int c, int i) {
		int[] tab = new int[2];
		int aEcart = Math.abs(a - c);
		int bEcart = Math.abs(b - c);
		tab[1] = i;
		if (aEcart < bEcart)
			return true;
		else
			return false;
	}
	
	/**
	 * calcul de la ligne la plus proche du centre de l'image
	 * @param lignes
	 * @param centre
	 * @return
	 */
	static int procheMilieu(ArrayList<Integer> lignes, int centre) {
		ArrayList<Integer> lignes2 = new ArrayList<Integer>(lignes);
		int milieu, indice, i;
		boolean valide;
		do {
			milieu = centre*2;
			valide = true;
			indice = 0;
			for (i= 0; i < lignes2.size(); i++) {
				if (plusProche(lignes2.get(i), milieu, centre, i))
				{
					indice = i;
					milieu = lignes2.get(i);
				}
			}
			if (lignesHautBas(lignes, true, milieu).size() == 0 ||
			   lignesHautBas(lignes, false, milieu).size() == 0)
			{
				lignes2.remove(indice);
				valide = false;
			}
			
		}while(!valide);
			
		return milieu;		
	}
	
	/**
	 * Calcul de la médiane
	 * @param contoursHorizon
	 * @return
	 */
	static int mediane(ArrayList<Integer> contoursHorizon) {
		int median1;
		int median2;
		ArrayList<Integer> medianHaut = new ArrayList<>();
		ArrayList<Integer> medianBas = new ArrayList<>();
		if (contoursHorizon.size() % 2 == 1)
		    median1 = contoursHorizon.get((int)contoursHorizon.size()/2);
		else
		{
		    median1 = contoursHorizon.get((int)(contoursHorizon.size()/2) -1);
		    medianHaut = lignesHautBas(contoursHorizon, true, median1);
		    median2 = contoursHorizon.get((int)contoursHorizon.size()/2);
		    medianBas = lignesHautBas(contoursHorizon, false, median2);
		    if (medianBas.size() > medianHaut.size())
		    	median1 = median2;
		}
		return median1;
	}
    
	public static void f(String path, Stage fenetrePrincipale, File repertoireSelectionne) {
		Mat matriceImageOrigine = transformerImageEnMatrice(path); // 
		Mat matriceImageLisse = transformerImageEnMatrice("");
		Mat matriceImageGris = transformerImageEnMatrice("");
//		Mat matriceImageSobelScharr = transformerImageEnMatrice("");
//		Mat matriceImageCanny = transformerImageEnMatrice("");
		Mat matriceImageResultat = transformerImageEnMatrice("");
		Mat matriceImageFinaleDessin = transformerImageEnMatrice("");
		
		String cheminImageResultat = path.split("\\.")[0];
//		transformerImageEnNiveauxDeGris(matriceImageOrigine, matriceImageGris);		
		matriceImageGris = seuillage(matriceImageOrigine, 2).get(0);
	
		//lissage(matriceImageOrigine, matriceImageLisse, 3);
		gaussienne(matriceImageGris, matriceImageLisse, 3);
		
		//On utilise Canny sur l'image lissée
		detectionContoursCanny(matriceImageLisse, matriceImageResultat, 50, 100);
//		detectionContoursSobelScharr(matriceImageLisse, matriceImageSobelScharr);
		
		int tailleTrait = 10;	
		int fondDuVerre = 0, eau = 0, hautDuVerre = 0;
		int iter = 0;
		ArrayList<Integer> lignesPos= new ArrayList<Integer>();
		
		//On cherche les lignes horizontales de l'image
		ArrayList<Integer> contoursHorizon = calculLignes(matriceImageResultat, iter);
		while (contoursHorizon.size() < 3 && iter < 2)
		{
			contoursHorizon = calculLignes(matriceImageResultat, iter);
			iter++;
		}
		
		if (contoursHorizon.size() >= 3)
		{
			//Ligne de l'eau
			Scalar ligneEau = new Scalar(255, 255, 255);
//			eau = mediane(contoursHorizon);
			eau = procheMilieu(contoursHorizon, (int)matriceImageOrigine.height()/2);
			Point pointDebutEau = new Point(0, eau);
			Point pointFinEau = new Point(matriceImageOrigine.width() -1, eau);
			Imgproc.line(matriceImageOrigine, pointDebutEau, pointFinEau,
					ligneEau, tailleTrait);
			
			//Ligne du fond du verre
			Scalar ligneFondDuVerre = new Scalar(0, 0, 0);
			lignesPos = lignesHautBas(contoursHorizon, false, eau);
			fondDuVerre = mediane(lignesPos);
			Point pointDebutFondDuVerre = new Point(0, fondDuVerre);
			Point pointFinFondDuVerre = new Point(matriceImageOrigine.width() -1, fondDuVerre);
			Imgproc.line(matriceImageOrigine, pointDebutFondDuVerre, pointFinFondDuVerre,
					ligneFondDuVerre, tailleTrait);
			
			//Ligne du haut du verre
			Scalar ligneHautDuVerre = new Scalar(128, 128, 128);
			lignesPos = lignesHautBas(contoursHorizon, true, eau);
			hautDuVerre = mediane(lignesPos);
			Point pointDebutHautDuVerre = new Point(0, hautDuVerre);
			Point pointFinHautDuVerre = new Point(matriceImageOrigine.width() -1, hautDuVerre);
			Imgproc.line(matriceImageOrigine, pointDebutHautDuVerre, pointFinHautDuVerre,
					ligneHautDuVerre, tailleTrait);
		}
		//Calcule du pourcentage
		long pourcentage = Math.round(((double)(fondDuVerre - eau) / (fondDuVerre - hautDuVerre)) * 100);
		
		matriceImageOrigine.convertTo(matriceImageFinaleDessin, CvType.CV_8U);
		Imgcodecs.imwrite(cheminImageResultat+"_resultat.png", matriceImageFinaleDessin);
		
		InterfaceGraphique.resultats.add(pourcentage + "%");
		InterfaceGraphique.nouvelleFenetrePrincipale(fenetrePrincipale, repertoireSelectionne, new Image("file:" + cheminImageResultat + "_resultat.png"));


	}
}