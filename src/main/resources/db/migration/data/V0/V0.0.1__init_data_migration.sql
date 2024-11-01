INSERT INTO author (first_name, last_name, birth_date)
VALUES ('John', 'Smith', '1970-05-15'),
       ('Jane', 'Doe', '1982-11-23'),
       ('Mark', 'Johnson', '1990-04-10'),
       ('Emily', 'Davis', '1985-02-05'),
       ('Michael', 'Brown', '1975-08-30'),
       ('Sarah', 'Wilson', '1988-01-22'),
       ('David', 'Martinez', '1968-09-14'),
       ('Linda', 'Garcia', '1972-06-29'),
       ('James', 'Anderson', '1980-12-12'),
       ('Mary', 'Taylor', '1995-03-17');

INSERT INTO book (title, publication_year)
VALUES ('The Great Gatsby', 1925),
       ('To Kill a Mockingbird', 1960),
       ('1984', 1949),
       ('Pride and Prejudice', 1813),
       ('The Catcher in the Rye', 1951),
       ('The Hobbit', 1937),
       ('Fahrenheit 451', 1953),
       ('Brave New World', 1932),
       ('Moby Dick', 1851),
       ('War and Peace', 1869),
       ('The Odyssey', -800),
       ('The Brothers Karamazov', 1880),
       ('Crime and Punishment', 1866),
       ('The Picture of Dorian Gray', 1890),
       ('Jane Eyre', 1847),
       ('Wuthering Heights', 1847),
       ('The Grapes of Wrath', 1939),
       ('The Bell Jar', 1963),
       ('The Alchemist', 1988),
       ('Life of Pi', 2001),
       ('The Road', 2006),
       ('The Fault in Our Stars', 2012),
       ('The Hunger Games', 2008),
       ('Divergent', 2011),
       ('The Maze Runner', 2009),
       ('Gone Girl', 2012),
       ('The Book Thief', 2005),
       ('All the Light We Cannot See', 2014),
       ('Where the Crawdads Sing', 2018),
       ('The Night Circus', 2011),
       ('Circe', 2018);

INSERT INTO book_author (book_id, author_id)
VALUES (1, 1),   -- The Great Gatsby by Author 1
       (1, 2),   -- The Great Gatsby by Author 2
       (2, 3),   -- To Kill a Mockingbird by Author 3
       (3, 4),   -- 1984 by Author 4
       (4, 5),   -- Pride and Prejudice by Author 5
       (5, 6),   -- The Catcher in the Rye by Author 6
       (6, 1),   -- The Hobbit by Author 1
       (7, 3),   -- Fahrenheit 451 by Author 3
       (8, 2),   -- Brave New World by Author 2
       (9, 7),   -- Moby Dick by Author 7
       (10, 8),  -- War and Peace by Author 8
       (11, 5),  -- The Odyssey by Author 5
       (12, 9),  -- The Brothers Karamazov by Author 9
       (13, 10), -- Crime and Punishment by Author 10
       (14, 6),  -- The Picture of Dorian Gray by Author 6
       (15, 4),  -- Jane Eyre by Author 4
       (16, 7),  -- Wuthering Heights by Author 7
       (17, 8),  -- The Grapes of Wrath by Author 8
       (18, 5),  -- The Bell Jar by Author 5
       (19, 10), -- The Alchemist by Author 10
       (20, 9),  -- Life of Pi by Author 9
       (21, 1),  -- The Road by Author 1
       (22, 2),  -- The Fault in Our Stars by Author 2
       (23, 3),  -- The Hunger Games by Author 3
       (24, 4),  -- Divergent by Author 4
       (25, 6),  -- The Maze Runner by Author 6
       (26, 7),  -- Gone Girl by Author 7
       (27, 8),  -- The Book Thief by Author 8
       (28, 9),  -- All the Light We Cannot See by Author 9
       (29, 10), -- Where the Crawdads Sing by Author 10
       (30, 1); -- The Night Circus by Author 1

