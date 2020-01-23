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

    // Return coordinates to all valid squares
    abstract ArrayList<Coordinate> getValidMoves();

    // Return true if move is valid and false otherwise
    abstract boolean isValidMove(Coordinate toCoordinates);

    void showValidMoves(Coordinate toCoordinate) {
        ArrayList<Coordinate> validMoves = getValidMoves();
        // BoardUI.displayValidMoves(validMoves);
    }

    void makeMove(Coordinate toCoordinate) {
        if (isValidMove(toCoordinate)) {
             Board.handleMove(coordinate, toCoordinate);
        } else {
            // BoardUI.setLabel("Invalid move")
        }
    };

    // Return squares threatened by piece
    abstract ArrayList<int[]> getThreatenedSquares();
    public String toString() {
        return "K";
    }
}
