package org.task.libraryapi.repostitory.book;

import org.task.libraryapi.domain.book.Book;

public interface BookRepository {
    Book save(Book book);
    Book find(Long id);
}
