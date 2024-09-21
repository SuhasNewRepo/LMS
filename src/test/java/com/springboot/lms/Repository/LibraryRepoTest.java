package com.springboot.lms.Repository;

import com.springboot.lms.Entity.Library;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class LibraryRepoTest {



        @Mock
        private LibraryRepo objLibrary;


    @Test
    void findByDueDateBeforeTest() {
        Library library1 = new Library(1, "user1", "book1", LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 15));
        Library library2 = new Library(2, "user2", "book2", LocalDate.of(2022, 1, 5), LocalDate.of(2022, 1, 20));
        Library library3 = new Library(3, "user3", "book3", LocalDate.of(2022, 1, 10), LocalDate.of(2022, 1, 25));

        when(objLibrary.findByDueDateBefore(LocalDate.of(2022, 1, 20))).thenReturn(Arrays.asList(library1, library2));
        List<Library> result = objLibrary.findByDueDateBefore(LocalDate.of(2022, 1, 20));

        assertEquals(2, result.size());
        assertEquals("book1", result.get(0).getBookName());
        assertEquals("book2", result.get(1).getBookName());
    }
    @Test
    void findByBookNameTest() {
        Library library1 = new Library(1, "user1", "book1", LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 15));
        Library library2 = new Library(2, "user2", "book2", LocalDate.of(2022, 1, 5), LocalDate.of(2022, 1, 20));
        Library library3 = new Library(3, "user3", "book1", LocalDate.of(2022, 1, 10), LocalDate.of(2022, 1, 25));

        when(objLibrary.findBybookName("book1")).thenReturn(library1);
        Library result = objLibrary.findBybookName("book1");

        assertNotNull(result);
        assertEquals("book1", result.getBookName());
        assertEquals("user1", result.getUsername());
    }



}