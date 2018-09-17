package at.emuhub.gamepad.event;

import at.emuhub.gamepad.EmuhubGamepadButton;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmuhubGamepadEventNotificationServiceTest {

    @Test
    void test_fireEvents_button_is_being_pressed() {
        assertEquals(EmuhubGamepadButton.A,
                EmuhubGamepadEventNotificationService.createEvents(Set.of(), Set.of(EmuhubGamepadButton.A)).iterator().next().getCode());
    }

    @Test
    void test_fireEvents_button_is_being_held() {
        assertTrue(
                EmuhubGamepadEventNotificationService.createEvents(Set.of(EmuhubGamepadButton.A), Set.of(EmuhubGamepadButton.A)).isEmpty());
    }

    @Test
    void test_fireEvents_button_is_being_released() {
        assertTrue(EmuhubGamepadEventNotificationService.createEvents(Set.of(EmuhubGamepadButton.A), Set.of()).isEmpty());
    }

    @Test
    void test_fireEvents_no_button() {
        assertTrue(EmuhubGamepadEventNotificationService.createEvents(Set.of(), Set.of()).isEmpty());
    }
}