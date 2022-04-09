package sk.stuba.fei.uim.oop.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.*;


public abstract class Tile  {

    @Getter
    protected int coordX;
    @Getter
    protected int coordY;
    @Getter
    protected int intRepresentation;


    public abstract void setCoordX(int coordX, int coordY);

    public abstract void paint(Graphics g);


}
