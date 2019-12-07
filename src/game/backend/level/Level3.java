package game.backend.level;

import game.backend.GameState;
import game.backend.cell.CandyGeneratorTimeProviderCell;
import game.backend.move.CandyMove;
import game.backend.move.Move;

public class Level3 extends Level1 {
	
	private final static int REQUIRED_SCORE = 550;
	private final static int MAX_MOVES = 20;
	private final static int TIME_PROVIDER_CANDIES = 10;
	public final static int INITIAL_TIME_LIMIT = 10;
	public final static int TIME_PER_PROVIDER_CELL = 10; //En segundos

	private static int remainingSeconds;

	private boolean wasMoved;

	private boolean firstTime = true;







	public Level3() {
		super();
		candyGenCell = new CandyGeneratorTimeProviderCell(this, TIME_PROVIDER_CANDIES);
		remainingSeconds = INITIAL_TIME_LIMIT;
		wasMoved = false;
	}


	
	@Override
	protected GameState newState() {
		return new Level3State(REQUIRED_SCORE, MAX_MOVES);
	}
	private void addSecondsIfTimeProvider(Move move){
		if(move.isTypeOfMove(CandyMove.class))
			addSeconds(move.cantOfCellsMatching(cell -> cell.isTimeProvider())*TIME_PER_PROVIDER_CELL);

	}
	@Override
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		Move move = moveMaker.getMove(i1, j1, i2, j2); /*Ejecutamos el getMove por separado ya que necesitamos hacer un chequeo de si hubieron y
														cuantos fueron caramelos timeProviders dentro del combo explotado*/

		/*addSecondsIfTimeProvider(move);

		if(move.isValid())
			System.out.println("is valid");
		else
			System.out.println("No is valid");
			//addSecondsIfTimeProvider(move);


		wasMoved = super.tryMove(i1, j1, i2, j2);

		if(wasMoved)
			System.out.println("Was moved");


		return wasMoved;*/

		boolean movedOk;





		swapContent(i1, j1, i2, j2);
		if (move.isValid()) {
			addSecondsIfTimeProvider(move);
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
			StringBuilder toReturn = new StringBuilder();
			toReturn.append("T.Restante:");
			toReturn.append(remainingSeconds);
			toReturn.append(" - Puntaje: ");
			toReturn.append(getScore());
			if(remainingSeconds>0 && !firstTime)
			{
					remainingSeconds--;
			}
			return toReturn.toString();
		}
		@Override
		public boolean gameOver() {
			return remainingSeconds==0 || playerWon();
		}
	}

}
