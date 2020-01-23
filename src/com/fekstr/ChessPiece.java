package com.fekstr;

import java.util.ArrayList;

public abstract class ChessPiece {
    String color;
    int[] coordinates = new int[2];

    // Return coordinates to all valid squares
    abstract ArrayList<int[]> checkValidMoves();

    // Return true if move is valid and false otherwise
    abstract boolean isValidMove(int[] toCoordinates);

    void makeMove(int[] toCoordinates) {
        if (isValidMove(toCoordinates)) {
            // Board.makeMove(coordinates, toCoordinates)
        } else {
            // BoardUI.setLabel("Invalid move")
        }
    };

    // Return squares threatened by piece
    abstract ArrayList<int[]> getThreatenedSquares();

}
