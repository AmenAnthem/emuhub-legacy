package at.emuhub.commands;

import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class EmuhubCommandExecutor {

    private final EmuhubCommand command;
    private final Path romPath;
    private final Stage stage;

    private EmuhubCommandExecutor(EmuhubCommand command, Path romPath, Stage stage) {
        this.command = command;
        this.romPath = romPath;
        this.stage = stage;
    }

    public static EmuhubCommandExecutor createExecutorFor(EmuhubCommand command, Path romPath, Node node) {
        return new EmuhubCommandExecutor(command, romPath, (Stage) node.getScene().getWindow());
    }

    public void executeCommand() {
        ProcessBuilder processBuilder = command.buildProcess(romPath);
        try {
            stage.hide();
            Process process = processBuilder.start();
            process.getErrorStream().transferTo(System.out);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException(e);
        }
        stage.show();
        stage.requestFocus();
    }
}