/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


/**
 *
 * @author SayefReyadh
 */
public class AudioTone {
    
    public void chatSound()
    {
        InputStream inputStream;
        try {
            inputStream = getClass().getResourceAsStream("tone.wav");
            AudioStream audios = new AudioStream(inputStream);
            AudioPlayer.player.start(audios);            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void loginSound()
    {
        InputStream inputStream;
        try {
            inputStream = getClass().getResourceAsStream("logintone.wav");
            AudioStream audios = new AudioStream(inputStream);
            AudioPlayer.player.start(audios);            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void startSound()
    {
        InputStream inputStream;
        try {
           
            inputStream = new AudioTone().getClass().getResourceAsStream("a.wav");
            AudioStream audios = new AudioStream(inputStream);
            AudioPlayer.player.start(audios);            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
