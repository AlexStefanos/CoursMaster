package up.mi.jgm.td06.paneldate;

import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PanelDateA extends BorderPane {
	public PanelDateA() {
		Label lab = new Label(new Date().toString());
		Button button = new Button("OK");
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				lab.setText(new Date().toString());
			}
			
		});
		this.setCenter(lab);
		this.setBottom(button);
	}
}
