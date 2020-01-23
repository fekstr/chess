package com.fekstr;

public class Coordinate {
    private int i, j;

    Coordinate(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public boolean equals(Coordinate c) {
        if (this.i == c.i && this.j == c.j) {
            return true;
        } else {
            return false;
        }
    }
}
