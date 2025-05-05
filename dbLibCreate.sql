CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `genre` varchar(100) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `publication_date` date DEFAULT NULL,
  `summary` text,
  `cover_url` varchar(255) DEFAULT NULL,
  `available_quantity` int NOT NULL DEFAULT '0',
  `total_quantity` int NOT NULL DEFAULT '0',
  `borrowed_count` int NOT NULL DEFAULT '0',
  `rating` double NOT NULL,
  `price` int NOT NULL DEFAULT '0',
  `audio_duration` int DEFAULT NULL,
  `audio_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (70,'Refactoring','Martin Fowler','Programming','Addison-Wesley','1999-07-08','Improving the Design of Existing Code','https://martinfowler.com/books/r2p.jpg',5,16,3,3.8,100000,NULL,NULL),(71,'Code Complete','Steve McConnell','Programming','Microsoft Press','2004-06-19','A Practical Handbook of Software Construction','https://upload.wikimedia.org/wikipedia/en/5/58/Code_Complete_1st_edition.jpg',9,25,3,3.5,0,NULL,NULL),(72,'You Don\'t Know JS','Kyle Simpson','JavaScript','O\'Reilly Media','2014-12-27','Deep dive into JavaScript core mechanisms','https://m.media-amazon.com/images/I/71FU6nxVhAL._AC_UF1000,1000_QL80_.jpg',5,12,1,0,0,NULL,NULL),(73,'Eloquent JavaScript','Marijn Haverbeke','JavaScript','No Starch Press','2018-12-04','A Modern Introduction to Programming','https://eloquentjavascript.net/3rd_edition/img/cover.jpg',10,20,0,0,0,NULL,NULL),(74,'JavaScript: The Good Parts','Douglas Crockford','JavaScript','O\'Reilly Media','2008-05-15','Unearthing the Excellence in JavaScript','https://m.media-amazon.com/images/I/7185IMvz88L.jpg',5,10,0,0,0,NULL,NULL),(75,'The Art of Computer Programming','Donald Knuth','Computer Science','Addison-Wesley','1968-01-01','Comprehensive algorithms and mathematics','https://m.media-amazon.com/images/I/61tIrzRmFdL.jpg',3,6,0,0,0,390,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//The%20Art%20of%20Computer%20Programming.mp3'),(76,'Introduction to the Theory of Computation','Michael Sipser','Computer Science','Cengage Learning','2012-06-27','Fundamentals of automata and complexity','https://m.media-amazon.com/images/I/61dPNb6AUJL._AC_UF1000,1000_QL80_.jpg',4,8,0,0,0,NULL,NULL),(77,'Operating System Concepts','Abraham Silberschatz, Greg Gagne, Peter B. Galvin','Operating Systems','Wiley','2018-01-01','Core concepts of operating systems','https://media.wiley.com/product_data/coverImage300/66/11198003/1119800366.jpg',6,12,0,0,0,NULL,NULL),(78,'Computer Networking: A Top-Down Approach','James F. Kurose, Keith W. Ross','Networking','Pearson','2016-03-10','A detailed approach to networking','https://m.media-amazon.com/images/I/81ewUnANZPL._AC_UF1000,1000_QL80_.jpg',7,14,0,0,0,NULL,NULL),(79,'TCP/IP Illustrated','W. Richard Stevens','Networking','Addison-Wesley','1994-11-01','In-depth study of TCP/IP protocols','https://m.media-amazon.com/images/I/91Ok5AaCC-L.jpg',5,10,0,0,0,NULL,NULL),(80,'Structure and Interpretation of Computer Programs','Harold Abelson, Gerald Jay Sussman','Computer Science','MIT Press','1996-07-25','The foundational text on programming languages','https://m.media-amazon.com/images/I/71BBXQnykuL.jpg',4,8,0,0,0,NULL,NULL),(81,'Compilers: Principles, Techniques, and Tools','Alfred V. Aho, Monica S. Lam, Ravi Sethi, Jeffrey D. Ullman','Compilers','Pearson','2006-08-21','The Dragon Book on compiler construction','https://m.media-amazon.com/images/I/71MvtEJneKL._AC_UF1000,1000_QL80_.jpg',3,6,0,0,0,NULL,NULL),(82,'The Mythical Man-Month','Frederick P. Brooks Jr.','Software Engineering','Addison-Wesley','1975-01-01','Classic essays on software engineering','https://m.media-amazon.com/images/I/817iWsLJR0L.jpg',4,10,1,0,0,NULL,NULL),(83,'The DevOps Handbook','Gene Kim, Jez Humble, Patrick Debois, John Willis','DevOps','IT Revolution','2016-10-06','How to create world-class agility and reliability','https://m.media-amazon.com/images/I/71mhqEw8LcL._AC_UF1000,1000_QL80_.jpg',8,16,0,0,0,NULL,NULL),(84,'Continuous Delivery','Jez Humble, David Farley','DevOps','Addison-Wesley','2010-07-27','Reliable software releases through build, test, and deployment automation','https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1347640457i/8686650.jpg',6,12,0,0,0,NULL,NULL),(85,'The Phoenix Project','Gene Kim, Kevin Behr, George Spafford','DevOps','IT Revolution','2013-01-10','A novel about IT, DevOps, and helping your business win','https://www.oreilly.com/api/v2/epubs/9781457191350/files/image/cover.jpg',7,14,0,0,0,NULL,NULL),(86,'Domain-Driven Design','Eric Evans','Software Architecture','Addison-Wesley','2003-08-30','Tackling complexity in the heart of software','https://images.viblo.asia/e226cbf4-0baf-4857-a656-f250cc62cae6.jpg',5,10,0,0,0,NULL,NULL),(87,'Patterns of Enterprise Application Architecture','Martin Fowler','Software Architecture','Addison-Wesley','2002-11-15','Catalog of software architecture patterns','https://m.media-amazon.com/images/I/61yNt+jcM0L.jpg',4,8,0,0,0,NULL,NULL),(88,'Microservices Patterns','Chris Richardson','Software Architecture','Manning','2025-03-14','Microservice architecture patterns','https://d28hgpri8am2if.cloudfront.net/book_images/onix/cvr9781617294549/microservices-patterns-9781617294549_hr.jpg',5,12,1,0,0,NULL,NULL),(89,'Building Microservices','Sam Newman','Software Architecture','O\'Reilly Media','2025-03-15','Designing fine-grained systems','https://garywoodfine.com/wp-content/uploads/2022/12/building-microservices-book-cover-2.jpg',5,10,0,0,0,NULL,NULL),(90,'Kubernetes Up & Running','Kelsey Hightower, Brendan Burns, Joe Beda','Cloud Computing','O\'Reilly Media','2025-03-16','Dive into Kubernetes operations','https://m.media-amazon.com/images/I/81obLZ3AbEL._AC_UF1000,1000_QL80_.jpg',8,16,0,0,0,NULL,NULL),(91,'The Docker Book','James Turnbull','Cloud Computing','Self-Published','2025-03-17','Everything you need to know about Docker','https://m.media-amazon.com/images/I/51v4b06vi0L._AC_UF1000,1000_QL80_.jpg',6,12,0,0,0,NULL,NULL),(92,'Cloud Native DevOps with Kubernetes','John Arundel, Justin Domingus','Cloud Computing','O\'Reilly Media','2025-03-18','Building, deploying, and scaling modern applications','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRRqxETsv88CaZZhe1z41qQkzRcWa7OX55Ng&s',7,14,0,0,0,NULL,NULL),(93,'Site Reliability Engineering','Betsy Beyer, Chris Jones, Jennifer Petoff, Niall Richard Murphy','Reliability Engineering','O\'Reilly Media','2025-03-19','How Google runs production systems','https://m.media-amazon.com/images/I/91CMi+LGZiL._UF1000,1000_QL80_.jpg',5,10,0,0,0,NULL,NULL),(94,'Web Security for Developers','Malcolm McDonald','Cybersecurity','No Starch Press','2025-03-20','Essential security techniques for developers','https://cdn2.penguin.com.au/covers/original/9781593279943.jpg',6,12,0,0,0,NULL,NULL),(95,'The Web Application Hacker\'s Handbook','Dafydd Stuttard, Marcus Pinto','Cybersecurity','Wiley','2025-03-13','Finding and exploiting security flaws','https://m.media-amazon.com/images/I/81a2pCFfm9L.jpg',4,8,0,0,0,NULL,NULL),(96,'Serious Cryptography','Jean-Philippe Aumasson','Cryptography','No Starch Press','2025-03-12','A practical introduction to modern encryption','https://m.media-amazon.com/images/I/81EGnJTeEYL.jpg',5,10,0,0,0,NULL,NULL),(97,'The Tangled Web','Michal Zalewski','Cybersecurity','No Starch Press','2025-03-11','A guide to securing modern web applications','https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1344675366i/11553604.jpg',7,14,0,0,0,NULL,NULL),(98,'Hacking: The Art of Exploitation','Jon Erickson','Cybersecurity','No Starch Press','2025-03-10','A deep dive into hacking techniques','https://m.media-amazon.com/images/I/91UlU666haL._AC_UF1000,1000_QL80_.jpg',6,12,0,0,0,NULL,NULL),(99,'Algorithms','Robert Sedgewick, Kevin Wayne','Computer Science','Addison-Wesley','2025-03-09','An in-depth look at modern algorithms','https://m.media-amazon.com/images/I/61KblZDBH+L._AC_UF894,1000_QL80_.jpg',5,10,0,0,0,NULL,NULL),(100,'Python Crash Course','Eric Matthes','Programming','No Starch Press','2025-03-08','A hands-on introduction to Python','https://www.oreilly.com/api/v2/epubs/9781492071266/files/images/9781593279295.jpg',8,16,0,0,0,NULL,NULL),(101,'Fluent Python','Luciano Ramalho','Programming','O\'Reilly Media','2025-03-07','A deep dive into Python features','https://juliensobczak.com/posts_resources/covers/fluent-python.jpg',7,14,0,0,0,NULL,NULL),(102,'Effective Java','Joshua Bloch','Programming','Addison-Wesley','2025-03-06','Best practices for Java programming','https://www.oreilly.com/api/v2/epubs/9780134686097/files/graphics/9780134686042.jpg',6,12,0,0,0,NULL,NULL),(117,'The Leaf Dresses','Ririro','Programming','Ririro.com','2018-10-16','The Leaf Dresses is a charming story about the leaves on trees learning that autumn is approaching and they are invited to a party where they should dress up in vibrant colors. As the wind carries them away, some maple leaves decide to stay behind to protect the flowers from the coming winter. They fall to the ground, covering the flowers and bringing joy to children with their beauty. A little girl collects some of these leaves and brings them to school, where they are admired throughout the winter. The story highlights the beauty of nature, the changing seasons, and the importance of caring for the environment.','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//the_leaf_dresses.jpg',7,10,3,0,150000,390,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//the_leaf_dresses.mp3'),(120,'The Plant Household','Emilie Poulsson','Computer Science','Ririro.com','2025-04-16','You can also find us on your favourite podcasting platform.','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//the_plant_household.jpg',10,10,0,0,150000,390,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//the_plant_household.mp3'),(121,'Decoding the Digital World: A Beginner Guide to Programming','Ada Byte','Cybersecurity','TechStart','2024-08-12','Unlock the power of code and embark on an exciting journey into the digital realm with \"Decoding the Digital World.\" This comprehensive guide demystifies the fundamentals of programming, making it accessible and engaging for absolute beginners. Learn the core concepts and build practical skills to create your own software and applications.','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//Decoding%20the%20Digital%20World.jpg',10,10,0,0,150000,390,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//Decoding%20the%20Digital%20World.mp3'),(122,'The Wonders of the Night Sky','Dr. Stella Nova','Astronomy','Celestial Insights Publishing','2024-07-12','Embark on a breathtaking journey through the cosmos with \"The Wonders of the Night Sky.\" This captivating book unveils the secrets of stars, planets, galaxies, and nebulae, making the wonders of astronomy accessible to all. Discover the stories behind the constellations and ignite your passion for the universe.','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//wonders_night_sky_cover.jpg',8,8,0,0,180000,450,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//wonders_night_sky_audio.mp3'),(123,'The Lean Startup Revolution: Building Tomorrow\'s Businesses','Eric Innovator','Business','Global Business Press','2024-11-20','Discover the groundbreaking principles of the Lean Startup methodology in \"The Lean Startup Revolution.\" This essential guide provides entrepreneurs and innovators with a practical framework for building successful businesses in today\'s rapidly changing world. Learn how to minimize waste, validate ideas quickly, and iterate effectively to create products customers truly love.','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//lean_startup_revolution.jpg',12,12,0,0,220000,510,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//lean_startup_revolution.mp3'),(124,'The Cybersecurity Handbook: Protecting Your Digital Life','Dr. Anya Shield','Cybersecurity','Secure Digital Press','2025-03-10','Navigate the complex world of online threats with \"The Cybersecurity Handbook.\" This essential guide provides practical strategies and actionable advice to safeguard your personal and professional digital life from cyberattacks and data breaches.','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//cybersecurity_handbook.jpg',7,7,0,0,190000,480,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//cybersecurity_handbook.mp3'),(125,'Advanced Penetration Testing: Hacking the Unhackable','Kai Breaker','Cybersecurity','Elite Security Publishing','2024-12-01','Go beyond the basics with \"Advanced Penetration Testing.\" This in-depth guide explores cutting-edge techniques and methodologies used by security professionals to identify and exploit complex vulnerabilities in modern systems and networks.','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//advanced_pentesting.jpg',5,5,0,0,250000,600,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//advanced_pentesting.mp3'),(126,'Cybersecurity for Beginners: Your Journey to Online Safety','Lena Protect','Cybersecurity','Safe Surfing Books','2025-06-15','Start your cybersecurity journey with \"Cybersecurity for Beginners.\" This friendly and accessible guide introduces fundamental concepts and practical steps anyone can take to enhance their online safety and protect themselves from common cyber threats.','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//cybersecurity_beginners.jpg',15,15,0,0,120000,300,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//cybersecurity_beginners.mp3'),(127,'The Art of Social Engineering: Human Hacking Exposed','Sam Persuader','Cybersecurity','Influence Security Press','2025-01-22','Understand the human element of cybersecurity with \"The Art of Social Engineering.\" This insightful book explores the techniques used by cybercriminals to manipulate and deceive individuals into compromising security.','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//social_engineering.jpg',10,10,0,0,200000,540,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//social_engineering.mp3'),(128,'Operating Systems Concepts and Principles','Dr. OS Kernel','Operating Systems','System Core Press','2024-09-18','Gain a comprehensive understanding of the fundamental concepts and principles behind modern operating systems. This book delves into process management, memory management, file systems, concurrency, and more.','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//os_concepts.jpg',9,9,0,0,210000,570,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//os_concepts.mp3'),(129,'Modern Operating Systems: Design and Implementation','Prof. Andy Code','Operating Systems','Tech Systems Publishing','2025-02-25','Explore the design and implementation details of contemporary operating systems, including Unix, Linux, and Windows. This book examines the architectural choices and engineering trade-offs involved in building robust and scalable systems.','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//modern_os.jpg',7,7,0,0,230000,630,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//modern_os.mp3');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowing_records`
