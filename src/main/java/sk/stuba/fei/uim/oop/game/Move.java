package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.board.*;

import java.util.ArrayList;
import java.util.List;

public class Move {

    private final Tile[][] board;
    private final Player player;
    private final Player opponent;

    public Move(Tile[][] board, Player player, Player opponent) {
        this.board = board;
        this.player = player;
        this.opponent = opponent;
    }

    public List<Tile> move(Tile tile, Direction d){
        int x = tile.getCoordX();
        int y = tile.getCoordY();
        x += d.getX();
        y += d.getY();
        List<Tile> direction = new ArrayList<>();
        List<Tile> empty = new ArrayList<>();
        while (board[x][y].getIntRepresentation() == opponent.getPlayerNum()){
            direction.add(board[x][y]);
            x += d.getX();
            y += d.getY();
            if (board[x][y].getIntRepresentation() == player.getPlayerNum()){
                return direction;
            }
        }
        return empty;
    }

}
