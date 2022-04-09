package sk.stuba.fei.uim.oop.board;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    @Setter
    @Getter
    private int boardSize;
    @Getter
    private Tile[][] board;
    private Player player;
    private Player opponent;

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
                Arrays.fill(board[i], new EmptyTile());
                board[i][0] = new OutOfBounds();
                board[i][boardSize+1] = new OutOfBounds();
            }
            Arrays.fill(board[0], new OutOfBounds());
            Arrays.fill(board[boardSize+1], new OutOfBounds());

            int middleIndex = (boardSize+2)/2;
            board[middleIndex][middleIndex] = new Stone(player);
            player.addStone(board[middleIndex][middleIndex]);
            board[middleIndex-1][middleIndex-1] = new Stone(player);
            player.addStone(board[middleIndex-1][middleIndex-1]);
            board[middleIndex-1][middleIndex] = new Stone(opponent);
            opponent.addStone(board[middleIndex-1][middleIndex]);
            board[middleIndex][middleIndex-1] = new Stone(opponent);
            opponent.addStone(board[middleIndex][middleIndex-1]);

            board[middleIndex-1][middleIndex+1] = new AvailableTile();
            board[middleIndex-2][middleIndex] = new AvailableTile();
            board[middleIndex][middleIndex-2] = new AvailableTile();
            board[middleIndex+1][middleIndex-1] = new AvailableTile();

    }
    public void paintBoard(Graphics2D g) {


        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++)  {
                this.board[i][j].paint(g);
            }
        }

    }
}
