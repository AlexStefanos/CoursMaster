package up.mi.jgm.td06.nombresecret;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NombreSecret extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new NombreSecretPane();
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
