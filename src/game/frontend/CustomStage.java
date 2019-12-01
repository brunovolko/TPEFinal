package game.frontend;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomStage {
    Stage originalStage;

    Scene mainMenuScene;

    public CustomStage(Stage originalStage) {
        this.originalStage = originalStage;
        mainMenuScene = new Scene(MainMenu.getFrame(this), 800, 600);
    }
}
