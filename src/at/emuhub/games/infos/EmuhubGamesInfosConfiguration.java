package at.emuhub.games.infos;

import at.emuhub.core.VisibleForTesting;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EmuhubGamesInfosConfiguration {

    private final JSONObject json;

    @VisibleForTesting
    EmuhubGamesInfosConfiguration(JSONObject json) {
        this.json = json;
    }

    public static EmuhubGamesInfosConfiguration read() {
        Path path = Paths.get(System.getProperty("user.home"), "emuhub", "gamesinfos.json");
        try {
            if (!Files.exists(path)) {
                Files.copy(EmuhubGamesInfosConfiguration.class.getResourceAsStream("defaultgamesinfos.json"), path);
            }
            return new EmuhubGamesInfosConfiguration(new JSONObject(new String(Files.readAllBytes(path))));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    JSONObject getJson() {
        return json;
    }
}