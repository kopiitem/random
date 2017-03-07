package com.kopiitem.timer;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author donny.fm
 */
public class Alarm extends BaseAlarm {

    private static final Logger logger = Logger.getLogger(Alarm.class.getName());
    private String time;
    private Buzzer buzzer;

    public Alarm() {

    }

    public Alarm(String time) {
        this.time = time;
    }

    public Alarm create(Song song) {
        this.timer = new Timer();
        this.buzzer = new Buzzer(timer, song);
        return this;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String now = getCurrentTime();
                logger.log(Level.INFO, "now: {0}", now);
                if (now.equals(time)) {
                    start();
                    break;
                }

                int currentHour = Integer.parseInt(now.split(":")[0]);
                int currentMinute = Integer.parseInt(now.split(":")[1]);

                int alarmHour = Integer.parseInt(time.split(":")[0]);
                int alarmMinute = Integer.parseInt(time.split(":")[1]);

                long delay = 1000l;

                if (Math.abs(currentHour - alarmHour) != 1) {
                    if (currentHour == alarmHour && alarmMinute < currentMinute) {
                        delay = ((24 - 1)) * 60 * 1000l;
                    }
                    if (currentHour < alarmHour) {
                        delay = (((alarmHour) - currentHour) - 1) * 60 * 1000l;
                    }
                    if (currentHour > alarmHour) {
                        delay = (((24 - currentHour + alarmHour)) - 1) * 60 * 1000l;
                    }
                }
                if (delay != 1000l) {
                    logger.log(Level.INFO, "Will be Pending: {0} minutes", (delay / 1000l));
                }
                Thread.sleep(delay);

            } catch (InterruptedException ex) {
                Logger.getLogger(Alarm.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    protected void start() {
        logger.log(Level.INFO, "start() Alarm Ringing ! {0}", getCurrentTime());
        timer.scheduleAtFixedRate(buzzer, 1 * 1000, 10 * 1000);
    }

}
