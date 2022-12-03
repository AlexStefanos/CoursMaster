package TP;
public class Main {
    public static void main(String[] args) throws Exception {
        Gerant gerant = new Gerant();
        Compte compte = new Compte(1, "Monsieur Dupont");
        compte.setEtatOuvert(true);
        gerant.start(compte);
        gerant.send(compte);
        gerant.receive(compte);
        gerant.faireOperation(compte, 100.0);
        gerant.stop();
    }
}