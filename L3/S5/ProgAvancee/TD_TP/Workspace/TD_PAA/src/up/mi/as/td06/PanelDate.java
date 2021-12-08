package up.mi.as.td06;

import java.util.Date;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PanelDate extends BorderPane {
	Label label;
	Button button;
	Date date;

	public PanelDate() {
		label = new Label("Date");
		button = new Button("Mettre à jour la date");
		date = new Date();
		
		button.setOnAction(new PanelHandler(label));
		
		this.setTop(label);
		this.setCenter(button);
	}
}
