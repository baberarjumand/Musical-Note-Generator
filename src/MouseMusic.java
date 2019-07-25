/**
 * This program plays music based on where you click
 * in the generated JFrame.
 * The x-axis of the JFrame represents level of volume.
 * The y-axis represents the value of the pitch of the sound generated.
 *
 * This is the fourth project for the Java Code Clinic (by Carlos Rivas) on Lynda.com.
 *
 * This class plays the sound based on the calculations of the position
 * of the mouse click in the JFrame.
 *
 * Author: Baber Arjumand
 * Date:   Jul 23 2019
 * Time:   10:02 PM
 */

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;

import org.jfugue.*;

public class MouseMusic implements Runnable {

    public Object lock = new Object();

    public MouseMusic() {

    }

    @Override
    public void run() {
        while(true) {
            synchronized (lock) {
                while(MouseChecker.m) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            Player player = new Player();
            Point loc = MouseInfo.getPointerInfo().getLocation();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double width = screenSize.getWidth();
            double height = screenSize.getHeight();
            int pitch = 127 - (int) (loc.y*127/height);
            int volume = (int) (loc.x*16383/width);
            player.play("X[Volume]=" + volume + " [" + pitch + "]");
        }
    }
}

