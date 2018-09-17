package at.emuhub.systems;

import at.emuhub.core.VisibleForTesting;

import java.nio.file.Path;
import java.util.*;

public class EmuhubSystemCommand {

    private final String name;
    private final String command;
    private final Collection<String> arguments;

    @VisibleForTesting
    EmuhubSystemCommand(String name, String command, Collection<String> arguments) {
        this.name = name;
        this.command = command;
        this.arguments = Collections.unmodifiableCollection(arguments);
    }

    static EmuhubSystemCommand createCommand(String name, String command, Collection<String> arguments) {
        List<String> cleanArguments = new ArrayList<>();
        for (String argument : arguments) {
            cleanArguments.add(cleanUp(argument));
        }
        return new EmuhubSystemCommand(name, cleanUp(command), cleanArguments);
    }

    public String getName() {
        return name;
    }

    public ProcessBuilder buildProcess(Path romPath) {
        List<String> commandWithArguments = new ArrayList<>();
        commandWithArguments.add(command);
        commandWithArguments.addAll(arguments);
        commandWithArguments.add(romPath.toString());
        return new ProcessBuilder(commandWithArguments);
    }

    @VisibleForTesting
    static String cleanUp(String string) {
        return string.replace("%HOMEPATH%", System.getProperty("user.home"));
    }
}