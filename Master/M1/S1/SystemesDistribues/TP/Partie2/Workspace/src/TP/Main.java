package TP;
public class Main {
    public static void main(String[] args) throws Exception {
        //Veuillez lire le README.txt s'il vous plaît
        Gerant gerant = new Gerant();
        Compte compteEtienne = new Compte(1, "Etienne"); //Celui avec un solde de 2022.0
        Compte compteStephane = new Compte(2, "Stephane"); //Celui a découvert (12.0)
        Client etienne = new Client("Etienne", compteEtienne);
        Client stephane = new Client("Stephane", compteStephane);
        DptDecouvert dptDecouvertEtienne = new DptDecouvert(compteEtienne, gerant);
        DptDecouvert dptDecouvertStephane = new DptDecouvert(compteStephane, gerant);
        DptPlacement dptPlacementEtienne = new DptPlacement(compteEtienne);
        DptPlacement dptPlacementStephane = new DptPlacement(compteStephane);
        gerant.start(compteEtienne);
        gerant.start(compteStephane);
        gerant.send(compteStephane);
        gerant.receive(compteStephane);
        compteEtienne.setSolde(2022.0);
        compteStephane.setSolde(12.0);
        gerant.send(compteEtienne);
        gerant.receive(compteEtienne);
        etienne.demandeOperation(-100.0);
        stephane.demandeOperation(-20.0);
        gerant.faireOperation(compteEtienne, -100.0);
        gerant.faireOperation(compteStephane, -20.0);
        dptDecouvertEtienne.retraitSolde();
        gerant.send(compteEtienne);
        gerant.receive(compteEtienne);
        dptDecouvertStephane.retraitSolde();
        gerant.send(compteStephane);
        gerant.receive(compteStephane);
        dptPlacementEtienne.propositionPlacement(compteEtienne.getDateOuverture());
        gerant.send(compteEtienne);
        gerant.receive(compteEtienne);
        dptPlacementStephane.propositionPlacement(compteStephane.getDateOuverture());
        gerant.send(compteStephane);
        gerant.receive(compteStephane);
        gerant.stop();
    }
}