package org.task.libraryapi.repostitory.book.impl;

import lombok.RequiredArgsConstructor;
import org.task.libraryapi.domain.book.Book;
import org.task.libraryapi.repostitory.book.BookRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static com.jooq.domain.Tables.BOOK;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final DSLContext context;

    @Override
    public Book save(Book book) {
        if (book.getBookId() != null) {
            context.update(BOOK)
                    .set(BOOK.TITLE, book.getTitle())
                    .set(BOOK.PUBLICATION_YEAR, book.getPublicationYear())
                    .where(BOOK.BOOK_ID.eq(book.getBookId()))
                    .execute();
            return book;
        } else {
            return context.insertInto(BOOK)
                    .set(BOOK.TITLE, book.getTitle())
                    .set(BOOK.PUBLICATION_YEAR, book.getPublicationYear())
                    .returning()
                    .fetchOne()
                    .into(Book.class);
        }
    }

    @Override
    public Book find(Long id) {
        return context.selectFrom(BOOK)
                .where(BOOK.BOOK_ID.eq(id))
                .fetchOneInto(Book.class);
    }
}
