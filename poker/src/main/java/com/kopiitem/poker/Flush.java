package com.kopiitem.poker;

/**
 *
 * @author donny.fm
 */
public class Flush implements Role {

    @Override
    public Player calculate(Player player) {

        int total = 0;

        TypeEnum typeEnum = player.getCards().get(0).getType();

        for (Card card : player.getCards()) {
            if (typeEnum.equals(card.getType())) {
                total++;
            }
        }

        if (total == 5) {

            for (int i = 0; i < player.getCards().size() - 2; i++) {
                int myNumber = player.getCards().get(i).getNumber();
                if (player.getCards().get(i + 2).getNumber() == (myNumber + 2)) {
                    player.setMyRole(RoleEnum.FLUSH);
                }

            }

        }
        return player;
    }

}
