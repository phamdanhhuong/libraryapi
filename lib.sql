CREATE USER 'library'@'localhost' IDENTIFIED BY '1234';

GRANT ALL PRIVILEGES ON library.* TO 'library'@'localhost';

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

CREATE TABLE genres (
    genre_id INT AUTO_INCREMENT PRIMARY KEY,
    genre VARCHAR(100) NOT NULL UNIQUE,
    url VARCHAR(255)
);


INSERT INTO books (title, author, genre, publisher, publication_date, summary, cover_url, available_quantity, total_quantity)
VALUES 
('Refactoring', 'Martin Fowler', 'Programming', 'Addison-Wesley', '2025-03-14', 'Improving the Design of Existing Code', 'https://martinfowler.com/books/r2p.jpg', 8, 16),
('Code Complete', 'Steve McConnell', 'Programming', 'Microsoft Press', '2025-03-15', 'A Practical Handbook of Software Construction', 'https://upload.wikimedia.org/wikipedia/en/5/58/Code_Complete_1st_edition.jpg', 12, 25),
('You Don\'t Know JS', 'Kyle Simpson', 'JavaScript', 'O\'Reilly Media', '2025-03-16', 'Deep dive into JavaScript core mechanisms', 'https://m.media-amazon.com/images/I/71FU6nxVhAL._AC_UF1000,1000_QL80_.jpg', 6, 12),
('Eloquent JavaScript', 'Marijn Haverbeke', 'JavaScript', 'No Starch Press', '2025-03-17', 'A Modern Introduction to Programming', 'https://eloquentjavascript.net/3rd_edition/img/cover.jpg', 10, 20),
('JavaScript: The Good Parts', 'Douglas Crockford', 'JavaScript', 'O\'Reilly Media', '2025-03-18', 'Unearthing the Excellence in JavaScript', 'https://m.media-amazon.com/images/I/7185IMvz88L.jpg', 5, 10),
('The Art of Computer Programming', 'Donald Knuth', 'Computer Science', 'Addison-Wesley', '2025-03-19', 'Comprehensive algorithms and mathematics', 'https://m.media-amazon.com/images/I/61tIrzRmFdL.jpg', 3, 6),
('Introduction to the Theory of Computation', 'Michael Sipser', 'Computer Science', 'Cengage Learning', '2025-03-20', 'Fundamentals of automata and complexity', 'https://m.media-amazon.com/images/I/61dPNb6AUJL._AC_UF1000,1000_QL80_.jpg', 4, 8),
('Operating System Concepts', 'Abraham Silberschatz, Greg Gagne, Peter B. Galvin', 'Operating Systems', 'Wiley', '2025-03-21', 'Core concepts of operating systems', 'https://media.wiley.com/product_data/coverImage300/66/11198003/1119800366.jpg', 6, 12),
('Computer Networking: A Top-Down Approach', 'James F. Kurose, Keith W. Ross', 'Networking', 'Pearson', '2025-03-14', 'A detailed approach to networking', 'https://m.media-amazon.com/images/I/81ewUnANZPL._AC_UF1000,1000_QL80_.jpg', 7, 14),
('TCP/IP Illustrated', 'W. Richard Stevens', 'Networking', 'Addison-Wesley', '2025-03-15', 'In-depth study of TCP/IP protocols', 'https://m.media-amazon.com/images/I/91Ok5AaCC-L.jpg', 5, 10),
('Structure and Interpretation of Computer Programs', 'Harold Abelson, Gerald Jay Sussman', 'Computer Science', 'MIT Press', '2025-03-16', 'The foundational text on programming languages', 'https://m.media-amazon.com/images/I/71BBXQnykuL.jpg', 4, 8),
('Compilers: Principles, Techniques, and Tools', 'Alfred V. Aho, Monica S. Lam, Ravi Sethi, Jeffrey D. Ullman', 'Compilers', 'Pearson', '2025-03-17', 'The Dragon Book on compiler construction', 'https://m.media-amazon.com/images/I/71MvtEJneKL._AC_UF1000,1000_QL80_.jpg', 3, 6),
('The Mythical Man-Month', 'Frederick P. Brooks Jr.', 'Software Engineering', 'Addison-Wesley', '2025-03-18', 'Classic essays on software engineering', 'https://m.media-amazon.com/images/I/817iWsLJR0L.jpg', 5, 10),
('Domain-Driven Design', 'Eric Evans', 'Software Architecture', 'Addison-Wesley', '2003-08-30', 'Tackling complexity in the heart of software', 'https://images.viblo.asia/e226cbf4-0baf-4857-a656-f250cc62cae6.jpg', 5, 10),
('Patterns of Enterprise Application Architecture', 'Martin Fowler', 'Software Architecture', 'Addison-Wesley', '2002-11-15', 'Catalog of software architecture patterns', 'https://m.media-amazon.com/images/I/61yNt+jcM0L.jpg', 4, 8),
('Microservices Patterns', 'Chris Richardson', 'Software Architecture', 'Manning', '2025-03-14', 'Microservice architecture patterns', 'https://d28hgpri8am2if.cloudfront.net/book_images/onix/cvr9781617294549/microservices-patterns-9781617294549_hr.jpg', 6, 12),
('Building Microservices', 'Sam Newman', 'Software Architecture', 'O\'Reilly Media', '2025-03-15', 'Designing fine-grained systems', 'https://garywoodfine.com/wp-content/uploads/2022/12/building-microservices-book-cover-2.jpg', 5, 10),
('Kubernetes Up & Running', 'Kelsey Hightower, Brendan Burns, Joe Beda', 'Cloud Computing', 'O\'Reilly Media', '2025-03-16', 'Dive into Kubernetes operations', 'https://m.media-amazon.com/images/I/81obLZ3AbEL._AC_UF1000,1000_QL80_.jpg', 8, 16),
('The Docker Book', 'James Turnbull', 'Cloud Computing', 'Self-Published', '2025-03-17', 'Everything you need to know about Docker', 'https://m.media-amazon.com/images/I/51v4b06vi0L._AC_UF1000,1000_QL80_.jpg', 6, 12),
('Cloud Native DevOps with Kubernetes', 'John Arundel, Justin Domingus', 'Cloud Computing', 'O\'Reilly Media', '2025-03-18', 'Building, deploying, and scaling modern applications', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRRqxETsv88CaZZhe1z41qQkzRcWa7OX55Ng&s', 7, 14),
('Site Reliability Engineering', 'Betsy Beyer, Chris Jones, Jennifer Petoff, Niall Richard Murphy', 'Reliability Engineering', 'O\'Reilly Media', '2025-03-19', 'How Google runs production systems', 'https://m.media-amazon.com/images/I/91CMi+LGZiL._UF1000,1000_QL80_.jpg', 5, 10),
('Web Security for Developers', 'Malcolm McDonald', 'Cybersecurity', 'No Starch Press', '2025-03-20', 'Essential security techniques for developers', 'https://cdn2.penguin.com.au/covers/original/9781593279943.jpg', 6, 12),
('The Web Application Hacker\'s Handbook', 'Dafydd Stuttard, Marcus Pinto', 'Cybersecurity', 'Wiley', '2025-03-13', 'Finding and exploiting security flaws', 'https://m.media-amazon.com/images/I/81a2pCFfm9L.jpg', 4, 8),
('Serious Cryptography', 'Jean-Philippe Aumasson', 'Cryptography', 'No Starch Press', '2025-03-12', 'A practical introduction to modern encryption', 'https://m.media-amazon.com/images/I/81EGnJTeEYL.jpg', 5, 10),
('The Tangled Web', 'Michal Zalewski', 'Cybersecurity', 'No Starch Press', '2025-03-11', 'A guide to securing modern web applications', 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1344675366i/11553604.jpg', 7, 14),
('Hacking: The Art of Exploitation', 'Jon Erickson', 'Cybersecurity', 'No Starch Press', '2025-03-10', 'A deep dive into hacking techniques', 'https://m.media-amazon.com/images/I/91UlU666haL._AC_UF1000,1000_QL80_.jpg', 6, 12),
('Algorithms', 'Robert Sedgewick, Kevin Wayne', 'Computer Science', 'Addison-Wesley', '2025-03-09', 'An in-depth look at modern algorithms', 'https://m.media-amazon.com/images/I/61KblZDBH+L._AC_UF894,1000_QL80_.jpg', 5, 10),
('Python Crash Course', 'Eric Matthes', 'Programming', 'No Starch Press', '2025-03-08', 'A hands-on introduction to Python', 'https://www.oreilly.com/api/v2/epubs/9781492071266/files/images/9781593279295.jpg', 8, 16),
('Fluent Python', 'Luciano Ramalho', 'Programming', 'O\'Reilly Media', '2025-03-07', 'A deep dive into Python features', 'https://juliensobczak.com/posts_resources/covers/fluent-python.jpg', 7, 14),
('Effective Java', 'Joshua Bloch', 'Programming', 'Addison-Wesley', '2025-03-06', 'Best practices for Java programming', 'https://www.oreilly.com/api/v2/epubs/9780134686097/files/graphics/9780134686042.jpg', 6, 12);



INSERT INTO genres (genre, url) VALUES 
('Programming', 'https://i.imgur.com/tPVjjd2.jpeg'),
('JavaScript', 'https://i.imgur.com/Pc754BY.jpeg'),
('Computer Science', 'https://i.imgur.com/fyd76YJ.jpeg'),
('Operating Systems', 'https://i.imgur.com/Hy09wz7.jpeg'),
('Networking', 'https://i.imgur.com/cGL3Jjw.jpeg'),
('Compilers', 'https://i.imgur.com/gHrd0Wo.jpeg'),
('Software Engineering', 'https://i.imgur.com/6afPwa1.jpeg'),
('DevOps', 'https://i.imgur.com/krGDcLY.jpeg'),
('Software Architecture', 'https://i.imgur.com/EWwKG6N.jpeg'),
('Cloud Computing', 'https://i.imgur.com/8GrDOOn.jpeg');

