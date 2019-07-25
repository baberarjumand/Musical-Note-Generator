/**
 * This program plays music based on where you click
 * in the generated JFrame.
 * The x-axis of the JFrame represents level of volume.
 * The y-axis represents the value of the pitch of the sound generated.
 *
 * This is the fourth project for the Java Code Clinic (by Carlos Rivas) on Lynda.com.
 *
 * This class uses the MouseListener interface to detect relevant mouse events.
 * This class also generates the JFrame.
 *
 * Author: Baber Arjumand
 * Date:   Jul 23 2019
 * Time:   10:01 PM
 */

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class MouseChecker extends JPanel implements MouseListener, Runnable {

    private static final long serialVersionUID = 1L;

    public static boolean m = true;

    public MouseMusic music;

    public MouseChecker() {
    }

    public void mousePressed(MouseEvent e) {
        m = false;
        synchronized (music.lock) {
            music.lock.notifyAll();
        }
    }

    public void mouseReleased(MouseEvent e) {
        m = true;
        synchronized (music.lock) {
            music.lock.notifyAll();
        }
    }

    @Override
    public void mouseClicked(MouseEvent paramMouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent paramMouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent paramMouseEvent) {
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("MouseMusic");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = this;
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);

        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setFont(new Font("Sans Serif", Font.PLAIN, 50));
        label.setText("Click anywhere");
        frame.add(label);

        addMouseListener(this);
        music = new MouseMusic();
        new Thread(music).start();
    }
}

