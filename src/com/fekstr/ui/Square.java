package com.fekstr.ui;
import com.fekstr.ChessPiece;
import com.fekstr.Coordinate;
import com.fekstr.Player;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.ArrayList;

public class Square extends JButton implements ActionListener {

    private ChessPiece piece = null;
    private Coordinate coordinate;
    private Player currentPlayer;
    public boolean isThreatened;
    public boolean hasPiece = false;

    private static ArrayList<Square> squareList = new ArrayList<Square>();

    private ArrayList<Coordinate> moves;

    private Color backgroundColor;



    Square(Color color) { //, Coordinate coordinate
        this.backgroundColor = color;
        //this.coordinate = coordinate;

        setBorder(new LineBorder(Color.WHITE, 0));
        setBackground(this.backgroundColor);
        setOpaque(true);
        setBorderPainted(true);

    }

    public void actionPerformed(ActionEvent e) {
        /*
        if(currentPlayer == this.piece.getColor() && !isEmpty()) {
            highlightSquares();
        }

         */
    }

    private Coordinate getCoordinate() {
        return this.coordinate;
    }

    private void highlightSquares() {
        this.backgroundColor.darker();
        /*
        moves = this.piece.getValidMoves();
        for (Square s: squareList) {
            for (Coordinate c: moves) {
                if(s.getCoordinate().equals(c)) {
                    s.backgroundColor.darker();
                }
            }
        }

         */
    }

    /*
    void put(ChessPiece piece) {
        // Should only occur at an attack
        if(hasPiece) {

        } else {
            hasPiece = !hasPiece;
            this.piece = piece;
        }

    }

    String getPiece() {
        return this.piece.getPiece();
    }

    String getPos() {
        return String.format("(%d, %d)",coordinate.getI(),coordinate.getJ());
    }

    void clear() {
        piece = null;
    }

    boolean isEmpty() {
        return piece == null;
    }

     */
}
