package game.frontend;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;

import game.backend.level.Level3;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

public class CandyFrame extends VBox {

	private static final int CELL_SIZE = 65;

	private BoardPanel boardPanel;
	private ScorePanel scorePanel;

	private Point2D lastPoint;
	private CandyGame game;

	public CandyFrame(CandyGame game, CustomStage primaryStage) {

		this.game = game;
		getChildren().add(new AppMenu(primaryStage));

		boardPanel = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE);
		getChildren().add(boardPanel);
		scorePanel = new ScorePanel();
		getChildren().add(scorePanel);
		game.initGame();

		GameListener listener;
		game.addGameListener(listener = new GameListener() {
			int remainingSeconds;
			Timer timer = new Timer();
			@Override
			public void gridUpdated() {

				Timeline timeLine = new Timeline();
				Duration frameGap = Duration.millis(100);
				Duration frameTime = Duration.ZERO;
				for (int i = game().getSize() - 1; i >= 0; i--) {
					for (int j = game().getSize() - 1; j >= 0; j--) {
						int finalI = i;
						int finalJ = j;
						Cell cell = CandyFrame.this.game.get(i, j);

						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, cell)));
					}
					frameTime = frameTime.add(frameGap);
				}
				timeLine.play();
			}

			@Override
			public void cellExplosion(Element e) {
				//
			}

			@Override
			public void timeUpdated(int newRemainingSeconds) { //Se usa si es necesario
				remainingSeconds = newRemainingSeconds;

				//timer.cancel(); //Por si ya se estaba ejecutando.
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						Platform.runLater(new TimerTask() {
							@Override
							public void run() {




								if (game().isFinished()) {
									scorePanel.updateScore(game().getScore());
									scorePanel.setWin(game().playerWon());
									timer.cancel();
								} else {
									scorePanel.updateScore(game().getScore());
								}

								remainingSeconds--;
							}
						});
					}
				}, 0, 1000);

			}
		});

		listener.gridUpdated();

		if(game.isLevel(Level3.class))
			listener.timeUpdated(Level3.INITIAL_TIME_LIMIT);
		else
			scorePanel.updateScore(game().getScore()); //Para mostrar el puntaje inicial de la forma adecuada a cada nivel



		addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (lastPoint == null) {
				lastPoint = translateCoords(event.getX(), event.getY());
				System.out.println("Get first = " +  lastPoint);
			} else {
				Point2D newPoint = translateCoords(event.getX(), event.getY());
				if (newPoint != null) {
					System.out.println("Get second = " +  newPoint);
					String message;
					if(game().tryMove((int)lastPoint.getX(), (int)lastPoint.getY(), (int)newPoint.getX(), (int)newPoint.getY())) {
						message = game().getScore();
						scorePanel.updateScore(message);
					}


					if (game().isFinished()) {
						scorePanel.setWin(game().playerWon());
					}

					lastPoint = null;
				}
			}
		});

	}

	private CandyGame game() {
		return game;
	}

	private Point2D translateCoords(double x, double y) {
		double i = x / CELL_SIZE;
		double j = y / CELL_SIZE;
		return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
	}

}
