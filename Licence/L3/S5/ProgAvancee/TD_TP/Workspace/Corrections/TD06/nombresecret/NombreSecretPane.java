package up.mi.jgm.td06.nombresecret;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import up5.mi.pary.exercice.p4.igsecret.Jeu;

public class NombreSecretPane extends FlowPane {
	private Label lab;
	private Button boutonPlus;
	private Button boutonMoins;
	private Button boutonTrouve;
	private Jeu jeu;

	public NombreSecretPane() {
		jeu = new Jeu(1, 100);
		lab = new Label("Je propose " + jeu.getProposition());
		boutonPlus = new Button("+");
		boutonMoins = new Button("-");
		boutonTrouve = new Button("Trouve");

		this.getChildren().addAll(lab, boutonPlus, boutonMoins, boutonTrouve);

		boutonPlus.setOnAction((event) -> {
			jeu.noterPropositionTropPetite();
			if (jeu.isJeuSansSolution()) {
				jeu.reset();
				lab.setText("Le jeu est sans solution ! Nouvelle partie ! Je propose " + jeu.getProposition());
			} else {
				lab.setText("Je propose " + jeu.getProposition());
			}
		});

		boutonMoins.setOnAction((event) -> {
			jeu.noterPropositionTropGrande();
			if (jeu.isJeuSansSolution()) {
				jeu.reset();
				lab.setText("Le jeu est sans solution ! Nouvelle partie ! Je propose " + jeu.getProposition());
			} else {
				lab.setText("Je propose " + jeu.getProposition());
			}
		});

		boutonTrouve.setOnAction((event) -> {
			jeu.noterPropositionOK();
			String str = "Trouve en " + jeu.getNbPropositions() + " coups ! Nouvelle partie !";
			jeu.reset();
			lab.setText(str + " Je propose " + jeu.getProposition());
		});
	}
}
