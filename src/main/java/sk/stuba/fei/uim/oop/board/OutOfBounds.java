package sk.stuba.fei.uim.oop.board;

import java.awt.*;

public class OutOfBounds extends Tile{

    public OutOfBounds() {
        super.intRepresentation = -2;
    }

    @Override
    public void setCoordX(int coordX, int coordY){
        super.coordX = coordX;
        super.coordY = coordY;
    }
    @Override
    public void paint(Graphics g) {

    }
}
