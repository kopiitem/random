package com.kopiitem.poker;

/**
 *
 * @author donny.fm
 */
public enum RoleEnum {

    FIVE_OF_KIND(0), STRAIGHT_FLUSH(1), FOUR_OF_KIND(2), FULL_HOUSE(3), FLUSH(4), STRAIGHT(5), THREE_OF_KIND(6), TWO_PAIR(7), ONE_PAIR(8), HIGH_CARD(9);

    private int value;

    private RoleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
