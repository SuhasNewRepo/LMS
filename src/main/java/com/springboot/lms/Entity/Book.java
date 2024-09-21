package com.springboot.lms.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long isbnId;

    @NotBlank(message = "Book name is required")
    @Size(min = 2, max = 20, message = "Book name should be between 2 and 20 characters")
    private String bookName;

    @NotBlank(message = "Author is required")
    @Size(min = 2, max = 20, message = "Author name should be between 2 and 20 characters")
    private String author;

    @NotBlank(message = "Genre is required")
    @Size(min = 2, max = 20, message = "Genre should be between 2 and 20 characters")
    private String genre;

    @NotNull(message = "Availability status is required")
    private boolean availabilityStatus;
}