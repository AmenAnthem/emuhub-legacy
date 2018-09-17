package at.emuhub.games;

import at.emuhub.games.infos.EmuhubGameInfo;
import at.emuhub.games.infos.EmuhubGamesInfosConfigurationAdapter;
import at.emuhub.games.infos.EmuhubGamesInfosConfiguration;
import at.emuhub.systems.EmuhubSystem;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmuhubGamesRepository {

    public EmuhubGames findGamesFor(EmuhubSystem system) {
        Path romFolderPath = system.getRomFolderPath();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(romFolderPath, romPath -> {
            for (String fileExtension : system.getFileExtensions()) {
                if (romPath.toString().endsWith(fileExtension)) {
                    return true;
                }
            }
            return false;
        })) {
            List<EmuhubGame> games = new ArrayList<>();
            Map<String, EmuhubGameInfo> gamesInfos = new EmuhubGamesInfosConfigurationAdapter().adaptFrom(EmuhubGamesInfosConfiguration.read());
            for (Path romPath : directoryStream) {
                games.add(new EmuhubGame(romPath, gamesInfos.get(romPath.getFileName().toString())));
            }
            return new EmuhubGames(games);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}