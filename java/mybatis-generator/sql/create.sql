DROP TABLE book IF EXISTS;

CREATE TABLE IF NOT EXISTS book (
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title varchar(255) NOT NULL,
    author varchar(255) NOT NULL
);

INSERT INTO book (id, title, author) VALUES (1, 'title', 'author');
