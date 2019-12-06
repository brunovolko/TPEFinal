package game.backend.move;

import game.backend.Figure;
import game.backend.FigureDetector;
import game.backend.Grid;
import game.backend.cell.Cell;

import java.util.function.Predicate;

public class CandyMove extends Move {

	private Figure f1;
	private Figure f2;
	
	private FigureDetector detector;
	private Grid grid;
	
	public CandyMove(Grid grid) {
		super(grid);
		this.grid = grid;
	}
	
	@Override
	public boolean internalValidation() {
		this.detector = new FigureDetector(grid);
		f1 = detector.checkFigure(i1, j1);
		f2 = detector.checkFigure(i2, j2);
		return f1 != null || f2 != null;
	}	

	@Override
	public void removeElements() {
		if (f1 != null) {
			detector.removeFigure(i1, j1, f1);
		}
		if (f2 != null) {
			detector.removeFigure(i2, j2, f2);
		}
	}

	@Override
	public int cantOfCellsMatching(Predicate<Cell> condition) {
		int cant = 0;
		if (f1 != null) {
			cant += detector.cantOfCellsMatching(i1, j1, f1, condition);
		}
		if (f2 != null) {
			cant += detector.cantOfCellsMatching(i2, j2, f2, condition);
		}

		return cant;

	}

	/*public void removeFigureAndGetCount() {
		//return detector.removeFigureAndGetCount(i1, j1, f1);
		//return
		detector.removeFigure(i1, j1, f1);
	}*/

}
