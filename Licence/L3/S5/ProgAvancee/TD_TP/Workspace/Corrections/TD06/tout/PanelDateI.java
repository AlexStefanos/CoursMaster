package up.mi.jgm.td06.paneldate;

import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PanelDateI extends BorderPane {
	private Label lab;
	private Button button;

	private class DateClickHandler implements EventHandler<ActionEvent> {
		private Label lab ;
		
		public DateClickHandler(Label lab) {
			this.lab = lab ;
		}

		@Override
		public void handle(ActionEvent arg0) {
			lab.setText(new Date().toString());
		}

	}

	public PanelDateI() {
		lab = new Label(new Date().toString());
		button = new Button("OK");
		button.setOnAction(new DateClickHandler(lab));
		this.setCenter(lab);
		this.setBottom(button);
	}
}
