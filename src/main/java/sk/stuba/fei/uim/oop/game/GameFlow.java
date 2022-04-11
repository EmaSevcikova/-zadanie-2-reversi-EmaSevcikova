package sk.stuba.fei.uim.oop.game;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.*;

import java.util.*;

public class GameFlow {

    private Tile[][] board;
    @Getter
    private HashMap<Tile, List<Tile>> possibleMoves;
    @Getter
    private HashMap<Tile, List<Tile>> aiPlayerMoves;


    public GameFlow(Board board) {
        this.board = board.getBoard();
        this.possibleMoves = new HashMap<>();
        initialMoves();
    }

    private void initialMoves(){
        int mIndex = board.length/2;
        possibleMoves.put(this.board[mIndex - 1][mIndex + 1], List.of(this.board[mIndex - 1][mIndex]));
        possibleMoves.put(this.board[mIndex - 2][mIndex], List.of(this.board[mIndex - 1][mIndex]));
        possibleMoves.put(this.board[mIndex + 1][mIndex - 1], List.of(this.board[mIndex][mIndex-1]));
        possibleMoves.put(this.board[mIndex][mIndex - 2], List.of(this.board[mIndex][mIndex - 1]));
    }
    public void playerMove(int x, int y,Player player, Player opponent) {
        for (Tile tile : possibleMoves.get(board[x][y])) {
            System.out.println(x + " " + y);
            board[(tile.getCoordX())][(tile.getCoordY())] = new Stone(player, (tile.getCoordX()), (tile.getCoordY()));
            board[x][y] = new Stone(player, x, y);

        }
    }
    private boolean isMovePossible(int coordX, int coordY, Player opponent){
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (board[coordX+i][coordY+j].getIntRepresentation() == opponent.getPlayerNum()){
                    return true;
                }
            }
        }
        return false;
    }
    private List<Tile> moveFromEmptyCells(Player opponent){
        List<Tile> possibleMoves = new ArrayList<>();
        for (int i = 1; i < board.length-1; i++){
            for (int j = 1; j < board.length-1; j++){
                if(board[i][j] instanceof EmptyTile && isMovePossible(i,j,opponent)){
                    possibleMoves.add(new EmptyTile(i,j));
                }
            }
        }
        return possibleMoves;
    }
    public void findPossibleMoves(Player player, Player opponent){
        this.possibleMoves = new HashMap<>();
        List<Tile> possibleCells = moveFromEmptyCells(opponent);
        List<Tile> opponentStones = new ArrayList<>();
        Move moves = new Move(board,player,opponent);
        for (Tile tile : possibleCells) {
            if (!moves.up(tile).isEmpty()){
                for (Tile stone: moves.up(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
                //possibleMoves.put(tile,moves.up(tile));
            }
            if (!moves.upLeft(tile).isEmpty()){
                for (Tile stone: moves.upLeft(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
                //possibleMoves.put(tile,moves.upLeft(tile));
            }
            if (!moves.upRight(tile).isEmpty()){
                for (Tile stone: moves.upRight(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
                //possibleMoves.put(tile,moves.upRight(tile));
            }
            if (!moves.down(tile).isEmpty()){
                for (Tile stone: moves.down(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
                //possibleMoves.put(tile,moves.down(tile));
            }
            if (!moves.downLeft(tile).isEmpty()){
                for (Tile stone: moves.downLeft(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
                //possibleMoves.put(tile,moves.downLeft(tile));
            }
            if (!moves.downRight(tile).isEmpty()){
                for (Tile stone: moves.downRight(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
                //possibleMoves.put(tile,moves.downRight(tile));
            }
            if (!moves.left(tile).isEmpty()){
                for (Tile stone: moves.left(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
                //possibleMoves.put(tile,moves.left(tile));
            }
            if (!moves.right(tile).isEmpty()){
                for (Tile stone: moves.right(tile)) {
                    Collections.addAll(opponentStones,stone);
                }
                //possibleMoves.put(tile,moves.right(tile));
            }
            if (!opponentStones.isEmpty()){
                possibleMoves.put(tile,opponentStones);
            }
        }
    }
    public void updateCells(){
        for (Tile tile: possibleMoves.keySet()) {
            board[tile.getCoordX()][tile.getCoordY()] = new AvailableTile(tile.getCoordX(),tile.getCoordY());
        }
    }
    public void resetAvailable(){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (board[i][j] instanceof AvailableTile){
                    board[i][j] = new EmptyTile(i,j);
                }
            }
        }

    }
    public void changeStones(Tile tile, Player player){
        List<Tile> chosenMove = possibleMoves.get(tile);
        for (Tile stone : chosenMove) {
            int x = stone.getCoordX();
            int y = stone.getCoordY();
            board[x][y] = new Stone(player,x,y);
        }
    }

}
