package at.emuhub.gamepad;

import at.emuhub.core.VisibleForTesting;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class EmuhubGamepadControllers {

    private final Set<Controller> controllers;

    @VisibleForTesting
    EmuhubGamepadControllers(Set<Controller> controllers) {
        this.controllers = Collections.unmodifiableSet(controllers);
    }

    public static EmuhubGamepadControllers current() {
        Set<Controller> supportedControllers = new HashSet<>();
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (Controller controller : controllers) {
            if (controller.getType().equals(Controller.Type.GAMEPAD)) {
                supportedControllers.add(controller);
            }
        }
        return new EmuhubGamepadControllers(supportedControllers);
    }

    public Set<EmuhubGamepadButton> getCurrentlyPressedButtons() {
        Set<EmuhubGamepadButton> buttons = new HashSet<>();
        for (Controller controller : controllers) {
            if (controller.poll()) {
                for (Component component : controller.getComponents()) {
                    EmuhubGamepadButton.getCorrespondingPressedButton(component).ifPresent(buttons::add);
                }
            }
        }
        return Collections.unmodifiableSet(buttons);
    }
}