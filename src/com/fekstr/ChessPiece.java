package com.fekstr;

import java.util.ArrayList;

public abstract class ChessPiece {
    Player color;
    Coordinate coordinate;

    ChessPiece(Player color, Coordinate coordinate) {
        this.color = color;
        this.coordinate = coordinate;
    }

    // Return coordinates to all valid squares
    abstract ArrayList<int[]> getValidMoves();

    // Return true if move is valid and false otherwise
    abstract boolean isValidMove(Coordinate toCoordinates);

    void makeMove(Coordinate toCoordinates) {
        if (isValidMove(toCoordinates)) {
             Board.handleMove(coordinate, toCoordinates);
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
