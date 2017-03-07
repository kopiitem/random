package com.kopiitem.poker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author donny.fm
 */
public class App {

    public static void main(String[] args) {
        AI ai = new AI();
        ai.addPlayer("Donny");
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(TypeEnum.CLUBS, 2));
        cards.add(new Card(TypeEnum.DIAMONDS, 3));
        cards.add(new Card(TypeEnum.DIAMONDS, 4));
        cards.add(new Card(TypeEnum.SPADES, 9));
        cards.add(new Card(TypeEnum.HEARTS, 10));

        ai.getCardPlayerByName("Donny").setCards(cards);
        ai.addPlayer("Wice");
        cards = new ArrayList<>();
        cards.add(new Card(TypeEnum.CLUBS, 2));
        cards.add(new Card(TypeEnum.DIAMONDS, 3));
        cards.add(new Card(TypeEnum.DIAMONDS, 4));
        cards.add(new Card(TypeEnum.SPADES, 8));
        cards.add(new Card(TypeEnum.CLUBS, 10));

        ai.getCardPlayerByName("Wice").setCards(cards);

//        ai.giveCards();
//        ai.giveCards();
//        ai.giveCards();
//        ai.giveCards();
//        ai.giveCards();

        ai.addPlayer("Testing");

        cards = new ArrayList<>();
        cards.add(new Card(TypeEnum.DIAMONDS, 11));
        cards.add(new Card(TypeEnum.DIAMONDS, 7));
        cards.add(new Card(TypeEnum.DIAMONDS, 8));
        cards.add(new Card(TypeEnum.DIAMONDS, 9));
        cards.add(new Card(TypeEnum.DIAMONDS, 10));

        ai.getCardPlayerByName("Testing").setCards(cards);

        ai.calculate();

        System.out.println(ai.getCardPlayerByName("Donny"));
        System.out.println(ai.getCardPlayerByName("Wice"));
        System.out.println(ai.getCardPlayerByName("Testing"));

        System.out.println("");
        System.out.println("After Calculate: ");
        System.out.println("Winner: " + ai.findWinner().getName());
        System.out.println("Looser: " +ai.findlooser().getName());

    }
}
