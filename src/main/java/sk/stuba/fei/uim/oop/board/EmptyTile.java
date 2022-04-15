package sk.stuba.fei.uim.oop.board;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.awt.*;

public class EmptyTile extends Tile {

    public EmptyTile(int coordX, int coordY){
        super.intRepresentation = -1;
        super.coordX = coordX;
        super.coordY = coordY;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect( 0,  0, 60, 60);
    }
}


