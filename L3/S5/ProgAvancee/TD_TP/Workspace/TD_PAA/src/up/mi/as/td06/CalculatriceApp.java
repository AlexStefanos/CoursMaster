package up.mi.as.td06;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CalculatriceApp extends Application {
	  @Override
	    public void start(Stage stage) throws Exception {
	        stage.setTitle("Calculatrice");
	        Pane pane = new CalculatriceBorderPane();
	        Scene scene = new Scene(pane);
	        stage.setScene(scene);
	        stage.sizeToScene();


	        stage.show();

	    }
	    public static void main(String[] args) {
	        launch(args);
	    }
}
