package at.emuhub.core;

import at.emuhub.commands.screen.EmuhubCommandsScreenController;
import at.emuhub.games.screen.EmuhubGamesScreenController;
import at.emuhub.splash.screen.EmuhubSplashScreenController;
import at.emuhub.systems.screen.EmuhubSystemsScreenController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;

public enum EmuhubScreen {
    SPLASHSCREEN(EmuhubSplashScreenController.class),
    SYSTEMSSCREEN(EmuhubSystemsScreenController.class),
    GAMESSCREEN(EmuhubGamesScreenController.class),
    COMMANDSSCREEN(EmuhubCommandsScreenController.class);

    private final FXMLLoader fxmlLoader;

    EmuhubScreen(Class<? extends Initializable> controllerClass) {
        this.fxmlLoader = new FXMLLoader(controllerClass.getResource("screen.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public <T> T getRoot() {
        return fxmlLoader.getRoot();
    }

    public <T> T getController() {
        return fxmlLoader.getController();
    }
}