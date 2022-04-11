package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.board.*;

import java.util.ArrayList;
import java.util.List;

public class Move {

    Tile[][] board;
    Player player;
    Player opponent;

    public Move(Tile[][] board, Player player, Player opponent) {
        this.board = board;
        this.player = player;
        this.opponent = opponent;
    }

    public List<Tile> up(Tile tile){
        int x = tile.getCoordX();
        int y = tile.getCoordY();
        List<Tile> direction = new ArrayList<>();
        List<Tile> empty = new ArrayList<>();
        while (board[x-1][y].getIntRepresentation() == opponent.getPlayerNum()){
            direction.add(new Stone(opponent,x-1,y));
            x--;
            if (board[x-1][y].getIntRepresentation() == player.getPlayerNum()){
                return direction;
            }
        }
            return empty;
    }

    public List<Tile> upRight(Tile tile){
        int x = tile.getCoordX();
        int y = tile.getCoordY();
        List<Tile> direction = new ArrayList<>();
        List<Tile> empty = new ArrayList<>();
        while (board[x-1][y+1].getIntRepresentation() == opponent.getPlayerNum()){
            direction.add(new Stone(opponent,x-1,y+1));
            x--;
            y++;
            if (board[x-1][y+1].getIntRepresentation() == player.getPlayerNum()){
                return direction;
            }
        }
            return empty;
    }

    public List<Tile> right(Tile tile){
        int x = tile.getCoordX();
        int y = tile.getCoordY();
        List<Tile> direction = new ArrayList<>();
        List<Tile> empty = new ArrayList<>();
        while (board[x][y+1].getIntRepresentation() == opponent.getPlayerNum()){
            direction.add(new Stone(opponent,x,y+1));
            y++;
            if (board[x][y+1].getIntRepresentation() == player.getPlayerNum()){
                return direction;
            }
        }
            return empty;
    }

    public List<Tile> downRight(Tile tile){
        int x = tile.getCoordX();
        int y = tile.getCoordY();
        List<Tile> direction = new ArrayList<>();
        List<Tile> empty = new ArrayList<>();
        while (board[x+1][y+1].getIntRepresentation() == opponent.getPlayerNum()){
            direction.add(new Stone(opponent,x+1,y+1));
            x++;
            y++;
            if (board[x+1][y+1].getIntRepresentation() == player.getPlayerNum()){
                return direction;
            }
        }
            return empty;
    }

    public List<Tile> down(Tile tile){
        int x = tile.getCoordX();
        int y = tile.getCoordY();
        List<Tile> direction = new ArrayList<>();
        List<Tile> empty = new ArrayList<>();
        while (board[x+1][y].getIntRepresentation() == opponent.getPlayerNum()){
            direction.add(new Stone(opponent,x+1,y));
            x++;
            if (board[x+1][y].getIntRepresentation() == player.getPlayerNum()){
                return direction;
            }
        }
            return empty;
    }

    public List<Tile> downLeft(Tile tile){
        int x = tile.getCoordX();
        int y = tile.getCoordY();
        List<Tile> direction = new ArrayList<>();
        List<Tile> empty = new ArrayList<>();
        while (board[x+1][y-1].getIntRepresentation() == opponent.getPlayerNum()){
            direction.add(new Stone(opponent,x+1,y-1));
            x++;
            y--;
            if (board[x+1][y-1].getIntRepresentation() == player.getPlayerNum()){
                return direction;
            }
        }
            return empty;
    }

    public List<Tile> left(Tile tile){
        int x = tile.getCoordX();
        int y = tile.getCoordY();
        List<Tile> direction = new ArrayList<>();
        List<Tile> empty = new ArrayList<>();
        while (board[x][y-1].getIntRepresentation() == opponent.getPlayerNum()){
            direction.add(new Stone(opponent,x,y-1));
            y--;
            if (board[x][y-1].getIntRepresentation() == player.getPlayerNum()){
                return direction;
            }
        }
            return empty;
    }

    public List<Tile> upLeft(Tile tile){
        int x = tile.getCoordX();
        int y = tile.getCoordY();
        List<Tile> direction = new ArrayList<>();
        List<Tile> empty = new ArrayList<>();
        while (board[x-1][y-1].getIntRepresentation() == opponent.getPlayerNum()){
            direction.add(new Stone(opponent,x-1,y-1));
            x--;
            y--;
            if (board[x-1][y-1].getIntRepresentation() == player.getPlayerNum()){
                return direction;
            }
        }
            return empty;
    }

}
