package at.emuhub.systems;

import at.emuhub.commands.EmuhubCommand;
import at.emuhub.core.VisibleForTesting;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class EmuhubSystemsConfigurationAdapter {

    EmuhubSystems adaptFrom(EmuhubSystemsConfiguration configuration) {
        try {
            JSONObject configurationJson = configuration.getJson();
            return new EmuhubSystems(getSystems(configurationJson.getJSONArray("systems")));
        } catch (JSONException e) {
            throw new IllegalStateException(e);
        }
    }

    @VisibleForTesting
    List<EmuhubSystem> getSystems(JSONArray systemsJson) {
        List<EmuhubSystem> systems = new ArrayList<>();
        for (int i = 0; i < systemsJson.length(); i++) {
            systems.add(getSystem(systemsJson.getJSONObject(i)));
        }
        return systems;
    }

    @VisibleForTesting
    EmuhubSystem getSystem(JSONObject systemJson) {
        return EmuhubSystem.builder().withId(systemJson.getString("id")).withName(systemJson.getString("name"))
                .withRomFolderPath(Paths.get(cleanUp(systemJson.getString("romFolderPath"))))
                .withFileExtensions(getFileExtensions(systemJson.getJSONArray("fileExtensions")))
                .withCommands(getCommands(systemJson.getJSONArray("commands"))).build();
    }

    @VisibleForTesting
    List<String> getFileExtensions(JSONArray fileExtensionsJson) {
        List<String> fileExtensions = new ArrayList<>();
        for (int i = 0; i < fileExtensionsJson.length(); i++) {
            fileExtensions.add(fileExtensionsJson.getString(i));
        }
        return fileExtensions;
    }

    @VisibleForTesting
    List<EmuhubCommand> getCommands(JSONArray commandsJson) {
        List<EmuhubCommand> commands = new ArrayList<>();
        for (int i = 0; i < commandsJson.length(); i++) {
            JSONObject commandJson = commandsJson.getJSONObject(i);
            List<String> arguments = getArguments(commandJson.getJSONArray("arguments"));
            commands.add(EmuhubCommand.createCommand(commandJson.getString("name"), commandJson.getString("command"), arguments));
        }
        return commands;
    }

    @VisibleForTesting
    List<String> getArguments(JSONArray argumentsJson) {
        List<String> arguments = new ArrayList<>();
        for (int i = 0; i < argumentsJson.length(); i++) {
            arguments.add(argumentsJson.getString(i));
        }
        return arguments;
    }

    @VisibleForTesting
    static String cleanUp(String string) {
        return string.replace("%HOMEPATH%", System.getProperty("user.home"));
    }
}
