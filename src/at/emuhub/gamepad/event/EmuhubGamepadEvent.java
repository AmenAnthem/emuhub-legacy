package at.emuhub.gamepad.event;

import at.emuhub.gamepad.EmuhubGamepadButton;
import javafx.event.EventType;
import javafx.scene.input.InputEvent;

public class EmuhubGamepadEvent extends InputEvent {

    public static EventType<EmuhubGamepadEvent> PRESSED = new EventType<>(InputEvent.ANY, "PRESSED");

    private final EmuhubGamepadButton code;

    private EmuhubGamepadEvent(EventType<EmuhubGamepadEvent> eventType, EmuhubGamepadButton code) {
        super(eventType);
        this.code = code;
    }

    static EmuhubGamepadEvent pressed(EmuhubGamepadButton code) {
        return new EmuhubGamepadEvent(PRESSED, code);
    }

    public EmuhubGamepadButton getCode() {
        return code;
    }
}