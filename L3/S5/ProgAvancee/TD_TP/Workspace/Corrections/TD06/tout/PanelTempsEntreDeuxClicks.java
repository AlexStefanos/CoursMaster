package up.mi.jgm.td06.paneldate;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PanelTempsEntreDeuxClicks extends BorderPane {
	private Label lab;
	private Button button;
	private long tempsPrecedent;

	public PanelTempsEntreDeuxClicks() {
		lab = new Label("0");
		button = new Button("Click");
		tempsPrecedent = System.currentTimeMillis();

		button.setOnAction((event) -> {
			long nouveauTemps = System.currentTimeMillis();
			long difference = nouveauTemps - tempsPrecedent;
			tempsPrecedent = nouveauTemps;
			lab.setText("" + (difference/1000));
		});

		this.setCenter(lab);
		this.setBottom(button);
	}
}
