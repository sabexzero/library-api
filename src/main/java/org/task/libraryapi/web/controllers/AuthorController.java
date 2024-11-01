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
import org.task.libraryapi.domain.author.Author;
import org.task.libraryapi.service.author.AuthorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/best")
    @Operation(
            summary = "Find best author",
            description = "Retrieves the author with the most popular books."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Best author found successfully",
                    content = @Content(schema = @Schema(implementation = Author.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    public ResponseEntity<?> findBestAuthor() {
        try {
            return ResponseEntity.ok(authorService.findBestAuthor());
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body("An unexpected error occurred: " + e.getLocalizedMessage());
        }
    }
}
