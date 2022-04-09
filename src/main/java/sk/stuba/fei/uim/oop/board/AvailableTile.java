package sk.stuba.fei.uim.oop.board;

import lombok.Setter;

import java.awt.*;

public class AvailableTile extends Tile{

    @Setter
    private int size;

    public AvailableTile() {
        super.intRepresentation = 0;
    }
    @Override
    public void setCoordX(int coordX, int coordY){
        super.coordX = coordX;
        super.coordY = coordY;
    }

    @Override
    public void paint(Graphics g){
//        int size = getWidth();
        g.setColor(Color.BLACK);
        //g.drawRect(0,0,size,size);
        g.drawOval(coordX,coordY,size,size);
    }
}
