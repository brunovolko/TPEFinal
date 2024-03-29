package game.backend.cell;


import game.backend.Grid;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;
import game.backend.element.timeProviderCandy;

public class CandyGeneratorTimeProviderCell extends CandyGeneratorCell {

    private int remainingTimeProviderCandies;





    public CandyGeneratorTimeProviderCell(Grid grid, int timeProviderCandies) {
        super(grid);
        remainingTimeProviderCandies = timeProviderCandies;
    }


    @Override
    public Element getContent() {
        int typeOfCandy = (int)(Math.random() * CandyColor.values().length);
        int timeProviderNumber = (int)(Math.random() * 20); // probabilidad de que genere un providerCell
        if(remainingTimeProviderCandies > 0 && timeProviderNumber == 0) { //Elegimos al 0 como al numero que debe salir para ser timeProvider
            remainingTimeProviderCandies--;
            return new timeProviderCandy(CandyColor.values()[typeOfCandy]);
        }
        return new Candy(CandyColor.values()[typeOfCandy]);
    }

}

