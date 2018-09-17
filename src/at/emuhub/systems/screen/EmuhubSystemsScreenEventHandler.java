package at.emuhub.systems.screen;

import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

class EmuhubSystemsScreenEventHandler implements EventHandler<InputEvent> {

    private final EmuhubSystemsScreenController controller;

    EmuhubSystemsScreenEventHandler(EmuhubSystemsScreenController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(InputEvent inputEvent) {
        EmuhubSystemsScreenEventAction.getCorrespondingAction(inputEvent).ifPresent(action -> action.execute(controller));
    }
}