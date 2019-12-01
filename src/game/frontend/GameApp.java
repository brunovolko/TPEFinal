package game.frontend;

import game.backend.CandyGame;
import game.backend.level.Level1;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Button;


import java.awt.*;

public class GameApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		VBox initialMenuFrame = new VBox();
		initialMenuFrame.setSpacing(20);
		initialMenuFrame.setAlignment(Pos.TOP_CENTER);

		Text welcomeText = new Text("¡ Welcome to CrushCandy !");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 30));

		Text chooseLevelText = new Text("Please choose a level");
		chooseLevelText.setFont(Font.font("Arial", FontWeight.BOLD, 24));


		Button btnLevel1 = new Button("Level 1");
		Button btnLevel2 = new Button("Level 2");

		VBox levelButtonsFrame = new VBox();
		levelButtonsFrame.getChildren().addAll(btnLevel1,btnLevel2);





		initialMenuFrame.getChildren().addAll(welcomeText,chooseLevelText,levelButtonsFrame);

		Scene initialMenuScene = new Scene(initialMenuFrame, 800, 600);
		primaryStage.setScene(initialMenuScene);
		primaryStage.show();






		/*CandyGame game = new CandyGame(Level1.class);
		CandyFrame frame = new CandyFrame(game);
		Scene scene = new Scene(frame);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();*/
	}

}
