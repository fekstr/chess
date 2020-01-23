package com.fekstr;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

    Pawn(Player color, Coordinate coordinate) {
        super(color, coordinate);
    }

    void computeValidMoves() {
        ArrayList<Coordinate> validMoves = new ArrayList<>();
        ArrayList<Coordinate> movesToTest = new ArrayList<>();

        movesToTest.add(new Coordinate(this.coordinate.getI() + 1, this.coordinate.getJ()));

        if (Board.squareContainsEnemyPiece(new Coordinate(this.coordinate.getI() + 1, this.coordinate.getJ() + 1)))
            movesToTest.add(new Coordinate(this.coordinate.getI() + 1, this.coordinate.getJ() + 1));

        if (Board.squareContainsEnemyPiece(new Coordinate(this.coordinate.getI() + 1, this.coordinate.getJ() - 1)))
            movesToTest.add(new Coordinate(this.coordinate.getI() + 1, this.coordinate.getJ() - 1));

        if (this.coordinate.getI() == 1) {
            movesToTest.add(new Coordinate(this.coordinate.getI() + 2, this.coordinate.getJ()));
        }

        for (Coordinate move: movesToTest) {
            if (!Board.isOutsideBoard(move)
                    && !Board.squareContainsOwnPiece(move)
                    && !Board.willCreateCheck(this.coordinate, move)
                ) {
                validMoves.add(move);
            }
        }

        this.validMoves = validMoves;
    }

    boolean isValidMove(Coordinate toCoordinate) {
        return validMoves.contains(toCoordinate);
    }

    ArrayList<Coordinate> getThreatenedSquares() {

    }


}
