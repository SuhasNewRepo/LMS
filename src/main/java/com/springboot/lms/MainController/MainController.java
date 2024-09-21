package com.springboot.lms.MainController;


import com.springboot.lms.Entity.Book;
import com.springboot.lms.Entity.Library;
import com.springboot.lms.Service.service;
import com.springboot.lms.dto.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController

@RequestMapping("/api/")
@Validated
public class MainController {
    @Autowired
    private service objservice;

    //    1.add new books
    @PostMapping("/addBook")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody Book book) {
    return  objservice.addBook(book);
    }

    //2.display their details. books
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return objservice.getallBooks();
    }


    //4.searching for a book by title or author,
    @GetMapping("/books/{title}")
    public Book searchBookByTitle(@PathVariable String title) {
        return objservice.searchBookByTitle(title);
    }

    @GetMapping("/books/author/{author}")
    public Book searchBookByAuthor(@PathVariable String author) {
        return objservice.searchBookByAuthor(author);
    }

    @GetMapping("/books/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return objservice.getBookById(id);
    }

    @GetMapping("/takebook/{bookname}/{yourname}")
    public ResponseEntity<?> takebook(@PathVariable String bookname, @PathVariable String yourname) {
        System.out.println("entering");
        return objservice.takeBook(bookname, yourname);
    }



    //3.adding library, displaying the library
    @GetMapping("/library")
    public List<Library> libraryrecords() {
      return  objservice.getLibraryRecords();
    }

    //7.managing overdue fines
    @GetMapping("/overdue")
    public ResponseEntity<Map<String, Long>> overdue() {
        return objservice.Overdue();

    }

    @DeleteMapping("/deletebook/{name}")
    public ResponseEntity<ResponseDto> deleteBook(@PathVariable String name) {
       return   objservice.DeleteBook(name);
    }


    //book return
    @DeleteMapping("bookReturn/{bookname}/{username}")
    public ResponseEntity<ResponseDto> bookreturn(@PathVariable String bookname, @PathVariable String username){
        return objservice.BookReturn(bookname, username);
    }

}
