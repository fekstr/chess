package com.fekstr;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

    Pawn(Player color, Coordinate coordinate) {
        super(color, coordinate);
    }

    ArrayList<Coordinate> getValidMoves() {
        ArrayList<Coordinate> validMoves = new ArrayList<>();

        ArrayList<Coordinate> movesToTest = new ArrayList<>();

        movesToTest.add(new Coordinate(this.coordinate.getX(), this.coordinate.getY() + 1));
        if (this.coordinate.getY() == 1) {
            movesToTest.add(new Coordinate(this.coordinate.getX(), this.coordinate.getY() + 2));
        }

        for (Coordinate move: movesToTest) {
            if (Board.checkIfSquareIsEmpty(move)) {
                validMoves.add(move);
            }
        }

        this.validMoves = validMoves;
        return null;
    }

    void showValidMoves(Coordinate toCoordinate) {}

    public boolean isValidMove(Coordinate toCoordinates) {
        return false;
    }

    public ArrayList<int[]> getThreatenedSquares() {
        return null;
    }

    public String toString() {
        return "P " + coordinate.getX() + coordinate.getY();
    }
}
