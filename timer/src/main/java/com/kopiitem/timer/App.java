package com.kopiitem.timer;

/**
 *
 * @author donny.fm
 */
public class App {

    public static void main(String[] args) throws InterruptedException {

        Alarm alarm = new Alarm("13:15:00").create(new Song("D://DUMP/10001-90210-01803.wav"));

        Thread t = new Thread(alarm);
        t.start();
        Thread.sleep(120 * 1000);
        alarm.stop();
    }

}
