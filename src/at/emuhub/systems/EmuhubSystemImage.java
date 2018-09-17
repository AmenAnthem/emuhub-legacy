package at.emuhub.systems;

import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public enum EmuhubSystemImage {
    SELECTION {
        @Override
        public Image find(EmuhubSystem system) {
            return find(system.getId());
        }
    },
    HEADER {
        @Override
        public Image find(EmuhubSystem system) {
            return find(system.getId());
        }
    };

    protected Image find(String systemId) {
        Path imagePath =
                Paths.get(System.getProperty("user.home"), "emuhub", "images", "systems", systemId + name().toLowerCase() + ".png");
        try {
            if (!Files.exists(imagePath)) {
                Files.copy(getClass().getResourceAsStream("no" + name().toLowerCase() + "imagefound.png"), imagePath);
            }
            return new Image(Files.newInputStream(imagePath));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public abstract Image find(EmuhubSystem system);
}