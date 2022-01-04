package up.mi.jgm.td06.labellien;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestLabelLien extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new FlowPane(Orientation.VERTICAL);
		for(int i = 1 ; i <= 10 ; i++) {
			LabelLien lab = new LabelLien("Mon LabelLien numero " + i);
			pane.getChildren().add(lab);
		}
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
