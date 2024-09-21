package com.springboot.lms.Service;

import com.springboot.lms.Entity.Book;
import com.springboot.lms.Entity.Library;
import com.springboot.lms.Repository.BookRepo;
import com.springboot.lms.Repository.LibraryRepo;
import com.springboot.lms.dto.ResponseDto;
import com.springboot.lms.exception.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class serviceTest {
    @Mock
    private BookRepo objBook;//create mock
    @Mock
    private LibraryRepo objLibrary;
    @InjectMocks
    private service objServ; //repo mock injected on service

    @Test
    public void searchBookByTitleTest() {
        Book expectedBook = new Book(1, "book1", "autho1", "genre1", true);
        when(objBook.findBybookName("book1")).thenReturn(expectedBook);
        Book actualBook = objServ.searchBookByTitle("book1");
        assertEquals(expectedBook, actualBook);
    }

    @Test
    public void searchBookByAuthorTest() {
        Book expectedBook = new Book(1, "book1", "author1", "genre1", true);
        when(objBook.findByauthor("author1")).thenReturn(expectedBook);
        Book actualBook = objServ.searchBookByAuthor("author1");
        assertEquals(expectedBook, actualBook);
    }

    @Test
    public void getBookByIdTest() {
        Book expectedBook = new Book(1, "book1", "author1", "genre1", true);
        when(objBook.findById((long)1)).thenReturn(Optional.of(expectedBook));
        Optional<Book> actualBook = objServ.getBookById((long)1);
        assertEquals(expectedBook, actualBook.get());
    }


    @Test
    public void addBookTest() {
        Book newBook = new Book(4, "book4", "author3", "genre3", true);
        when(objBook.findBybookName("book4")).thenReturn(newBook);
        ResponseEntity<ResponseDto> response = objServ.addBook(newBook);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("201", response.getBody().getStatusCode());
        assertEquals("successfully added", response.getBody().getStatusMsg());
    }

    @Test
    public void getLibraryRecordsTest() {
        List<Library> expectedData = Arrays.asList(
                new Library(1, "user1", "book1", LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 15)),
                new Library(2, "user2", "book2", LocalDate.of(2022, 2, 1), LocalDate.of(2022, 2, 15))
        );
        when(objLibrary.findAll()).thenReturn(expectedData);
        List<Library> actualData = objServ.getLibraryRecords();
        assertEquals(expectedData, actualData);
    }


    @Test
    public void takeBookTest() {
        Book book = new Book(1, "book1", "autho1", "genre1", true);
        when(objBook.findBybookName("book1")).thenReturn(book);
        ResponseEntity<?> response = objServ.takeBook("book1", "user1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Library library = (Library) response.getBody();
        assertNotNull(library);
        assertFalse(book.isAvailabilityStatus());
    }
    @Test
    public void OverdueTest() {
        List<Library> data = Arrays.asList(
                new Library(1, "user1", "book1", LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 15)),
                new Library(2, "user2", "book2", LocalDate.of(2022, 2, 1), LocalDate.of(2022, 2, 15))
        );
        when(objLibrary.findByDueDateBefore(LocalDate.now())).thenReturn(data);
        ResponseEntity<Map<String, Long>> response = objServ.Overdue();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Long> overdue = response.getBody();
        assertNotNull(overdue);
        assertEquals(2, overdue.size());
    }

    @Test
    public void DeleteBookTest() {
        Book book = new Book(1, "book1", "autho1", "genre1", true);
        when(objBook.findBybookName("book1")).thenReturn(book);
        ResponseEntity<ResponseDto> response = objServ.DeleteBook("book1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseDto dto = response.getBody();
        assertNotNull(dto);
        assertEquals("204", dto.getStatusCode());
        assertEquals("successfully deleted", dto.getStatusMsg());
        verify(objBook, times(1)).deleteBybookName("book1");
    }

}