package byteDefense;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

	public void start(Stage primaryStage) {
		
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("byteDefense/view/GameView.fxml"));
			Scene scene = new Scene(root,900,900);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

