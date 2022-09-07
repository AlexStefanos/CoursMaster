package up.mi.jgm.td06.paneldate;

import java.util.Date;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PanelDate extends BorderPane {
	public PanelDate() {
		Label lab = new Label(new Date().toString());
		Button button = new Button("OK");
		button.setOnAction(new DateClickHandler(lab));
		this.setCenter(lab);
		this.setBottom(button);
	}
}
