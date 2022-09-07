package up.mi.jgm.td06.labellien;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class LabelLien extends Label {
	
	public LabelLien(String texte) {
		super(texte);
		setOnMouseEntered((event)->{
			setTextFill(Color.RED);
		});
		setOnMouseExited((event)->{
			setTextFill(Color.BLACK);
		});
	}
}
