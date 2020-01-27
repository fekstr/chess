package com.fekstr;

import java.util.ArrayList;

/**
 * Created by Alfred on 2020-01-27.
 */
public class Rook extends ChessPiece {
    Rook(Player color, Square currentSquare) {
        super(color, currentSquare, Piece.ROOK);
    }

    private ArrayList<Coordinate> getMovesToTest() {
        ArrayList<Coordinate> movesToTest = new ArrayList<>();
        Coordinate coordinate = currentSquare.getCoordinate();

        boolean containsPiece = false;
        int i = 1;
        while (!containsPiece) {
            Coordinate p = new Coordinate(coordinate.getX(), coordinate.getY() + i);
            movesToTest.add(p);
            containsPiece = Board.squareContainsEnemyPiece(p) || Board.squareContainsOwnPiece(p) || Board.isOutsideBoard(p);
            i += 1;
        }

        containsPiece = false;
        i = 1;
        while (!containsPiece) {
            Coordinate p = new Coordinate(coordinate.getX(), coordinate.getY() - i);
            movesToTest.add(p);
            containsPiece = Board.squareContainsEnemyPiece(p) || Board.squareContainsOwnPiece(p) || Board.isOutsideBoard(p);
            i += 1;
        }

        containsPiece = false;
        i = 1;
        while (!containsPiece) {
            Coordinate p = new Coordinate(coordinate.getX() + i, coordinate.getY());
            movesToTest.add(p);
            containsPiece = Board.squareContainsEnemyPiece(p) || Board.squareContainsOwnPiece(p) || Board.isOutsideBoard(p);
            i += 1;
        }

        containsPiece = false;
        i = 1;
        while (!containsPiece) {
            Coordinate p = new Coordinate(coordinate.getX() - i, coordinate.getY());
            movesToTest.add(p);
            containsPiece = Board.squareContainsEnemyPiece(p) || Board.squareContainsOwnPiece(p) || Board.isOutsideBoard(p);
            i += 1;
        }


        return movesToTest;
    }

    void computeValidMoves() {
        ArrayList<Coordinate> validMoves = new ArrayList<>();
        ArrayList<Coordinate> movesToTest = getMovesToTest();
        Coordinate coordinate = currentSquare.getCoordinate();

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

    void showValidMoves(Coordinate toCoordinate) {}

    boolean isValidMove(Coordinate toCoordinate) {
        return validMoves.contains(toCoordinate);

    }

    public String toString() {
        Coordinate coordinate = currentSquare.getCoordinate();
        return "rook " + coordinate.getX() + coordinate.getY();

    }

    ArrayList<Coordinate> getThreatenedSquares() {
        ArrayList<Coordinate> movesToTest = getMovesToTest();
        System.out.println(movesToTest);
        return movesToTest;

    }
}
