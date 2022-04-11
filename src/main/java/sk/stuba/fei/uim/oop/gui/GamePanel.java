package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.board.*;
import sk.stuba.fei.uim.oop.controls.GameLogic;
import sk.stuba.fei.uim.oop.game.GameFlow;
import sk.stuba.fei.uim.oop.game.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final int gameSizeInt;
    private final Board board;

    public GamePanel(int gameSizeInt, Board board) {
        this.gameSizeInt = gameSizeInt;
        this.board = board;
        this.setLayout(new GridLayout(gameSizeInt,gameSizeInt));
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++)  {
                this.add(board.getBoard()[i+1][j+1]);
            }
        }
    }
    public void updatePanel(Board board, Player player, Player opponent, GameFlow gameFlow){
        this.setLayout(new GridLayout(board.getBoardSize(),board.getBoardSize()));
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++)  {
                this.add(board.getBoard()[i+1][j+1]);
                GameLogic logic = new GameLogic(board,board.getBoard()[i+1][j+1],this,player, opponent, gameFlow);

            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //this.setLayout(new GridLayout(gameSizeInt,gameSizeInt));
        g.setColor(new Color(1, 175, 109));
        g.fillRect(0,0,getWidth(),getHeight());


        //this.setLayout(new GridLayout(gameSizeInt,gameSizeInt));
        //board.paintBoard(g);



//        for (int i = 0; i < gameSizeInt; i++)
//            for (int j = 0; j < gameSizeInt; j++) {
//                this.board.paint(g);
//                g.setColor(new Color(1, 175, 109));
//                g.fillRect(j * 60, i * 60, 60, 60);
//                g.setColor(Color.black);
//                g.drawRect(j * 60, i * 60, 60, 60);
//            }

//        for (int i = 1; i < board.getBoardSize()-1; i++) {
//            for (int j = 1; j < board.getBoardSize()-1; j++) {
//                switch (board.getBoard()[j+1][i+1].getIntRepresentation()) {
//                    case -1:
//                        break;
//                    case 1:
//                        g.setColor(Color.BLACK);
//                        g.fillOval(5 + i * 60, 5 + j * 60, 50, 50);
//                        break;
//                    case 2:
//                        g.setColor(Color.WHITE);
//                        g.fillOval(5 + i * 60, 5 + j * 60, 50, 50);
//                        break;
//                    case 0:
//                        g.setColor(Color.BLUE);
//                        g.drawOval(5 + i * 60, 5 + j * 60, 50, 50);
//                        break;
//
//                }
//            }
//        }
    }

}
