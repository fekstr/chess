package com.fekstr.ui;

import com.fekstr.*;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SquareUI extends JButton implements ActionListener {

    private Square square;
    private ChessPiece piece;
    private Color backgroundColor;
    private static ArrayList<SquareUI> squareuiList = new ArrayList<SquareUI>();
    private ArrayList<Coordinate> possibleMoves = new ArrayList<Coordinate>();
    public boolean highlighted;
    public static boolean activePlay = false;


    SquareUI(Square square, Color color) {
        this.backgroundColor = color;
        //this.coordinate = coordinate;
        this.square = square;
        this.highlighted = false;

        setBorder(new LineBorder(Color.WHITE, 0));
        setBackground(this.backgroundColor);
        setOpaque(true);
        setBorderPainted(true);
        addActionListener(this);

        squareuiList.add(this);

        if(!square.isEmpty()) {

            // debug
            if (square.getPiece().getColor() == Player.WHITE) {
                setForeground(Color.WHITE);
            } else {
                setForeground(Color.BLACK);
            }
            setText(square.getPiece().getClass().getSimpleName());
        } else {
            setText(null);
        }

        /* DEBUG AREA */
        //setForeground(Color.RED);
        if (square.isThreatened) {
            setBorder(new LineBorder(Color.RED, 1));
        }
    }

    public void actionPerformed(ActionEvent e) {

        // Click on piece or highlighted square
        if(!this.square.isEmpty() || this.isHighlighted()) {

            Coordinate c = square.getCoordinate();


            // A piece has previously been selected
            if(!activePlay && Board.getCurrentPlayer() == this.square.getPlayer()) {
                activePlay = true;
                ArrayList<Coordinate> validMoves = square.getValidMoves();

                if (validMoves.size() == 0) {
                    activePlay = false;
                    System.out.println("No valid moves");
                } else {
                    BoardUI.highlightValidMoves(validMoves);
                    for (Coordinate cc : validMoves) {
                        System.out.println(cc);
                    }

                    BoardUI.setCurrentCoordinate(c);
                }

            }

            // No piece has been selected
            else if (activePlay && BoardUI.getCurrentCoordinate() != null && this.isHighlighted()) {
                System.out.println("Triggered");
                Board.makeMove(BoardUI.getCurrentCoordinate(), c);
                BoardUI.setCurrentCoordinate(null);

                BoardUI.renderBoard();

                activePlay = false;
            }

            else {
                activePlay = false;
                BoardUI.resetHighlight();
            }


            System.out.println("that worked");
        } else {
            // Reset on click outside of highlight
            activePlay = false;
            BoardUI.resetHighlight();

        }

//        System.out.println(square.getCoordinate());
//        System.out.println(square.getPiece());
//        highlightSquares();

    }

    private Square getSquare() {
        return this.square;
    }

    public void highlightGreen() {
        this.highlighted = !this.highlighted;
        setBackground(Color.GREEN);

    }

    public void resetBackgroundColor() {
        setBackground(this.backgroundColor);
    }

    public boolean isHighlighted() {
        return this.highlighted;
    }


    private void highlight() {
        if(backgroundColor.equals(Color.WHITE)) {
            setBackground(Color.lightGray);
        } else {
            setBackground(Color.darkGray);
        }

    }

    private void highlightSquares() {
        highlight();


    }
}
