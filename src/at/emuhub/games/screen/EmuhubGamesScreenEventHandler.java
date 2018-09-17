package at.emuhub.games.screen;

import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

class EmuhubGamesScreenEventHandler implements EventHandler<InputEvent> {

    private final EmuhubGamesScreenController controller;

    EmuhubGamesScreenEventHandler(EmuhubGamesScreenController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(InputEvent inputEvent) {
        EmuhubGamesScreenEventAction.getCorrespondingAction(inputEvent).ifPresent(action -> action.execute(controller));
    }
}