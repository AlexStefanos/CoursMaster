package up.mi.as.td06;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class CalculatriceBorderPane extends BorderPane{
    private Button button1;
    private Button button2;
    private Button button3;
    private CalculatriceFlowPane flow;

    public CalculatriceBorderPane() {
        flow = new CalculatriceFlowPane();
        button1 = new Button("+");
        button2 = new Button("-");
        button3 = new Button("Reset");
        this.setLeft(button1);
        this.setCenter(button2);
        this.setRight(button3);
        this.setTop(flow);
        
        button1.setOnAction((event)-> {
            flow.getUp().setText(flow.addition());
        });

        button2.setOnAction((event)-> {
            flow.getUp().setText(flow.Soustraction());
        });
        
        button3.setOnAction((event)-> {
            flow.Reset();
        });
        
        flow.getChildren().addAll();
    }
}
