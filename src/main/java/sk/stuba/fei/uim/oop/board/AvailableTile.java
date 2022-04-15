package sk.stuba.fei.uim.oop.board;

import java.awt.*;

public class AvailableTile extends Tile {


    public AvailableTile(int coordX, int coordY) {
        super.intRepresentation = 0;
        super.coordX = coordX;
        super.coordY = coordY;
    }

    @Override
    public void paint(Graphics g){

        g.setColor(Color.BLACK);
        g.drawRect( 0, 0, 60, 60);

        g.setColor(Color.BLUE);
        g.drawOval(5 , 5 , 50, 50);
    }

    public void highlight(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect( 0, 0, 60, 60);

        g.setColor(Color.MAGENTA);
        g.fillOval(5 , 5 , 50, 50);
    }
    public void blueAgain(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect( 0, 0, 60, 60);

        g.setColor(new Color(1, 175, 109));
        g.fillOval(5 , 5 , 50, 50);
        g.setColor(Color.BLUE);
        g.drawOval(5 , 5 , 50, 50);
    }
}

