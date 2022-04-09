package sk.stuba.fei.uim.oop.board;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.awt.*;

public class EmptyTile extends Tile {

    @Setter
    private int size;

    public EmptyTile(){
        super.intRepresentation = -1;
    }

    @Override
    public void setCoordX(int coordX, int coordY){
        super.coordX = coordX;
        super.coordY = coordY;
    }
    @Override
    public void paint(Graphics g) {
//        int size = getWidth();
//        int height = getHeight();
        g.setColor(Color.GREEN);
        g.drawRect(0,0,90,90);
    }
}
