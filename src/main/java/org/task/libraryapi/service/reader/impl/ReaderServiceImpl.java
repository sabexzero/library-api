package org.task.libraryapi.service.reader.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.task.libraryapi.domain.reader.Reader;
import org.task.libraryapi.repostitory.reader.ReaderRepository;
import org.task.libraryapi.service.reader.ReaderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepository readerRepository;

    @Override
    public List<Reader> findDebtors() {
        return readerRepository.findWithSortByNotReturningBooks();
    }

    @Override
    public Reader findBestReader() {
        return readerRepository.findBestReader();
    }
}
