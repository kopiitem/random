package com.kopiitem.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author donny.fm
 */
public class Buzzer extends TimerTask {

    private Timer timer;
    private Runnable song;

    public Buzzer(Timer timer, Runnable song) {
        this.timer = timer;
        this.song = song;
    }

    @Override
    public void run() {
        Thread thread = new Thread(song);
        thread.start();
    }

}
