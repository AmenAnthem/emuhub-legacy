package at.emuhub.core;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class EmuhubApplicationExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        logError(throwable);
        if (Platform.isFxApplicationThread()) {
            showErrorAlert("An error occured, see the log for details.");
        }
    }

    private void logError(Throwable throwable) {
        Logger logger = Logger.getLogger("EmuhubLog");
        try {
            FileHandler fileHandler = new FileHandler("emuhub.logger");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.log(Level.SEVERE, throwable.getMessage(), throwable);
        } catch (IOException e) {
            if (Platform.isFxApplicationThread()) {
                showErrorAlert("An error occured which could not be written to the logger.");
            }
        }
    }

    private void showErrorAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, text);
        alert.showAndWait();
        System.exit(0);
    }
}