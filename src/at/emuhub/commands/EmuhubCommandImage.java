package at.emuhub.commands;

import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public enum EmuhubCommandImage {
    SELECTION {
        @Override
        public Image find(EmuhubCommand command) {
            return find(command.getName());
        }
    };

    protected Image find(String commandName) {
        Path imagePath =
                Paths.get(System.getProperty("user.home"), "emuhub", "images", "commands", commandName + name().toLowerCase() + ".png");
        try {
            if (!Files.exists(imagePath)) {
                Files.copy(getClass().getResourceAsStream("no" + name().toLowerCase() + "imagefound.png"), imagePath);
            }
            return new Image(Files.newInputStream(imagePath));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public abstract Image find(EmuhubCommand command);
}