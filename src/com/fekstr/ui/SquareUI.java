package com.fekstr.ui;

import com.fekstr.ChessPiece;
import com.fekstr.Coordinate;
import com.fekstr.Square;

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


    SquareUI(Square square, Color color) {
        this.backgroundColor = color;
        //this.coordinate = coordinate;
        this.square = square;
        this.piece = this.square.getPiece();

        setBorder(new LineBorder(Color.WHITE, 0));
        setBackground(this.backgroundColor);
        setOpaque(true);
        setBorderPainted(true);
        addActionListener(this);

        squareuiList.add(this);

        if(this.piece != null) {
            setText(this.piece.getClass().getSimpleName());
        } else {
            setText(null);
        }

        /* DEBUG AREA */
        setForeground(Color.RED);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(square.getPiece());
        highlightSquares();

    }

    private Square getSquare() {
        return this.square;
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

        //possibleMoves = this.piece.showValidMoves();
        //System.out.println(possibleMoves);

        /*
        for (SquareUI s: squareuiList) {
            for (Coordinate c: possibleMoves) {
                if(s.getSquare().getPiece().) {
                    s.highlight();
                }
            }
        }

         */
    }
}
