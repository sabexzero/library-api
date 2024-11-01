package org.task.libraryapi.domain.book;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Book {
    private Long bookId;
    private String title;
    private Integer publicationYear;

    /**
     * Json object of an array of author IDs
     */
    private String authors;
}
