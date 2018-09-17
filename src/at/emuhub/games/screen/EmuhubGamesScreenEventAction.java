package at.emuhub.games.screen;

import at.emuhub.gamepad.EmuhubGamepadButton;
import at.emuhub.gamepad.event.EmuhubGamepadEvent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Optional;
import java.util.Set;

enum EmuhubGamesScreenEventAction {
    SELECT_GAME(Set.of(KeyCode.ENTER, KeyCode.SPACE), Set.of(EmuhubGamepadButton.A)) {
        @Override
        void execute(EmuhubGamesScreenController controller) {
            controller.gotoCommandsScreen();
        }
    },
    GOTO_PREVIOUS_GAME(Set.of(KeyCode.UP, KeyCode.W), Set.of(EmuhubGamepadButton.UP)) {
        @Override
        void execute(EmuhubGamesScreenController controller) {
            controller.gotoPreviousGame();
        }
    },
    GOTO_NEXT_GAME(Set.of(KeyCode.DOWN, KeyCode.S), Set.of(EmuhubGamepadButton.DOWN)) {
        @Override
        void execute(EmuhubGamesScreenController controller) {
            controller.gotoNextGame();
        }
    },
    SKIP_10_GAMES_BACKWARD(Set.of(KeyCode.PAGE_UP), Set.of(EmuhubGamepadButton.LEFT)) {
        @Override
        void execute(EmuhubGamesScreenController controller) {
            for (int i = 0; i < SKIP_GAME_SIZE; i++) {
                controller.gotoPreviousGame();
            }
        }
    },
    SKIP_10_GAMES_FORWARD(Set.of(KeyCode.PAGE_DOWN), Set.of(EmuhubGamepadButton.RIGHT)) {
        @Override
        void execute(EmuhubGamesScreenController controller) {
            for (int i = 0; i < SKIP_GAME_SIZE; i++) {
                controller.gotoNextGame();
            }
        }
    },
    BACK(Set.of(KeyCode.ESCAPE, KeyCode.BACK_SPACE), Set.of(EmuhubGamepadButton.B)) {
        @Override
        void execute(EmuhubGamesScreenController controller) {
            controller.gotoSystemsScreen();
        }
    };

    private static final int SKIP_GAME_SIZE = 10;

    private final Set<KeyCode> keyCodes;
    private final Set<EmuhubGamepadButton> gamepadButtons;

    EmuhubGamesScreenEventAction(Set<KeyCode> keyCodes, Set<EmuhubGamepadButton> gamepadButtons) {
        this.keyCodes = keyCodes;
        this.gamepadButtons = gamepadButtons;
    }

    static Optional<EmuhubGamesScreenEventAction> getCorrespondingAction(InputEvent inputEvent) {
        for (EmuhubGamesScreenEventAction action : values()) {
            if (isCorrespondingKeyEvent(inputEvent, action) || isCorrespondingGamepadEvent(inputEvent, action)) {
                return Optional.of(action);
            }
        }
        return Optional.empty();
    }

    private static boolean isCorrespondingKeyEvent(InputEvent inputEvent, EmuhubGamesScreenEventAction action) {
        return inputEvent instanceof KeyEvent && inputEvent.getEventType().equals(KeyEvent.KEY_PRESSED) &&
                action.keyCodes.contains(((KeyEvent) inputEvent).getCode());
    }

    private static boolean isCorrespondingGamepadEvent(InputEvent inputEvent, EmuhubGamesScreenEventAction action) {
        return inputEvent instanceof EmuhubGamepadEvent && inputEvent.getEventType().equals(EmuhubGamepadEvent.PRESSED) &&
                action.gamepadButtons.contains(((EmuhubGamepadEvent) inputEvent).getCode());
    }

    abstract void execute(EmuhubGamesScreenController controller);
}