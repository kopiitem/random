package com.kopiitem.poker;

/**
 *
 * @author donny.fm
 */
public class Straight implements Role {

    @Override
    public Player calculate(Player player) {
        int totalCard = player.getCards().size();

        int myNumber = player.getCards().get(0).getNumber();

        if (player.getCards().get(totalCard - 1).getNumber() == (myNumber + (totalCard - 1))) {
            player.setMyRole(RoleEnum.STRAIGHT);
        }
        return player;

    }
}