--

DROP TABLE IF EXISTS `borrowing_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowing_records` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `borrow_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `due_date` timestamp NOT NULL,
  `return_date` timestamp NULL DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `penalty_fee` decimal(10,2) DEFAULT '0.00',
  `renewal_count` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `borrowing_records_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `borrowing_records_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowing_records`
--

LOCK TABLES `borrowing_records` WRITE;
/*!40000 ALTER TABLE `borrowing_records` DISABLE KEYS */;
INSERT INTO `borrowing_records` VALUES (1,9,71,'2025-04-25 08:06:00','2025-05-04 17:00:00',NULL,'BORROWED',0.00,1),(2,9,70,'2025-04-25 08:09:24','2025-05-05 17:00:00',NULL,'BORROWED',0.00,1),(3,9,70,'2025-04-25 08:09:24','2025-05-04 17:00:00',NULL,'BORROWED',0.00,1),(4,9,71,'2025-04-25 08:09:24','2025-05-02 17:00:00',NULL,'BORROWED',0.00,0),(5,9,70,'2025-04-25 08:09:24','2025-05-02 17:00:00',NULL,'BORROWED',0.00,0),(6,9,72,'2025-04-25 08:09:26','2025-05-02 17:00:00',NULL,'BORROWED',0.00,1),(7,9,82,'2025-04-25 08:09:26','2025-04-25 17:00:00',NULL,'OVERDUE',0.00,0),(8,9,117,'2025-04-29 11:19:37','2025-05-06 17:00:00',NULL,'BORROWED',0.00,1),(9,9,117,'2025-04-29 11:19:37','2025-05-06 11:19:37',NULL,'BORROWED',0.00,0),(10,11,117,'2025-05-02 08:37:24','2025-05-09 17:00:00',NULL,'BORROWED',0.00,1),(11,11,71,'2025-05-03 09:49:06','2025-05-10 17:00:00',NULL,'BORROWED',0.00,1),(12,11,88,'2025-05-03 09:49:06','2025-05-10 17:00:00',NULL,'BORROWED',0.00,1);
/*!40000 ALTER TABLE `borrowing_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fines`
--

DROP TABLE IF EXISTS `fines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fines` (
  `fine_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `record_id` int NOT NULL,
  `amount` decimal(38,2) NOT NULL,
  `paid` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`fine_id`),
  KEY `user_id` (`user_id`),
  KEY `record_id` (`record_id`),
  CONSTRAINT `fines_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fines_ibfk_2` FOREIGN KEY (`record_id`) REFERENCES `borrowing_records` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fines`
--

LOCK TABLES `fines` WRITE;
/*!40000 ALTER TABLE `fines` DISABLE KEYS */;
/*!40000 ALTER TABLE `fines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genres` (
  `genre_id` int NOT NULL AUTO_INCREMENT,
  `genre` varchar(100) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `book_count` int NOT NULL DEFAULT '0',
  `audiobook_count` int DEFAULT NULL,
  `ebook_count` int DEFAULT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,'Programming','https://i.imgur.com/tPVjjd2.jpeg',2,NULL,NULL),(2,'JavaScript','https://i.imgur.com/Pc754BY.jpeg',0,NULL,NULL),(3,'Computer Science','https://i.imgur.com/fyd76YJ.jpeg',0,NULL,NULL),(4,'Operating Systems','https://i.imgur.com/Hy09wz7.jpeg',2,NULL,NULL),(5,'Networking','https://i.imgur.com/cGL3Jjw.jpeg',0,NULL,NULL),(6,'Compilers','https://i.imgur.com/gHrd0Wo.jpeg',0,NULL,NULL),(7,'Software Engineering','https://i.imgur.com/6afPwa1.jpeg',0,NULL,NULL),(8,'DevOps','https://i.imgur.com/krGDcLY.jpeg',0,NULL,NULL),(9,'Software Architecture','https://i.imgur.com/EWwKG6N.jpeg',0,NULL,NULL),(10,'Cloud Computing','https://i.imgur.com/8GrDOOn.jpeg',0,NULL,NULL),(13,'Reliability Engineering','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//Reliability%20Engineering.jpg',0,NULL,NULL),(19,'Cybersecurity','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//Cybersecurity.jpg',0,NULL,NULL),(20,'Cryptography','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//Cryptography.jpg',0,NULL,NULL),(24,'Business','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//Business.jpg',0,NULL,NULL),(25,'Astronomy','https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/audiobooks//Astronomy.jpg',0,NULL,NULL);
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` varchar(255) NOT NULL,
  `is_read` bit(1) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `timestamp` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` enum('DUE_REMINDER','NEW_BOOK','ORDER_STATUS','PROMOTION','RECOMMENDATION','SYSTEM') DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_send_log`
--

DROP TABLE IF EXISTS `notification_send_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification_send_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `notification_type` enum('DUE_REMINDER','NEW_BOOK','ORDER_STATUS','PROMOTION','RECOMMENDATION','SYSTEM') DEFAULT NULL,
  `send_date` date NOT NULL,
  `type` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_send_log`
--

LOCK TABLES `notification_send_log` WRITE;
/*!40000 ALTER TABLE `notification_send_log` DISABLE KEYS */;
INSERT INTO `notification_send_log` VALUES (1,NULL,'2025-04-25','DUE_REMINDER',9),(2,NULL,'2025-04-26','DUE_REMINDER',9),(3,NULL,'2025-04-27','DUE_REMINDER',9),(4,NULL,'2025-04-27','RENEWAL_SUCCESS',9),(5,NULL,'2025-04-28','RECOMMENDATION',9),(6,NULL,'2025-04-29','RECOMMENDATION',9),(7,NULL,'2025-04-29','RENEWAL_SUCCESS',9),(8,NULL,'2025-05-01','DUE_REMINDER',9),(9,NULL,'2025-05-01','RECOMMENDATION',9),(10,NULL,'2025-05-02','RECOMMENDATION',11),(11,NULL,'2025-05-02','DUE_REMINDER',9),(12,NULL,'2025-05-03','RECOMMENDATION',11),(13,NULL,'2025-05-03','RECOMMENDATION',11),(14,NULL,'2025-05-03','RECOMMENDATION',11),(15,NULL,'2025-05-03','RECOMMENDATION',11),(16,NULL,'2025-05-03','RECOMMENDATION',11),(17,NULL,'2025-05-03','RENEWAL_SUCCESS',11),(18,NULL,'2025-05-03','ORDER_STATUS',11),(19,NULL,'2025-05-03','RENEWAL_SUCCESS',11);
/*!40000 ALTER TABLE `notification_send_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `is_read` bit(1) NOT NULL,
  `message` varchar(500) NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,'2025-04-25 13:44:30.331732',_binary '\0','Bạn đã thêm cuốn sách \"Refactoring\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',9),(2,'2025-04-25 13:46:26.995124',_binary '\0','Bạn đã thêm cuốn sách \"Refactoring\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',9),(3,'2025-04-25 13:50:53.944300',_binary '\0','Bạn đã thêm cuốn sách \"Refactoring\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',9),(4,'2025-04-25 13:51:52.277480',_binary '\0','Bạn đã thêm cuốn sách \"Refactoring\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',9),(5,'2025-04-25 13:55:10.988614',_binary '\0','Bạn đã thêm cuốn sách \"You Don\'t Know JS\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',9),(6,'2025-04-25 13:59:56.651365',_binary '\0','Bạn đã thêm cuốn sách \"You Don\'t Know JS\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',9),(7,'2025-04-25 14:09:04.384837',_binary '\0','Bạn đã thêm cuốn sách \"Refactoring\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',9),(8,'2025-04-25 14:09:17.468682',_binary '\0','Bạn đã thêm cuốn sách \"The Phoenix Project\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',9),(9,'2025-04-25 14:18:23.001499',_binary '\0','Bạn đã thêm cuốn sách \"The Art of Computer Programming\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',9),(10,'2025-04-25 14:48:53.125360',_binary '\0','Bạn đã thêm cuốn sách \"Refactoring\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',9),(11,'2025-04-25 22:00:47.584208',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(12,'2025-04-25 22:01:13.671152',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(13,'2025-04-25 22:08:23.718154',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(14,'2025-04-25 22:12:25.709699',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(15,'2025-04-25 22:12:36.667653',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(16,'2025-04-25 22:20:15.903853',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(17,'2025-04-25 22:21:50.329814',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(18,'2025-04-25 22:22:03.254340',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(19,'2025-04-25 22:22:23.466835',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(20,'2025-04-25 22:31:40.528240',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(21,'2025-04-25 22:31:51.157621',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(22,'2025-04-25 22:36:35.371594',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(23,'2025-04-26 13:03:07.491721',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(24,'2025-04-27 08:32:41.481353',_binary '\0','Các sách sau sắp đến hạn trả: \'The Mythical Man-Month\'','Sắp đến hạn trả!','DUE_REMINDER',9),(25,'2025-04-27 15:05:22.561233',_binary '\0','Bạn đã gia hạn thành công cuốn sách \'You Don\'t Know JS\' đến ngày 2025-05-03.','Gia hạn thành công','RENEWAL_SUCCESS',9),(26,'2025-04-28 20:25:56.708978',_binary '\0','Bạn đã thêm cuốn sách \"Code Complete\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',9),(27,'2025-04-29 15:47:43.076278',_binary '\0','Bạn đã thêm cuốn sách \"The Leaf Dresses\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',9),(28,'2025-04-29 18:22:08.204788',_binary '\0','Bạn đã gia hạn thành công cuốn sách \'The Leaf Dresses\' đến ngày 2025-05-07.','Gia hạn thành công','RENEWAL_SUCCESS',9),(29,'2025-05-01 14:30:12.345400',_binary '\0','Các sách sau sắp đến hạn trả: \'Code Complete\', \'Refactoring\', \'You Don\'t Know JS\'','Sắp đến hạn trả!','DUE_REMINDER',9),(30,'2025-05-01 15:18:18.081643',_binary '\0','Bạn đã thêm cuốn sách \"Refactoring\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',9),(31,'2025-05-02 15:30:11.594947',_binary '\0','Bạn đã thêm cuốn sách \"The Leaf Dresses\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',11),(32,'2025-05-02 16:05:06.209375',_binary '\0','Các sách sau sắp đến hạn trả: \'Code Complete\', \'Refactoring\', \'You Don\'t Know JS\'','Sắp đến hạn trả!','DUE_REMINDER',9),(33,'2025-05-03 09:21:26.622949',_binary '\0','Bạn đã thêm cuốn sách \"Refactoring\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',11),(34,'2025-05-03 16:40:22.644957',_binary '\0','Bạn đã thêm cuốn sách \"The Leaf Dresses\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',11),(35,'2025-05-03 16:40:29.487924',_binary '\0','Bạn đã thêm cuốn sách \"Building Microservices\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',11),(36,'2025-05-03 16:43:59.279817',_binary '\0','Bạn đã thêm cuốn sách \"Building Microservices\" vào danh sách yêu thích.','Đã thêm vào Yêu thích','RECOMMENDATION',11),(37,'2025-05-03 16:44:24.012069',_binary '\0','Bạn hủy đặt lịch đơn #\"16\".','Đã hủy đặt lịch thành công!','RECOMMENDATION',11),(38,'2025-05-03 16:49:25.498593',_binary '\0','Bạn đã gia hạn thành công cuốn sách \'Code Complete\' đến ngày 2025-05-11.','Gia hạn thành công','RENEWAL_SUCCESS',11),(39,'2025-05-03 16:49:51.213134',_binary '\0','Bạn hủy đặt lịch đơn #\"18\".','Đã hủy đặt lịch thành công!','ORDER_STATUS',11),(40,'2025-05-03 16:51:15.011280',_binary '\0','Bạn đã gia hạn thành công cuốn sách \'Microservices Patterns\' đến ngày 2025-05-11.','Gia hạn thành công','RENEWAL_SUCCESS',11);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_books`
--

DROP TABLE IF EXISTS `reservation_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reservation_id` int NOT NULL,
  `book_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `reservation_id` (`reservation_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `reservation_books_ibfk_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`reservation_id`) ON DELETE CASCADE,
  CONSTRAINT `reservation_books_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_books`
--

LOCK TABLES `reservation_books` WRITE;
/*!40000 ALTER TABLE `reservation_books` DISABLE KEYS */;
INSERT INTO `reservation_books` VALUES (1,1,70),(2,1,71),(3,1,72),(4,2,70),(5,2,71),(6,2,72),(7,3,72),(8,4,72),(9,5,72),(10,5,82),(11,6,70),(12,6,70),(13,6,71),(14,6,70),(15,7,71),(16,8,70),(17,8,70),(18,8,70),(19,8,70),(20,8,72),(21,8,72),(22,8,70),(23,8,85),(24,8,75),(25,8,70),(26,9,71),(27,10,117),(28,10,117),(29,11,117),(31,13,89),(32,13,90),(33,13,77),(34,14,70),(35,14,80),(36,15,73),(37,16,117),(38,16,89),(39,17,71),(40,17,88),(41,18,70);
/*!40000 ALTER TABLE `reservation_books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `reservation_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `reservation_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `expiration_date` timestamp NOT NULL,
  `status` enum('PENDING','APPROVED','REJECTED','COMPLETED','CANCELLED') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,9,NULL,'2025-04-15 07:03:38','PENDING'),(2,9,NULL,'2025-04-15 07:03:38','PENDING'),(3,9,NULL,'2025-04-23 16:59:00','PENDING'),(4,9,NULL,'2025-04-23 16:59:00','PENDING'),(5,9,'2025-04-23 14:37:48','2025-04-24 16:59:00','COMPLETED'),(6,9,'2025-04-23 14:39:31','2025-04-24 16:59:00','COMPLETED'),(7,9,'2025-04-23 14:40:08','2025-04-26 16:59:00','COMPLETED'),(8,9,'2025-04-27 01:35:33','2025-04-28 16:59:00','PENDING'),(9,9,'2025-04-28 13:42:19','2025-04-29 16:59:00','PENDING'),(10,9,'2025-04-29 11:08:56','2025-04-30 16:59:00','COMPLETED'),(11,11,'2025-05-02 08:31:50','2025-05-09 16:59:00','COMPLETED'),(13,11,'2025-05-03 09:02:58','2025-05-09 16:59:00','CANCELLED'),(14,11,'2025-05-03 09:23:11','2025-05-05 16:59:00','CANCELLED'),(15,11,'2025-05-03 09:26:36','2025-05-03 16:59:00','CANCELLED'),(16,11,'2025-05-03 09:44:10','2025-05-05 16:59:00','CANCELLED'),(17,11,'2025-05-03 09:48:10','2025-05-03 16:59:00','COMPLETED'),(18,11,'2025-05-03 09:49:43','2025-05-03 16:59:00','CANCELLED');
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `comment` text,
  `rating` double NOT NULL,
  `book_id` int NOT NULL,
  `user_id` int NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `FK6a9k6xvev80se5rreqvuqr7f9` (`book_id`),
  KEY `FKcgy7qjc1r99dp117y9en6lxye` (`user_id`),
  CONSTRAINT `FK6a9k6xvev80se5rreqvuqr7f9` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`),
  CONSTRAINT `FKcgy7qjc1r99dp117y9en6lxye` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,'ok',4,70,9,NULL),(2,'good',3,70,9,NULL),(3,'4 sao',4,71,9,NULL),(4,'thoitam',2,71,9,NULL),(5,'cung ok',5,71,9,NULL),(6,'hi',3,71,9,NULL),(7,'a',3,70,9,NULL),(8,'ffff',4,70,9,NULL),(9,'Cung hay',5,70,11,'2025-05-02 15:02:18.844725');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone_number` bigint DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '0',
  `otp` varchar(255) DEFAULT NULL,
  `otp_expiry` datetime DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (8,'hieu','$2a$10$kcl5rBkcsd2cbaTw/ZYt6uI9Ii1Nqs4G8bZdPVQk4ID/7Gw6Fop/q','Dang Hieu',123123123,'hieu7a3thcsbt@gmail.com',1,NULL,NULL,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/avatars//avatar3.png'),(9,'hieuuu','$2a$10$w9EldC.X4xMYs7keWEkzguQ0ME.EVPpargo/yotQx4VfFtu6wD/g2','123',123,'22110322@student.hcmute.edu.vn',1,NULL,NULL,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/avatars//avatar1.pnghttps://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/avatars//avatar1.png'),(11,'danghieu','$2a$10$RqWJMnVoGefC6NkGcYrMd./9RD32yicjwzLCK8V8kYpcsKTJo0dp6','Le Dang Hieu',704010205,'vidcoy6@gmail.com',1,NULL,NULL,'https://bopuiwpbccpifydfplgj.supabase.co/storage/v1/object/public/avatars//avatar2.png');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist` (
  `wishlist_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  PRIMARY KEY (`wishlist_id`),
  KEY `FKtrd6335blsefl2gxpb8lr0gr7` (`user_id`),
  CONSTRAINT `FKtrd6335blsefl2gxpb8lr0gr7` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (33,9);
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist_books`
--

DROP TABLE IF EXISTS `wishlist_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist_books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int NOT NULL,
  `wishlist_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmdxdlaga4a3kl1wut48wqddip` (`book_id`),
  KEY `FKt2xhfj0vyfj3jhhabcyy1fegb` (`wishlist_id`),
  CONSTRAINT `FKmdxdlaga4a3kl1wut48wqddip` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`),
  CONSTRAINT `FKt2xhfj0vyfj3jhhabcyy1fegb` FOREIGN KEY (`wishlist_id`) REFERENCES `wishlist` (`wishlist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist_books`
--

LOCK TABLES `wishlist_books` WRITE;
/*!40000 ALTER TABLE `wishlist_books` DISABLE KEYS */;
INSERT INTO `wishlist_books` VALUES (33,70,33);
/*!40000 ALTER TABLE `wishlist_books` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-05 18:06:07
