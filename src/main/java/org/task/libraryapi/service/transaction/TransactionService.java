package org.task.libraryapi.service.transaction;

import org.task.libraryapi.domain.transaction.LibraryTransaction;
import org.task.libraryapi.web.requests.transaction.TransactionMakeRequest;

public interface TransactionService {
    LibraryTransaction make(TransactionMakeRequest request);
}
