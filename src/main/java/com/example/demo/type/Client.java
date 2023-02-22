package com.example.demo.type;

public enum Client {
    UNDEFINED(0),
    A_R_R(1),
    G_K_V(2),
    S_A_P(3);

    private int numVal;

    Client(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

    public static Client fromInt(int x) {
        switch (x) {
            case 0:
                return UNDEFINED;
            case 1:
                return A_R_R;
            case 2:
                return G_K_V;
            case 3:
                return S_A_P;
        }
        return UNDEFINED;
    }
}
