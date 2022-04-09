package sk.stuba.fei.uim.oop.board;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.board.Tile;

import java.awt.*;

public class Stone extends Tile {

    @Setter
    @Getter
    private Player player;
    @Setter
    private int size;



    public Stone(Player player ) {
        this.player = player;
        super.intRepresentation = player.getPlayerNum();
    }

    @Override
    public void setCoordX(int coordX, int coordY){
        super.coordX = coordX;
        super.coordY = coordY;
    }
    @Override
    public void paint(Graphics g){
        //int size = getWidth();
        g.setColor(Color.BLACK);
        //g.drawRect(0,0,size,size);

        if(player.getPlayerNum() == 2){
            g.setColor(Color.WHITE);
        }
        g.drawOval(0,0,size,size);
        g.fillOval(0,0,size,size);
    }
}
