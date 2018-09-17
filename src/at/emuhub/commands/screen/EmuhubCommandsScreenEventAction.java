package at.emuhub.commands.screen;

import at.emuhub.gamepad.EmuhubGamepadButton;
import at.emuhub.gamepad.event.EmuhubGamepadEvent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Optional;
import java.util.Set;

enum EmuhubCommandsScreenEventAction {
    SELECT_COMMAND(Set.of(KeyCode.ENTER, KeyCode.SPACE), Set.of(EmuhubGamepadButton.A)) {
        @Override
        void execute(EmuhubCommandsScreenController controller) {
            controller.startSelectedCommand();
        }
    },
    GOTO_PREVIOUS_COMMAND(Set.of(KeyCode.UP, KeyCode.W), Set.of(EmuhubGamepadButton.UP)) {
        @Override
        void execute(EmuhubCommandsScreenController controller) {
            controller.gotoPreviousCommand();
        }
    },
    GOTO_NEXT_COMMAND(Set.of(KeyCode.DOWN, KeyCode.S), Set.of(EmuhubGamepadButton.DOWN)) {
        @Override
        void execute(EmuhubCommandsScreenController controller) {
            controller.gotoNextCommand();
        }
    },
    BACK(Set.of(KeyCode.ESCAPE, KeyCode.BACK_SPACE), Set.of(EmuhubGamepadButton.B)) {
        @Override
        void execute(EmuhubCommandsScreenController controller) {
            controller.gotoGamesScreen();
        }
    };

    private final Set<KeyCode> keyCodes;
    private final Set<EmuhubGamepadButton> gamepadButtons;

    EmuhubCommandsScreenEventAction(Set<KeyCode> keyCodes, Set<EmuhubGamepadButton> gamepadButtons) {
        this.keyCodes = keyCodes;
        this.gamepadButtons = gamepadButtons;
    }

    static Optional<EmuhubCommandsScreenEventAction> getCorrespondingAction(InputEvent inputEvent) {
        for (EmuhubCommandsScreenEventAction action : values()) {
            if (isCorrespondingKeyEvent(inputEvent, action) || isCorrespondingGamepadEvent(inputEvent, action)) {
                return Optional.of(action);
            }
        }
        return Optional.empty();
    }

    private static boolean isCorrespondingKeyEvent(InputEvent inputEvent, EmuhubCommandsScreenEventAction action) {
        return inputEvent instanceof KeyEvent && inputEvent.getEventType().equals(KeyEvent.KEY_PRESSED) &&
                action.keyCodes.contains(((KeyEvent) inputEvent).getCode());
    }

    private static boolean isCorrespondingGamepadEvent(InputEvent inputEvent, EmuhubCommandsScreenEventAction action) {
        return inputEvent instanceof EmuhubGamepadEvent && inputEvent.getEventType().equals(EmuhubGamepadEvent.PRESSED) &&
                action.gamepadButtons.contains(((EmuhubGamepadEvent) inputEvent).getCode());
    }

    abstract void execute(EmuhubCommandsScreenController controller);
}