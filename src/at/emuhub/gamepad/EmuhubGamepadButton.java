package at.emuhub.gamepad;

import net.java.games.input.Component;

import java.util.Optional;

public enum EmuhubGamepadButton {
    UP(Component.Identifier.Axis.POV) {
        @Override
        protected boolean isPollDataMatching(float pollData) {
            return pollData == 0.25f;
        }
    },
    DOWN(Component.Identifier.Axis.POV) {
        @Override
        protected boolean isPollDataMatching(float pollData) {
            return pollData == 0.75f;
        }
    },
    LEFT(Component.Identifier.Axis.POV) {
        @Override
        protected boolean isPollDataMatching(float pollData) {
            return pollData == 1.0f;
        }
    },
    RIGHT(Component.Identifier.Axis.POV) {
        @Override
        protected boolean isPollDataMatching(float pollData) {
            return pollData == 0.5f;
        }
    },
    A(Component.Identifier.Button._0) {
        @Override
        protected boolean isPollDataMatching(float pollData) {
            return pollData > 0.0f;
        }
    },
    B(Component.Identifier.Button._1) {
        @Override
        protected boolean isPollDataMatching(float pollData) {
            return pollData > 0.0f;
        }
    };

    private final Component.Identifier identifier;

    EmuhubGamepadButton(Component.Identifier identifier) {
        this.identifier = identifier;
    }

    static Optional<EmuhubGamepadButton> getCorrespondingPressedButton(Component component) {
        for (EmuhubGamepadButton gamepadButton : values()) {
            if (gamepadButton.identifier.equals(component.getIdentifier()) && gamepadButton.isPollDataMatching(component.getPollData())) {
                return Optional.of(gamepadButton);
            }
        }
        return Optional.empty();
    }

    protected abstract boolean isPollDataMatching(float pollData);
}