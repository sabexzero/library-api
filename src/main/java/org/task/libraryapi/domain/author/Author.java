package org.task.libraryapi.domain.author;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Author {
    private Long authorId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
