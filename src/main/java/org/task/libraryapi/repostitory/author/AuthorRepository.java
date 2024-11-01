package org.task.libraryapi.repostitory.author;

import org.task.libraryapi.domain.author.Author;

public interface AuthorRepository {
    Author save(Author author);
    Author find(Long id);
    Author findBestAuthor();
}
