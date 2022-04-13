package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.board.AvailableTile;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.controls.UniversalAdapter;
import sk.stuba.fei.uim.oop.game.GameFlow;
import sk.stuba.fei.uim.oop.game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Reversi extends UniversalAdapter {

    private final JFrame frame;
    private GamePanel gamePanel;
    private int size;
    private final Player player;
    private final Player opponent;
    private Board board;
    private GameFlow gameFlow;
    private JMenuBar bottomMenu;

    public Reversi() {
        this.frame = new JFrame();
        this.size = 6;
        this.player = new Player(1);
        this.opponent = new Player(2);
        this.board = new Board(size, player, opponent);
        this.gamePanel = new GamePanel(board);
        this.gameFlow = new GameFlow(board);
        this.bottomMenu = new JMenuBar();
        bottomMenu.setLayout(new BorderLayout());
        bottomMenu.add(new JLabel("board size: 6x6"),BorderLayout.WEST);


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
             //new GameLogic(board, (JPanel) c, gamePanel, player, opponent, gameFlow);
            c.addMouseListener(this);

        }

        menuBar.add(resize, BorderLayout.EAST);
        menuBar.add(button, BorderLayout.WEST);
        menuBar.add(new JLabel("   player: Black"),BorderLayout.CENTER);
        frame.add(menuBar, BorderLayout.PAGE_START);
        frame.add(bottomMenu, BorderLayout.PAGE_END);
        frame.pack();



        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        Dimension dimension = new Dimension(gamePanel.getWidth(), gamePanel.getHeight());

        if (e.getSource() instanceof JComboBox) {

            JComboBox comboBox1 = (JComboBox) e.getSource();
            String object = (String) comboBox1.getSelectedItem();

            switch (object) {
                case "6x6":
                    size = 6;
                    dimension = new Dimension(360, 360);
                    bottomMenu.removeAll();
                    bottomMenu.add(new JLabel("board size: 6x6"),BorderLayout.WEST);
                    break;
                case "8x8":
                    size = 8;
                    dimension = new Dimension(480, 480);
                    bottomMenu.removeAll();
                    bottomMenu.add(new JLabel("board size: 8x8"),BorderLayout.WEST);
                    break;
                case "10x10":
                    size = 10;
                    dimension = new Dimension(600, 600);
                    bottomMenu.removeAll();
                    bottomMenu.add(new JLabel("board size: 10x10"),BorderLayout.WEST);
                    break;
                case "12x12":
                    size = 12;
                    dimension = new Dimension(720, 720);
                    bottomMenu.removeAll();
                    bottomMenu.add(new JLabel("board size: 12x12"),BorderLayout.WEST);
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
            c.addMouseListener(this);
        }
        frame.pack();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof AvailableTile) {
            ((AvailableTile) e.getSource()).highlight(((AvailableTile) e.getSource()).getGraphics());
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof AvailableTile) {
            ((AvailableTile) e.getSource()).blueAgain(((AvailableTile) e.getSource()).getGraphics());
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel b = (JPanel) e.getSource();
        Rectangle r = b.getBounds();
        Point p = b.getLocation();
        int row = p.y / r.height;
        int col = p.x / r.width;

        if (board.getBoard()[row+1][col+1] instanceof AvailableTile) {

            gamePanel.removeAll();
            gamePanel.revalidate();

            gameFlow.playerMove(row+1, col+1, player);
            board.resetAvailable();

            gameFlow.aiMove(opponent,player);
            gameFlow.assignPossibleMoves(player,opponent);

            gameFlow.updateCells();
            if (gameFlow.checkEndOfGame()){
                gameFlow.checkWinner(player,opponent);
                if (gameFlow.getWinner().getPlayerNum() == 1){
                    bottomMenu.add(new JLabel("you win!!!   "),BorderLayout.EAST);
                }
                else {
                    bottomMenu.add(new JLabel("your opponent wins  "),BorderLayout.EAST);
                }
            }


            gamePanel.updatePanel(board);
            for (Component panel: gamePanel.getComponents()) {
                panel.addMouseListener(this);
            }
            gamePanel.revalidate();
            gamePanel.repaint();

        }
    }
    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){

            case KeyEvent.VK_ESCAPE:
                frame.dispose();
                break;

            case KeyEvent.VK_R:
                frame.remove(gamePanel);
                frame.revalidate();
                board = new Board(size, player, opponent);
                gamePanel = new GamePanel(board);
                gamePanel.setPreferredSize(new Dimension(gamePanel.getWidth(),gamePanel.getHeight()));
                frame.add(gamePanel);
                gameFlow = new GameFlow(board);

                for (Component c : gamePanel.getComponents()) {
                    c.addMouseListener(this);
                }
                frame.pack();
                break;
        }
    }

}
