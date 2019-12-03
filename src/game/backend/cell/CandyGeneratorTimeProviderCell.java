package game.backend.cell;


import game.backend.Grid;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;
import game.backend.element.timeProviderCandy;

public class CandyGeneratorTimeProviderCell extends Cell {

    private int remainingTimeProviderCandies;





    public CandyGeneratorTimeProviderCell(Grid grid, int timeProviderCandies) {
        super(grid);
        remainingTimeProviderCandies = timeProviderCandies;
        //super.isTimeProvider(false);
    }

    @Override
    public boolean isMovable(){
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Element getContent() {
        int typeOfCandy = (int)(Math.random() * CandyColor.values().length);
        int timeProviderNumber = (int)(Math.random() * 20); //20% de probabilidad de que genere un providerCell
        if(remainingTimeProviderCandies > 0 && timeProviderNumber == 0) { //Elegimos al 0 como al numero que debe salir para ser timeProvider
            //super.isTimeProvider(true);
            remainingTimeProviderCandies--;
            //System.out.println("Generando un timeProvider.");
            return new timeProviderCandy(CandyColor.values()[typeOfCandy]);
        }


        return new Candy(CandyColor.values()[typeOfCandy]);
    }

    @Override
    public Element getAndClearContent() {
        return getContent();
    }

    @Override
    public boolean fallUpperContent() {
        throw new IllegalStateException();
    }

    @Override
    public void setContent(Element content) {
        throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

}

