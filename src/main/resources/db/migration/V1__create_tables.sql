-- Create authors table
CREATE TABLE authors (
                         id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                         full_name VARCHAR(255) NOT NULL
);

-- Create publishers table
CREATE TABLE publishers (
                            id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            name VARCHAR(255) NOT NULL
);

-- Create genres table
CREATE TABLE genres (
                        id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                        name VARCHAR(255) NOT NULL
);

-- Create books table
CREATE TABLE books (
                       id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       title VARCHAR(255) NOT NULL,
                       author_name VARCHAR(255),
                       published_date TIMESTAMP,
                       author_id UUID,
                       publisher_id UUID,
                       FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE SET NULL,
                       FOREIGN KEY (publisher_id) REFERENCES publishers(id) ON DELETE SET NULL
);

-- Create book_genres join table
CREATE TABLE book_genres (
                             book_id UUID,
                             genre_id UUID,
                             PRIMARY KEY (book_id, genre_id),
                             FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
                             FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE
);

-- Create users table
CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       full_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       role VARCHAR(255) NOT NULL,

);

-- Add auditing columns to all tables
ALTER TABLE authors
    ADD COLUMN created_at TIMESTAMP DEFAULT NOW(),
ADD COLUMN created_by VARCHAR(255),
ADD COLUMN updated_at TIMESTAMP DEFAULT NOW(),
ADD COLUMN updated_by VARCHAR(255);

ALTER TABLE publishers
    ADD COLUMN created_at TIMESTAMP DEFAULT NOW(),
ADD COLUMN created_by VARCHAR(255),
ADD COLUMN updated_at TIMESTAMP DEFAULT NOW(),
ADD COLUMN updated_by VARCHAR(255);

ALTER TABLE genres
    ADD COLUMN created_at TIMESTAMP DEFAULT NOW(),
ADD COLUMN created_by VARCHAR(255),
ADD COLUMN updated_at TIMESTAMP DEFAULT NOW(),
ADD COLUMN updated_by VARCHAR(255);

ALTER TABLE books
    ADD COLUMN created_at TIMESTAMP DEFAULT NOW(),
ADD COLUMN created_by VARCHAR(255),
ADD COLUMN updated_at TIMESTAMP DEFAULT NOW(),
ADD COLUMN updated_by VARCHAR(255);

ALTER TABLE users
    ADD COLUMN created_at TIMESTAMP DEFAULT NOW(),
ADD COLUMN created_by VARCHAR(255),
ADD COLUMN updated_at TIMESTAMP DEFAULT NOW(),
ADD COLUMN updated_by VARCHAR(255);
