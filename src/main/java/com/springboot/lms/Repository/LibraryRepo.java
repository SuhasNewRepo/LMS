package com.springboot.lms.Repository;

import com.springboot.lms.Entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LibraryRepo extends JpaRepository<Library,Long> {
    List<Library> findByDueDateBefore(LocalDate date);

    Library findBybookName(String bookname);
}
