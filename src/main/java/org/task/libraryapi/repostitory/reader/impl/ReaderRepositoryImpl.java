package org.task.libraryapi.repostitory.reader.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.impl.DSL;
import org.task.libraryapi.domain.reader.Gender;
import org.task.libraryapi.domain.reader.Reader;
import org.task.libraryapi.repostitory.reader.ReaderRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jooq.domain.Tables.LIBRARY_TRANSACTION;
import static com.jooq.domain.Tables.READER;

@Repository
@RequiredArgsConstructor
public class ReaderRepositoryImpl implements ReaderRepository {
    private final DSLContext context;

    @Override
    public Reader save(Reader reader) {
        if (reader.getPhoneNumber() != null) {
            context.update(READER)
                    .set(READER.FIRST_NAME, reader.getFirstName())
                    .set(READER.LAST_NAME, reader.getLastName())
                    .set(READER.GENDER, reader.getGender().toString())
                    .set(READER.BIRTH_DATE, reader.getBirthDate())
                    .where(READER.PHONE_NUMBER.eq(reader.getPhoneNumber()))
                    .execute();
            return reader;
        } else {
            return context.insertInto(READER)
                    .set(READER.PHONE_NUMBER, reader.getPhoneNumber())
                    .set(READER.FIRST_NAME, reader.getFirstName())
                    .set(READER.LAST_NAME, reader.getLastName())
                    .set(READER.GENDER, reader.getGender().toString())
                    .set(READER.BIRTH_DATE, reader.getBirthDate())
                    .returning()
                    .fetchOne()
                    .into(Reader.class);
        }
    }

    @Override
    public Reader find(String phoneNumber) {
        return context.select(
                        READER.PHONE_NUMBER,
                        READER.FIRST_NAME,
                        READER.LAST_NAME,
                        READER.GENDER,
                        READER.BIRTH_DATE
                ).from(READER)
                .where(READER.PHONE_NUMBER.eq(phoneNumber))
                .fetchOne(record -> Reader.builder()
                        .phoneNumber(record.get(READER.PHONE_NUMBER))
                        .firstName(record.get(READER.FIRST_NAME))
                        .lastName(record.get(READER.LAST_NAME))
                        .gender(Gender.valueOf(record.get(READER.GENDER).toUpperCase()))
                        .birthDate(record.get(READER.BIRTH_DATE))
                        .build()
                );
    }

    @Override
    public Reader findBestReader() {
        return context.select(READER.fields())
                .from(READER)
                .join(LIBRARY_TRANSACTION).on(READER.PHONE_NUMBER.eq(LIBRARY_TRANSACTION.READER_ID))
                .groupBy(READER.PHONE_NUMBER)
                .orderBy(DSL.count(LIBRARY_TRANSACTION.BOOK_ID).desc())
                .limit(1) // Возвращаем только одного читателя
                .fetchOne(record -> Reader.builder()
                        .phoneNumber(record.get(READER.PHONE_NUMBER))
                        .firstName(record.get(READER.FIRST_NAME))
                        .lastName(record.get(READER.LAST_NAME))
                        .gender(Gender.valueOf(record.get(READER.GENDER).toUpperCase()))
                        .birthDate(record.get(READER.BIRTH_DATE))
                        .build()
                );
    }

    @Override
    public List<Reader> findWithSortByNotReturningBooks() {
        return context.select(READER.fields())
                .from(READER)
                .join(LIBRARY_TRANSACTION).on(READER.PHONE_NUMBER.eq(LIBRARY_TRANSACTION.READER_ID))
                .where(LIBRARY_TRANSACTION.BOOK_ID.isNotNull()) // Предполагается, что не возвращенные книги будут иметь идентификатор
                .groupBy(READER.PHONE_NUMBER)
                .orderBy(DSL.count(LIBRARY_TRANSACTION.BOOK_ID).desc()) // Сортировка по количеству взятых книг
                .fetch(record -> Reader.builder()
                        .phoneNumber(record.get(READER.PHONE_NUMBER))
                        .firstName(record.get(READER.FIRST_NAME))
                        .lastName(record.get(READER.LAST_NAME))
                        .gender(Gender.valueOf(record.get(READER.GENDER).toUpperCase()))
                        .birthDate(record.get(READER.BIRTH_DATE))
                        .build()
                );
    }
}
