package com.kopiitem.poker;

/**
 *
 * @author donny.fm
 */
public class HighCard implements Role {

    @Override
    public Player calculate(Player player) {

        player.setMyRole(RoleEnum.HIGH_CARD);
        return player;
    }

}
