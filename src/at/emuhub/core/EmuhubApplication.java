package at.emuhub.core;

import at.emuhub.gamepad.EmuhubGamepadControllers;
import at.emuhub.gamepad.event.EmuhubGamepadEventNotifier;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EmuhubApplication extends Application {

    @Override
    public void start(Stage stage) {
        Thread.setDefaultUncaughtExceptionHandler(new EmuhubApplicationExceptionHandler());
        initializeStage(stage);
        Scene mainScene = new Scene(EmuhubScreen.SPLASHSCREEN.getRoot());
        mainScene.setCursor(Cursor.NONE);
        stage.setScene(mainScene);
        stage.show();
        new EmuhubGamepadEventNotifier(EmuhubGamepadControllers.current()).register(mainScene);
    }

    private void initializeStage(Stage stage) {
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setTitle("Emuhub");
    }

    public static void main(String[] args) {
        launch(args);
    }
}