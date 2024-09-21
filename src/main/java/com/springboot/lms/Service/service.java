package com.springboot.lms.Service;

import com.springboot.lms.Entity.Book;
import com.springboot.lms.Entity.Library;
import com.springboot.lms.Repository.BookRepo;

import com.springboot.lms.Repository.LibraryRepo;
import com.springboot.lms.dto.ResponseDto;
import com.springboot.lms.exception.BookAlreadyExistsException;
import com.springboot.lms.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class service {
    @Autowired
    private LibraryRepo objlibrary;
    @Autowired
    private BookRepo objbookRepo;


    public Book searchBookByTitle(String title) {
        return objbookRepo.findBybookName(title);
    }

    public Book searchBookByAuthor(String author) {
        return objbookRepo.findByauthor(author);
    }

    public Optional<Book> getBookById(Long id) {
        return objbookRepo.findById(id);
    }
//    public boolean checkAvailability(Long id) {
//        return  objbookRepo.findByavailabilityStatus(id);
//    }


    public List<Book> getallBooks() {

        List<Book> books = objbookRepo.findAll();
        return books;

    }

    public ResponseEntity<ResponseDto> addBook(Book book) {
        Optional<Book> books = Optional.ofNullable(objbookRepo.findBybookName(book.getBookName()));
        if (books.isPresent()) {
            throw new BookAlreadyExistsException(book.getBookName()+"   is  already exists");
        } else {
            objbookRepo.save(book);
            return ResponseEntity.ok().body(new ResponseDto("201","successfully added"));
        }

    }

    public List<Library> getLibraryRecords() {
        List<Library> data = objlibrary.findAll();
        System.out.println("inside library");
        return data;
    }


    public ResponseEntity<?> takeBook(String bookname, String username) {
        Book book = objbookRepo.findBybookName(bookname);
        if (book != null && book.isAvailabilityStatus() == true) {
            Library library = new Library(0, username, bookname, LocalDate.now(), LocalDate.now().plusDays(10));
            objlibrary.save(library);
            book.setAvailabilityStatus(false);
            objbookRepo.save(book); // Save the updated book
            return ResponseEntity.ok().body(library);
        } else {
             throw new BookNotFoundException(bookname+" - this book  not found");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }


    public ResponseEntity<Map<String, Long>> Overdue() {
        List<Library> data = objlibrary.findByDueDateBefore(LocalDate.now());
        System.out.println(data);

        Map<String, Long> overdue = new HashMap<>();
        System.out.println("inside");
        for (Library l : data) {
            long days = ChronoUnit.DAYS.between(l.getDueDate(), LocalDate.now());
            System.out.println(l.getUsername() + " " + l.getDueDate() + " " + LocalDate.now() + " " + days);
            long fine_amount = days * 10;

            overdue.put(l.getUsername(), fine_amount);
        }
        System.out.println("returning find data");

        return ResponseEntity.ok().body(overdue);
    }

    public ResponseEntity<ResponseDto> DeleteBook(String name) {
        Optional<Book> isbookexist= Optional.ofNullable(objbookRepo.findBybookName(name));
        if (isbookexist.isPresent()) {
            objbookRepo.deleteBybookName(name);
            return ResponseEntity.ok().body(new ResponseDto("204","successfully deleted"));

        }
      throw new BookNotFoundException("Book not found");
    }

    public ResponseEntity<ResponseDto> BookReturn(String bookname, String username) {
    Library data=objlibrary.findBybookName(bookname);
    if(data==null){
        return ResponseEntity.ok().body(new ResponseDto("200","You did not take book from our library"));
    }
    objlibrary.deleteById(data.getId());
   Book book=objbookRepo.findBybookName(bookname);
   book.setAvailabilityStatus(true);
   objbookRepo.save(book);
        return ResponseEntity.ok().body(new ResponseDto("200","thank you for returning our book"));

    }


    }