package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Element;
import game.backend.element.Nothing;
import game.backend.element.timeProviderCandy;
import game.backend.move.Direction;

import java.util.function.Predicate;

public class Cell {
	
	private Grid grid;
	private Cell[] around = new Cell[Direction.values().length];
	private Element content;
	private boolean golden;


	
	public Cell(Grid grid) {
		this.grid = grid;
		this.content = new Nothing();
		golden = false;
	}
	
	public void setAround(Cell up, Cell down, Cell left, Cell right) {
		this.around[Direction.UP.ordinal()] = up;
		this.around[Direction.DOWN.ordinal()] = down;
		this.around[Direction.LEFT.ordinal()] = left;
		this.around[Direction.RIGHT.ordinal()] = right;
	}

	public boolean hasFloor() {
		return !around[Direction.DOWN.ordinal()].isEmpty();
	}
	
	public boolean isMovable(){
		return content.isMovable();
	}
	
	public boolean isEmpty() {
		return !content.isSolid();
	}

	public Element getContent() {
		return content;
	}
	
	public void clearContent() {
		if (content.isMovable()) {
			Direction[] explosionCascade = content.explode();
			grid.cellExplosion(content);
			this.content = new Nothing();
			if (explosionCascade != null) {
				expandExplosion(explosionCascade); 
			}
			this.content = new Nothing();
		}
	}
	
	private void expandExplosion(Direction[] explosion) {
		for(Direction d: explosion) {
			this.around[d.ordinal()].explode(d);
		}
	}
	
	private void explode(Direction d) {
		clearContent();
		if (this.around[d.ordinal()] != null)
			this.around[d.ordinal()].explode(d);
	}
	
	public Element getAndClearContent() {
		if (content.isMovable()) {
			Element ret = content;
			this.content = new Nothing();
			return ret;
		}
		return null;
	}

	public boolean fallUpperContent() {
		Cell up = around[Direction.UP.ordinal()];
		if (this.isEmpty() && !up.isEmpty() && up.isMovable()) {
			this.content = up.getAndClearContent();
			grid.wasUpdated();
			if (this.hasFloor()) {
				grid.tryRemove(this);
				return true;
			} else {
				Cell down = around[Direction.DOWN.ordinal()];
				return down.fallUpperContent();
			}
		} 
		return false;
	}
	
	public void setContent(Element content) {
		this.content = content;
	}

	public boolean isGolden() {
		return golden;
	}
	public void makeGolden() {
		golden = true;
	}

	public boolean isTimeProvider() {
		/*
		* Si bien sabemos que el uso del instanceof no es de buen estilo, nos parecio la mejor forma de detectar cuando un Candy es de tipo TimeProvider, ya que al no ser esta una propiedad
		* de una celda (puesto que uno podría intercambiar un caramelo TimeProvider y cambiaría de celda) debe ser un atributo del caramelo. Sin embargo, casi todos los métodos reciben y retornan
		* Element, por lo que sería necesario agregar un atributo tipo boolean a Element que sea si es o no TimeProvider, lo cual también nos parece que no es correcto. Element debe ser lo más
		* genérico posible. Por lo tanto, decidimos hacerlo de esta forma.
		* */
		return getContent() instanceof timeProviderCandy;
	}
}
