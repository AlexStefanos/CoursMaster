class utiliseTampon {

    public static void main(String args[]) {
        
        tamponCirc tampon = new tamponCirc(5);
        producteur prod = new producteur(tampon);
        // producteur prod2 = new producteur(tampon);
        consommateur cons = new consommateur(tampon);
	consommateur cons2 = new consommateur(tampon);
        prod.start();
        //prod2.start();
	cons.start();
        cons2.start();

    }

}
