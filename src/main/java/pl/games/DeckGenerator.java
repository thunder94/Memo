package pl.games;

import java.util.*;

import static java.util.Collections.shuffle;

public class DeckGenerator {

    public static Map<Coordinates, String> generateDeck(int deckSize) {

        //List of Coordinates [(0, 0), (0, 1), ... , (1, 0), ...]
        List<Coordinates> coordinatesList = new ArrayList<>();
        for(int i = 0; i < deckSize; i++) {
            for(int j = 0; j < deckSize; j++) {
                coordinatesList.add(new Coordinates(i, j));
            }
        }

        //Random permutation
        shuffle(coordinatesList);

        //Sort list when inserting to map: [(0, 0), (0, 1), ... , (1, 0), ...]
        Map<Coordinates, String> deck = new TreeMap<>(
                (c1, c2) -> {
                    if(c1.getX() > c2.getX()) return 1;
                    else if(c1.getX() < c2.getX()) return -1;
                    else {
                        return Integer.compare(c1.getY(), c2.getY());
                    }
                }
        );

        int halfSize = coordinatesList.size()/2;

        //Random pairs of cells with same image
        for(int i = 0; i < halfSize; i++) {
            deck.put(coordinatesList.get(i), "image"+i);
            deck.put(coordinatesList.get(i+halfSize), "image"+i);
        }

        return deck;

    }

}