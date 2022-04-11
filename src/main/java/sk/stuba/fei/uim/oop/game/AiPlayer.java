package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.board.Stone;
import sk.stuba.fei.uim.oop.board.Tile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AiPlayer extends Player{

    private HashMap<Tile, List<Tile>> aiPlayerMoves;
    private GameFlow gameFlow;

    public AiPlayer(int playerNum, GameFlow gameFlow, Player opponent) {
        super(playerNum);
        this.gameFlow = gameFlow;
        this.aiPlayerMoves = new HashMap<>();
    }

    public void aiMove(HashMap<Tile, List<Tile>> possibleMoves, Tile[][] board){
        int max = 0;
        Tile key = new Stone(this,0,0);
        for (Map.Entry<Tile,List<Tile>> entry: possibleMoves.entrySet()) {
            if (entry.getValue().size() > max){
                max = entry.getValue().size();
                key = entry.getKey();
            }
        }
        board[key.getCoordX()][key.getCoordY()] = new Stone(this, key.getCoordX(), key.getCoordY());
        for (Tile tile: possibleMoves.get(key)) {
            board[tile.getCoordX()][tile.getCoordY()] = new Stone(this, tile.getCoordX(),tile.getCoordY());
        }
    }
}
