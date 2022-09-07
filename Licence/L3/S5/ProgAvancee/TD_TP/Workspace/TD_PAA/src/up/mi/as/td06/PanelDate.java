package up.mi.as.td06;

import java.util.Date;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class PanelDate extends BorderPane {
	Label label, labelVert;
	Button button, buttonColor;
	Date date;

	public PanelDate() {
		label = new Label("Date");
		labelVert = new Label("Je suis Vert");
		button = new Button("Mettre à jour la date");
		buttonColor = new Button("Je change de couleur");
		date = new Date();
		
		labelVert.setText("Je suis Vert");
		labelVert.setTextFill(Color.GREEN);
		button.setOnAction(new PanelHandler(label));
		buttonColor.setOnAction(new PanelHandlerColor(label, labelVert));
		
		
		this.setTop(label);
		this.setCenter(labelVert);
		this.setBottom(buttonColor);
	}
}
