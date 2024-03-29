package game.backend.move;

import game.backend.Grid;
import game.backend.cell.Cell;
import game.backend.element.Element;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Predicate;

public abstract class Move {
	
	private Grid grid;
	protected int i1, j1, i2, j2;
	
	public Move(Grid grid) {
		this.grid = grid;
	}
	
	public void setCoords(int i1, int j1, int i2, int j2){
		this.i1 = i1;
		this.j1 = j1;
		this.i2 = i2;
		this.j2 = j2;
	}

	public boolean validHorizontalMove() {
		return (i1 == i2 && Math.abs(j1-j2) == 1);
	}

	public boolean validVerticalMove() {
		return (j1 == j2 && Math.abs(i1-i2) == 1);
	}
	
	public boolean isValid() {
		//Agregamos los metodos isHorizontalMove y isVerticalMove para mejorar el estilo y por necesidad del nivel 2
		if ( validHorizontalMove() || validVerticalMove() ) {
			return internalValidation();
		}
		return false;
	}

	public boolean isTypeOfMove(Class<?> clazz) {
		return clazz.equals(this.getClass());
	}
	
	protected boolean internalValidation() {
		return true;
	}
	
	protected Element get(int i, int j) {
		return grid.get(i, j);
	}
	
	protected void clearContent(int i, int j) {
		grid.clearContent(i, j);
	}
	
	protected void setContent(int i, int j, Element e){
		grid.setContent(i, j, e);
	}
	
	protected void wasUpdated(){
		grid.wasUpdated();
	}
	
	public abstract void removeElements();

	public int cantOfCellsMatching(Predicate<Cell> condition) { throw new NotImplementedException(); }

}
