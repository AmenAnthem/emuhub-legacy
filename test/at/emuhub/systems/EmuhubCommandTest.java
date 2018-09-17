package at.emuhub.systems;

import at.emuhub.systems.EmuhubSystemCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmuhubCommandTest {

    @Test
    void test_cleanUp_with_homepath() {
        assertEquals(System.getProperty("user.home") + "/test", EmuhubSystemCommand.cleanUp("%HOMEPATH%/test"));
    }

    @Test
    void test_cleanUp_without_homepath() {
        assertEquals("/test", EmuhubSystemCommand.cleanUp("/test"));
    }
}