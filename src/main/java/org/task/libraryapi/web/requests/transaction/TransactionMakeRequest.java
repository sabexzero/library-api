package org.task.libraryapi.web.requests.transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.task.libraryapi.domain.transaction.LibraryTransaction;
import org.task.libraryapi.domain.transaction.TransactionType;

import java.time.LocalDateTime;

public record TransactionMakeRequest(
        @NotNull(message = "ReaderId must be not null!")
        String readerId,

        @NotNull(message = "BookId must be not null!")
        Long bookId,

        @NotNull
        @Pattern(
                regexp = "^(TAKING|RETURNING)$",
                message = "TransactionType must be TAKING or RETURNING!")
        String transactionType
) {
    public LibraryTransaction toDomain(){
        return LibraryTransaction.builder()
                .readerId(readerId)
                .bookId(bookId)
                .type(TransactionType.valueOf(transactionType))
                .performedAt(LocalDateTime.now())
                .build();
    }
}
