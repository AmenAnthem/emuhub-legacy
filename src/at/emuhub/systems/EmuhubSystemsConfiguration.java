package at.emuhub.systems;

import at.emuhub.core.VisibleForTesting;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EmuhubSystemsConfiguration {

    private final JSONObject json;

    @VisibleForTesting
    EmuhubSystemsConfiguration(JSONObject json) {
        this.json = json;
    }

    public static EmuhubSystemsConfiguration read() {
        Path path = Paths.get(System.getProperty("user.home"), "emuhub", "systems.json");
        try {
            if (!Files.exists(path)) {
                Files.copy(EmuhubSystemsConfiguration.class.getResourceAsStream("defaultsystems.json"), path);
            }
            return new EmuhubSystemsConfiguration(new JSONObject(new String(Files.readAllBytes(path))));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    JSONObject getJson() {
        return json;
    }
}