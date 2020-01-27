package com.fekstr;

import java.util.ArrayList;

/**
 * Created by Alfred on 2020-01-23.
 */
public class Square {
    private ChessPiece piece;
    public boolean isThreatened;
    private Coordinate coordinate;

    public Square(Coordinate coordinate) {
        piece = null;
        isThreatened = false;
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public ArrayList<Coordinate> getValidMoves() {
        return piece.getValidMoves();
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
            piece.coordinate.setY(coordinates.getY());
            piece.coordinate.setX(coordinates.getX());
        }
    }

    public String toString() {
        if (isEmpty()) {
            if (isThreatened) {
                return "_     " + " T";
            }
            return "_     ";
        } else {
            if (isThreatened) {
                return piece.toString() + " T";
            }
            return piece.toString() + "   ";
        }

    }
}