INSERT INTO reader (phone_number, first_name, last_name, gender, birth_date)
VALUES ('1234567890', 'Alice', 'Johnson', 'Female', '1990-05-14'),
       ('2345678901', 'Bob', 'Smith', 'Male', '1985-03-22'),
       ('3456789012', 'Charlie', 'Brown', 'Male', '1992-07-08'),
       ('4567890123', 'Diana', 'King', 'Female', '1995-10-11'),
       ('5678901234', 'Edward', 'Miller', 'Male', '1988-12-25'),
       ('6789012345', 'Fiona', 'Garcia', 'Female', '1991-01-16'),
       ('7890123456', 'George', 'Martinez', 'Male', '1984-04-30'),
       ('8901234567', 'Hannah', 'Lopez', 'Female', '1993-06-19'),
       ('9012345678', 'Ian', 'Wilson', 'Male', '1987-09-14'),
       ('0123456789', 'Julia', 'Anderson', 'Female', '1994-02-28'),
       ('1234567891', 'Kevin', 'Thomas', 'Male', '1990-08-05'),
       ('2345678902', 'Lily', 'Taylor', 'Female', '1996-11-30'),
       ('3456789013', 'Mike', 'Hernandez', 'Male', '1986-12-15'),
       ('4567890124', 'Nina', 'Nguyen', 'Female', '1989-03-17'),
       ('5678901235', 'Oscar', 'Clark', 'Male', '1992-10-21'),
       ('6789012346', 'Paula', 'Rodriguez', 'Female', '1991-04-29'),
       ('7890123457', 'Quentin', 'Lewis', 'Male', '1985-06-22'),
       ('8901234568', 'Rachel', 'Walker', 'Female', '1993-08-09'),
       ('9012345679', 'Steve', 'Hall', 'Male', '1988-01-12'),
       ('0123456790', 'Tina', 'Young', 'Female', '1990-09-18');

INSERT INTO library_transaction (transaction_type, performed_at, reader_id, book_id)
VALUES ('TAKING', '2024-01-01 10:00:00', '1234567890', 1),
       ('RETURNING', '2024-01-05 14:30:00', '2345678901', 2),
       ('TAKING', '2024-01-10 09:15:00', '3456789012', 3),
       ('RETURNING', '2024-01-15 12:45:00', '4567890123', 4),
       ('TAKING', '2024-01-20 11:00:00', '5678901234', 5),
       ('RETURNING', '2024-01-22 15:30:00', '6789012345', 6),
       ('TAKING', '2024-01-25 10:00:00', '7890123456', 7),
       ('RETURNING', '2024-01-28 16:00:00', '8901234567', 8),
       ('TAKING', '2024-01-30 10:30:00', '9012345678', 9),
       ('RETURNING', '2024-02-02 14:00:00', '0123456789', 10),
       ('TAKING', '2024-02-05 11:45:00', '1234567891', 11),
       ('RETURNING', '2024-02-08 13:30:00', '2345678902', 12),
       ('TAKING', '2024-02-12 09:00:00', '3456789013', 13),
       ('RETURNING', '2024-02-15 14:15:00', '4567890124', 14),
       ('TAKING', '2024-02-18 10:00:00', '5678901235', 15),
       ('RETURNING', '2024-02-20 16:30:00', '6789012346', 16),
       ('TAKING', '2024-02-22 11:00:00', '7890123457', 17),
       ('RETURNING', '2024-02-25 12:00:00', '8901234568', 18),
       ('TAKING', '2024-02-28 14:45:00', '9012345679', 19),
       ('RETURNING', '2024-03-02 15:15:00', '0123456790', 20),
       ('TAKING', '2024-03-05 10:30:00', '1234567890', 21),
       ('RETURNING', '2024-03-10 14:00:00', '2345678901', 22),
       ('TAKING', '2024-03-12 11:45:00', '3456789012', 23),
       ('RETURNING', '2024-03-15 13:30:00', '4567890123', 24),
       ('TAKING', '2024-03-18 09:00:00', '5678901234', 25),
       ('RETURNING', '2024-03-22 16:00:00', '6789012345', 26),
       ('TAKING', '2024-03-25 10:00:00', '7890123456', 27),
       ('RETURNING', '2024-03-28 15:30:00', '8901234567', 28),
       ('TAKING', '2024-03-30 10:30:00', '9012345678', 29),
       ('RETURNING', '2024-04-02 14:00:00', '0123456789', 30),
       ('TAKING', '2024-04-05 12:15:00', '1234567891', 1),
       ('RETURNING', '2024-04-07 13:00:00', '2345678902', 2);
