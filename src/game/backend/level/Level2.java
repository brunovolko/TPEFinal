package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.element.Wall;
import game.backend.move.Move;

public class Level2 extends Level1 {
	
	private final static int REQUIRED_SCORE = 5000;
	private final static int MAX_MOVES = 20;


	private int nonGoldenCells;
	
	@Override
	protected GameState newState() {
		return new Level2State(REQUIRED_SCORE, MAX_MOVES, SIZE*SIZE);
	}

	
	@Override
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		boolean ret;
		if(ret=super.tryMove(i1, j1, i2, j2)){
			if(moveMaker.getMove(i1, j1, i2, j2).validHorizontalMove())
				newGoldenCells(setGoldenRow(i1));
			else
				newGoldenCells(setGoldenColumn(j1));
		}
		return ret;
	}

	private int setGoldenRow(int row) {
		int newGoldenCells = 0;
		for(int i = 0; i < SIZE; i++)
			if(!g()[row][i].isGolden()) {
				g()[row][i].makeGolden();
				newGoldenCells++;
			}
		return newGoldenCells;

	}
	private int setGoldenColumn(int column) {
		int newGoldenCells = 0;
		for(int i = 0; i < SIZE; i++)
			if(!g()[i][column].isGolden()) {
				g()[i][column].makeGolden();
				newGoldenCells++;
			}
		return newGoldenCells;
	}

	private void newGoldenCells(int newGoldenCells) {
		nonGoldenCells -= newGoldenCells;
	}
	
	private class Level2State extends Level1State {

		
		public Level2State(long requiredScore, int maxMoves, int cellsNumber) {
			super(requiredScore,maxMoves);
			nonGoldenCells = cellsNumber;
		}

		@Override
		public boolean playerWon() {
			return nonGoldenCells == 0;
		}

		@Override
		public String getPrintableScore(){
			int movementsLeft = MAX_MOVES - getMoves();
			StringBuilder toReturn = new StringBuilder();
			if(!gameOver()) {
				toReturn.append("Mov. restantes:");
				toReturn.append(movementsLeft);
				toReturn.append(" -");
				toReturn.append("Celdas restantes:");
				toReturn.append(nonGoldenCells);
			}
			toReturn.append(" Puntaje:");
			toReturn.append(getScore());
			return toReturn.toString();		}

	}

}
