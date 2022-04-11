package sk.stuba.fei.uim.oop.controls;

import sk.stuba.fei.uim.oop.board.*;

import lombok.*;
import sk.stuba.fei.uim.oop.game.GameFlow;
import sk.stuba.fei.uim.oop.game.Player;
import sk.stuba.fei.uim.oop.gui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;



public class GameLogic extends UniversalAdapter{

    private Board board;
    private GamePanel wholeBoard;
    private JPanel panel;
    @Getter
    private Tile tileClicked;
    private GameFlow gameFlow;
    private Player player;
    private Player opponent;

    public GameLogic(Board board, JPanel panel, GamePanel wholeBoard, Player player, Player opponent,GameFlow gameFlow){
        this.board = board;
        this.panel = panel;
        this.wholeBoard = wholeBoard;
        this.player = player;
        this.opponent = opponent;
        this.gameFlow =gameFlow;

//        for (Tile[] col: board.getBoard()){
//            for (Tile row : col) {
//                row.addM
//            }
//        }
        panel.addMouseListener(this);
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        if (panel.getComponentAt(e.getPoint()) instanceof AvailableTile){
            ((AvailableTile) panel.getComponentAt(e.getPoint())).highlight(panel.getGraphics());
            System.out.println("pink");
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof AvailableTile){
            ((AvailableTile) e.getSource()).blueAgain(panel.getGraphics());
            //panel.repaint();
            System.out.println(e.getSource());
            System.out.println("blue again");
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
//        int x = e.getX();
//        int y = e.getY();
//        System.out.println(panel.getComponentAt(e.getPoint()));
//        System.out.println(e.getPoint());
        JPanel b = (JPanel) e.getSource();
        Rectangle r = b.getBounds();
        Point p = b.getLocation();
        int row = p.y / r.height;
        int col = p.x / r.width;

        System.out.println(p.x + " " + p.y);
        System.out.println((row+1) + " " + (col+1));
        if (board.getBoard()[row+1][col+1] instanceof AvailableTile) {

            wholeBoard.removeAll();
            wholeBoard.revalidate();
            gameFlow.playerMove(row+1, col+1, player, opponent);
            gameFlow.resetAvailable();
            gameFlow.findPossibleMoves(player,opponent);
            gameFlow.getPossibleMoves().forEach((key, value) -> System.out.println(key + " " + value));

            gameFlow.updateCells();
            wholeBoard.updatePanel(board,player,opponent,gameFlow);
            wholeBoard.revalidate();
            wholeBoard.repaint();

            for (int i = 0; i < board.getBoard().length; i++){
                for (int j = 0; j < board.getBoard().length; j++){
                    System.out.print(board.getBoard()[i][j].getIntRepresentation());
                    System.out.print(" ");
                }
                System.out.println("\n");
            }


        }


    }




}
