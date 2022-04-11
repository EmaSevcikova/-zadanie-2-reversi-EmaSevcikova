package sk.stuba.fei.uim.oop.board;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.game.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Board  {

    @Setter
    @Getter
    private int boardSize;
    @Getter
    private Tile[][] board;
    private final Player player;
    private final Player opponent;

    public Board(int boardSize, Player player, Player opponent) {
        this.boardSize = boardSize;
        this.board = new Tile[boardSize][boardSize];
        this.player = player;
        this.opponent = opponent;
        this.fillBoard();
    }

    private void fillBoard(){
            this.board = new Tile[boardSize+2][boardSize+2];
            for (int i = 0; i < boardSize+1; i++){
                for (int j = 0; j < boardSize+1; j++){
                    board[i][j] = new EmptyTile(i,j);
                    board[i][0] = new OutOfBounds();
                    board[i][boardSize+1] = new OutOfBounds();
                }
                //Arrays.fill(board[i], new EmptyTile());
                Arrays.fill(board[0], new OutOfBounds());
                Arrays.fill(board[boardSize+1], new OutOfBounds());
            }

            int middleIndex = (boardSize+2)/2;
            board[middleIndex][middleIndex] = new Stone(player,middleIndex,middleIndex);
            player.addStone(board[middleIndex][middleIndex]);
            board[middleIndex-1][middleIndex-1] = new Stone(player,middleIndex-1, middleIndex-1);
            player.addStone(board[middleIndex-1][middleIndex-1]);
            board[middleIndex-1][middleIndex] = new Stone(opponent,middleIndex-1, middleIndex);
            opponent.addStone(board[middleIndex-1][middleIndex]);
            board[middleIndex][middleIndex-1] = new Stone(opponent,middleIndex,middleIndex-1);
            opponent.addStone(board[middleIndex][middleIndex-1]);

            board[middleIndex-1][middleIndex+1] = new AvailableTile(middleIndex-1,middleIndex+1);
            board[middleIndex-2][middleIndex] = new AvailableTile(middleIndex-2,middleIndex);
            board[middleIndex][middleIndex-2] = new AvailableTile(middleIndex,middleIndex-2);
            board[middleIndex+1][middleIndex-1] = new AvailableTile(middleIndex+1,middleIndex-1);

    }

    public void paintBoard(Graphics g) {

//        for (int i = 0; i < boardSize; i++){
//            for (int j = 0; j < boardSize; j++) {
//                g.setColor(new Color(1, 175, 109));
//                g.fillRect(j * 60, i * 60, 60, 60);
//                g.setColor(Color.BLACK);
//                g.drawRect(j * 60, i * 60, 60, 60);
//            }
//    }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++)  {
//                if (this.board[i+1][j+1] instanceof Stone || this.board[i+1][j+1] instanceof AvailableTile){
//                    g.setColor(new Color(1, 175, 109));
//                    g.fillRect(j * 60, i * 60, 60, 60);
//                    g.setColor(Color.BLACK);
//                    g.drawRect(j * 60, i * 60, 60, 60);
//                }
                this.board[i+1][j+1].paint(g);
            }
        }

    }
}
