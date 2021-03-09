-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: vote_system
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `answers_sheets`
--

DROP TABLE IF EXISTS `answers_sheets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answers_sheets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blank_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `q_id` int DEFAULT NULL,
  `q_ans` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `blank_id` (`blank_id`),
  KEY `q_id` (`q_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `answers_sheets_ibfk_1` FOREIGN KEY (`blank_id`) REFERENCES `blanks` (`id`),
  CONSTRAINT `answers_sheets_ibfk_2` FOREIGN KEY (`q_id`) REFERENCES `questions` (`id`),
  CONSTRAINT `answers_sheets_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers_sheets`
--

LOCK TABLES `answers_sheets` WRITE;
/*!40000 ALTER TABLE `answers_sheets` DISABLE KEYS */;
INSERT INTO `answers_sheets` VALUES (27,1,1,5,'A'),(28,1,1,6,'A'),(29,1,2,5,'B'),(30,1,2,6,'B'),(31,6,2,7,'C'),(32,1,3,5,'C'),(33,1,3,6,'C'),(34,6,3,7,'A'),(35,6,1,7,'A');
/*!40000 ALTER TABLE `answers_sheets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `authority_id` int unsigned NOT NULL AUTO_INCREMENT,
  `authority_name` varchar(64) NOT NULL,
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (3,'delete'),(4,'update');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blanks`
--

DROP TABLE IF EXISTS `blanks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blanks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` text,
  `topic` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blanks`
--

LOCK TABLES `blanks` WRITE;
/*!40000 ALTER TABLE `blanks` DISABLE KEYS */;
INSERT INTO `blanks` VALUES (1,'General','General'),(6,'Interests','Hobby');
/*!40000 ALTER TABLE `blanks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_text` text,
  `question_variant_a` varchar(255) DEFAULT NULL,
  `question_variant_b` varchar(255) DEFAULT NULL,
  `question_variant_c` varchar(255) DEFAULT NULL,
  `blank_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `blank_id` (`blank_id`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`blank_id`) REFERENCES `blanks` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (5,'Favourite food','Pizza','Sushi','Doner',1),(6,'Favourite drink','Coca-Cola','Fanta','Sprite',1),(7,'What hobby you like?','Read books','Play piano','Play chess',6);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_authorities`
--

DROP TABLE IF EXISTS `roles_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles_authorities` (
  `role_id` int unsigned NOT NULL,
  `authority_id` int unsigned NOT NULL,
  PRIMARY KEY (`role_id`,`authority_id`),
  KEY `authority_id` (`authority_id`),
  CONSTRAINT `roles_authorities_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `roles_authorities_ibfk_2` FOREIGN KEY (`authority_id`) REFERENCES `authorities` (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_authorities`
--

LOCK TABLES `roles_authorities` WRITE;
/*!40000 ALTER TABLE `roles_authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` text,
  `last_name` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `interests` text,
  `login` text,
  `group_name` varchar(255) DEFAULT NULL,
  `email` text,
  `phone` text,
  `password` text,
  `role_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Azamat','Saiduly',18,'Football','aza123','SE-1909','azamattolegenov1@gmail.com','+77005488851','$2a$10$2TK7YhZeDFjjA4RwZ8X/zuzjpJ.2hc4HVpk9vJRL4HmJjZUNh3h42',1),(2,'Aniyar','Kaliev',18,'Football','aniyar','SE-1909','aniyar@example.com','+77005400022','$2a$10$2TK7YhZeDFjjA4RwZ8X/zuzjpJ.2hc4HVpk9vJRL4HmJjZUNh3h42',2),(3,'Ainura','Kursabayeva',18,'Read books','ainura1','SE-1909','ainura@example.com','+77775400022','$2a$10$2TK7YhZeDFjjA4RwZ8X/zuzjpJ.2hc4HVpk9vJRL4HmJjZUNh3h42',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-09 23:17:54
