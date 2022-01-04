package up.mi.jgm.td06.paneldate;

import java.util.Date;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PanelDateL extends BorderPane {
	public PanelDateL() {
		Label lab = new Label(new Date().toString());
		Button button = new Button("OK");
		button.setOnAction((event) -> {
			lab.setText(new Date().toString());
		});
		this.setCenter(lab);
		this.setBottom(button);
	}
}
