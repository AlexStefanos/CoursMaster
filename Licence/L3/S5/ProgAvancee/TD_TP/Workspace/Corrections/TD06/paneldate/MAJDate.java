package up.mi.jgm.td06.paneldate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MAJDate extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		//Pane pane = new PanelDate();
		//Pane pane = new PanelDateI();
		//Pane pane = new PanelDateA();
		Pane pane = new PanelDateL();
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
