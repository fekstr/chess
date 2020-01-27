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

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public ArrayList<Coordinate> getValidMoves() {
        return piece.getValidMoves();
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public void put(ChessPiece p) {
        piece = p;

        if (p != null) {
            p.setCurrentSquare(this);
        }
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

    public Player getPlayer() {
        return piece.getColor();
    }
}
