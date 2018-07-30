package pl.games;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ButtonEvent implements ActionListener {

    private JButton button;
    private Map.Entry<Coordinates, String> cell;

    public ButtonEvent(JButton button, Map.Entry<Coordinates, String> cell) {
        this.button = button;
        this.cell = cell;
    }

    public JButton getButton() {
        return button;
    }

    public Map.Entry<Coordinates, String> getCell() {
        return cell;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!Main.processing) {  // prevents from clicking multiple images while processing wrong play
            handleButtonClick();
        }
    }

    private void handleButtonClick() {
        if(Main.buttonClicked == null) { // "first" clicked button
            Main.buttonClicked = this;
            ButtonIconSetter.setButtonIconUncovered(this);
        } else { // "second" clicked button
            handleSecondClick();
        }
    }

    private void handleSecondClick() {
        if(Main.buttonClicked != this) { // if it's not the same button as first
            int currentScore = Integer.parseInt(Main.scoreLabel.getText());

            if(Main.buttonClicked.cell.getValue().equals(this.cell.getValue())) { // cell images match
                ButtonIconSetter.setButtonIconDisabled(Main.buttonClicked);
                Main.buttonClicked.button.setEnabled(false);
                ButtonIconSetter.setButtonIconDisabled(this);
                this.button.setEnabled(false);
                currentScore += 10;
                if(++Main.found == Main.TO_FIND) {
                    handleWin(currentScore);
                }
            } else {
                Main.processing = true; // processing wrong play flag
                ButtonIconSetter.setButtonIconUncovered(this);
                JButton firstButton = Main.buttonClicked.getButton();
                SwingUtilities.invokeLater(new WrongPlayHandler(firstButton, button));
                currentScore -= 2;
            }

            Main.buttonClicked = null;
            Main.scoreLabel.setText(Integer.toString(currentScore));
        }
    }

    private void handleWin(int score) {
        SwingUtilities.invokeLater(() -> endGame(score));
    }

    private void endGame(int score) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 0, 10));
        JLabel label = new JLabel("Congratulations! Your score: " + score);
        styleLabel(label);
        JButton replayButton = new JButton("Play again");
        replayButton.addActionListener(new ReplayButtonHandler());
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(l -> System.exit(0));
        panel.add(label);
        panel.add(replayButton);
        panel.add(exitButton);
        Main.frame.getContentPane().removeAll();
        Main.frame.getContentPane().add(panel);
        Main.frame.pack();
        Main.frame.setLocationRelativeTo(null);
        Main.frame.revalidate();
    }

    private void styleLabel(JLabel label) { // sets margin and font
        Border border = label.getBorder();
        Border margin = new EmptyBorder(10,10,10,10);
        label.setBorder(new CompoundBorder(border, margin));
        label.setFont(new Font("Serif", Font.PLAIN, 28));
    }

}
