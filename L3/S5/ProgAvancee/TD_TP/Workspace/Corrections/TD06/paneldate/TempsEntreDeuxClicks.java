package up.mi.jgm.td06.paneldate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TempsEntreDeuxClicks extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		PanelTempsEntreDeuxClicks pane = new PanelTempsEntreDeuxClicks();
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
