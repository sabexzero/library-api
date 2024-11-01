package org.task.libraryapi.repostitory.transaction.impl;

import lombok.RequiredArgsConstructor;
import org.task.libraryapi.domain.transaction.LibraryTransaction;
import org.task.libraryapi.domain.transaction.TransactionType;
import org.task.libraryapi.repostitory.transaction.TransactionRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static com.jooq.domain.Tables.LIBRARY_TRANSACTION;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {
    private final DSLContext context;

    @Override
    public LibraryTransaction save(LibraryTransaction libraryTransaction) {
        return context.insertInto(LIBRARY_TRANSACTION)
                .set(LIBRARY_TRANSACTION.BOOK_ID, libraryTransaction.getBookId())
                .set(LIBRARY_TRANSACTION.READER_ID, libraryTransaction.getReaderId())
                .set(LIBRARY_TRANSACTION.TRANSACTION_TYPE, libraryTransaction.getType().toString())
                .set(LIBRARY_TRANSACTION.PERFORMED_AT, libraryTransaction.getPerformedAt())
                .returning()
                .fetchOne()
                .into(LibraryTransaction.class);
    }

    @Override
    public LibraryTransaction find(String readerId, Long bookId) {
        return context.selectFrom(LIBRARY_TRANSACTION)
                .where(LIBRARY_TRANSACTION.READER_ID.eq(readerId))
                .and(LIBRARY_TRANSACTION.BOOK_ID.eq(bookId))
                .orderBy(LIBRARY_TRANSACTION.PERFORMED_AT.desc())
                .limit(1)
                .fetchOne(record -> LibraryTransaction.builder()
                        .bookId(record.get(LIBRARY_TRANSACTION.BOOK_ID))
                        .readerId(record.get(LIBRARY_TRANSACTION.READER_ID))
                        .type(TransactionType.valueOf(record.get(LIBRARY_TRANSACTION.TRANSACTION_TYPE).toUpperCase()))
                        .performedAt(record.get(LIBRARY_TRANSACTION.PERFORMED_AT))
                        .transactionId(record.get(LIBRARY_TRANSACTION.TRANSACTION_ID))
                        .build()
                );
    }
}
