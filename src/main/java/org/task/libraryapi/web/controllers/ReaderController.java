package org.task.libraryapi.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.task.libraryapi.domain.reader.Reader;
import org.task.libraryapi.service.reader.ReaderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/readers")
public class ReaderController {
    private final ReaderService readerService;

    @GetMapping("/best")
    @Operation(
            summary = "Find best reader",
            description = "Retrieves the reader with the most books borrowed."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Best reader found successfully",
                    content = @Content(schema = @Schema(implementation = Reader.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    public ResponseEntity<?> findBestReader() {
        try {
            return ResponseEntity.ok(readerService.findBestReader());
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body("An unexpected error occurred: " + e.getLocalizedMessage());
        }
    }

    @GetMapping("/debtors")
    @Operation(summary = "Find debtors", description = "Retrieves readers who have overdue books.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Debtors found successfully",
                    content = @Content(schema = @Schema(
                            implementation = Reader.class,
                            type = "array"
                    ))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    public ResponseEntity<?> findDebtors() {
        try {
            return ResponseEntity.ok(readerService.findDebtors());
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body("An unexpected error occurred: " + e.getLocalizedMessage());
        }
    }
}