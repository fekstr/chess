package com.fekstr.ui;
import com.fekstr.Board;

import javax.swing.*;
import java.awt.*;

public class BoardUI extends JFrame {
    //private ArrayList board;

    private JPanel mainPanel;
    private JPanel chessPanel;
    private JPanel controlPanel;
    private Board gameState;

    BoardUI() {
        initialize();
    }

    private final void initialize() {
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
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if ((j + i) % 2 == 0) {
                    clr = Color.WHITE;
                } else {
                    clr = Color.BLACK;
                }
                JButton square = new Square(clr);

                chessPanel.add(square);

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
    public static void main(String[] args) {
        BoardUI app = new BoardUI();
    }
}
