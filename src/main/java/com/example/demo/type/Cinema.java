package com.example.demo.type;

public enum Cinema {
    UNDEFINED(0),
    KINOMINI(1),
    CINEMA_0(2),
    BEE_MOVIE(3);

    private int numVal;

    Cinema(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

    public static Cinema fromInt(int x) {
        switch (x) {
            case 0:
                return UNDEFINED;
            case 1:
                return KINOMINI;
            case 2:
                return CINEMA_0;
            case 3:
                return BEE_MOVIE;
        }
        return UNDEFINED;
    }
}
