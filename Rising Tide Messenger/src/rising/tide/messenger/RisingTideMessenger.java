/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rising.tide.messenger;

import audio.AudioTone;
import clientproject.ClientInfo;
import designfiles.Login;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author SayefReyadh
 */
public class RisingTideMessenger {
    //shaafi 27-7-16 start
    
    //icon path
    public static final String ICON_PATH = "/img/icon.png";
    
    //function that will generate the icon from img folder for the default icon of the project
    public static void defaultIconCreator(JFrame mFrame, String title)
    {
        ImageIcon icon;
        icon = new ImageIcon(mFrame.getClass().getResource(ICON_PATH));
        mFrame.setIconImage(icon.getImage());
        mFrame.setTitle(title + " | Rising Tide Messenger");
    }

    //end

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AudioTone.startSound();
       
        try {
            Thread.sleep(5500);
        } catch (InterruptedException ex) {
            Logger.getLogger(RisingTideMessenger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Login obLogin = new Login();
        obLogin.setVisible(true);
        obLogin.repaint();
        ClientInfo.loginInstance = obLogin;
        
    }
    
}
