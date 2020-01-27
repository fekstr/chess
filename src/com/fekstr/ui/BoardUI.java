package com.fekstr.ui;
import com.fekstr.Board;
import com.fekstr.Coordinate;
import com.fekstr.Square;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardUI extends JFrame {

    private JPanel mainPanel;
    private JPanel chessPanel;
    private JPanel controlPanel;
    private Board board;
    private static SquareUI[][] uiSquares = new SquareUI[8][8];

    BoardUI() {
        initialize();
    }

    private final void initialize() {
        board = new Board();

        System.out.println("Init..");
        mainPanel = new JPanel();
        chessPanel = new JPanel();
        controlPanel = new JPanel();

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        controlPanel.setBackground(Color.LIGHT_GRAY);
        chessPanel.setBackground(Color.WHITE);
        

        chessPanel.setLayout(new GridLayout(8,8));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.weighty = 1;
        c.weightx = 1;
        c.gridy = 0;
        mainPanel.add(chessPanel,c);

        Color clr;
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                if ((x + y) % 2 == 0) {
                    clr = Color.decode("#D18B47");
                } else {
                    clr = Color.decode("#FFCE9E");
                }
                Square square = board.getSquare(x,y);
                SquareUI squareui = new SquareUI(square,clr);
                uiSquares[x][y] = squareui;
                chessPanel.add(squareui);

            }
        }

        c.gridy = 1;
        c.weighty = 0.05;

        controlPanel.add(new JButton("Start"));
        controlPanel.add(new JButton("XXXXX"));
        controlPanel.add(new JLabel("Status: XX"));

        controlPanel.setSize(400,50);

        mainPanel.add(controlPanel,c);

        setContentPane(mainPanel);
        setSize(400,500);
        setMinimumSize(new Dimension(400,450));
        setLocationRelativeTo(null);
        setLocationByPlatform(true);
        setResizable(false);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void renderBoard() {

    }

    private static SquareUI getUISquare(Coordinate coordinate) {
        return uiSquares[coordinate.getX()][coordinate.getY()];
    }

    public static void highlightValidMoves(ArrayList<Coordinate> validMoves) {
        for (Coordinate c: validMoves) {
            SquareUI square = getUISquare(c);
            square.highlightGreen();
        }
    }

    public static void resetHighlight() {
        for(SquareUI[] s: uiSquares) {
            for(SquareUI ss: s) {
                ss.resetBackgroundColor();
            }

        }
    }

    public static void main(String[] args) {
        BoardUI app = new BoardUI();
    }
}
