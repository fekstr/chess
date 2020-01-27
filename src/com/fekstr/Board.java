package com.fekstr;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Created by Alfred on 2020-01-23.
 */
public class Board {
    private static Square[][] gameState = new Square[8][8];

    private static boolean isCheckmate;
    private static Player currentPlayer;
    private static boolean isCurrentlyCheck;


    public Board() {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                gameState[x][y] = new Square(new Coordinate(x, y));
            }
        }

        currentPlayer = Player.WHITE;
        isCheckmate = false;
        isCurrentlyCheck = false;
        setUpBoard(Player.WHITE);
        flip();
        setUpBoard(Player.BLACK);
        flip();

        getThreatenedSquares();
    }

    private void setUpBoard(Player forPlayer) {

        for (int x = 0; x < 8; x++) {
            Square currentSquare = getSquare(x, 1);
            currentSquare.put(new Pawn(forPlayer, currentSquare));
        }



        if (forPlayer == Player.BLACK) {
            Square currentSquare = getSquare(3, 0);
            currentSquare.put(new King(forPlayer, currentSquare));
            currentSquare = getSquare(4, 0);
            currentSquare.put(new Queen(forPlayer, currentSquare));
        } else {
            Square currentSquare = getSquare(4, 0);
            currentSquare.put(new King(forPlayer, currentSquare));
            currentSquare = getSquare(3, 0);
            currentSquare.put(new Queen(forPlayer, currentSquare));
        }


        Square currentSquare = getSquare(1, 0);
        currentSquare.put(new Knight(forPlayer, currentSquare));

        currentSquare = getSquare(6, 0);
        currentSquare.put(new Knight(forPlayer, currentSquare));


        currentSquare = getSquare(5, 0);
        currentSquare.put(new Bishop(forPlayer, currentSquare));

        currentSquare = getSquare(2, 0);
        currentSquare.put(new Bishop(forPlayer, currentSquare));


        currentSquare = getSquare(7, 0);
        currentSquare.put(new Rook(forPlayer, currentSquare));

        currentSquare = getSquare(0, 0);
        currentSquare.put(new Rook(forPlayer, currentSquare));
        
    }


    public static Square getSquare(int x, int y) {
        if (isOutsideBoard(new Coordinate(x,y))) {
            return null;
        }
        return gameState[x][y];

    }

    private static void getThreatenedSquares() {
        // TODO: Fix bug where all threatened squares are not marked
        flip();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square currentSquare = getSquare(x, y);
                ChessPiece currentPiece = currentSquare.getPiece();
                // only get threatening squares if they are threatened by other player
                if (!currentSquare.isEmpty() && currentPiece.color != currentPlayer) {
                    ArrayList<Coordinate> listOfThreatenedSquares = currentPiece.getThreatenedSquares();

                    for (Coordinate coordinate : listOfThreatenedSquares) {
                        if (!isOutsideBoard(coordinate)) {
                            getSquare(coordinate.getX(), coordinate.getY()).setThreatened(true);
                        }

                    }
                }
            }
        }
        flip();
    }

    public static void newTurn() {
        // Resets squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //getSquare(i, j).setThreatened(false);
            }
        }
        getThreatenedSquares();
    }

    public static boolean willCreateCheck(Coordinate currentCoordinate, Coordinate move) {
        ChessPiece pieceOnMoveSquare = getSquare(move.getX(), move.getY()).getPiece();
        handleMove(currentCoordinate, move);
        clearThreathend();
        switchPlayer();
        flip();
        getThreatenedSquares();


        boolean isChecked =  isCheck();
        revertHandleMove(currentCoordinate, move, pieceOnMoveSquare);
        System.out.println("is Checked? " + isChecked);
        return isChecked;
    }

    public static boolean isCheck() {
        // find King and see if he is on a threatened square
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square currentSquare = getSquare(x, y);
                ChessPiece currentPiece = currentSquare.getPiece();
                if (!currentSquare.isEmpty() && currentPiece.getType() == Piece.KING) {
                    System.out.println("King Square of curr player " + currentPlayer);
                }
                if(currentPiece instanceof King && currentPiece.color == currentPlayer && currentSquare.isThreatened) {
                    System.out.println("INSIEDE KING CHECK");
                    isCurrentlyCheck = true;
                    return true;
                }
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

    private static void clearThreathend() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                getSquare(x,y).setThreatened(false);
            }
        }

    }

    public static String handleMove(Coordinate currentPieceCoordinate, Coordinate toCurrentCoordinate) {
        String status = "";

        // set current Square
        Square currentSquare = gameState[currentPieceCoordinate.getX()][currentPieceCoordinate.getY()];
        Player player = currentSquare.getPlayer();
        String playerString;

        if (player == Player.WHITE) {
            playerString = "WHITE";
        } else {
            playerString = "BLACK";
        }

        Square nextSquare = gameState[toCurrentCoordinate.getX()][toCurrentCoordinate.getY()];

        if (currentSquare.getPiece().getType() == Piece.PAWN && toCurrentCoordinate.getY() == 7) {
            // Promotion
            nextSquare.put(new Queen(currentPlayer, nextSquare));
            status = playerString + " promoted a pawn to queen";
        } else {
            nextSquare.put(currentSquare.getPiece());
        }

        currentSquare.clear();
        switchPlayer();
        flip();
        clearThreathend();
        getThreatenedSquares();


        System.out.println(status);
        return status;
    }

    public static String makeMove(Coordinate currentPieceCoordinate, Coordinate toCurrentCoordinate) {
        // Handle move and check if checkmate

        String status = "";

        handleMove(currentPieceCoordinate, toCurrentCoordinate);

        checkIfCheckmate();
        if (isCheckmate) {
            status = "Game over";
        }

        System.out.println(status);
        return status;

    }

    private static void checkIfCheckmate() {
        isCheckmate = true;

        Player player = getCurrentPlayer();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square currentSquare = getSquare(x, y);
                if (currentSquare.getPiece() != null && currentSquare.getPiece().getColor() == player) {
                    ArrayList<Coordinate> currentValidMoves = currentSquare.getValidMoves();
                    if (currentValidMoves.size() > 0) {
                        isCheckmate = false;
                        break;
                    }
                }
            }
        }
    }


    public static void revertHandleMove(Coordinate currentPieceCoordinate, Coordinate toCurrentCoordinate, ChessPiece moveSquarePiece) {
        Square currentSquare = getSquare(currentPieceCoordinate.getX(), currentPieceCoordinate.getY());
        Square nextSquare = getSquare(toCurrentCoordinate.getX(), toCurrentCoordinate.getY());
        currentSquare.put(nextSquare.getPiece());
        nextSquare.put(moveSquarePiece);

    }

    public static boolean isOutsideBoard(Coordinate coordinate) {
        return coordinate.getX() >= 8 ||coordinate.getY() >= 8 || coordinate.getX() < 0 || coordinate.getY() < 0;
    }


    public static boolean checkIfSquareIsEmpty(Coordinate coordinates) {
        return getSquare(coordinates.getX(), coordinates.getY()).isEmpty();
    }

    public static boolean squareContainsOwnPiece(Coordinate move) {
        if (isOutsideBoard(move)) return false;
        Square currentSquare = getSquare(move.getX(), move.getY());
        if (currentSquare.isEmpty()) {
            return false;
        } else {
            return currentSquare.getPiece().color == currentPlayer;
        }

    }

    public static boolean squareContainsEnemyPiece(Coordinate move) {
        if (isOutsideBoard(move)) return false;
        Square currentSquare = getSquare(move.getX(), move.getY());
        if (currentSquare.isEmpty()) {
            return false;
        } else {
            return currentSquare.getPiece().color != currentPlayer;
        }

    }

    private static void flipHorizontally() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8/2; x++) {
                Square tmp = getSquare(8-x-1, y);
                getSquare(x,y).setCoordinate(new Coordinate(8-x-1, y));
                gameState[8-x-1][y] = getSquare(x, y);
                tmp.setCoordinate(new Coordinate(x, y));
                gameState[x][y] = tmp;
            }
        }
    }

    private static void flipVertically() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8/2; y++) {
                Square tmp = getSquare(x, 8 - y - 1);
                getSquare(x,y).setCoordinate(new Coordinate(x, 8 - y - 1));
                gameState[x][8 - y - 1] = getSquare(x, y);
                tmp.setCoordinate(new Coordinate(x,y));
                gameState[x][y] = tmp;
            }
        }
    }

    private static void flip() {
        flipHorizontally();
        flipVertically();
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }


}
