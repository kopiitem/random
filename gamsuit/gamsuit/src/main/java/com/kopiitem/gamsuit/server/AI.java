package com.kopiitem.gamsuit.server;

import com.kopiitem.gamsuit.util.BidEnum;
import com.kopiitem.gamsuit.util.WinEnum;

/**
 *
 * @author donny.fm
 */
public class AI {

    public static WinEnum getResult(BidEnum player, BidEnum robot) {
        if (player == robot) {
            return WinEnum.DRAW;
        }

        switch (player) {
            case PAPER:
                return (robot == BidEnum.SCISSOR) ? WinEnum.ROBOT : WinEnum.PLAYER;
            case SCISSOR:
                return (robot == BidEnum.STONE) ? WinEnum.ROBOT : WinEnum.PLAYER;
            case STONE:
                return (robot == BidEnum.SCISSOR) ? WinEnum.PLAYER : WinEnum.ROBOT;
            default:
                return null;
        }
    }
}
