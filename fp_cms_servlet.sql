-- MySQL dump 10.17  Distrib 10.3.15-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: my_CMS_Servlet
-- ------------------------------------------------------
-- Server version	10.3.15-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `conferences`
--

DROP TABLE IF EXISTS `conferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conferences` (
  `conference_id` int(11) NOT NULL AUTO_INCREMENT,
  `conference_name_en_US` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `conference_name_uk_UA` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `conference_datetime` datetime NOT NULL,
  `conference_location_en_US` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `conference_location_uk_UA` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`conference_id`),
  UNIQUE KEY `conference_id_UNIQUE` (`conference_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conferences`
--

LOCK TABLES `conferences` WRITE;
/*!40000 ALTER TABLE `conferences` DISABLE KEYS */;
INSERT INTO `conferences` VALUES (1,'Java Day Kyiv 2019','Джава Дей Київ 2019','2019-06-04 11:59:00','Kyiv, Green street, 56','Київ, Україна'),(3,'Java Day Odesa 2019','Джава Дей Одеса 2019','2019-07-12 15:33:00','Odesa, Black street','Одеса, Україна'),(18,'Java Day Berlin 2019','Джава Дей Берлін 2019','2019-06-07 14:59:00','Aqua street','Берлін, Німеччина '),(29,'Spring Plaform','Спрінг Платформ','2019-06-20 19:11:00','Austin, Texas, U.S.A.','Остін, Техас, США'),(30,'MakeIT','МейкІТ','2019-06-19 19:14:00','Portoroz, Slovenia','Порторож, Словенія'),(31,'GeekOut','ГікАут','2019-06-19 19:15:00','Tallinn, Estonia','Таллінн, Естонія'),(33,'AxonIQ','Аксон','2019-06-19 19:29:00','Amsterdam, Netherlands','Амстердам, Нідерланди'),(34,'Python','Пайтон','2019-06-13 23:07:00','Dublin, Ireland','Дублін, Ірландія'),(35,'C#','Сі-шарп','2019-06-13 23:10:00','Mexico, Mexica','Мексика, Мексика'),(36,'C++','Сі ++','2019-06-13 23:11:00','Torronto, Canada','Торонто, Канада'),(37,'Ruby','Рубі','2019-06-13 23:12:00','Havana, Cuba','Гавана, Куба'),(50,'JavaZone','ДжаваЗон','2019-06-21 18:40:00','Oslo, Norway','Осло, Норвегія'),(52,'Java Day Dublin 2019','Джава Дей Дублін 2019','2019-06-22 19:01:00','Dublin, Ireland','Дублін, Ірландія');
/*!40000 ALTER TABLE `conferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `report_theme_en_US` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `report_theme_uk_UA` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `report_datetime` datetime NOT NULL,
  `speaker_id` int(11) NOT NULL,
  PRIMARY KEY (`report_id`),
  UNIQUE KEY `report_id_UNIQUE` (`report_id`),
  KEY `fk_reports_2_idx` (`speaker_id`),
  CONSTRAINT `fk_reports_2` FOREIGN KEY (`speaker_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (4,'Java 8','Джава 8','2019-06-05 19:07:49',7),(5,'Java 9','Джава 9','2019-06-06 11:41:37',7),(17,'Gradle','Гредл','2019-06-15 23:53:00',12),(23,'Maven2','Maven2','2019-06-12 07:13:16',7),(25,'Gradle5','Градл5','2019-06-14 13:01:00',7),(26,'Spring','Спрінг','2019-06-14 17:57:00',12),(27,'JavaServer Pages','Сторінки Джава-Сервер','2019-06-22 20:28:00',13),(28,'Servlet API','Сервлет','2019-06-19 20:30:00',12),(29,'Regex','Регулярні вирази','2019-06-20 21:47:00',7),(33,'C++ 19 History','С++ 19 Історія','2019-06-22 13:27:00',14),(34,'Spring Data','Спрінг Дата','2019-06-21 14:04:00',15),(35,'Java GC','Збирання сміття в Джава','2019-06-22 14:06:00',15),(37,'Spring Security 5','Спрінг Безпека 5','2019-06-22 19:43:00',14);
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports_conferences`
--

DROP TABLE IF EXISTS `reports_conferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports_conferences` (
  `report_id` int(11) NOT NULL,
  `conference_id` int(11) NOT NULL,
  KEY `fk_reports_conferences_1_idx` (`report_id`),
  KEY `fk_reports_conferences_2_idx` (`conference_id`),
  CONSTRAINT `fk_reports_conferences_1` FOREIGN KEY (`report_id`) REFERENCES `reports` (`report_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_reports_conferences_2` FOREIGN KEY (`conference_id`) REFERENCES `conferences` (`conference_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports_conferences`
--

LOCK TABLES `reports_conferences` WRITE;
/*!40000 ALTER TABLE `reports_conferences` DISABLE KEYS */;
INSERT INTO `reports_conferences` VALUES (4,1),(4,3),(17,18),(25,1),(26,1),(27,1),(28,1),(29,1),(33,36),(34,29),(35,1),(37,29);
/*!40000 ALTER TABLE `reports_conferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_login` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_password` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_name_en_US` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_name_uk_UA` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_surname_en_US` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_surname_uk_UA` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_email` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_role` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `user_login_UNIQUE` (`user_login`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (6,'dmytro','$2a$10$cXCmmSW369LJfishLXeUhuL56zYjJU10BU3s7y/JdJYfqg1K.1llu','Dmytro','Дмитро','Kasiianenko','Касіяненко','dmytro.kasiianenko@gmail.com','ADMIN'),(7,'danila','$2a$10$z9pVuTD6FfmFKCgf16s53uDpE17u2G2Fi4KOkqfZolHM3UyuPafHG','Danila','Данило','Kulesha','Кулеша','danila.kulesha@gmail.com','SPEAKER'),(8,'bogdan','$2a$10$sXXVkefXo0VCONpL6/ay.e2yswqgyxhnY46CiFEZspfjNrpPN41sy','Bogdan','Богдан','Borovets','Боровець','bogdan.borovets@gmail.com','USER'),(9,'oksana','$2a$10$I3bgogtMg0BgIBk8c4fvzuuEZy7fv04seSUaxD9U2g96A/5hJnrHy','Oksana','Оксана','Pohorila','Погоріла','oksana.pohorila@gmail.com','ADMIN'),(11,'konner','$2a$10$8tuCJ/qGmIU36QpKXDvmXe.o3gyrumMhm4GhogPJ2d.wPkgsQdz1G','Konner','Конер','Rice','Райс','konner.rice@gmail.com','USER'),(12,'eliza','$2a$10$k.UPl5Iz.mfKXZAFDoRnm.2CDlaoNFqvZhcQziV2wsr8Bh/icdKN2','Eliza','Еліза','Conway','Конвей','eliza.conway@gmail.com','SPEAKER'),(13,'olha','$2a$10$cQW4L7ZSZSAxtoRLxTP08uNLeAE1SUreAllZOKJuiX1zI6Woqa4FK','Olha','Ольга','Lasarenko','Лазаренко','olha.lasarenko@gmail.com','SPEAKER'),(14,'jorden','$2a$10$KJ4PEwHqR1XDiy/TPjciFOFz4rzrGWfAbARMTQQpC7K9x./bZiiYu','Jorden','Джорден','Rush','Раш','jordan.rush@gamila.com','SPEAKER'),(15,'hayden','$2a$10$7rO3GPw8n8mGvKbzQjnoTegsGRhQemw9MnwHl/hISLoemU.4ULxZ6','Hayden','Хайден','Pierce','Пірс','hayden.pierce@yahoo.com','SPEAKER'),(16,'camron','$2a$10$Jmc6vaHd//Ou5ogDsCqx7uelEDy8w6dv0GKO6n7mEmZ3cVnMA3m46','Camron','Камрон','Clark','Кларк','camron.clark@yahoo.com','SPEAKER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_conferences`
--

DROP TABLE IF EXISTS `users_conferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_conferences` (
  `user_id` int(11) NOT NULL,
  `conference_id` int(11) NOT NULL,
  KEY `fk_users_conferences_1_idx` (`user_id`),
  KEY `fk_users_conferences_2_idx` (`conference_id`),
  CONSTRAINT `fk_users_conferences_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_conferences_2` FOREIGN KEY (`conference_id`) REFERENCES `conferences` (`conference_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_conferences`
--

LOCK TABLES `users_conferences` WRITE;
/*!40000 ALTER TABLE `users_conferences` DISABLE KEYS */;
INSERT INTO `users_conferences` VALUES (11,18),(8,18),(6,1),(6,18),(8,1),(7,18),(8,3),(14,33),(14,50),(6,52),(14,29),(14,3),(6,30),(8,30),(8,33),(14,30),(6,31);
/*!40000 ALTER TABLE `users_conferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_speakers_rating`
--

DROP TABLE IF EXISTS `users_speakers_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_speakers_rating` (
  `user_id` int(11) NOT NULL,
  `speaker_id` int(11) NOT NULL,
  `speaker_rating` int(11) NOT NULL,
  `speaker_bonuses` int(11) NOT NULL,
  KEY `fk_users_speakers_rating_1_idx` (`user_id`),
  KEY `fk_users_speakers_rating_2_idx` (`speaker_id`),
  CONSTRAINT `fk_users_speakers_rating_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_speakers_rating_2` FOREIGN KEY (`speaker_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_speakers_rating`
--

LOCK TABLES `users_speakers_rating` WRITE;
/*!40000 ALTER TABLE `users_speakers_rating` DISABLE KEYS */;
INSERT INTO `users_speakers_rating` VALUES (8,7,4,400),(8,12,3,300),(6,7,3,300),(7,12,3,300),(6,12,3,300),(6,15,2,200),(6,13,2,200),(14,7,4,400),(14,15,4,400);
/*!40000 ALTER TABLE `users_speakers_rating` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-19 12:35:54
