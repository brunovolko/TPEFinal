package game.frontend;

import game.backend.cell.Cell;
import game.backend.element.Element;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BoardPanel extends TilePane {


	private StackPane[][] cells;
	private ImageManager images;

	public BoardPanel(final int rows, final int columns, final int cellSize) {
		images = new ImageManager();
		setPrefRows(rows);
		setPrefColumns(columns);
		setPrefTileHeight(cellSize);
		setPrefTileWidth(cellSize);
		//this.cells = new ImageView[rows][columns];
		this.cells = new StackPane[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				cells[i][j] = new StackPane();
				getChildren().add(cells[i][j]);
			}
		}
	}
	
	public void setImage(int row, int column, Cell cell) {
		Element element = cell.getContent();
		Image image = images.getImage(element);

		ImageView imgView = new ImageView();
		imgView.setImage(image);

		cells[row][column].getChildren().add(imgView);

		/*cells[row][column].setImage(null);
		cells[row][column].setImage(image);*/
		if(cell.isGolden()) {
			Light.Distant spotLight = new Light.Distant();
			spotLight.setColor(Color.YELLOW);
			spotLight.setElevation(100);
			Lighting lighting = new Lighting(spotLight);
			cells[row][column].setEffect(lighting);
		}
		if(cell.isTimeProvider()) {
			Text text = new Text("+10");
			text.setFont(Font.font("Impact", FontWeight.BOLD, 38));
			text.setFill(Color.BLACK);

			cells[row][column].getChildren().add(text);

		}


	}

}