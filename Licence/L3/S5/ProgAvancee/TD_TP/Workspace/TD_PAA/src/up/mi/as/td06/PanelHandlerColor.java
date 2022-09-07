package up.mi.as.td06;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class PanelHandlerColor implements EventHandler<ActionEvent>{
	Label label, labelVert;
	
	public PanelHandlerColor(Label label, Label labelVert) {
		this.label = label;
		this.labelVert = labelVert;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		label.setTextFill(Color.GREEN);
		labelVert.setTextFill(Color.RED);
	}
}
