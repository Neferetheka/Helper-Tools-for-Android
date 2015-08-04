package com.aerilys.acr.android.tools;

/**
 * Simple tuple class.
 */
public class Tuple<X, Y> {
    private final X x;
    private final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public Y getY() {
        return y;
    }

    public X getX() {
        return x;
    }
}