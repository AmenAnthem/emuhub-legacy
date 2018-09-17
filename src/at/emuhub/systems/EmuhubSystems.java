package at.emuhub.systems;

import at.emuhub.core.VisibleForTesting;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmuhubSystems {

    private final List<EmuhubSystem> systems;
    private int currentIndex = 0;

    @VisibleForTesting
    EmuhubSystems(List<EmuhubSystem> systems) {
        this.systems = Collections.unmodifiableList(systems);
    }

    public static EmuhubSystems parseFrom(EmuhubSystemsConfiguration configuration) {
        return new EmuhubSystemsConfigurationAdapter().adaptFrom(configuration);
    }

    public Optional<EmuhubSystem> getCurrentSystem() {
        if (systems.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(systems.get(currentIndex));
    }

    public Optional<EmuhubSystem> getPreviousSystem() {
        if (systems.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(systems.get(currentIndex == 0 ? systems.size() - 1 : currentIndex - 1));
    }

    public Optional<EmuhubSystem> getNextSystem() {
        if (systems.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(systems.get(currentIndex == systems.size() - 1 ? 0 : currentIndex + 1));
    }

    public void previous() {
        if (systems.isEmpty()) {
            currentIndex = 0;
            return;
        }
        if (currentIndex <= 0) {
            currentIndex = systems.size() - 1;
            return;
        }
        currentIndex--;
    }

    public void next() {
        if (systems.isEmpty()) {
            currentIndex = 0;
            return;
        }
        if (currentIndex >= systems.size() - 1) {
            currentIndex = 0;
            return;
        }
        currentIndex++;
    }
}