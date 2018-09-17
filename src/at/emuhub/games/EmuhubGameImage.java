package at.emuhub.games;

import at.emuhub.games.infos.EmuhubGameInfo;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public enum EmuhubGameImage {
    SELECTION {
        @Override
        public Image find(EmuhubGame game) {
            Optional<EmuhubGameInfo> gameInfo = game.getGameInfo();
            if (gameInfo.isPresent()) {
                return find(gameInfo.get().getRomName());
            }
            return find(game.getRomPath().getFileName().toString());
        }
    };

    protected Image find(String romName) {
        Path imagePath = Paths.get(System.getProperty("user.home"), "emuhub", "images", "games", romName + name().toLowerCase() + ".png");
        try {
            if (!Files.exists(imagePath)) {
                Files.copy(getClass().getResourceAsStream("no" + name().toLowerCase() + "imagefound.png"), imagePath);
            }
            return new Image(Files.newInputStream(imagePath));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public abstract Image find(EmuhubGame game);
}