-- Tạo lại cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS library;

-- Sử dụng cơ sở dữ liệu vừa tạo
USE library;

-- Tạo bảng users 
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,  -- Để kiểu INT
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name NVARCHAR(50),
    phone_number BIGINT,
    email NVARCHAR(50),
    is_active BOOLEAN DEFAULT false,
    otp VARCHAR(50),
    otp_expiry DATETIME
);

-- Tạo bảng books 
CREATE TABLE IF NOT EXISTS books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,  -- Để kiểu INT
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    publisher VARCHAR(255),
    publication_date DATE,
    summary TEXT,
    cover_url VARCHAR(255),
    available_quantity INT NOT NULL DEFAULT 0,
    total_quantity INT NOT NULL DEFAULT 0,
    borrowed_count INT NOT NULL DEFAULT 0
);

-- Xóa bảng cũ nếu cần
DROP TABLE IF EXISTS reservation_books;
DROP TABLE IF EXISTS reservations;

-- Tạo lại bảng reservations
CREATE TABLE IF NOT EXISTS reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expiration_date TIMESTAMP NOT NULL,
    status ENUM('PENDING', 'APPROVED', 'REJECTED', 'COMPLETED') DEFAULT 'PENDING',
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Tạo bảng trung gian để lưu danh sách sách của mỗi reservation
CREATE TABLE IF NOT EXISTS reservation_books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_id INT NOT NULL,
    book_id INT NOT NULL,
    FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

-- Tạo bảng borrowing_records
CREATE TABLE IF NOT EXISTS borrowing_records (
    record_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    borrow_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Ngày mượn sách
    due_date TIMESTAMP NOT NULL,  -- Ngày hạn trả sách
    return_date TIMESTAMP NULL,  -- Ngày trả thực tế (NULL nếu chưa trả)
    status ENUM('BORROWED', 'RETURNED', 'OVERDUE') DEFAULT 'BORROWED',  
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

-- Tạo bảng fines (quản lý tiền phạt khi trả sách trễ)
CREATE TABLE IF NOT EXISTS fines (
    fine_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    record_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL, -- Số tiền phạt
    paid BOOLEAN DEFAULT false,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (record_id) REFERENCES borrowing_records(record_id)
);

-- Thêm dữ liệu vào bảng books
INSERT INTO books (title, author, genre, publisher, publication_date, summary, cover_url, available_quantity, total_quantity)
VALUES 
('Clean Code', 'Robert C. Martin', 'Programming', 'Prentice Hall', '2008-08-01', 'A Handbook of Agile Software Craftsmanship', 'https://example.com/clean-code.jpg', 10, 20),
('The Pragmatic Programmer', 'Andy Hunt, Dave Thomas', 'Programming', 'Addison-Wesley', '1999-10-30', 'Your Journey To Mastery', 'https://example.com/pragmatic-programmer.jpg', 5, 10),
('Design Patterns', 'Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides', 'Software Engineering', 'Addison-Wesley', '1994-10-21', 'Elements of Reusable Object-Oriented Software', 'https://example.com/design-patterns.jpg', 7, 15);
