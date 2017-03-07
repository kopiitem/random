package com.kopiitem.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author donny.fm
 */
public abstract class BaseAlarm implements Runnable {

    private static final Logger logger = Logger.getLogger(BaseAlarm.class.getName());

    protected Timer timer;

    protected String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    protected abstract void start();

    protected void stop() {
        logger.log(Level.INFO, "stop() Alarm Ringing ! {0}", getCurrentTime());
        timer.cancel();
        timer.purge();
    }

}
