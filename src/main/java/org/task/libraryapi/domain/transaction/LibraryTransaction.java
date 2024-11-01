package org.task.libraryapi.domain.transaction;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LibraryTransaction {
    private Long transactionId;
    private TransactionType type;
    private LocalDateTime performedAt;

    /**
     * The phone number acts as an identifier
     */
    private String readerId;
    private Long bookId;
}
