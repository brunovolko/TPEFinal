package game.backend.level;

import game.backend.GameState;
import game.backend.cell.CandyGeneratorTimeProviderCell;
import game.backend.move.CandyMove;
import game.backend.move.Move;

public class Level3 extends Level1 {
	
	private final static int REQUIRED_SCORE = 5000;
	private final static int MAX_MOVES = 20;
	private final static int TIME_PROVIDER_CANDIES = 10;
	public final static int INITIAL_TIME_LIMIT = 30;
	public final static int TIME_PER_PROVIDER_CELL = 10; //En segundos

	private static int remainingSeconds;

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
		boolean movedOk;

		Move move = moveMaker.getMove(i1, j1, i2, j2); /*En vez de llamar a super, sobreescribimos el método tryMove pues necesitamos tener control sobre el objeto move ya que
														necesitamos hacer un chequeo de si hubieron y cuantos fueron caramelos timeProviders dentro del combo explotado*/

		swapContent(i1, j1, i2, j2);
		if (move.isValid()) {
			if(move.isTypeOfMove(CandyMove.class)) {
				int cantOfCells = move.cantOfCellsMatching(cell -> cell.isTimeProvider());

				addSeconds(cantOfCells * TIME_PER_PROVIDER_CELL);

				System.out.println("Se agregaron " + String.valueOf(cantOfCells * TIME_PER_PROVIDER_CELL) + " s");
			}


			move.removeElements();



			fallElements();
			movedOk = true;
			state().addMove();
		} else {
			swapContent(i1, j1, i2, j2);
			movedOk = false;
		}


		wasMoved = movedOk;

		if(wasMoved)
			System.out.println("Was moved");

		return movedOk;
	}

	private void addSeconds(int seconds) { remainingSeconds += seconds; }

	public static int getRemainingSeconds() { return remainingSeconds; }
	
	protected class Level3State extends Level1State {
		
		public Level3State(long requiredScore, int maxMoves) {
			super(requiredScore,maxMoves);
		}


		@Override
		public String getPrintableScore(){
			firstTime = false;
			if(wasMoved) {
				remainingSeconds++;
				wasMoved = false;
			}
			String returnableScore = "T. Restante: "+remainingSeconds + " - Puntaje: "+getScore();
			if(remainingSeconds>0 && !firstTime)
			{
					remainingSeconds--;
			}



			return returnableScore;
		}
		@Override
		public boolean gameOver() {
			return remainingSeconds==0 || playerWon();
		}
	}

}
