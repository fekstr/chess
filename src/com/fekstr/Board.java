package com.fekstr;

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
                gameState[x][y] = new Square();
            }
        }
        currentPlayer = Player.WHITE;
        isCheckmate = false;
        isCurrentlyCheck = false;
        //TODO: Place pieces correctly

        // Black
        /*for (int x = 0; x < 8; x++) {
            Square currentSquare = getSquare(x, 6);
            currentSquare.put(new Pawn(Player.BLACK, new Coordinate(x, 6)));
        }




        for (int x = 0; x < 8; x++) {
            Square currentSquare = getSquare(x, 1);
            currentSquare.put(new Pawn(Player.WHITE, new Coordinate(x, 1)));
        }*/

        setUpBoard(Player.WHITE);
        flipHorizontally();
        setUpBoard(Player.BLACK);
        flipHorizontally();

        getThreatenedSquares();
    }

    private void setUpBoard(Player forPlayer) {

        for (int x = 0; x < 8; x++) {
            Square currentSquare = getSquare(x, 1);
            currentSquare.put(new Pawn(forPlayer, new Coordinate(x, 1)));
        }
        Square currentSquare = getSquare(4, 0);
        currentSquare.put(new King(forPlayer, new Coordinate(4, 0)));

    }

    public static Square getSquare(int x, int y) {
        return gameState[x][y];

    }

    public static void getThreatenedSquares() {
        flipHorizontally();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square currentSquare = getSquare(x, y);
                ChessPiece currentPiece = currentSquare.getPiece();
                // only get threatening squares if they are threatened by other player
                if (!currentSquare.isEmpty() && currentPiece.color != currentPlayer) {
                    ArrayList<Coordinate> listOfThreatenedSquares = currentPiece.getThreatenedSquares();
                    System.out.println(listOfThreatenedSquares);
                    for (Coordinate coordinate : listOfThreatenedSquares) {
                        if (!isOutsideBoard(coordinate)) {
                            getSquare(coordinate.getX(), coordinate.getY()).setThreatened(true);
                        }

                    }
                }
            }
        }
        flipHorizontally();
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
                if(currentPiece instanceof King && currentPiece.color == currentPlayer && currentSquare.isThreatened) {
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

    public static void handleMove(Coordinate currentPieceCoordinate, Coordinate toCurrentCoordinate) {
        Square currentSquare = gameState[currentPieceCoordinate.getY()][currentPieceCoordinate.getX()];
        Square nextSquare = gameState[toCurrentCoordinate.getX()][toCurrentCoordinate.getY()];
        nextSquare.put(currentSquare.getPiece());
        currentSquare.clear();
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
        return getSquare(coordinates.getY(), coordinates.getX()).isEmpty();
    }

    public static boolean squareContainsOwnPiece(Coordinate move) {
        Square currentSquare = getSquare(move.getX(), move.getY());
        if (currentSquare.isEmpty()) {
            return false;
        } else {
            return currentSquare.getPiece().color == currentPlayer;
        }

    }

    public static boolean squareContainsEnemyPiece(Coordinate move) {
        Square currentSquare = getSquare(move.getX(), move.getY());
        if (currentSquare.isEmpty()) {
            return false;
        } else {
            return currentSquare.getPiece().color != currentPlayer;
        }

    }

    public static void flipHorizontally() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8/2; x++) {
                Square tmp = getSquare(y, 8-x-1);
                getSquare(x, y).setCoordinatesOfPiece(new Coordinate(y, 8-x-1));
                gameState[y][8-x-1] = getSquare(y, x);
                tmp.setCoordinatesOfPiece(new Coordinate(y,x));
                gameState[y][x] = tmp;
            }
        }
    }


    public static void printBoard() {
        /*System.out.println(Arrays.deepToString(gameState)
                .replace("],","\n").replace(",","\t| ")
                .replaceAll("[\\[\\]]", " "));
        System.out.println("\n");*/
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (Square[] row : gameState) {
            sj.add(Arrays.toString(row));
        }
        String result = sj.toString();
        System.out.println(result);
    }

    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(gameState[0][1]);
        printBoard();


    }

}
