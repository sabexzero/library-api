package org.task.libraryapi.repostitory.transaction;

import org.task.libraryapi.domain.transaction.LibraryTransaction;

public interface TransactionRepository {
    LibraryTransaction save(LibraryTransaction libraryTransaction);
    LibraryTransaction find(String readerId, Long bookId);
}
