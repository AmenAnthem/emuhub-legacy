package at.emuhub.splash.screen;

import at.emuhub.core.EmuhubScreen;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class EmuhubSplashScreenController implements Initializable {

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            Pane pane = EmuhubScreen.SYSTEMSSCREEN.getRoot();
            root.getScene().setRoot(pane);
            pane.requestFocus();
        });
        pause.play();
    }
}