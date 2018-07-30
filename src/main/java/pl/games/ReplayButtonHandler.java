package pl.games;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayButtonHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.frame.setVisible(false);
        Main.frame.dispose(); //Destroy the JFrame object
        Main.found = 0;
        Main.scoreLabel.setText("0");
        Main.buttonClicked = null;
        Main.processing = false;
        SwingUtilities.invokeLater(new Main());
    }

}
