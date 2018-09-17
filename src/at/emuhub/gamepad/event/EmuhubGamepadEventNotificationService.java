package at.emuhub.gamepad.event;

import at.emuhub.core.VisibleForTesting;
import at.emuhub.gamepad.EmuhubGamepadButton;
import at.emuhub.gamepad.EmuhubGamepadControllers;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;

class EmuhubGamepadEventNotificationService extends ScheduledService<Set<EmuhubGamepadButton>> {

    private static final Duration DEFAULT_PERIOD = Duration.millis(40);

    private final EmuhubGamepadControllers controllers;

    private EmuhubGamepadEventNotificationService(EmuhubGamepadControllers controllers) {
        this.controllers = controllers;
    }

    static EmuhubGamepadEventNotificationService createService(EmuhubGamepadControllers controllers, Scene scene) {
        EmuhubGamepadEventNotificationService notificationService = new EmuhubGamepadEventNotificationService(controllers);
        notificationService.setPeriod(DEFAULT_PERIOD);
        notificationService.setOnFailed(stateEvent -> {
            throw new IllegalStateException(notificationService.getException());
        });
        notificationService.setOnSucceeded(stateEvent -> {
            Node focusOwner = scene.getFocusOwner();
            if (focusOwner != null) {
                Set<EmuhubGamepadEvent> events = createEvents(notificationService.getLastValue(), notificationService.getValue());
                for (EmuhubGamepadEvent event : events) {
                    focusOwner.fireEvent(event);
                }
            }
        });
        return notificationService;
    }

    @VisibleForTesting
    static Set<EmuhubGamepadEvent> createEvents(Set<EmuhubGamepadButton> previouslyPressedButtons,
            Set<EmuhubGamepadButton> currentlyPressedButtons) {
        Set<EmuhubGamepadEvent> events = new HashSet<>();
        for (EmuhubGamepadButton pressedButton : currentlyPressedButtons) {
            if (!previouslyPressedButtons.contains(pressedButton)) {
                events.add(EmuhubGamepadEvent.pressed(pressedButton));
            }
        }
        return events;
    }

    @Override
    protected Task<Set<EmuhubGamepadButton>> createTask() {
        return new Task<>() {
            @Override
            protected Set<EmuhubGamepadButton> call() {
                return controllers.getCurrentlyPressedButtons();
            }
        };
    }
}