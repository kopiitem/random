package com.kopiitem.timer;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author donny.fm
 */
public class Song implements Runnable {

    private String title;

    public Song() {
    }

    public Song(String title) {
        this.title = title;
    }

    @Override
    public void run() {
        try {
            File file = new File(title);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            Logger.getLogger(Buzzer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
