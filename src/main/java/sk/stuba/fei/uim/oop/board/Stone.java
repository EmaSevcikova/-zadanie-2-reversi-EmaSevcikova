package sk.stuba.fei.uim.oop.board;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.game.Player;

import java.awt.*;

public class Stone extends Tile {

    @Setter
    @Getter
    private Player player;


    public Stone(Player player,int coordX, int coordY ) {
        this.player = player;
        super.intRepresentation = player.getPlayerNum();
        super.coordX = coordX;
        super.coordY = coordY;
    }


//    @Override
//    public void paint(Graphics g, int i, int j){
////        g.setColor(new Color(1, 175, 109));
////        g.fillRect(j * 60, i * 60, 60, 60);
//        g.setColor(Color.BLACK);
//        g.drawRect(j * 60, i * 60, 60, 60);
//        //g.setColor(Color.BLACK);
//
//        if(player.getPlayerNum() == 2){
//            g.setColor(Color.WHITE);
//        }
//        g.fillOval(5 + i * 60, 5 + j * 60, 50, 50);
//    }

    @Override
    public void paint(Graphics g){
//        g.setColor(new Color(1, 175, 109));
//        g.fillRect(j * 60, i * 60, 60, 60);
        g.setColor(Color.BLACK);
        g.drawRect( 0,  0, 60, 60);
        //g.setColor(Color.BLACK);

        if(player.getPlayerNum() == 2){
            g.setColor(Color.WHITE);
        }
        g.fillOval(5 , 5 , 50, 50);
    }


}

