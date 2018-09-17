package at.emuhub.systems;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmuhubSystemsConfigurationTest {

    @Test
    void test_getJson_not_empty() {
        assertEquals("test", new EmuhubSystemsConfiguration(new JSONObject("{\"test\":\"test\"}")).getJson().getString("test"));
    }

    @Test
    void test_getJson_empty() {
        assertTrue(new EmuhubSystemsConfiguration(new JSONObject("{}")).getJson().isEmpty());
    }
}