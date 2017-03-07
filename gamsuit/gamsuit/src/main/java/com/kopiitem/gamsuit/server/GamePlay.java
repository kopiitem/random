package com.kopiitem.gamsuit.server;

import com.kopiitem.gamsuit.client.Information;
import com.kopiitem.gamsuit.client.Player;
import com.kopiitem.gamsuit.util.BidEnum;
import com.kopiitem.gamsuit.util.CommandEnum;
import com.kopiitem.gamsuit.util.Transport;

/**
 *
 * @author donny.fm
 */
public class GamePlay extends Transport implements Runnable {

    private Robot robot;

    public GamePlay(Robot robot) {
        this.robot = robot;
        socket = robot.getSocket();
    }

    public void run() {
        while (socket.isConnected()) {
            Player player = (Player) read();
            switch (player.getCommandEnum()) {
                case HANDSHAKE:
                    Information iUser = player.getUser();
                    Information iRobot = player.getRobot();
                    iUser.setScore(0);
                    iRobot.setName(robot.getRobotName()[(int) (Math.random() * robot.getRobotName().length)]);
                    iRobot.setScore(0);
                    System.out.println("Player " + iUser.getName() + " got Connected! " + iRobot.getName() + " was Created! ");
                    player.setCommandEnum(CommandEnum.CONNECTED);
                    send(player);
                    robot.getPlayers().add(player);
                    break;
                case PLAY:
                    int x = (int) (Math.random() * BidEnum.values().length);
                    BidEnum bidEnum = BidEnum.values()[x];
                    player.getRobot().setBid(bidEnum);
                    int current = 0;
                    switch (AI.getResult(player.getUser().getBid(), bidEnum)) {
                        case ROBOT:
                            current = player.getRobot().getScore();
                            player.getRobot().setScore(current + 1);
                            break;
                        case PLAYER:
                            current = player.getUser().getScore();
                            player.getUser().setScore(current + 1);
                            break;
                        case DRAW:
                            break;
                    }

                    send(player);
                    break;
            }
        }
    }

}
