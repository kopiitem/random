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
    private int playerScore = 0;
    private int robotScore = 0;

    public GamePlay(Robot robot) {
        this.robot = robot;
        socket = robot.getSocket();
        initStreams();
    }

    public void run() {
        Player player = null;
        try {
            while (!socket.isClosed()) {
                player = read();
                if (player == null) {
                    break;
                }
                switch (player.getCommandEnum()) {
                    case HANDSHAKE:
                        Information iUser = player.getUser();
                        Information iRobot = player.getRobot();
                        playerScore = 0;
                        robotScore = 0;
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
                        switch (AI.getResult(player.getUser().getBid(), bidEnum)) {
                            case ROBOT:
                                robotScore++;
                                break;
                            case PLAYER:
                                playerScore++;
                                break;
                            case DRAW:
                                break;
                        }
                        player.getUser().setScore(playerScore);
                        player.getRobot().setScore(robotScore);
                        send(player);
                        break;
                    case CLOSE:
                        System.out.println("Player " + player.getUser().getName() + " disconnected.");
                        robot.getPlayers().remove(player);
                        socket.close();
                        return;
                }
            }
        } catch (Exception ex) {
            System.out.println("Game session ended unexpectedly: " + ex.getMessage());
        } finally {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (Exception ignored) {
            }
        }
    }

}
