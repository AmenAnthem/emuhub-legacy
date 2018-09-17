package at.emuhub.games.infos;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class EmuhubGamesInfosConfigurationAdapterTest {

    @Test
    void test_adaptFrom_configuration_with_one_game() {
        try {
            assertEquals(1, new EmuhubGamesInfosConfigurationAdapter().adaptFrom(new EmuhubGamesInfosConfiguration(
                    new JSONObject(new String(Files.readAllBytes(Paths.get(getClass().getResource("testconfig_onegame.json").toURI()))))))
                    .size());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void test_adaptFrom_configuration_with_many_games() {
        try {
            assertEquals(30, new EmuhubGamesInfosConfigurationAdapter().adaptFrom(new EmuhubGamesInfosConfiguration(new JSONObject(
                    new String(Files.readAllBytes(Paths.get(getClass().getResource("testconfig_manygames.json").toURI())))))).size());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void test_adaptFrom_configuration_invalid() {
        assertThrows(IllegalStateException.class,
                () -> new EmuhubGamesInfosConfigurationAdapter().adaptFrom(new EmuhubGamesInfosConfiguration(new JSONObject("{}"))));
    }
}