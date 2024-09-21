package com.springboot.lms.Repository;

import com.springboot.lms.Entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookRepoTest {
    @Mock
    private BookRepo objBook;

    @Test
    void findByAuthorTest() {
        Book book1 = new Book(1, "book1", "autho1", "genre1", true);
        Book book2 = new Book(2, "book2", "autho2", "genre2", true);
        Book book3 = new Book(3, "book1", "autho1", "genre1", true);

        when(objBook.findByauthor("autho1")).thenReturn(book1);
        Book result = objBook.findByauthor("autho1");
        assertEquals("book1", result.getBookName());
        assertEquals("autho1", result.getAuthor());
    }

    @Test
    void findByBookNameTest() {
        Book book1 = new Book(1, "book1", "autho1", "genre1", true);
        Book book2 = new Book(2, "book2", "autho2", "genre2", true);
        Book book3 = new Book(3, "book1", "autho1", "genre1", true);

        when(objBook.findBybookName("book1")).thenReturn(book1);
        Book result = objBook.findBybookName("book1");
        assertEquals("book1", result.getBookName());
        assertEquals("autho1", result.getAuthor());
    }



    @Test
    void deleteByBookNameTest() {
        Book book1 = new Book(1, "book1", "autho1", "genre1", true);
        Book book2 = new Book(2, "book2", "autho2", "genre2", true);
        Book book3 = new Book(3, "book1", "autho1", "genre1", true);

        when(objBook.findBybookName("book1")).thenReturn(book1);
        objBook.deleteBybookName("book1");
        verify(objBook, times(1)).deleteBybookName("book1");
    }


}