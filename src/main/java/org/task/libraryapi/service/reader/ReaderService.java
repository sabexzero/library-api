package org.task.libraryapi.service.reader;

import org.task.libraryapi.domain.reader.Reader;

import java.util.List;

public interface ReaderService {
    List<Reader> findDebtors();
    Reader findBestReader();
}
