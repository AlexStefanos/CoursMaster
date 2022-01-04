package up.mi.jgm.td06.panelcouleurbouton;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class PanelCouleurBouton extends BorderPane {
	private Label lab;
	private Button boutonRouge;
	private Button boutonVert;

	public PanelCouleurBouton() {
		lab = new Label("Je change de couleur.");
		boutonRouge = new Button("Couleur rouge");
		boutonVert = new Button("Couleur verte");
		this.setTop(lab);
		this.setLeft(boutonRouge);
		this.setRight(boutonVert);

		boutonRouge.setOnAction((event) -> {
			lab.setTextFill(Color.RED);
		});
		boutonRouge.setTextFill(Color.RED);
		
		boutonVert.setOnAction((event) -> {
			lab.setTextFill(Color.GREEN);
		});
		boutonVert.setTextFill(Color.GREEN);
	}
}
