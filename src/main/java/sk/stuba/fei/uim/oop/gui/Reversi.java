package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.controls.GameLogic;
import sk.stuba.fei.uim.oop.game.AiPlayer;
import sk.stuba.fei.uim.oop.game.GameFlow;
import sk.stuba.fei.uim.oop.game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reversi implements ActionListener {
    private final JFrame frame;
    private GamePanel gamePanel;
    private int size = 6;
    private final Player player;
    private final AiPlayer opponent;
    private Board board;
    private GameFlow gameFlow;

    public Reversi() {
        this.frame = new JFrame();
        this.player = new Player(1);
        this.opponent = new AiPlayer(2, player);
        this.board = new Board(size, player, opponent);
        this.gamePanel = new GamePanel(board);
        this.gameFlow = new GameFlow(board);

//        for (int i = 0; i < board.getBoard().length; i++){
//        for (int j = 0; j < board.getBoard().length; j++){
//            System.out.print(board.getBoard()[i][j].getIntRepresentation());
//            System.out.print(" ");
//        }
//        System.out.println("\n");
//    }

        frame.setTitle("Reversi");
        //frame.setLocation(500, 150);

        gamePanel.setPreferredSize(new Dimension(360, 360));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new BorderLayout());
        String[] sizes = {"6x6", "8x8", "10x10", "12x12"};
        JComboBox<String> resize = new JComboBox<>(sizes);
        resize.addActionListener(this);
        JButton button = new JButton("Reset");
        button.addActionListener(this);

        frame.setResizable(false);
        //frame.getContentPane().setBackground( new Color(1, 175, 109));
        frame.setLayout(new BorderLayout());

        frame.add(gamePanel, BorderLayout.CENTER);

        //GameLogic logic = new GameLogic(board, panel,player,opponent);
        // Tile clicked = new EmptyTile(0,0);
        for (Component c : gamePanel.getComponents()) {
             new GameLogic(board, (JPanel) c, gamePanel, player, opponent, gameFlow);

        }

        menuBar.add(resize, BorderLayout.EAST);
        menuBar.add(button, BorderLayout.WEST);
        frame.add(menuBar, BorderLayout.PAGE_START);
        frame.pack();


        //System.out.println(gamePanel.getComponentCount());

        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        Dimension dimension = new Dimension(gamePanel.getWidth(), gamePanel.getHeight());

        if (e.getSource() instanceof JComboBox) {

            JComboBox comboBox1 = (JComboBox) e.getSource();
            String object = (String) comboBox1.getSelectedItem();
            //System.out.println(object);

            switch (object) {
                case "6x6":
                    size = 6;
                    dimension = new Dimension(360, 360);
                    break;
                case "8x8":
                    size = 8;
                    dimension = new Dimension(480, 480);
                    break;
                case "10x10":
                    size = 10;
                    dimension = new Dimension(600, 600);
                    break;
                case "12x12":
                    size = 12;
                    dimension = new Dimension(720, 720);
                    break;
            }

        }
        frame.remove(gamePanel);
        frame.revalidate();
        board = new Board(size, player, opponent);
        gamePanel = new GamePanel(board);
        gamePanel.setPreferredSize(dimension);
        frame.add(gamePanel);
        gameFlow = new GameFlow(board);
        for (Component c : gamePanel.getComponents()) {
            new GameLogic(board, (JPanel) c, gamePanel, player, opponent, gameFlow);

        }
        frame.pack();
    }

}
