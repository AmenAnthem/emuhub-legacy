package at.emuhub.systems;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmuhubSystemsTest {

    @Test
    void test_getCurrentSystem_without_systems() {
        assertFalse(new EmuhubSystems(List.of()).getCurrentSystem().isPresent());
    }

    @Test
    void test_getCurrentSystem_with_one_system() {
        assertTrue(new EmuhubSystems(List.of(mock(EmuhubSystem.class))).getCurrentSystem().isPresent());
    }

    @Test
    void test_getCurrentSystem_with_two_systems() {
        EmuhubSystem system1 = mock(EmuhubSystem.class);
        when(system1.getId()).thenReturn("1");
        EmuhubSystem system2 = mock(EmuhubSystem.class);
        when(system2.getId()).thenReturn("2");
        Optional<EmuhubSystem> currentSystem = new EmuhubSystems(List.of(system1, system2)).getCurrentSystem();
        assertTrue(currentSystem.isPresent());
        assertEquals("1", currentSystem.get().getId());
    }

    @Test
    void test_getCurrentSystem_with_three_systems() {
        EmuhubSystem system1 = mock(EmuhubSystem.class);
        when(system1.getId()).thenReturn("1");
        EmuhubSystem system2 = mock(EmuhubSystem.class);
        when(system2.getId()).thenReturn("2");
        EmuhubSystem system3 = mock(EmuhubSystem.class);
        when(system3.getId()).thenReturn("3");
        Optional<EmuhubSystem> currentSystem = new EmuhubSystems(List.of(system1, system2, system3)).getCurrentSystem();
        assertTrue(currentSystem.isPresent());
        assertEquals("1", currentSystem.get().getId());
    }

    @Test
    void test_getPreviousSystem_without_systems() {
        assertFalse(new EmuhubSystems(List.of()).getPreviousSystem().isPresent());
    }

    @Test
    void test_getPreviousSystem_with_one_system() {
        assertTrue(new EmuhubSystems(List.of(mock(EmuhubSystem.class))).getPreviousSystem().isPresent());
    }

    @Test
    void test_getPreviousSystem_with_two_systems() {
        EmuhubSystem system1 = mock(EmuhubSystem.class);
        when(system1.getId()).thenReturn("1");
        EmuhubSystem system2 = mock(EmuhubSystem.class);
        when(system2.getId()).thenReturn("2");
        Optional<EmuhubSystem> previousSystem = new EmuhubSystems(List.of(system1, system2)).getPreviousSystem();
        assertTrue(previousSystem.isPresent());
        assertEquals("2", previousSystem.get().getId());
    }

    @Test
    void test_getPreviousSystem_with_three_systems() {
        EmuhubSystem system1 = mock(EmuhubSystem.class);
        when(system1.getId()).thenReturn("1");
        EmuhubSystem system2 = mock(EmuhubSystem.class);
        when(system2.getId()).thenReturn("2");
        EmuhubSystem system3 = mock(EmuhubSystem.class);
        when(system3.getId()).thenReturn("3");
        Optional<EmuhubSystem> previousSystem = new EmuhubSystems(List.of(system1, system2, system3)).getPreviousSystem();
        assertTrue(previousSystem.isPresent());
        assertEquals("3", previousSystem.get().getId());
    }

    @Test
    void test_getNextSystem_without_systems() {
        assertFalse(new EmuhubSystems(List.of()).getNextSystem().isPresent());
    }

    @Test
    void test_getNextSystem_with_one_system() {
        assertTrue(new EmuhubSystems(List.of(mock(EmuhubSystem.class))).getNextSystem().isPresent());
    }

    @Test
    void test_getNextSystem_with_two_systems() {
        EmuhubSystem system1 = mock(EmuhubSystem.class);
        when(system1.getId()).thenReturn("1");
        EmuhubSystem system2 = mock(EmuhubSystem.class);
        when(system2.getId()).thenReturn("2");
        Optional<EmuhubSystem> nextSystem = new EmuhubSystems(List.of(system1, system2)).getNextSystem();
        assertTrue(nextSystem.isPresent());
        assertEquals("2", nextSystem.get().getId());
    }

    @Test
    void test_getNextSystem_with_three_systems() {
        EmuhubSystem system1 = mock(EmuhubSystem.class);
        when(system1.getId()).thenReturn("1");
        EmuhubSystem system2 = mock(EmuhubSystem.class);
        when(system2.getId()).thenReturn("2");
        EmuhubSystem system3 = mock(EmuhubSystem.class);
        when(system3.getId()).thenReturn("3");
        Optional<EmuhubSystem> nextSystem = new EmuhubSystems(List.of(system1, system2, system3)).getNextSystem();
        assertTrue(nextSystem.isPresent());
        assertEquals("2", nextSystem.get().getId());
    }

    @Test
    void test_previous_without_systems() {
        EmuhubSystems systems = new EmuhubSystems(List.of());
        systems.previous();
        assertFalse(systems.getCurrentSystem().isPresent());
        assertFalse(systems.getPreviousSystem().isPresent());
        assertFalse(systems.getNextSystem().isPresent());
    }

    @Test
    void test_previous_with_two_systems() {
        EmuhubSystem system1 = mock(EmuhubSystem.class);
        when(system1.getId()).thenReturn("1");
        EmuhubSystem system2 = mock(EmuhubSystem.class);
        when(system2.getId()).thenReturn("2");
        EmuhubSystems systems = new EmuhubSystems(List.of(system1, system2));
        systems.previous();
        Optional<EmuhubSystem> currentSystem = systems.getCurrentSystem();
        assertTrue(currentSystem.isPresent());
        assertEquals("2", currentSystem.get().getId());
        Optional<EmuhubSystem> previousSystem = systems.getPreviousSystem();
        assertTrue(previousSystem.isPresent());
        assertEquals("1", previousSystem.get().getId());
        Optional<EmuhubSystem> nextSystem = systems.getNextSystem();
        assertTrue(nextSystem.isPresent());
        assertEquals("1", nextSystem.get().getId());
    }

    @Test
    void test_previous_with_three_systems() {
        EmuhubSystem system1 = mock(EmuhubSystem.class);
        when(system1.getId()).thenReturn("1");
        EmuhubSystem system2 = mock(EmuhubSystem.class);
        when(system2.getId()).thenReturn("2");
        EmuhubSystem system3 = mock(EmuhubSystem.class);
        when(system3.getId()).thenReturn("3");
        EmuhubSystems systems = new EmuhubSystems(List.of(system1, system2, system3));
        systems.previous();
        Optional<EmuhubSystem> currentSystem = systems.getCurrentSystem();
        assertTrue(currentSystem.isPresent());
        assertEquals("3", currentSystem.get().getId());
        Optional<EmuhubSystem> previousSystem = systems.getPreviousSystem();
        assertTrue(previousSystem.isPresent());
        assertEquals("2", previousSystem.get().getId());
        Optional<EmuhubSystem> nextSystem = systems.getNextSystem();
        assertTrue(nextSystem.isPresent());
        assertEquals("1", nextSystem.get().getId());
    }

    @Test
    void test_next_without_systems() {
        EmuhubSystems systems = new EmuhubSystems(List.of());
        systems.next();
        assertFalse(systems.getCurrentSystem().isPresent());
        assertFalse(systems.getPreviousSystem().isPresent());
        assertFalse(systems.getNextSystem().isPresent());
    }

    @Test
    void test_next_with_two_systems() {
        EmuhubSystem system1 = mock(EmuhubSystem.class);
        when(system1.getId()).thenReturn("1");
        EmuhubSystem system2 = mock(EmuhubSystem.class);
        when(system2.getId()).thenReturn("2");
        EmuhubSystems systems = new EmuhubSystems(List.of(system1, system2));
        systems.next();
        Optional<EmuhubSystem> currentSystem = systems.getCurrentSystem();
        assertTrue(currentSystem.isPresent());
        assertEquals("2", currentSystem.get().getId());
        Optional<EmuhubSystem> previousSystem = systems.getPreviousSystem();
        assertTrue(previousSystem.isPresent());
        assertEquals("1", previousSystem.get().getId());
        Optional<EmuhubSystem> nextSystem = systems.getNextSystem();
        assertTrue(nextSystem.isPresent());
        assertEquals("1", nextSystem.get().getId());
    }

    @Test
    void test_next_with_three_systems() {
        EmuhubSystem system1 = mock(EmuhubSystem.class);
        when(system1.getId()).thenReturn("1");
        EmuhubSystem system2 = mock(EmuhubSystem.class);
        when(system2.getId()).thenReturn("2");
        EmuhubSystem system3 = mock(EmuhubSystem.class);
        when(system3.getId()).thenReturn("3");
        EmuhubSystems systems = new EmuhubSystems(List.of(system1, system2, system3));
        systems.next();
        Optional<EmuhubSystem> currentSystem = systems.getCurrentSystem();
        assertTrue(currentSystem.isPresent());
        assertEquals("2", currentSystem.get().getId());
        Optional<EmuhubSystem> previousSystem = systems.getPreviousSystem();
        assertTrue(previousSystem.isPresent());
        assertEquals("1", previousSystem.get().getId());
        Optional<EmuhubSystem> nextSystem = systems.getNextSystem();
        assertTrue(nextSystem.isPresent());
        assertEquals("3", nextSystem.get().getId());
    }
}