package at.emuhub.systems;

import at.emuhub.commands.EmuhubCommand;

import java.nio.file.Path;
import java.util.Collection;

public class EmuhubSystem {

    private String id;
    private String name;
    private Path romFolderPath;
    private Collection<String> fileExtensions;
    private Collection<EmuhubCommand> commands;

    private EmuhubSystem() {
        //
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Path getRomFolderPath() {
        return romFolderPath;
    }

    public Collection<String> getFileExtensions() {
        return fileExtensions;
    }

    public Collection<EmuhubCommand> getCommands() {
        return commands;
    }

    static Builder builder() {
        return new Builder();
    }

    static class Builder {

        private EmuhubSystem system;

        private Builder() {
            this.system = new EmuhubSystem();
        }

        Builder withId(String id) {
            system.id = id;
            return this;
        }

        Builder withName(String name) {
            system.name = name;
            return this;
        }

        Builder withRomFolderPath(Path romFolderPath) {
            system.romFolderPath = romFolderPath;
            return this;
        }

        Builder withFileExtensions(Collection<String> fileExtensions) {
            system.fileExtensions = fileExtensions;
            return this;
        }

        Builder withCommands(Collection<EmuhubCommand> commands) {
            system.commands = commands;
            return this;
        }

        EmuhubSystem build() {
            if (system.id == null || system.name == null || system.romFolderPath == null || system.fileExtensions == null ||
                    system.commands == null) {
                throw new IllegalStateException("System isn't configured properly!");
            }
            return system;
        }
    }
}