package org.task.libraryapi.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.task.libraryapi.service.transaction.TransactionService;
import org.task.libraryapi.web.requests.transaction.TransactionMakeRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping()
    @Operation(
            summary = "Make a transaction",
            description = "Performs a new library transaction, such as borrowing or returning a book."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Transaction completed successfully",
                    content = @Content(schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    public ResponseEntity<?> makeTransaction(
            @Valid
            @RequestBody
            TransactionMakeRequest request
    ) {
        try {
            return ResponseEntity.ok(transactionService.make(request));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body("An unexpected error occurred: " + e.getLocalizedMessage());
        }
    }
}