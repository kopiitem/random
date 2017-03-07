package com.kopiitem.poker;

import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author donny.fm
 */
public class TwoPair implements Role {

    @Override
    public Player calculate(Player player) {

        Map<Integer, Long> map = player.getCards().stream().collect(Collectors.groupingBy(c -> c.getNumber(), Collectors.counting()));

        if (map.size() == 3) {
            for (Map.Entry<Integer, Long> entrySet : map.entrySet()) {

                if (entrySet.getValue() == 2) {
                    player.setMyRole(RoleEnum.TWO_PAIR);
                }

            }
        }

        return player;
    }

}
