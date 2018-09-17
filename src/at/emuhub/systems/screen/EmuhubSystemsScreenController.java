package at.emuhub.systems.screen;

import at.emuhub.core.EmuhubScreen;
import at.emuhub.games.screen.EmuhubGamesScreenController;
import at.emuhub.systems.EmuhubSystemImage;
import at.emuhub.systems.EmuhubSystems;
import at.emuhub.systems.EmuhubSystemsConfiguration;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EmuhubSystemsScreenController implements Initializable {

    private EmuhubSystems systems;

    @FXML
    private BorderPane root;

    @FXML
    private ImageView current;

    @FXML
    private ImageView previous;

    @FXML
    private ImageView next;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.addEventHandler(InputEvent.ANY, new EmuhubSystemsScreenEventHandler(this));
        systems = EmuhubSystems.parseFrom(EmuhubSystemsConfiguration.read());
        updateView();
    }

    private void updateView() {
        systems.getPreviousSystem().ifPresent(system -> previous.setImage(EmuhubSystemImage.SELECTION.find(system)));
        systems.getCurrentSystem().ifPresent(system -> current.setImage(EmuhubSystemImage.SELECTION.find(system)));
        systems.getNextSystem().ifPresent(system -> next.setImage(EmuhubSystemImage.SELECTION.find(system)));
    }

    void gotoGamesScreen() {
        EmuhubScreen gamesScreen = EmuhubScreen.GAMESSCREEN;
        BorderPane gamesRoot = gamesScreen.getRoot();
        EmuhubGamesScreenController gamesScreenController = gamesScreen.getController();
        root.getScene().setRoot(gamesRoot);
        gamesRoot.requestFocus();
        systems.getCurrentSystem().ifPresent(gamesScreenController::updateView);
    }

    void gotoPreviousSystem() {
        systems.previous();
        updateView();
    }

    void gotoNextSystem() {
        systems.next();
        updateView();
    }

    void exitEmuhub() {
        System.exit(0);
    }
}