package pl.games;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WrongPlayHandler implements Runnable {

    private JButton button1;
    private JButton button2;

    public WrongPlayHandler(JButton button1, JButton button2) {
        this.button1 = button1;
        this.button2 = button2;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            try {
                ClassLoader classLoader = ButtonIconSetter.class.getClassLoader();
                File file = new File(classLoader.getResource("images/cover.png").getFile());
                Image image = ImageIO.read(file);
                button1.setIcon(new ImageIcon(image));
                button2.setIcon(new ImageIcon(image));
                Main.processing = false;  // wrong play processing finished
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
