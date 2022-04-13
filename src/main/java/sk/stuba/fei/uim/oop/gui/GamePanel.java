package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.board.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel(Board board) {
        updatePanel(board);
    }

    public void updatePanel(Board board){
        this.setLayout(new GridLayout(board.getBoardSize(),board.getBoardSize()));
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++)  {
                this.add(board.getBoard()[i+1][j+1]);
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(1, 175, 109));
        g.fillRect(0,0,getWidth(),getHeight());

    }

}
