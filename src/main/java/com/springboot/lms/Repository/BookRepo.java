package com.springboot.lms.Repository;

import com.springboot.lms.Entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
    Book findBybookName(String title);

    Book findByauthor(String author);


@Transactional
@Modifying
    void deleteBybookName(String name);

//    boolean findByavailabilityStatus(Boolean id);
}
