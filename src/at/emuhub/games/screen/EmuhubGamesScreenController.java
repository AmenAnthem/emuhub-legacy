package at.emuhub.games.screen;

import at.emuhub.commands.screen.EmuhubCommandsScreenController;
import at.emuhub.core.EmuhubScreen;
import at.emuhub.games.EmuhubGame;
import at.emuhub.games.EmuhubGameImage;
import at.emuhub.games.EmuhubGamesRepository;
import at.emuhub.systems.EmuhubSystem;
import at.emuhub.systems.EmuhubSystemImage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EmuhubGamesScreenController implements Initializable {

    private EmuhubSystem system;

    @FXML
    private BorderPane root;

    @FXML
    private ImageView header;

    @FXML
    private ImageView gameImage;

    @FXML
    private ListView<EmuhubGame> gameList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.addEventHandler(InputEvent.ANY, new EmuhubGamesScreenEventHandler(this));
    }

    public void updateView(EmuhubSystem system) {
        this.system = system;
        header.setImage(EmuhubSystemImage.HEADER.find(system));
        gameList.setItems(new EmuhubGamesRepository().findGamesFor(system).observableList());
        gameList.setCellFactory(callback -> new EmuhubGamesScreenListCell());
        MultipleSelectionModel<EmuhubGame> selectionModel = gameList.getSelectionModel();
        selectionModel.selectFirst();
        gameList.scrollTo(selectionModel.getSelectedIndex());
        gameImage.setImage(EmuhubGameImage.SELECTION.find(selectionModel.getSelectedItem()));
    }

    void gotoCommandsScreen() {
        EmuhubScreen commandsScreen = EmuhubScreen.COMMANDSSCREEN;
        BorderPane commandsRoot = commandsScreen.getRoot();
        EmuhubCommandsScreenController commandsScreenController = commandsScreen.getController();
        root.getScene().setRoot(commandsRoot);
        commandsRoot.requestFocus();
        commandsScreenController.updateView(system, gameList.getSelectionModel().getSelectedItem().getRomPath());
    }

    void gotoSystemsScreen() {
        EmuhubScreen systemsScreen = EmuhubScreen.SYSTEMSSCREEN;
        BorderPane systemsRoot = systemsScreen.getRoot();
        root.getScene().setRoot(systemsRoot);
        systemsRoot.requestFocus();
    }

    void gotoPreviousGame() {
        MultipleSelectionModel<EmuhubGame> selectionModel = gameList.getSelectionModel();
        if (selectionModel.getSelectedIndex() > 0) {
            selectionModel.selectPrevious();
        } else {
            selectionModel.selectLast();
        }
        gameList.scrollTo(selectionModel.getSelectedIndex());
        gameImage.setImage(EmuhubGameImage.SELECTION.find(selectionModel.getSelectedItem()));
    }

    void gotoNextGame() {
        MultipleSelectionModel<EmuhubGame> selectionModel = gameList.getSelectionModel();
        if (selectionModel.getSelectedIndex() < gameList.getItems().size() - 1) {
            selectionModel.selectNext();
        } else {
            selectionModel.selectFirst();
        }
        gameList.scrollTo(selectionModel.getSelectedIndex());
        gameImage.setImage(EmuhubGameImage.SELECTION.find(selectionModel.getSelectedItem()));
    }
}
