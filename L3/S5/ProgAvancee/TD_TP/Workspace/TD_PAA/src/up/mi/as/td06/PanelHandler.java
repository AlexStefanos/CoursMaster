package up.mi.as.td06;

import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class PanelHandler implements EventHandler<ActionEvent>{
	private Label label;
	
	public PanelHandler(Label label) {
		this.label = label;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		label.setText(new Date().toString());
	}
}
