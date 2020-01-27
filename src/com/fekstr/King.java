package com.fekstr;

import java.util.ArrayList;

/**
 * Created by Alfred on 2020-01-27.
 */
public class King extends ChessPiece {
    King(Player color, Square currentSquare) {
        super(color, currentSquare);
    }

    void computeValidMoves() {
        ArrayList<Coordinate> validMoves = new ArrayList<>();
        ArrayList<Coordinate> movesToTest = new ArrayList<>();
        Coordinate coordinate = currentSquare.getCoordinate();
        movesToTest.add(new Coordinate(coordinate.getX() + 1, coordinate.getY()));
        movesToTest.add(new Coordinate(coordinate.getX() - 1, coordinate.getY()));
        movesToTest.add(new Coordinate(coordinate.getX(), coordinate.getY() + 1));
        movesToTest.add(new Coordinate(coordinate.getX(), coordinate.getY() - 1));
        movesToTest.add(new Coordinate(coordinate.getX() + 1, coordinate.getY() + 1));
        movesToTest.add(new Coordinate(coordinate.getX() - 1, coordinate.getY() + 1));
        movesToTest.add(new Coordinate(coordinate.getX() + 1, coordinate.getY() - 1));
        movesToTest.add(new Coordinate(coordinate.getX() - 1, coordinate.getY() - 1));


        for (Coordinate move: movesToTest) {
            if (!Board.isOutsideBoard(move)
                    && !Board.squareContainsOwnPiece(move)
                    && !Board.willCreateCheck(coordinate, move)
                    ) {
                validMoves.add(move);
            }
        }

        this.validMoves = validMoves;
    }

    boolean isValidMove(Coordinate toCoordinate) {
        return validMoves.contains(toCoordinate);

    }



    public String toString() {
        Coordinate coordinate = currentSquare.getCoordinate();
        return "K " + coordinate.getX() + coordinate.getY();

    }



    ArrayList<Coordinate> getThreatenedSquares() {
        ArrayList<Coordinate> movesToTest = new ArrayList<>();
        Coordinate coordinate = currentSquare.getCoordinate();
        movesToTest.add(new Coordinate(coordinate.getX() + 1, coordinate.getY()));
        movesToTest.add(new Coordinate(coordinate.getX() - 1, coordinate.getY()));
        movesToTest.add(new Coordinate(coordinate.getX(), coordinate.getY() + 1));
        movesToTest.add(new Coordinate(coordinate.getX(), coordinate.getY() - 1));
        movesToTest.add(new Coordinate(coordinate.getX() + 1, coordinate.getY() + 1));
        movesToTest.add(new Coordinate(coordinate.getX() - 1, coordinate.getY() + 1));
        movesToTest.add(new Coordinate(coordinate.getX() + 1, coordinate.getY() - 1));
        movesToTest.add(new Coordinate(coordinate.getX() - 1, coordinate.getY() - 1));
        return movesToTest;

    }

    public ArrayList<Coordinate> getValidMoves() {
        return null;
    }
}
