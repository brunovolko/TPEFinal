package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ScorePanel extends BorderPane {

	private Label scoreLabel;
	private String text;
	private boolean hasReceivedWin;

	public ScorePanel() {
		hasReceivedWin = false;
		setStyle("-fx-background-color: #5490ff");
		scoreLabel = new Label("0");
		scoreLabel.setAlignment(Pos.CENTER);
		scoreLabel.setStyle("-fx-font-size: 24");
		setCenter(scoreLabel);
	}
	
	public void updateScore(String text) {
		this.text = text;
		scoreLabel.setText(text);
	}

	public void setWin(boolean win) {
		if(!hasReceivedWin) {
			if (win) {
				updateScore(text + " Finished - Player Won!");
				hasReceivedWin = true;
			} else
				updateScore(text + " Finished - Loser !");
		}
	//	hasReceivedWin = true;
	}

}