package up.mi.jgm.td06.paneldate;

import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class DateClickHandler implements EventHandler<ActionEvent> {
	private Label lab ;
	
	public DateClickHandler(Label lab) {
		this.lab = lab ;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		lab.setText(new Date().toString());
	}

}
