package com.company;

public enum CardSuits {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    public final int power;

    CardSuits(int power) {
        this.power = power;
    }
}
