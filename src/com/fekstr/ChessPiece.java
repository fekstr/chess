package com.fekstr;

import java.util.ArrayList;

public abstract class ChessPiece {
    Player color;
    Coordinate coordinate;
    ArrayList<Coordinate> validMoves;

    ChessPiece(Player color, Coordinate coordinate) {
        this.color = color;
        this.coordinate = coordinate;
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
             Board.handleMove(coordinate, toCoordinate);
             this.coordinate = toCoordinate;
        } else {
            // BoardUI.setLabel("Invalid move")
        }
    };

    // Return squares threatened by piece
    abstract ArrayList<Coordinate> getThreatenedSquares();
    public String toString() {
        return "K";
    }
}
