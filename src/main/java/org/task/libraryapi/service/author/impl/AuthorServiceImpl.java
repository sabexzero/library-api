package org.task.libraryapi.service.author.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.task.libraryapi.domain.author.Author;
import org.task.libraryapi.repostitory.author.AuthorRepository;
import org.task.libraryapi.service.author.AuthorService;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author findBestAuthor() {
        return authorRepository.findBestAuthor();
    }
}
