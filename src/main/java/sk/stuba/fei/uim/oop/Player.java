package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.board.*;

import java.util.ArrayList;
import java.util.List;

public class Player {

    @Setter
    @Getter
    private int playerNum;
    private final List<Tile> playerStones;

    public Player(int playerNum) {
        this.playerNum = playerNum;
        this.playerStones = new ArrayList<>();
    }
    public void addStone(Tile stone){
        playerStones.add(stone);
    }
}
