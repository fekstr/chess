package com.fekstr;

import java.util.ArrayList;

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
    }

    private static Square getSquare(int col, int row) {
        return gameState[col][row];
    }

    public static void getThreatenedSquares() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square currentSquare = getSquare(i, j);
                ChessPiece currentPiece = currentSquare.getPiece();
                // only get threatening squares if they are threatened by other player
                if (currentPiece.color != currentPlayer) {
                    ArrayList<int[]> listOfThreatenedSquares = currentPiece.getThreatenedSquares();
                    for (int[] coordinate : listOfThreatenedSquares) {
                        getSquare(coordinate[0], coordinate[1]).setThreatened(true);
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

    public static boolean isCheck() {
        // find King and see if he is on a threathend square
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square currentSquare = getSquare(i,j);
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
    public static void handleMove(int[] currentPieceCoordinate, int[] toCurrentCoordinate) {
        Square currentSquare = gameState[currentPieceCoordinate[0]][currentPieceCoordinate[1]];
        Square nextSquare = gameState[toCurrentCoordinate[0]][toCurrentCoordinate[1]];
        nextSquare.put(currentSquare.getPiece());
        currentSquare.clear();
    }
}
