package at.emuhub.systems;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class EmuhubSystemsConfigurationAdapterTest {

    @Test
    void test_adaptFrom_configuration_with_one_system() {
        try {
            assertTrue(new EmuhubSystemsConfigurationAdapter().adaptFrom(new EmuhubSystemsConfiguration(
                    new JSONObject(new String(Files.readAllBytes(Paths.get(getClass().getResource("testconfig_onesystem.json").toURI()))))))
                    .getCurrentSystem().isPresent());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void test_adaptFrom_configuration_with_two_systems() {
        try {
            assertTrue(new EmuhubSystemsConfigurationAdapter().adaptFrom(new EmuhubSystemsConfiguration(new JSONObject(
                    new String(Files.readAllBytes(Paths.get(getClass().getResource("testconfig_twosystems.json").toURI()))))))
                    .getCurrentSystem().isPresent());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void test_adaptFrom_configuration_invalid() {
        assertThrows(IllegalStateException.class,
                () -> new EmuhubSystemsConfigurationAdapter().adaptFrom(new EmuhubSystemsConfiguration(new JSONObject("{}"))));
    }
}