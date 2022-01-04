package up.mi.jgm.td06.compteurclics;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PremiereGUISansDecoupage extends Application {

	private class Compteur {
		private int valeur;

		public Compteur() {
			valeur = 0;
		}

		public void incrementer() {
			valeur++;
		}

		public int getValeur() {
			return valeur;
		}
	}

	private class CompteurHandler implements EventHandler<ActionEvent> {
		private Compteur compteur;
		private Label label;

		public CompteurHandler(Label label) {
			compteur = new Compteur();
			this.label = label;
		}

		@Override
		public void handle(ActionEvent event) {
			compteur.incrementer();
			label.setText("--> " + compteur.getValeur());
		}

	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("GUI inutile");
		BorderPane pane = new BorderPane();
		Label lab = new Label("--> 0");
		Button button = new Button("OK");
		pane.setCenter(lab);
		pane.setBottom(button);

		button.setOnAction(new CompteurHandler(lab));

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
