package at.emuhub.systems;

import at.emuhub.systems.EmuhubSystem;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class EmuhubSystemTest {

    @Test
    void test_builder_valid_object() {
        try {
            EmuhubSystem.builder().withId("test").withName("test").withRomFolderPath(Paths.get(System.getProperty("user.home")))
                    .withFileExtensions(List.of()).withCommands(List.of()).build();
        } catch (IllegalStateException e) {
            fail(e);
        }
    }

    @Test
    void test_builder_invalid_object_missing_id() {
        assertThrows(IllegalStateException.class,
                () -> EmuhubSystem.builder().withName("test").withRomFolderPath(Paths.get(System.getProperty("user.home")))
                        .withFileExtensions(List.of()).withCommands(List.of()).build());
    }

    @Test
    void test_builder_invalid_object_missing_name() {
        assertThrows(IllegalStateException.class,
                () -> EmuhubSystem.builder().withId("test").withRomFolderPath(Paths.get(System.getProperty("user.home")))
                        .withFileExtensions(List.of()).withCommands(List.of()).build());
    }

    @Test
    void test_builder_invalid_object_missing_romFolderPath() {
        assertThrows(IllegalStateException.class,
                () -> EmuhubSystem.builder().withId("test").withName("test").withFileExtensions(List.of()).withCommands(List.of()).build());
    }

    @Test
    void test_builder_invalid_object_missing_fileExtensions() {
        assertThrows(IllegalStateException.class,
                () -> EmuhubSystem.builder().withId("test").withName("test").withRomFolderPath(Paths.get(System.getProperty("user.home")))
                        .withCommands(List.of()).build() );
    }

    @Test
    void test_builder_invalid_object_missing_commant() {
        assertThrows(IllegalStateException.class,
                () -> EmuhubSystem.builder().withId("test").withName("test").withRomFolderPath(Paths.get(System.getProperty("user.home")))
                        .withFileExtensions(List.of()).build());
    }
}