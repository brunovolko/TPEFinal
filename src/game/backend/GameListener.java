package game.backend;

import game.backend.element.Element;

public interface GameListener {
	
	void gridUpdated();
	
	void cellExplosion(Element e);

	void timeUpdated(int remainingSeconds);
	
}