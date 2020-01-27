package com.fekstr;

import java.util.ArrayList;

/**
 * Created by Alfred on 2020-01-27.
 */
public class Knight extends ChessPiece {
    Knight(Player color, Square currentSquare) {
        super(color, currentSquare);
    }

    private ArrayList<Coordinate> getMovesToTest() {
        ArrayList<Coordinate> movesToTest = new ArrayList<>();
        Coordinate coordinate = currentSquare.getCoordinate();
        movesToTest.add(new Coordinate(coordinate.getX() + 1, coordinate.getY() + 2));
        movesToTest.add(new Coordinate(coordinate.getX() - 1, coordinate.getY() + 2));

        movesToTest.add(new Coordinate(coordinate.getX() + 2, coordinate.getY() + 1));
        movesToTest.add(new Coordinate(coordinate.getX() - 2, coordinate.getY() + 1));

        movesToTest.add(new Coordinate(coordinate.getX() + 2, coordinate.getY() - 1));
        movesToTest.add(new Coordinate(coordinate.getX() - 2, coordinate.getY() - 1));

        movesToTest.add(new Coordinate(coordinate.getX() + 1, coordinate.getY() - 2));
        movesToTest.add(new Coordinate(coordinate.getX() - 1, coordinate.getY() - 2));
        return movesToTest;
    }

    void computeValidMoves() {
        ArrayList<Coordinate> validMoves = new ArrayList<>();
        ArrayList<Coordinate> movesToTest = getMovesToTest();

        for (Coordinate move: movesToTest) {
            if (!Board.isOutsideBoard(move)
                    && !Board.squareContainsOwnPiece(move)
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
        Coordinate coordinate = currentSquare.getCoordinate();
        return "Knight " + coordinate.getX() + coordinate.getY();

    }

    ArrayList<Coordinate> getThreatenedSquares() {
        ArrayList<Coordinate> movesToTest = getMovesToTest();

        return movesToTest;

    }

}
