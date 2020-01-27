package com.fekstr;

import java.util.ArrayList;

/**
 * Created by Alfred on 2020-01-27.
 */
public class King extends ChessPiece {
    King(Player color, Coordinate coordinate) {
        super(color, coordinate);
    }

    void computeValidMoves() {
        ArrayList<Coordinate> validMoves = new ArrayList<>();
        ArrayList<Coordinate> movesToTest = new ArrayList<>();

        movesToTest.add(new Coordinate(this.coordinate.getX() + 1, this.coordinate.getY()));
        movesToTest.add(new Coordinate(this.coordinate.getX() - 1, this.coordinate.getY()));
        movesToTest.add(new Coordinate(this.coordinate.getX(), this.coordinate.getY() + 1));
        movesToTest.add(new Coordinate(this.coordinate.getX(), this.coordinate.getY() - 1));
        movesToTest.add(new Coordinate(this.coordinate.getX() + 1, this.coordinate.getY() + 1));
        movesToTest.add(new Coordinate(this.coordinate.getX() - 1, this.coordinate.getY() + 1));
        movesToTest.add(new Coordinate(this.coordinate.getX() + 1, this.coordinate.getY() - 1));
        movesToTest.add(new Coordinate(this.coordinate.getX() - 1, this.coordinate.getY() - 1));


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

    void showValidMoves(Coordinate toCoordinate) {}

    boolean isValidMove(Coordinate toCoordinate) {
        return validMoves.contains(toCoordinate);

    }



    public String toString() {
        return "K " + coordinate.getX() + coordinate.getY();

    }



    ArrayList<Coordinate> getThreatenedSquares() {
        ArrayList<Coordinate> movesToTest = new ArrayList<>();
        movesToTest.add(new Coordinate(this.coordinate.getX() + 1, this.coordinate.getY()));
        movesToTest.add(new Coordinate(this.coordinate.getX() - 1, this.coordinate.getY()));
        movesToTest.add(new Coordinate(this.coordinate.getX(), this.coordinate.getY() + 1));
        movesToTest.add(new Coordinate(this.coordinate.getX(), this.coordinate.getY() - 1));
        movesToTest.add(new Coordinate(this.coordinate.getX() + 1, this.coordinate.getY() + 1));
        movesToTest.add(new Coordinate(this.coordinate.getX() - 1, this.coordinate.getY() + 1));
        movesToTest.add(new Coordinate(this.coordinate.getX() + 1, this.coordinate.getY() - 1));
        movesToTest.add(new Coordinate(this.coordinate.getX() - 1, this.coordinate.getY() - 1));
        return movesToTest;

    }
}
