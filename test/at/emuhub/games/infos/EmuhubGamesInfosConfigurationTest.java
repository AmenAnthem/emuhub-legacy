package at.emuhub.games.infos;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmuhubGamesInfosConfigurationTest {

    @Test
    void test_getJson_not_empty() {
        assertEquals("test", new EmuhubGamesInfosConfiguration(new JSONObject("{\"test\":\"test\"}")).getJson().getString("test"));
    }

    @Test
    void test_getJson_empty() {
        assertTrue(new EmuhubGamesInfosConfiguration(new JSONObject("{}")).getJson().isEmpty());
    }
}