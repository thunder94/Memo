package pl.games;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import java.util.*;

class DeckGeneratorTest {

    @Test
    void generateDeckTest() {
        Map<Coordinates, String> deck = DeckGenerator.generateDeck(4);

        List<Coordinates> coordinates = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                coordinates.add(new Coordinates(i, j));
            }
        }

        Iterator<Coordinates> actual = deck.keySet().iterator();
        Iterator<Coordinates> expected = coordinates.iterator();
        while(actual.hasNext()) {
            if(expected.hasNext()) {
                int expectedX = expected.next().getX();
                int expectedY = expected.next().getY();
                int actualX = actual.next().getX();
                int actualY = actual.next().getY();
                assertEquals(expectedX, actualX);
                assertEquals(expectedY, actualY);
            } else {
                fail("Invalid deck size!");
            }
        }

        int[] imageOccurrences = new int[8];
        for(String value: deck.values()) {
            try {
                int number = Integer.parseInt(value.substring(value.length() - 1));
                imageOccurrences[number]++;
            } catch(NumberFormatException e) {
                fail("Invalid image name!");
            }
        }
        for(int i = 0; i < 8; i++) {
            if(imageOccurrences[i] != 2) {
                fail("Each image must repeat 2 times!");
            }
        }

    }

}
