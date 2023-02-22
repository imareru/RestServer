package com.example.demo.type;

public enum Room {
    UNDEFINED(0),
    FIRST(1),
    SECOND(2),
    VIP(3);

    private int numVal;

    Room(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

    public static Room fromInt(int x) {
        switch (x) {
            case 0:
                return UNDEFINED;
            case 1:
                return FIRST;
            case 2:
                return SECOND;
            case 3:
                return VIP;
        }
        return UNDEFINED;
    }
}
