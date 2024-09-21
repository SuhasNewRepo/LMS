package com.springboot.lms.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



    public class BookTest {

        @Test
        void testGettersAndSetters() {
            // Create a new Book object
            Book book = new Book();

            // Set values using setter methods
            book.setIsbnId(1L);
            book.setBookName("Test Book");
            book.setAuthor("Test Author");
            book.setGenre("Test Genre");
            book.setAvailabilityStatus(true);

            // Verify values using getter methods
            assertEquals(1L, book.getIsbnId());
            assertEquals("Test Book", book.getBookName());
            assertEquals("Test Author", book.getAuthor());
            assertEquals("Test Genre", book.getGenre());
            assertEquals(true, book.isAvailabilityStatus());

            // Verify that the object is not null
            assertNotNull(book);
        }
    }


