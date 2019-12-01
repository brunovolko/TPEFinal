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
	public void start(Stage originalStage) {
		CustomStage primaryStage = new CustomStage(originalStage);

		originalStage.setResizable(false);


		originalStage.setScene(primaryStage.mainMenuScene);
		originalStage.show();



	}

}
