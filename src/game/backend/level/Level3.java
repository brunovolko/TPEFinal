package game.backend.level;

import game.backend.GameListener;
import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.CandyGeneratorTimeProviderCell;
import game.backend.cell.Cell;
import game.backend.element.Wall;

public class Level3 extends Level1 {
	
	private final static int REQUIRED_SCORE = 5000;
	private final static int MAX_MOVES = 20;
	private final static int TIME_PROVIDER_CANDIES = 10;
	public final static int INITIAL_TIME_LIMIT = 10;

	private int remainingSeconds;

	private boolean wasMoved;

	private boolean firstTime = true;





	public Level3() {
		super();
		super.candyGenCell = new CandyGeneratorTimeProviderCell(this, TIME_PROVIDER_CANDIES);
		remainingSeconds = INITIAL_TIME_LIMIT;
		wasMoved = false;
	}


	
	@Override
	protected GameState newState() {
		return new Level3State(REQUIRED_SCORE, MAX_MOVES);
	}

	@Override
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		boolean ret;
		if (ret = super.tryMove(i1, j1, i2, j2)) {
			state().addMove();
		}
		wasMoved = ret;
		return ret;
	}
	
	protected class Level3State extends Level1State {
		
		public Level3State(long requiredScore, int maxMoves) {
			super(requiredScore,maxMoves);
		}

		/*@Override
		public boolean gameOver() {
			return playerWon() || getMoves() >= maxMoves;
		}*/

		@Override
		public String getPrintableScore(){
			firstTime = false;
			System.out.println("Segun Level3 van " + remainingSeconds + " s.");
			String returnableScore = "T. Restante: "+remainingSeconds + " - Puntaje: "+getScore();
			if(remainingSeconds>0 && !wasMoved && !firstTime)
			{
				remainingSeconds--;
			}
			/*if(!wasMoved) //Osea el getPrintableScore se debio a que paso un segundo
				remainingSeconds--;*/
			return returnableScore;
		}
		@Override
		public boolean gameOver() {
			return remainingSeconds==0 || playerWon(); //HAY QUE REVISAR ESTO LOCO ----------------------------------------------------------------------------
		}
		/*public String getImprovedPrintableScore(){
			return "T. Restante: "+remainingSeconds-- + " - Puntaje: "+getScore();
		}*/
	}

}
