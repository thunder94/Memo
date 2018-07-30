package pl.games;

import java.awt.*;
import javax.swing.*;
import java.util.Map;

import static pl.games.DeckGenerator.generateDeck;

public class Main implements Runnable
{
    private final int DECK_SIZE = 4;  // 4 x 4 cardboard
    public static final int TO_FIND = 8; // 8 pairs to find (2 * 8 = 4 * 4)
    public static int found = 0;
    public static JLabel scoreLabel = new JLabel("0", SwingConstants.LEFT);
    public static ButtonEvent buttonClicked = null;
    public static boolean processing = false;
    public static JFrame frame;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Main());
    }

    public void run()
    {
        Map<Coordinates, String> deck = generateDeck(DECK_SIZE); // (x, y) -> image
        JPanel scorePanel = new JPanel(new GridLayout(1,2));

        JLabel scoreText = new JLabel("Score: ", SwingConstants.RIGHT);
        scoreText.setFont(new Font("Serif", Font.PLAIN, 28));
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 28));
        scorePanel.add(scoreText);
        scorePanel.add(scoreLabel);

        JPanel gamePanel = new JPanel(new GridLayout(DECK_SIZE, DECK_SIZE));

        for(Map.Entry<Coordinates, String> cell: deck.entrySet()) {
            JButton button = new JButton();
            ButtonEvent buttonEvent = new ButtonEvent(button, cell);
            ButtonIconSetter.setButtonIconCovered(buttonEvent);
            button.addActionListener(buttonEvent);
            gamePanel.add(button);
        }

        frame = new JFrame("Memo game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(scorePanel, BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
