package com.fekstr;

/**
 * Created by Alfred on 2020-01-23.
 */
public class Square {
    private ChessPiece piece;
    public boolean isThreatened;

    public Square() {
        piece = null;
        isThreatened = false;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public void put(ChessPiece p) {
        piece = p;
    }

    public void clear() {
        piece = null;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setThreatened(boolean _isThreatened) {
        isThreatened = _isThreatened;
    }

    public void setCoordinatesOfPiece(Coordinate coordinates) {
        if (!isEmpty()) {
            piece.coordinate.setX(coordinates.getX());
            piece.coordinate.setY(coordinates.getY());
        }
    }

    public String toString() {
        if (isEmpty()) {
            return "_  ";
        } else {
            return piece.toString();
        }

    }
}
