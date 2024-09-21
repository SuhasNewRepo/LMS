package com.springboot.lms.Entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;

public class LibraryTest {

    @Test
    void testGettersAndSetters() {
        // Create a new Library object
        Library library = new Library();

        // Set values using setter methods
        library.setId(1L);
        library.setUsername("Test User");
        library.setBookName("Test Book");
        library.setDate(LocalDate.now());
        library.setDueDate(LocalDate.now().plusDays(7));

        // Verify values using getter methods
        assertEquals(1L, library.getId());
        assertEquals("Test User", library.getUsername());
        assertEquals("Test Book", library.getBookName());
        assertEquals(LocalDate.now(), library.getDate());
        assertEquals(LocalDate.now().plusDays(7), library.getDueDate());

        // Verify that the object is not null
        assertNotNull(library);
    }
}