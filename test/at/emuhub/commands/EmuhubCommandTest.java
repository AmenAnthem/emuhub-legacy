package at.emuhub.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmuhubCommandTest {

    @Test
    void test_cleanUp_with_homepath() {
        assertEquals(System.getProperty("user.home") + "/test", EmuhubCommand.cleanUp("%HOMEPATH%/test"));
    }

    @Test
    void test_cleanUp_without_homepath() {
        assertEquals("/test", EmuhubCommand.cleanUp("/test"));
    }
}