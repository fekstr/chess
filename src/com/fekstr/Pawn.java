package com.fekstr;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

    Pawn(Player color, Square currentSquare) {
        super(color, currentSquare, Piece.PAWN);
    }

    void computeValidMoves() {
        ArrayList<Coordinate> validMoves = new ArrayList<>();
        ArrayList<Coordinate> movesToTest = new ArrayList<>();

        Coordinate coordinate = currentSquare.getCoordinate();
        // One step forward
        Coordinate t1 = new Coordinate(coordinate.getX(), coordinate.getY() + 1);
        if (!Board.isOutsideBoard(t1) && Board.checkIfSquareIsEmpty(t1))
            movesToTest.add(t1);

        // Two steps forward
        Coordinate t2 = new Coordinate(coordinate.getX(), coordinate.getY() + 2);
        if (!Board.isOutsideBoard(t2) && coordinate.getY() == 1 && Board.checkIfSquareIsEmpty(t2))
            movesToTest.add(t2);

        // Step forward and right
        Coordinate t3 = new Coordinate(coordinate.getX() + 1, coordinate.getY() + 1);
        if (!Board.isOutsideBoard(t3) && Board.squareContainsEnemyPiece(t3))
            movesToTest.add(t3);

        // Step forward and left
        Coordinate t4 = new Coordinate(coordinate.getX() - 1, coordinate.getY() + 1);
        if (!Board.isOutsideBoard(t4) && Board.squareContainsEnemyPiece(t4))
            movesToTest.add(t4);


        for (Coordinate move: movesToTest) {
            //if (!Board.squareContainsOwnPiece(move) && !Board.willCreateCheck(coordinate, move))
            if (!Board.squareContainsOwnPiece(move) && !Board.willCreateCheck(coordinate, move))
                validMoves.add(move);
        }

        this.validMoves = validMoves;
    }

    void showValidMoves(Coordinate toCoordinate) {
        // BoardUI.highlightSquares(this.validMoves)
    }

    boolean isValidMove(Coordinate toCoordinate) {
        return validMoves.contains(toCoordinate);

    }



    public String toString() {
        Coordinate coordinate = currentSquare.getCoordinate();
        return "Pawn " + coordinate.getX() + coordinate.getY();

    }



    ArrayList<Coordinate> getThreatenedSquares() {
        ArrayList<Coordinate> threatenedSquares = new ArrayList<>();
        ArrayList<Coordinate> squaresToTest = new ArrayList<>();
        Coordinate coordinate = currentSquare.getCoordinate();
        squaresToTest.add(new Coordinate(coordinate.getX() + 1, coordinate.getY() + 1));
        squaresToTest.add(new Coordinate(coordinate.getX() - 1, coordinate.getY() + 1));
        for (Coordinate c: squaresToTest) {
            if (!Board.isOutsideBoard(c) && !Board.squareContainsOwnPiece(c))
                threatenedSquares.add(c);
        }

        return threatenedSquares;
    }


}
