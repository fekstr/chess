package com.fekstr;

public class Coordinate {
    private int x, y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Coordinate c) {
        if (getX() == c.getX() && getY() == c.getY()) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}
