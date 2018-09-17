package at.emuhub.gamepad.event;

import at.emuhub.gamepad.EmuhubGamepadControllers;
import javafx.scene.Scene;

public class EmuhubGamepadEventNotifier {

    private final EmuhubGamepadControllers controllers;

    public EmuhubGamepadEventNotifier(EmuhubGamepadControllers controllers) {
        this.controllers = controllers;
    }

    public void register(Scene scene) {
        EmuhubGamepadEventNotificationService.createService(controllers, scene).start();
    }
}