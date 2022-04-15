package sk.stuba.fei.uim.oop.game;

import lombok.Getter;
import lombok.Setter;


public class Player {

    @Setter
    @Getter
    private int playerNum;


    public Player(int playerNum) {
        this.playerNum = playerNum;
    }
}
