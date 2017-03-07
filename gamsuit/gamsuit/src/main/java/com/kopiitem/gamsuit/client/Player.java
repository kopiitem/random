package com.kopiitem.gamsuit.client;

import com.kopiitem.gamsuit.util.CommandEnum;
import java.io.Serializable;

/**
 *
 * @author donny.fm
 */
public class Player implements Serializable {

    private Information user;
    private Information robot;
    private CommandEnum commandEnum;

    public Player() {
        this.user = new Information();
        this.robot = new Information();
    }

    public Player(Information user, Information robot) {
        this.user = user;
        this.robot = robot;
    }

    public Information getUser() {
        return user;
    }

    public void setUser(Information user) {
        this.user = user;
    }

    public Information getRobot() {
        return robot;
    }

    public void setRobot(Information robot) {
        this.robot = robot;
    }

    public CommandEnum getCommandEnum() {
        return commandEnum;
    }

    public void setCommandEnum(CommandEnum commandEnum) {
        this.commandEnum = commandEnum;
    }

}
