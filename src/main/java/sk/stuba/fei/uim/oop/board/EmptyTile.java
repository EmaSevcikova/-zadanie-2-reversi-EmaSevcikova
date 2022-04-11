package sk.stuba.fei.uim.oop.board;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.awt.*;

public class EmptyTile extends Tile {

    @Setter
    private int size;

    public EmptyTile(int coordX, int coordY){
        super.intRepresentation = -1;
        super.coordX = coordX;
        super.coordY = coordY;
    }

    @Override
    public void setCoordX(int coordX, int coordY){
        super.coordX = coordX;
        super.coordY = coordY;
    }

//    @Override
//    public void paint(Graphics g, int i, int j) {
////        g.setColor(new Color(1, 175, 109));
////        g.fillRect(j * 60, i * 60, 60, 60);
//        g.setColor(Color.BLACK);
//        g.drawRect(j * 60, i * 60, 60, 60);
//    }
@Override
public void paint(Graphics g) {
//        g.setColor(new Color(1, 175, 109));
//        g.fillRect(j * 60, i * 60, 60, 60);
    g.setColor(Color.BLACK);
    g.drawRect( 0,  0, 60, 60);
}
}


