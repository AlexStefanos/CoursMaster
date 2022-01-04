package up.mi.jgm.td06.panelcouleurbouton;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BoutonsCouleur extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new PanelCouleurBouton();
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
