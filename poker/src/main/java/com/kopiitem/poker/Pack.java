package com.kopiitem.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author donny.fm
 */
public class Pack {

    public List<Card> cards = new ArrayList<>();

    public Pack(int total) {
        for (int i = 0; i < total; i++) {
            create();
        }
    }

    private void create() {
        for (int i = 2; i <= 14; i++) {
            cards.add(new Card(TypeEnum.CLUBS, i));
            cards.add(new Card(TypeEnum.DIAMONDS, i));
            cards.add(new Card(TypeEnum.HEARTS, i));
            cards.add(new Card(TypeEnum.SPADES, i));
        }
    }

    public void suffle() {
        Collections.shuffle(cards);
    }

    public Card pull() {
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
    
    
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        cards = cards;
    }
}
