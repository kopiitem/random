package com.kopiitem.gamsuit.server;

import com.kopiitem.gamsuit.util.BidEnum;
import com.kopiitem.gamsuit.util.WinEnum;

/**
 *
 * @author donny.fm
 */
public class AI {

    public static WinEnum getResult(BidEnum player, BidEnum robot) {
        WinEnum winEnum = null;
        if (player == robot) {
            winEnum = WinEnum.DRAW;
        }

        switch (player) {
            case PAPER:
                if (robot == BidEnum.SCISSOR) {
                    winEnum = WinEnum.ROBOT;
                }
                if (robot == BidEnum.STONE) {
                    winEnum = WinEnum.PLAYER;
                }

                break;
            case SCISSOR:
                if (robot == BidEnum.STONE) {
                    winEnum = WinEnum.ROBOT;
                }
                if (robot == BidEnum.PAPER) {
                    winEnum = WinEnum.PLAYER;
                }

                break;
            case STONE:
                if (robot == BidEnum.SCISSOR) {
                    winEnum = WinEnum.PLAYER;
                }
                if (robot == BidEnum.PAPER) {
                    winEnum = WinEnum.ROBOT;
                }
                break;
        }

        return winEnum;
    }
}
