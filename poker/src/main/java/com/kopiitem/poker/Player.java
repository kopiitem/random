package com.kopiitem.poker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author donny.fm
 */
public class Player {

    String name;
    List<Card> cards;
    RoleEnum myRole;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public RoleEnum getMyRole() {
        return myRole;
    }

    public void setMyRole(RoleEnum myRole) {
        this.myRole = myRole;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", cards=" + cards + ", myRule=" + myRole + '}';
    }

}
