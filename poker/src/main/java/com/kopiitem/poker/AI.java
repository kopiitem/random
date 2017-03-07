package com.kopiitem.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author donny.fm
 */
public class AI {

    private Pack pack;
    private List<Player> players;

    public AI() {
        pack = new Pack(1);
        players = new ArrayList<>();
        pack.suffle();
    }

    public AI(int totalPack) {
        pack = new Pack(totalPack);
        players = new ArrayList<>();
        pack.suffle();
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    public void giveCards() {
        players.stream().forEach((player) -> {
            giveCardsToPlayers(pack.pull(), player);
        });
    }

    private void giveCardsToPlayers(Card card, Player player) {
        player.getCards().add(card);

    }

    public Player getCardPlayerByName(String name) {
        for (Player player : players) {
            if (name.equalsIgnoreCase(player.getName())) {
                return player;
            }
        }
        return null;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void calculate() {
        for (Player player : players) {
            sortingCardByNumber(player);
            examination(player);
        }

    }

    public Player findWinner() {

        this.players = players.stream().sorted((Player o1, Player o2) -> o1.getMyRole().getValue() - o2.getMyRole().getValue()).collect(Collectors.toList());

        return players.get(0);
    }

    public Player findlooser() {

        this.players = players.stream().sorted((Player o1, Player o2) -> o1.getMyRole().getValue() - o2.getMyRole().getValue()).collect(Collectors.toList());

        List<Player> loosers = new ArrayList<>();
        for (Player player : players) {
            if (player.getMyRole().equals(players.get(players.size() - 1).getMyRole())) {
                loosers.add(player);
            }
        }

//        return players.get(players.size() - 1);
        return getTheMostLooser(loosers);
    }

    private Player getTheMostLooser(List<Player> loosers) {
        RoleEnum roleEnum = loosers.get(0).getMyRole();
        switch (roleEnum) {
            case HIGH_CARD:
                for (int p = 0; p < loosers.size() - 1; p++) {

                    if (loosers.get(p).getCards().stream().mapToInt(l -> l.getNumber()).sum() > loosers.get(p + 1).getCards().stream().mapToInt(l -> l.getNumber()).sum()) {
                        Player temp = loosers.get(p);
                        loosers.set(p, loosers.get(p + 1));
                        loosers.set(p + 1, temp);
                    }
                }
                break;
            default:
                throw new AssertionError();
        }

        return loosers.get(0);
    }

    private void examination(Player player) {

        if (player.getMyRole() == null) {
            FiveOfAKind fiveOfAKind = new FiveOfAKind();
            fiveOfAKind.calculate(player);
        }
        if (player.getMyRole() == null) {
            StraightFlush straightFlush = new StraightFlush();
            straightFlush.calculate(player);
        }
        if (player.getMyRole() == null) {
            FourOfAKind fourOfAKind = new FourOfAKind();
            fourOfAKind.calculate(player);
        }
        if (player.getMyRole() == null) {
            FullHouse fullHouse = new FullHouse();
            fullHouse.calculate(player);
        }
        if (player.getMyRole() == null) {
            Flush flush = new Flush();
            flush.calculate(player);
        }
        if (player.getMyRole() == null) {
            Straight straight = new Straight();
            straight.calculate(player);
        }
        if (player.getMyRole() == null) {
            ThreeOfAKind threeOfAKind = new ThreeOfAKind();
            threeOfAKind.calculate(player);
        }
        if (player.getMyRole() == null) {
            TwoPair twoPair = new TwoPair();
            twoPair.calculate(player);
        }
        if (player.getMyRole() == null) {
            OnePair onePair = new OnePair();
            onePair.calculate(player);
        }
        if (player.getMyRole() == null) {
            HighCard highCard = new HighCard();
            highCard.calculate(player);
        }
    }

    private Player sortingCardByNumber(Player player) {

        player.setCards(player.getCards().stream().sorted((Card o1, Card o2) -> o1.getNumber() - o2.getNumber()).collect(Collectors.toList()));

        return player;
    }

}
