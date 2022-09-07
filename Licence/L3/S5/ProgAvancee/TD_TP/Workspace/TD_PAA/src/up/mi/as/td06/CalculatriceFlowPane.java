package up.mi.as.td06;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class CalculatriceFlowPane extends FlowPane{
	private TextField text1;
	private TextField text2;
	private Label Up;
	
	public CalculatriceFlowPane() {
	        text1 = new TextField();
	        text2 = new TextField();
	        Up = new Label();
	        this.getChildren().addAll( text1, text2,Up);


	    }

	    public int getText1() {
	        return (Integer.parseInt(text1.getText()));
	    }
	    public int getText2() {
	        return (Integer.parseInt(text2.getText()));
	    }
	    public String addition() {
	        int temp = Integer.parseInt(text1.getText())+Integer.parseInt(text2.getText());
	        return ("" + temp);
	    }

	    public String Soustraction() {
	        int temp = Integer.parseInt(text1.getText())-Integer.parseInt(text2.getText());
	        return ("" + temp);
	    }
	    public Label getUp() {
	        return Up;
	    }
	    public void Reset() {
	        text1.setText("");
	        Up.setText("");
	        text2.setText("");
	    }
}
