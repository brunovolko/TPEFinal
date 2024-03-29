package game.frontend;

import game.backend.CandyGame;
import game.backend.level.Level1;
import game.backend.level.Level2;
import game.backend.level.Level3;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu  {

    private static void playLevel (CustomStage primaryStage, CandyGame level){
        CandyFrame levelGameFrame = new CandyFrame(level, primaryStage);
        Scene levelScene = new Scene(levelGameFrame);
        primaryStage.getOriginalStage().setScene(levelScene);
        primaryStage.getOriginalStage().show();
    }

    public static VBox getFrame(CustomStage primaryStage) {
        VBox initialMenuFrame = new VBox();
        initialMenuFrame.setSpacing(20);
        initialMenuFrame.setAlignment(Pos.TOP_CENTER);
        initialMenuFrame.setPadding(new Insets(50, 50, 50, 50));

        Text welcomeText = new Text("¡ Bienvenido al CrushCandy !");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        Text chooseLevelText = new Text("Por favor seleccione un nivel");
        chooseLevelText.setFont(Font.font("Arial", FontWeight.BOLD, 24));


        Button btnLevel1 = new Button("Nivel 1");
        btnLevel1.setBackground(new Background(new BackgroundFill(Color.web("#03adfc"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnLevel1.setStyle("-fx-text-fill: #ffffff;");
        btnLevel1.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        btnLevel1.setPadding(new Insets(10, 10, 10, 10));

        Button btnLevel2 = new Button("Nivel 2");
        btnLevel2.setBackground(new Background(new BackgroundFill(Color.web("#03adfc"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnLevel2.setStyle("-fx-text-fill: #ffffff;");
        btnLevel2.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        btnLevel2.setPadding(new Insets(10, 10, 10, 10));

        Button btnLevel3 = new Button("Nivel 3");
        btnLevel3.setBackground(new Background(new BackgroundFill(Color.web("#03adfc"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnLevel3.setStyle("-fx-text-fill: #ffffff;");
        btnLevel3.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        btnLevel3.setPadding(new Insets(10, 10, 10, 10));



        btnLevel1.setOnAction(event -> {
            playLevel(primaryStage, new CandyGame(Level1.class));
        });

        btnLevel2.setOnAction(event -> {
            playLevel(primaryStage, new CandyGame(Level2.class));
        });

        btnLevel3.setOnAction(event -> {
            playLevel(primaryStage, new CandyGame(Level3.class));
        });

        VBox levelButtonsFrame = new VBox();
        levelButtonsFrame.setAlignment(Pos.CENTER);
        levelButtonsFrame.getChildren().addAll(btnLevel1, btnLevel2, btnLevel3);
        levelButtonsFrame.setSpacing(20);



        initialMenuFrame.getChildren().addAll(welcomeText,chooseLevelText,levelButtonsFrame);

        return initialMenuFrame;



    }
}
