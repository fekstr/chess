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

public class Square_outdated extends JButton implements ActionListener {

    private ChessPiece piece = null;
    private Coordinate coordinate;
    private Player currentPlayer;
    public boolean isThreatened;
    public boolean hasPiece = false;

    // Image of piece

    private static ArrayList<Square_outdated> squareList = new ArrayList<Square_outdated>();

    private ArrayList<Coordinate> moves;

    private Color backgroundColor;



    public Square_outdated(Color color) { //, Coordinate coordinate
        this.backgroundColor = color;
        //this.coordinate = coordinate;

        setBorder(new LineBorder(Color.WHITE, 0));
        setBackground(this.backgroundColor);
        setOpaque(true);
        setBorderPainted(true);
        addActionListener(this);
        setForeground(Color.RED);


    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(getPiece());
        highlightSquares();

    }

    private Coordinate getCoordinate() {
        return this.coordinate;
    }

    private void highlightSquares() {
        setBackground(Color.lightGray);

        /*moves = this.piece.getValidMoves();
        for (Square s: squareList) {
            for (Coordinate c: moves) {
                if(s.getCoordinate().equals(c)) {
                    s.backgroundColor.darker();
                }
            }
        }*/
    }


    public void put(ChessPiece piece) {
        // Should only occur at an attack
        if(hasPiece) {

        } else {
            hasPiece = !hasPiece;
            this.piece = piece;

            setText(piece.getClass().getSimpleName());
        }

    }

    public ChessPiece getPiece() {
        return this.piece;
    }

    String getPos() {
        return String.format("(%d, %d)",coordinate.getI(),coordinate.getJ());
    }

    public void clear() {
        piece = null;
    }

    public boolean isEmpty() {
        return piece == null;
    }
}
