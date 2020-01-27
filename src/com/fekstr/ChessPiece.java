package com.fekstr;

import java.util.ArrayList;

public abstract class ChessPiece {
    Player color;
    Square currentSquare;
    ArrayList<Coordinate> validMoves;
    Piece type;

    ChessPiece(Player color, Square currentSquare, Piece type) {
        this.color = color;
        this.currentSquare = currentSquare;
        this.type = type;
    }

    public Piece getType() {
        return type;
    }

    public void setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
    }
    // Set coordinates to all valid squares
    abstract void computeValidMoves();

    // Return true if move is valid and false otherwise
    abstract boolean isValidMove(Coordinate toCoordinate);

    void showValidMoves(Coordinate toCoordinate) {
        // BoardUI.displayValidMoves(validMoves);
    }

    void makeMove(Coordinate toCoordinate) {
        if (isValidMove(toCoordinate)) {
             Board.handleMove(currentSquare.getCoordinate(), toCoordinate);
        } else {
            // BoardUI.setLabel("Invalid move")
        }
    };

    public Player getColor() {
        return color;
    }
    // Return squares threatened by piece
    abstract ArrayList<Coordinate> getThreatenedSquares();
    public String toString() {
        return "K";
    }

    public ArrayList<Coordinate> getValidMoves() {
        computeValidMoves();
        System.out.println(validMoves);
        return validMoves;
    }
}
