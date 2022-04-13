package sk.stuba.fei.uim.oop.game;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.*;

import java.util.*;

public class GameFlow {

    private Tile[][] board;
    @Getter
    private HashMap<Tile, List<Tile>> playerMoves;
    @Getter
    private HashMap<Tile, List<Tile>> aiPlayerMoves;
    @Getter
    private Player winner;


    public GameFlow(Board board) {
        this.board = board.getBoard();
        this.playerMoves = new HashMap<>();
        initialMoves();
    }

    private void initialMoves(){
        int mIndex = board.length/2;
        playerMoves.put(this.board[mIndex - 1][mIndex + 1], List.of(this.board[mIndex - 1][mIndex]));
        playerMoves.put(this.board[mIndex - 2][mIndex], List.of(this.board[mIndex - 1][mIndex]));
        playerMoves.put(this.board[mIndex + 1][mIndex - 1], List.of(this.board[mIndex][mIndex-1]));
        playerMoves.put(this.board[mIndex][mIndex - 2], List.of(this.board[mIndex][mIndex - 1]));
    }

    public void playerMove(int x, int y,Player player) {

        if (passMove(playerMoves)) {
            for (Tile tile : playerMoves.get(board[x][y])) {
                board[(tile.getCoordX())][(tile.getCoordY())] = new Stone(player, (tile.getCoordX()), (tile.getCoordY()));
                board[x][y] = new Stone(player, x, y);
            }
        }
        else{
            System.out.println("player passed");
        }

    }

    public void aiMove(Player aiplayer, Player opponent){
        int max = 0;
        this.aiPlayerMoves = findPossibleMoves(aiplayer,opponent);

        if (passMove(aiPlayerMoves)) {
            Tile key = new Stone(aiplayer, 0, 0);
            for (Map.Entry<Tile, List<Tile>> entry : this.aiPlayerMoves.entrySet()) {
                if (entry.getValue().size() > max) {
                    max = entry.getValue().size();
                    key = entry.getKey();
                }
            }
            board[key.getCoordX()][key.getCoordY()] = new Stone(aiplayer, key.getCoordX(), key.getCoordY());
            for (Tile tile : this.aiPlayerMoves.get(key)) {
                board[tile.getCoordX()][tile.getCoordY()] = new Stone(aiplayer, tile.getCoordX(), tile.getCoordY());
            }
            aiPlayerMoves.clear();
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
                    possibleMoves.add(board[i][j]);
                }
            }
        }
        return possibleMoves;
    }

    public void assignPossibleMoves(Player player, Player opponent){
        this.playerMoves = findPossibleMoves(player,opponent);
    }
    private HashMap<Tile, List<Tile>> findPossibleMoves(Player player, Player opponent){
        HashMap<Tile, List<Tile>> possibleMoves = new HashMap<>();
        List<Tile> possibleCells = moveFromEmptyCells(opponent);
        Move moves = new Move(board,player,opponent);
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
                possibleMoves.put(new AvailableTile(tile.getCoordX(),tile.getCoordY()),opponentStones);
            }
        }
        return possibleMoves;
    }

    public void updateCells(){
        for (Tile tile: playerMoves.keySet()) {
            board[tile.getCoordX()][tile.getCoordY()] = tile;
        }
    }

    private boolean passMove(HashMap<Tile, List<Tile>> moves){
        return !moves.isEmpty();
    }

    public boolean checkEndOfGame() {
        return this.playerMoves.isEmpty() && this.aiPlayerMoves.isEmpty();
    }
    public void checkWinner(Player player, Player opponent){
        if (checkEndOfGame()){
            int black = 0;
            int white = 0;
            for (Tile[] row: board) {
                for (Tile tile : row) {
                    if (tile instanceof Stone){
                        if (((Stone) tile).getPlayer().getPlayerNum() == 1){
                            black++;
                        }
                        else {
                            white++;
                        }
                    }

                }

            }
            if (black > white){
                winner = player;
            }
            else {
               winner = opponent;
            }
        }
    }
}
