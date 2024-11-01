package org.task.libraryapi.repostitory.author.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.impl.DSL;
import org.task.libraryapi.domain.author.Author;
import org.task.libraryapi.repostitory.author.AuthorRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static com.jooq.domain.Tables.BOOK_AUTHOR;
import static com.jooq.domain.Tables.LIBRARY_TRANSACTION;
import static com.jooq.domain.tables.Author.AUTHOR;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {
    private final DSLContext context;

    @Override
    public Author save(Author author) {
        if (author.getAuthorId() != null) {
            context.update(AUTHOR)
                    .set(AUTHOR.FIRST_NAME, author.getFirstName())
                    .set(AUTHOR.LAST_NAME, author.getLastName())
                    .set(AUTHOR.BIRTH_DATE, author.getBirthDate())
                    .where(AUTHOR.AUTHOR_ID.eq(author.getAuthorId()))
                    .execute();
            return author;
        } else {
            return context.insertInto(AUTHOR)
                    .set(AUTHOR.FIRST_NAME, author.getFirstName())
                    .set(AUTHOR.LAST_NAME, author.getLastName())
                    .set(AUTHOR.BIRTH_DATE, author.getBirthDate())
                    .returning()
                    .fetchOne()
                    .into(Author.class);
        }
    }

    @Override
    public Author find(Long id) {
        return context.selectFrom(AUTHOR)
            .where(AUTHOR.AUTHOR_ID.eq(id))
            .fetchOneInto(Author.class);
    }

    @Override
    public Author findBestAuthor() {
        return context.select(AUTHOR.fields())
                .from(AUTHOR)
                .join(BOOK_AUTHOR).on(AUTHOR.AUTHOR_ID.eq(BOOK_AUTHOR.AUTHOR_ID))
                .join(LIBRARY_TRANSACTION).on(BOOK_AUTHOR.BOOK_ID.eq(LIBRARY_TRANSACTION.BOOK_ID))
                .groupBy(AUTHOR.AUTHOR_ID)
                .orderBy(DSL.count().desc())
                .limit(1)
                .fetchOneInto(Author.class);
    }
}
