package org.task.libraryapi.service.transaction.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.task.libraryapi.domain.transaction.LibraryTransaction;
import org.task.libraryapi.domain.transaction.TransactionType;
import org.task.libraryapi.repostitory.transaction.TransactionRepository;
import org.task.libraryapi.service.transaction.TransactionService;
import org.task.libraryapi.web.requests.transaction.TransactionMakeRequest;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public LibraryTransaction make(TransactionMakeRequest request) {
        LibraryTransaction existTransaction = transactionRepository.find(
                request.readerId(),
                request.bookId()
        );

        if (existTransaction != null) {
            // Check if the user is trying to return a book
            if (request.transactionType().equals(TransactionType.RETURNING.toString())) {
                // The user cannot return a book they have already returned
                if (existTransaction.getType().equals(TransactionType.RETURNING)) {
                    throw new RuntimeException(
                            "This operation is not possible because the user book has already been returned!"
                    );
                }
            }
            // Check if the user is trying to take a book
            else if (request.transactionType().equals(TransactionType.TAKING.toString())) {
                // The user cannot take a book they already have
                if (existTransaction.getType().equals(TransactionType.TAKING)) {
                    throw new RuntimeException(
                            "This operation is not possible because the user has already took the book!"
                    );
                }
            }
        }

        return transactionRepository.save(
                request.toDomain()
        );
    }
}
