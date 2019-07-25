/**
 * This program plays music based on where you click
 * in the generated JFrame.
 * The x-axis of the JFrame represents level of volume.
 * The y-axis represents the value of the pitch of the sound generated.
 * The JFugue library was used to generate sounds.
 *
 * This is the fourth project for the Java Code Clinic (by Carlos Rivas) on Lynda.com.
 *
 * This class is the main driver class.
 *
 * Author: Baber Arjumand
 * Date:   Jul 23 2019
 * Time:   9:51 PM
 */

public class Main {

    public static void main(String[] args) {
        new Thread(new MouseChecker()).start();
    }
}
