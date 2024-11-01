package org.task.libraryapi.repostitory.reader;

import org.task.libraryapi.domain.reader.Reader;

import java.util.List;

public interface ReaderRepository {
    Reader save(Reader reader);
    Reader find(String phoneNumber);
    Reader findBestReader();
    List<Reader> findWithSortByNotReturningBooks();
}
