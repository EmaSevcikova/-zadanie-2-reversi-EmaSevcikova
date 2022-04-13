package sk.stuba.fei.uim.oop.board;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;


public abstract class Tile extends JPanel{

    @Getter
    protected int coordX;
    @Getter
    protected int coordY;
    @Getter
    protected int intRepresentation;


    public abstract void paint(Graphics g);

}
