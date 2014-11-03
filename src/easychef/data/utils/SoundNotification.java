/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.AudioClip;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Tumendelger
 */
public class SoundNotification {

    private static final Logger logger = Logger.getLogger(SoundNotification.class.getName());
    private AudioInputStream inStream;
    private AudioFormat audioFormat;
    private DataLine.Info info;
    private Clip clip;

    public void playSound(String soundFile) {
        File sndFile = new File(soundFile);
        if (sndFile.exists()) {
            logger.info(String.format("Playing audio file: %s", sndFile.getAbsolutePath()));
            try {
                inStream = AudioSystem.getAudioInputStream(new File(soundFile));
                audioFormat = inStream.getFormat();
                info = new DataLine.Info(Clip.class, audioFormat);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(inStream);
                clip.start();
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(SoundNotification.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SoundNotification.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(SoundNotification.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            logger.warning(String.format("Sound file %s does NOT exists.", sndFile.getAbsolutePath()));
        }

    }

}
