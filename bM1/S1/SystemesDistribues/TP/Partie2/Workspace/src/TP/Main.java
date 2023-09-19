package TP;

/**
 * Main du Projet
 * @author Alexandre Stefanos
 */
public class Main {
    public static void main(String[] args) throws Exception {
        //Veuillez lire le README.txt s'il vous plaît

        //Iniatialisation du Gérant
        Gerant gerant = new Gerant();

        //Initialisation des deux comptes exemples
        Compte compteEtienne = new Compte(1, "Etienne"); //Celui avec un solde de 2022.0
        Compte compteStephane = new Compte(2, "Stephane"); //Celui a découvert (12.0)

        //Initialisation des deux clients exemples
        Client etienne = new Client("Etienne", compteEtienne);
        Client stephane = new Client("Stephane", compteStephane);

        //Initialisation des deux departements découverts exemples
        DptDecouvert dptDecouvertEtienne = new DptDecouvert(compteEtienne, gerant);
        DptDecouvert dptDecouvertStephane = new DptDecouvert(compteStephane, gerant);

        //Initialisation des deux départements découverts exemples
        DptPlacement dptPlacementEtienne = new DptPlacement(compteEtienne);
        DptPlacement dptPlacementStephane = new DptPlacement(compteStephane);

        //Ouveture des comptes
        gerant.start(compteEtienne);
        gerant.start(compteStephane);

        //Test d'envoies et de réceptions
        gerant.send(compteStephane);
        gerant.receive(compteStephane);
        gerant.send(compteEtienne);
        gerant.receive(compteEtienne);

        //Mise à jour du Solde des deux comptes
        compteEtienne.setSolde(2022.0);
        compteStephane.setSolde(12.0);

        //Demandes d'opérations pour les deux clients exemples
        etienne.demandeOperation(-100.0);
        stephane.demandeOperation(-20.0);

        //Exécution des opérations du côté du gérant
        gerant.faireOperation(compteEtienne, -100.0);
        gerant.faireOperation(compteStephane, -20.0);

        //Vérifications des départements découvertes
        dptDecouvertEtienne.retraitSolde();
        gerant.send(compteEtienne);
        gerant.receive(compteEtienne);
        dptDecouvertStephane.retraitSolde();
        gerant.send(compteStephane);
        gerant.receive(compteStephane);

        //Vérifications des départements placements
        dptPlacementEtienne.propositionPlacement(compteEtienne.getDateOuverture());
        gerant.send(compteEtienne);
        gerant.receive(compteEtienne);
        dptPlacementStephane.propositionPlacement(compteStephane.getDateOuverture());
        gerant.send(compteStephane);
        gerant.receive(compteStephane);

        //Fermeture des processus
        compteEtienne.close();
        compteStephane.close();
        gerant.close();
    }
}