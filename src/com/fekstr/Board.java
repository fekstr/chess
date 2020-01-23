package com.fekstr;

/**
 * Created by Alfred on 2020-01-23.
 */
public class Board {
    private static Square[][] gameState = new Square[8][8];
    private boolean isCheckmate;
    enum Player {
        WHITE,
        BLACK
    }
    private Player currentPlayer;

    public Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameState[i][j] = new Square();
            }
        }
        currentPlayer = Player.WHITE;
        isCheckmate = false;
        //TODO: Place pieces correctly
    }




    private void switchPlayer() {
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
