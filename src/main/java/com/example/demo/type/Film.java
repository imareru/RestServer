package com.example.demo.type;

public enum Film {
    UNDEFINED(0),
    PROKAZAN(1),
    RT_ONLINE(2),
    BUSINESS_GAZETA(3);

    private int numVal;

    Film(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

    public static Film fromInt(int x) {
        switch (x) {
            case 0:
                return UNDEFINED;
            case 1:
                return PROKAZAN;
            case 2:
                return RT_ONLINE;
            case 3:
                return BUSINESS_GAZETA;
        }
        return UNDEFINED;
    }

}
