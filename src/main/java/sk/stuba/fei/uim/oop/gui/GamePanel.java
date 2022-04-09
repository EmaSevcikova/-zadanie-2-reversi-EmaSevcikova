package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.board.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private int gameSizeInt;
    private Tile[][] board;

    public GamePanel(int gameSizeInt, Tile[][] board) {
        this.gameSizeInt = gameSizeInt;
        this.board = board;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < gameSizeInt; i++)
            for (int j = 0; j < gameSizeInt; j++) {
                g.setColor(new Color(1, 175, 109));
                g.fillRect(j * 60, i * 60, 60, 60);
                g.setColor(Color.black);
                g.drawRect(j * 60, i * 60, 60, 60);
            }
        for (int i = 1; i < board.length-1; i++) {
            for (int j = 1; j < board.length-1; j++) {
                switch (board[j+1][i+1].getIntRepresentation()) {
                    case -1:
                        break;

                    case 1:
                        g.setColor(Color.BLACK);
                        g.fillOval(5 + i * 60, 5 + j * 60, 50, 50);
                        break;
                    case 2:
                        g.setColor(Color.WHITE);
                        g.fillOval(5 + i * 60, 5 + j * 60, 50, 50);
                        break;
                    case 0:
                        //if (help.getState()) {
                        g.setColor(Color.BLUE);
                        g.drawOval(5 + i * 60, 5 + j * 60, 50, 50);
                        //}
                        break;

                }
            }
        }
    }

}
