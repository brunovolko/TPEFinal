package game.frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class GameApp extends Application {

	Scene mainMenuScene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setResizable(false);

		mainMenuScene = new Scene(MainMenu.getFrame(primaryStage), 800, 600);
		primaryStage.setScene(mainMenuScene);
		primaryStage.show();



	}

}
