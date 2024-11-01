CREATE TABLE author
(
    author_id   BIGSERIAL PRIMARY KEY,
    first_name  VARCHAR(255) NOT NULL,
    last_name   VARCHAR(255) NOT NULL,
    birth_date  DATE
);

CREATE TABLE book
(
    book_id           BIGSERIAL PRIMARY KEY,
    title             VARCHAR(255) NOT NULL,
    publication_year  INTEGER
);

CREATE TABLE book_author
(
    book_id   BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, author_id)
);

CREATE TABLE reader
(
    phone_number VARCHAR(15) PRIMARY KEY,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    gender       VARCHAR(50)  NOT NULL,
    birth_date   DATE
);

CREATE TABLE library_transaction
(
    transaction_id BIGSERIAL PRIMARY KEY,
    transaction_type VARCHAR(50) NOT NULL,
    performed_at     TIMESTAMP   NOT NULL,
    reader_id        VARCHAR(15) NOT NULL,
    book_id          BIGINT
);
