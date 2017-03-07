package com.kopiitem.poker;

/**
 *
 * @author donny.fm
 */
public class Card {

    private TypeEnum type;
    private int number;

    public Card() {
    }

    public Card(TypeEnum type, int number) {
        this.type = type;
        this.number = number;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Card{" + "type=" + type + ", number=" + number + '}';
    }

}
