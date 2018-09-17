package at.emuhub.games;

import at.emuhub.games.infos.EmuhubGameInfo;

import java.nio.file.Path;
import java.util.Optional;

public class EmuhubGame {

    private final Path romPath;
    private final EmuhubGameInfo gameInfo;

    EmuhubGame(Path romPath, EmuhubGameInfo gameInfo) {
        this.romPath = romPath;
        this.gameInfo = gameInfo;
    }

    public Path getRomPath() {
        return romPath;
    }

    public Optional<EmuhubGameInfo> getGameInfo() {
        return Optional.ofNullable(gameInfo);
    }
}