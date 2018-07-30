package pl.games;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ButtonIconSetterTest {

    @Test
    void setButtonIconUncoveredTest() {
        test("Uncovered");
    }

    @Test
    void setButtonIconCoveredTest() {
        test("Covered");
    }

    @Test
    void setButtonIconDisabledTest() {
        test("Disabled");
    }

    private void test(String function) {
        JButton button = new JButton();
        Map.Entry<Coordinates, String> cell = new Map.Entry<Coordinates, String>() {
            @Override
            public Coordinates getKey() {
                return new Coordinates(0,0);
            }

            @Override
            public String getValue() {
                return "image0";
            }

            @Override
            public String setValue(String value) {
                return null;
            }
        };
        ButtonEvent buttonEvent = new ButtonEvent(button, cell);
        switch(function) {
            case "Uncovered":
                ButtonIconSetter.setButtonIconUncovered(buttonEvent);
                break;
            case "Covered":
                ButtonIconSetter.setButtonIconCovered(buttonEvent);
                break;
            case "Disabled":
                ButtonIconSetter.setButtonIconDisabled(buttonEvent);
                break;
            default:
                fail("No such function");
        }
        assertNotEquals(null, buttonEvent.getButton().getIcon());
    }

}
