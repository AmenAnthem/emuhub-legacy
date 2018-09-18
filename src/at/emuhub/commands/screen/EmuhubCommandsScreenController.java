package at.emuhub.commands.screen;

import at.emuhub.commands.EmuhubCommand;
import at.emuhub.commands.EmuhubCommandExecutor;
import at.emuhub.commands.EmuhubCommandImage;
import at.emuhub.core.EmuhubScreen;
import at.emuhub.systems.EmuhubSystem;
import at.emuhub.systems.EmuhubSystemImage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class EmuhubCommandsScreenController implements Initializable {

    private Path romPath;

    @FXML
    private BorderPane root;

    @FXML
    private ImageView header;

    @FXML
    private ImageView commandImage;

    @FXML
    private ListView<EmuhubCommand> commandList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.addEventHandler(InputEvent.ANY, new EmuhubCommandsScreenEventHandler(this));
    }

    public void updateView(EmuhubSystem system, Path romPath) {
        this.romPath = romPath;
        header.setImage(EmuhubSystemImage.HEADER.find(system));
        commandList.setItems(FXCollections.observableArrayList(system.getCommands()));
        commandList.setCellFactory(callback -> new EmuhubCommandsScreenListCell());
        MultipleSelectionModel<EmuhubCommand> selectionModel = commandList.getSelectionModel();
        selectionModel.selectFirst();
        commandList.scrollTo(selectionModel.getSelectedIndex());
        commandImage.setImage(EmuhubCommandImage.SELECTION.find(selectionModel.getSelectedItem()));
    }

    void startSelectedCommand() {
        EmuhubCommandExecutor.createExecutorFor(commandList.getSelectionModel().getSelectedItem(), romPath, root).executeCommand();
    }

    void gotoPreviousCommand() {
        MultipleSelectionModel<EmuhubCommand> selectionModel = commandList.getSelectionModel();
        if (selectionModel.getSelectedIndex() > 0) {
            selectionModel.selectPrevious();
        } else {
            selectionModel.selectLast();
        }
        commandList.scrollTo(commandList.getSelectionModel().getSelectedIndex());
        commandImage.setImage(EmuhubCommandImage.SELECTION.find(selectionModel.getSelectedItem()));
    }

    void gotoNextCommand() {
        MultipleSelectionModel<EmuhubCommand> selectionModel = commandList.getSelectionModel();
        if (selectionModel.getSelectedIndex() < commandList.getItems().size() - 1) {
            selectionModel.selectNext();
        } else {
            selectionModel.selectFirst();
        }
        commandList.scrollTo(commandList.getSelectionModel().getSelectedIndex());
        commandImage.setImage(EmuhubCommandImage.SELECTION.find(selectionModel.getSelectedItem()));
    }

    void gotoGamesScreen() {
        EmuhubScreen gamesScreen = EmuhubScreen.GAMESSCREEN;
        BorderPane systemsRoot = gamesScreen.getRoot();
        root.getScene().setRoot(systemsRoot);
        systemsRoot.requestFocus();
    }
}