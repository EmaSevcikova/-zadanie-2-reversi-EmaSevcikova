package sk.stuba.fei.uim.oop.game;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.*;

import java.util.*;

public class AiPlayer extends Player{

    private Player opponent;
    @Getter
    private HashMap<Tile, List<Tile>> aiPlayerMoves;

    public AiPlayer(int playerNum, Player opponent) {
        super(playerNum);
        this.aiPlayerMoves = new HashMap<>();
        this.opponent = opponent;

    }

    public void aiMove(Tile[][] board){
        int max = 0;
        findPossibleMoves(board);
        Tile key = new Stone(this,0,0);
        for (Map.Entry<Tile,List<Tile>> entry: this.aiPlayerMoves.entrySet()) {
            if (entry.getValue().size() > max){
                max = entry.getValue().size();
                key = entry.getKey();
            }
        }
        board[key.getCoordX()][key.getCoordY()] = new Stone(this, key.getCoordX(), key.getCoordY());
        //System.out.println(key.getCoordX() + " " + key.getCoordY());
        for (Tile tile: this.aiPlayerMoves.get(key)) {
            board[tile.getCoordX()][tile.getCoordY()] = new Stone(this, tile.getCoordX(),tile.getCoordY());
            //System.out.println(tile.getCoordX() + " " + tile.getCoordY());
        }
    }
    private boolean isMovePossible(int coordX, int coordY, Player opponent, Tile[][] board){
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (board[coordX+i][coordY+j].getIntRepresentation() == opponent.getPlayerNum()){
                    return true;
                }
            }
        }
        return false;
    }
    private List<Tile> moveFromEmptyCells( Tile[][] board){
        List<Tile> possibleMoves = new ArrayList<>();
        for (int i = 1; i < board.length-1; i++){
            for (int j = 1; j < board.length-1; j++){
                if(board[i][j] instanceof EmptyTile && isMovePossible(i,j,opponent, board)){
                    possibleMoves.add(board[i][j]);
                }
            }
        }
        return possibleMoves;
    }
    public void findPossibleMoves( Tile[][] board){
        this.aiPlayerMoves = new HashMap<>();
        List<Tile> possibleCells = moveFromEmptyCells(board);
        Move moves = new Move(board,this,opponent);
        for (Tile tile : possibleCells) {
            List<Tile> opponentStones = new ArrayList<>();
            if (!moves.up(tile).isEmpty()){
                for (Tile stone: moves.up(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
            }
            if (!moves.upLeft(tile).isEmpty()){
                for (Tile stone: moves.upLeft(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
            }
            if (!moves.upRight(tile).isEmpty()){
                for (Tile stone: moves.upRight(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
            }
            if (!moves.down(tile).isEmpty()){
                for (Tile stone: moves.down(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
            }
            if (!moves.downLeft(tile).isEmpty()){
                for (Tile stone: moves.downLeft(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
            }
            if (!moves.downRight(tile).isEmpty()){
                for (Tile stone: moves.downRight(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
            }
            if (!moves.left(tile).isEmpty()){
                for (Tile stone: moves.left(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
            }
            if (!moves.right(tile).isEmpty()){
                for (Tile stone: moves.right(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
            }
            if (!opponentStones.isEmpty()){
                aiPlayerMoves.put(new AvailableTile(tile.getCoordX(),tile.getCoordY()),opponentStones);
            }
        }
    }

}
