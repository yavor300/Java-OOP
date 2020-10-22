package com.company;

public enum CardSuits {


    CLUBS(0),
    DIAMONDS(1),
    HEARTS(2),
    SPADES(3);

    private int code;
    CardSuits(int code) {
        this.code = code;
    }
}
