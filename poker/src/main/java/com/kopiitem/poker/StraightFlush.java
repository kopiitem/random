package com.kopiitem.poker;

/**
 *
 * @author donny.fm
 */
public class StraightFlush implements Role {

    @Override
    public Player calculate(Player player) {
        int total = 0;
        int totalCard = player.getCards().size();

        TypeEnum typeEnum = player.getCards().get(0).getType();
        int myNumber = player.getCards().get(0).getNumber();

        for (Card card : player.getCards()) {
            if (typeEnum.equals(card.getType())) {
                total++;
            }
        }

        if (total == 5) {
            if (player.getCards().get(totalCard - 1).getNumber() == (myNumber + (totalCard - 1))) {
                player.setMyRole(RoleEnum.STRAIGHT_FLUSH);
            }
        }
        return player;

    }
}
