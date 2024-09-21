package com.springboot.lms.MainController;

import com.springboot.lms.Entity.Book;
import com.springboot.lms.Entity.Library;
import com.springboot.lms.Service.service;
import com.springboot.lms.dto.ResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {
    @Mock
    private service objServ;

    @InjectMocks//need to use this
    private MainController objContr;

    @Test
    void addBookTest() {
        Book book1 = new Book(1, "book1", "autho1", "genre1", true);
        ResponseDto responseDto = new ResponseDto("200", "Book added successfully");
        ResponseEntity<ResponseDto> responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        when(objServ.addBook(book1)).thenReturn(responseEntity);

        ResponseEntity<ResponseDto> result = objContr.addBook(book1);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(responseDto, result.getBody());
    }


    @Test
    void getAllBooksTest() {
        List<Book> books = Arrays.asList(new Book(1, "book1", "author1", "genre1", true), new Book(2, "book2", "author2", "genre2", false));
        when(objServ.getallBooks()).thenReturn(books);

        List<Book> result = objContr.getAllBooks();

        assertNotNull(result);
        assertEquals(books, result);
    }

    @Test
    void searchBookByTitleTest() {
        Book book = new Book(1, "book1", "author1", "genre1", true);
        when(objServ.searchBookByTitle("book1")).thenReturn(book);

        Book result = objContr.searchBookByTitle("book1");

        assertNotNull(result);
        assertEquals(book, result);
    }

    @Test
    void searchBookByAuthorTest() {
        Book book = new Book(1, "book1", "author1", "genre1", true);
        when(objServ.searchBookByAuthor("author1")).thenReturn(book);

        Book result = objContr.searchBookByAuthor("author1");

        assertNotNull(result);
        assertEquals(book, result);
    }

    @Test
    void getBookByIdTest() {
        Book book = new Book(1, "book1", "author1", "genre1", true);
        when(objServ.getBookById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = objContr.getBookById(1L);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(book, result.get());
    }

//    @Test
//    void takeBookTest() {
//        ResponseDto responseDto = new ResponseDto("200", "Book taken successfully");
//
//        ResponseEntity<ResponseDto> responseEntity1 = ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        when(objServ.takeBook("book1", "user1")).thenReturn(responseEntity1); // Corrected here
//
//        ResponseEntity<?> result = objContr.takebook("book1", "user1");
//
//        assertNotNull(result);
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals(responseDto, result.getBody());
//    }

    @Test
    void libraryRecordsTest() {
        List<Library> libraries = Arrays.asList(
                new Library(1, "user1", "book1", LocalDate.now(), LocalDate.now().plusDays(14)),
                new Library(2, "user2", "book2", LocalDate.now(), LocalDate.now().plusDays(14))
        );
        when(objServ.getLibraryRecords()).thenReturn(libraries);

        List<Library> result = objContr.libraryrecords();

        assertNotNull(result);
        assertEquals(libraries.size(), result.size());
        for (int i = 0; i < libraries.size(); i++) {
            assertEquals(libraries.get(i).getId(), result.get(i).getId());
            assertEquals(libraries.get(i).getUsername(), result.get(i).getUsername());
            assertEquals(libraries.get(i).getBookName(), result.get(i).getBookName());
            assertEquals(libraries.get(i).getDate(), result.get(i).getDate());
            assertEquals(libraries.get(i).getDueDate(), result.get(i).getDueDate());
        }
    }

    @Test
    void overdueTest() {
        Map<String, Long> overdueMap = new HashMap<>();
        overdueMap.put("book1", 10L);
        ResponseEntity<Map<String, Long>> responseEntity = ResponseEntity.ok(overdueMap);
        when(objServ.Overdue()).thenReturn(responseEntity);

        ResponseEntity<Map<String, Long>> result = objContr.overdue();

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(overdueMap, result.getBody());
    }

    @Test
    void deleteBookTest() {
        ResponseDto responseDto = new ResponseDto("200", "Book deleted successfully");
        ResponseEntity<ResponseDto> responseEntity = ResponseEntity.ok(responseDto);

    }
}