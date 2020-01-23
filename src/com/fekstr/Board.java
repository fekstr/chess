package com.fekstr;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Alfred on 2020-01-23.
 */
public class Board {
    private static Square[][] gameState = new Square[8][8];

    private static boolean isCheckmate;
    private static Player currentPlayer;
    private static boolean isCurrentlyCheck;


    public Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameState[i][j] = new Square();
            }
        }
        currentPlayer = Player.WHITE;
        isCheckmate = false;
        isCurrentlyCheck = false;
        //TODO: Place pieces correctly
        for (int i = 0; i < 8; i++) {
            Square currentSquare = getSquare(0, i);
            currentSquare.put(new Pawn(Player.BLACK, new Coordinate(i, 0)));
        }
    }

    private static Square getSquare(int row, int col) {
        return gameState[row][col];
    }

    public static void getThreatenedSquares() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square currentSquare = getSquare(i, j);
                ChessPiece currentPiece = currentSquare.getPiece();
                // only get threatening squares if they are threatened by other player
                if (currentPiece.color != currentPlayer) {
                    ArrayList<Coordinate> listOfThreatenedSquares = currentPiece.getThreatenedSquares();
                    for (Coordinate coordinate : listOfThreatenedSquares) {
                        getSquare(coordinate.getI(), coordinate.getJ()).setThreatened(true);
                    }
                }
            }
        }
    }

    public static void newTurn() {
        // Resets squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                getSquare(i, j).setThreatened(false);
            }
        }
        getThreatenedSquares();
    }

    public static boolean willCreateCheck(Coordinate currentCoordinate, Coordinate move) {
        ChessPiece pieceOnMoveSquare = getSquare(move.getI(), move.getJ()).getPiece();
        handleMove(currentCoordinate, move);
        getThreatenedSquares();
        boolean isChecked =  isCheck();
        revertHandleMove(currentCoordinate, move, pieceOnMoveSquare);
        return isChecked;
    }

    public static boolean isCheck() {
        // find King and see if he is on a threathend square
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square currentSquare = getSquare(j,i);
                ChessPiece currentPiece = currentSquare.getPiece();
                //TODO: Make King Piece
                /*
                if(currentPiece instanceof King && currentPiece.color == currentPlayer && currentSquare.isThreatened) {
                    isCurrentlyCheck = true;
                }
                */

            }
        }

        return false;
    }

    private static void switchPlayer() {
        if (currentPlayer == Player.WHITE) {
            currentPlayer = Player.BLACK;
        } else {
            currentPlayer = Player.WHITE;
        }
    }

    public static void handleMove(Coordinate currentPieceCoordinate, Coordinate toCurrentCoordinate) {
        Square currentSquare = gameState[currentPieceCoordinate.getJ()][currentPieceCoordinate.getI()];
        Square nextSquare = gameState[toCurrentCoordinate.getI()][toCurrentCoordinate.getJ()];
        nextSquare.put(currentSquare.getPiece());
        currentSquare.clear();
    }

    public static void revertHandleMove(Coordinate currentPieceCoordinate, Coordinate toCurrentCoordinate, ChessPiece moveSquarePiece) {
        Square currentSquare = getSquare(currentPieceCoordinate.getI(), currentPieceCoordinate.getJ());
        Square nextSquare = getSquare(toCurrentCoordinate.getI(), toCurrentCoordinate.getJ());
        currentSquare.put(nextSquare.getPiece());
        nextSquare.put(moveSquarePiece);
    }

    public static boolean isOutsideBoard(Coordinate coordinate) {
        return coordinate.getI() >= 8 ||coordinate.getJ() >= 8;
    }


    public static boolean checkIfSquareIsEmpty(Coordinate coordinates) {
        return getSquare(coordinates.getJ(), coordinates.getI()).isEmpty();
    }

    public static boolean squareContainsOwnPiece(Coordinate move) {
        Square currentSquare = getSquare(move.getI(), move.getJ());
        if (currentSquare.isEmpty()) {
            return false;
        } else {
            return currentSquare.getPiece().color == currentPlayer;
        }

    }

    public static boolean squareContainsEnemyPiece(Coordinate move) {
        Square currentSquare = getSquare(move.getI(), move.getJ());
        if (currentSquare.isEmpty()) {
            return false;
        } else {
            return currentSquare.getPiece().color != currentPlayer;
        }

    }


    public static void flipHorizontally() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8/2; x++) {
                Square tmp = getSquare(8-x-1, y);
                getSquare(x, y).setCoordinatesOfPiece(new Coordinate(8-x-1, y));
                gameState[8-x-1][y] = getSquare(x, y);
                tmp.setCoordinatesOfPiece(new Coordinate(x,y));
                gameState[x][y] = tmp;
            }
        }
    }


    public static void printBoard() {
        System.out.println(Arrays.deepToString(gameState)
                .replace("],","\n").replace(",","\t| ")
                .replaceAll("[\\[\\]]", " "));
        System.out.println("\n");
    }
    public static void main(String[] args) {
        Board board = new Board();
        System.out.println("Tudilu");
        printBoard();

    }
}
