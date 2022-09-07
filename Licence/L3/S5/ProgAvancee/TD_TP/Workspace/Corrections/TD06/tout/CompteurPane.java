package up.mi.jgm.td06.compteurclics;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class CompteurPane extends FlowPane {
	private Label lab;
	private Button button;
	private Compteur compteur;

	public CompteurPane() {
		lab = new Label("--> 0");
		button = new Button("OK");
		compteur = new Compteur();
		button.setOnAction((event) -> {
			compteur.incrementer();
			lab.setText("--> " + compteur.getValeur());
		});
		this.getChildren().addAll(button,lab);
	}
}
