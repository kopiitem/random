package com.kopiitem.gamsuit.server;

import com.kopiitem.gamsuit.client.Player;
import com.kopiitem.gamsuit.util.Constants;
import com.kopiitem.gamsuit.util.Transport;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author donny.fm
 */
public class Robot extends Transport {

    private ServerSocket listener;
    private List<Player> players;
    private String robotName[] = {"Donny", "Wice", "Vincent", "Batman", "Superman", "Robin", "Sheena"};

    public Robot() {
        players = new ArrayList<Player>();
    }

    public void execute() {
        try {
            listener = new ServerSocket(Constants.PORT);
            while (true) {
                socket = listener.accept();
                GamePlay game = new GamePlay(this);
                Thread t = new Thread(game);
                t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String[] getRobotName() {
        return robotName;
    }

    public void setRobotName(String[] robotName) {
        this.robotName = robotName;
    }

}
