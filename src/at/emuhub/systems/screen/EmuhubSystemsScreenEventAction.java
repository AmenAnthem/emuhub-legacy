package at.emuhub.systems.screen;

import at.emuhub.gamepad.EmuhubGamepadButton;
import at.emuhub.gamepad.event.EmuhubGamepadEvent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Optional;
import java.util.Set;

enum EmuhubSystemsScreenEventAction {
    SELECT_SYSTEM(Set.of(KeyCode.ENTER, KeyCode.SPACE), Set.of(EmuhubGamepadButton.A)) {
        @Override
        void execute(EmuhubSystemsScreenController controller) {
            controller.gotoGamesScreen();
        }
    },
    GOTO_PREVIOUS_SYSTEM(Set.of(KeyCode.LEFT, KeyCode.A), Set.of(EmuhubGamepadButton.LEFT)) {
        @Override
        void execute(EmuhubSystemsScreenController controller) {
            controller.gotoPreviousSystem();
        }
    },
    GOTO_NEXT_SYSTEM(Set.of(KeyCode.RIGHT, KeyCode.D), Set.of(EmuhubGamepadButton.RIGHT)) {
        @Override
        void execute(EmuhubSystemsScreenController controller) {
            controller.gotoNextSystem();
        }
    },
    EXIT(Set.of(KeyCode.ESCAPE, KeyCode.BACK_SPACE), Set.of(EmuhubGamepadButton.B)) {
        @Override
        void execute(EmuhubSystemsScreenController controller) {
            controller.exitEmuhub();
        }
    };

    private final Set<KeyCode> keyCodes;
    private final Set<EmuhubGamepadButton> gamepadButtons;

    EmuhubSystemsScreenEventAction(Set<KeyCode> keyCodes, Set<EmuhubGamepadButton> gamepadButtons) {
        this.keyCodes = keyCodes;
        this.gamepadButtons = gamepadButtons;
    }

    static Optional<EmuhubSystemsScreenEventAction> getCorrespondingAction(InputEvent inputEvent) {
        for (EmuhubSystemsScreenEventAction action : values()) {
            if (isCorrespondingGamepadEvent(inputEvent, action) || isCorrespondingKeyEvent(inputEvent, action)) {
                return Optional.of(action);
            }
        }
        return Optional.empty();
    }

    private static boolean isCorrespondingKeyEvent(InputEvent inputEvent, EmuhubSystemsScreenEventAction action) {
        return inputEvent instanceof KeyEvent && inputEvent.getEventType().equals(KeyEvent.KEY_PRESSED) &&
                action.keyCodes.contains(((KeyEvent) inputEvent).getCode());
    }

    private static boolean isCorrespondingGamepadEvent(InputEvent inputEvent, EmuhubSystemsScreenEventAction action) {
        return inputEvent instanceof EmuhubGamepadEvent && inputEvent.getEventType().equals(EmuhubGamepadEvent.PRESSED) &&
                action.gamepadButtons.contains(((EmuhubGamepadEvent) inputEvent).getCode());
    }

    abstract void execute(EmuhubSystemsScreenController controller);
}