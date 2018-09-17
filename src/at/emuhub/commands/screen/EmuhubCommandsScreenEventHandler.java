package at.emuhub.commands.screen;

import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

class EmuhubCommandsScreenEventHandler implements EventHandler<InputEvent> {

    private final EmuhubCommandsScreenController controller;

    EmuhubCommandsScreenEventHandler(EmuhubCommandsScreenController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(InputEvent inputEvent) {
        EmuhubCommandsScreenEventAction.getCorrespondingAction(inputEvent).ifPresent(action -> action.execute(controller));
    }
}