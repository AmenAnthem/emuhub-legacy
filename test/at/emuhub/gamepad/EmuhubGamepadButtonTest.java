package at.emuhub.gamepad;

import net.java.games.input.Component;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmuhubGamepadButtonTest {

    @Test
    void test_getCorrespondingPressedButton_supported_button_pressed() {
        Component component = mock(Component.class);
        when(component.getIdentifier()).thenReturn(Component.Identifier.Button._0);
        when(component.getPollData()).thenReturn(1.0f);
        assertTrue(EmuhubGamepadButton.getCorrespondingPressedButton(component).isPresent());
    }

    @Test
    void test_getCorrespondingPressedButton_supported_button_not_pressed() {
        Component component = mock(Component.class);
        when(component.getIdentifier()).thenReturn(Component.Identifier.Button._0);
        when(component.getPollData()).thenReturn(0.0f);
        assertFalse(EmuhubGamepadButton.getCorrespondingPressedButton(component).isPresent());
    }

    @Test
    void test_getCorrespondingPressedButton_unsuppoted_button() {
        Component component = mock(Component.class);
        when(component.getIdentifier()).thenReturn(Component.Identifier.Button._31);
        assertFalse(EmuhubGamepadButton.getCorrespondingPressedButton(component).isPresent());
    }
}