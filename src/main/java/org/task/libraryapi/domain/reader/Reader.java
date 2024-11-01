package org.task.libraryapi.domain.reader;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Reader {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
}
