package pl.games;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public final class ButtonIconSetter {

    public static void setButtonIconUncovered(ButtonEvent buttonEvent) {
        String path = "images/" + buttonEvent.getCell().getValue() + ".jpg";
        setButtonIcon(buttonEvent, path);
    }

    public static void setButtonIconCovered(ButtonEvent buttonEvent) {
        String path = "images/cover.png";
        setButtonIcon(buttonEvent, path);
    }

    public static void setButtonIconDisabled(ButtonEvent buttonEvent) {
        String path = "images/transparent.png";
        setButtonIcon(buttonEvent, path);
    }

    private static void setButtonIcon(ButtonEvent buttonEvent, String path) {
        try {
            ClassLoader classLoader = ButtonIconSetter.class.getClassLoader();
            File file = new File(classLoader.getResource(path).getFile());
            Image image = ImageIO.read(file);
            buttonEvent.getButton().setIcon(new ImageIcon(image));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}