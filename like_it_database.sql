CREATE DATABASE  IF NOT EXISTS `like_it` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `like_it`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: like_it
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `auth`
--

DROP TABLE IF EXISTS `auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth` (
  `user_id` int(10) unsigned NOT NULL,
  `access_token` varchar(100) DEFAULT NULL COMMENT 'token for access to secret information of user',
  `access_token_expiration` timestamp NULL DEFAULT NULL COMMENT 'after this time token will be not valid',
  `refresh_token` varchar(100) DEFAULT NULL COMMENT 'token for refreshing the pair of tokens (access, refresh)',
  `refresh_token_expiration` timestamp NULL DEFAULT NULL COMMENT 'after this time token will be not valid',
  PRIMARY KEY (`user_id`),
  KEY `fk_auth_user1_idx` (`user_id`),
  CONSTRAINT `fk_auth_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table for tokens';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth`
--

LOCK TABLES `auth` WRITE;
/*!40000 ALTER TABLE `auth` DISABLE KEYS */;
INSERT INTO `auth` VALUES (1,'1:f677ee1d6cd6fa06efdaebc34a9fb3c0d15bd545989166c4dc4bf912882093b4:SUPER_ADMIN','2018-02-06 07:51:09','03e032022f7e63274c398d8b7131229458d3ce3e4e9aab5c57e91b3fce48fbdc','2019-02-06 05:51:09'),(2,'2:23b1fcb0dfdfd851582b05b6d65157333a1ba03d2c6133363b00dc8a29613136:USER','2018-01-22 17:17:01','073650444a14e83bf5d65e70b4d28c1aee88c415bed560e0adf9645fe14227d9','2019-01-22 15:17:01'),(3,'3:8cda06ff014f8fe6ee59f0db12cc280557dda0551265dceed12e1f42633ade57:ADMIN','2018-02-01 18:53:47','9cc6bccb3eb1dd2831e8cf9873c74e1d315c2e0618957f69534164960578409a','2019-02-01 16:53:47'),(4,'4:77339e90324733ff9919db84f406c8986cb62fba74346d68c9b4cbbd1ff17dac:USER','2018-01-22 17:09:59','c726a0182050a9ce45a5ec5b88b535bf616853324798c181e4d144fd7ecb54b4','2019-01-22 15:09:59'),(5,NULL,NULL,NULL,NULL),(6,'6:133d411af1dbfb08c83d3282a51556cb9d6b60e43070d64e05e807209daecb62:USER','2018-01-27 12:15:56','779ba372719ff55e38c98898ade1f5bab4050e0b6ed1eef9ba704c94b8ea42ca','2019-01-27 10:15:56'),(7,NULL,NULL,NULL,NULL),(8,NULL,NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL),(12,'12:da3eb4dfda12d1e4b41cf4651c1dc15c00746cf24b1b77788040fa9b8bce2f5e:USER','2018-02-01 21:45:50','0e58fa39e6e30db185e982250674b3078b2ca969da98e1d1a787c020d912479e','2019-02-01 19:45:50'),(13,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `lang_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'identificator if language',
  `lang_short_name` varchar(3) NOT NULL COMMENT 'short nae of language',
  `lang_full_name` varchar(50) NOT NULL COMMENT 'full name of language',
  PRIMARY KEY (`lang_id`),
  UNIQUE KEY `lang_short_name_UNIQUE` (`lang_short_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'ru','Russian'),(2,'be','Belarussian'),(3,'en','English');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes` (
  `user_id` int(10) unsigned NOT NULL,
  `message_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`message_id`),
  KEY `fk_user_has_topic_topic1_idx` (`message_id`),
  KEY `fk_user_has_topic_user1_idx` (`user_id`),
  CONSTRAINT `fk_user_has_topic_topic1` FOREIGN KEY (`message_id`) REFERENCES `message` (`message_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_topic_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (2,1),(1,14),(2,14),(3,14),(3,83);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `like_it`.`likes_AFTER_INSERT` AFTER INSERT ON `likes` FOR EACH ROW
BEGIN
	UPDATE `user` SET `user_likes` = `user_likes` + 1 WHERE `user_id` = (
		SELECT `user_id` FROM `message` WHERE `message_id` = NEW.message_id
    );
    UPDATE `message` SET `message_likes` = `message_likes` + 1 WHERE `message_id`=NEW.message_id;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `like_it`.`likes_AFTER_DELETE` AFTER DELETE ON `likes` FOR EACH ROW
BEGIN
	UPDATE `user` SET `user_likes` = `user_likes` - 1 WHERE `user_id` = (
		SELECT `user_id` FROM `message` WHERE `message_id` = OLD.message_id
    );
    UPDATE `message` SET `message_likes` = `message_likes` - 1 WHERE `message_id`=OLD.message_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `message_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id of message',
  `message_context` longtext NOT NULL COMMENT 'text of message',
  `message_creating_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'date of creating a message',
  `message_likes` int(11) DEFAULT '0' COMMENT 'message''s likes',
  `user_id` int(10) unsigned NOT NULL,
  `topic_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `fk_message_user1_idx` (`user_id`),
  KEY `fk_message_topic1_idx` (`topic_id`),
  CONSTRAINT `fk_message_topic1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='message (comment) of user in topic';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'Kek','2018-01-09 14:45:05',1,1,1),(14,'А мне не спадабалася','2018-01-10 15:56:42',3,2,4),(19,'Good luck ahaphahahah','2018-01-11 14:23:43',0,1,1),(83,'Я люблю с сырным. Думаю это классический для курочки :)','2018-01-21 19:46:46',1,2,24);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theme`
--

DROP TABLE IF EXISTS `theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `theme` (
  `theme_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `theme_is_blocked` bit(1) NOT NULL DEFAULT b'0' COMMENT 'show/not show this theme',
  PRIMARY KEY (`theme_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='theme of topics';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme`
--

LOCK TABLES `theme` WRITE;
/*!40000 ALTER TABLE `theme` DISABLE KEYS */;
INSERT INTO `theme` VALUES (1,'\0'),(2,'\0'),(3,'\0'),(4,'\0'),(5,'\0'),(16,'\0'),(18,'\0');
/*!40000 ALTER TABLE `theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theme_has_language`
--

DROP TABLE IF EXISTS `theme_has_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `theme_has_language` (
  `theme_id` int(10) unsigned NOT NULL,
  `lang_id` int(10) unsigned NOT NULL,
  `locale_theme_name` varchar(50) NOT NULL COMMENT 'name of theme on different languages',
  PRIMARY KEY (`theme_id`,`lang_id`),
  KEY `fk_theme_has_language_language1_idx` (`lang_id`),
  KEY `fk_theme_has_language_theme1_idx` (`theme_id`),
  CONSTRAINT `fk_theme_has_language_language1` FOREIGN KEY (`lang_id`) REFERENCES `language` (`lang_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_theme_has_language_theme1` FOREIGN KEY (`theme_id`) REFERENCES `theme` (`theme_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme_has_language`
--

LOCK TABLES `theme_has_language` WRITE;
/*!40000 ALTER TABLE `theme_has_language` DISABLE KEYS */;
INSERT INTO `theme_has_language` VALUES (1,1,'Спорт'),(1,2,'Спорт'),(1,3,'Sport'),(2,1,'Искусство'),(2,2,'Мастацтва'),(2,3,'Art'),(3,1,'Литература'),(3,2,'Літаратура'),(3,3,'Literature'),(4,1,'Здоровье'),(4,2,'Здароўе'),(4,3,'Health'),(5,1,'Садоводство'),(5,2,'Садоўніцтва'),(5,3,'Gardening'),(16,1,'Хобби'),(16,2,'Хобі'),(16,3,'Hobby'),(18,1,'Автомобили'),(18,2,'Аўтамабілі'),(18,3,'Cars');
/*!40000 ALTER TABLE `theme_has_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `topic_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id of topic',
  `topic_header` varchar(250) NOT NULL COMMENT 'header of topic',
  `topic_context` longtext NOT NULL COMMENT 'context o topic',
  `topic_creating_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'date of creating a topic',
  `user_id` int(10) unsigned NOT NULL,
  `theme_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`topic_id`),
  KEY `fk_topic_user1_idx` (`user_id`),
  KEY `header_index` (`topic_header`),
  KEY `fk_topic_theme1_idx` (`theme_id`),
  FULLTEXT KEY `full_text_header` (`topic_header`,`topic_context`),
  CONSTRAINT `fk_topic_theme1` FOREIGN KEY (`theme_id`) REFERENCES `theme` (`theme_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_topic_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='topic on what will be the conversation.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'My first post with new database','Надеюсь больше не нужно будет перестраивать бд. Ну пажээээээээ','2018-01-04 19:07:08',1,2),(4,'Мертвые души. Гоголь','Шо за книга, норм полистац?','2018-01-07 11:50:12',2,3),(24,'Кто любит курочку KFC?','С каким соусом вкуснее?','2018-01-21 19:42:28',3,2),(36,'Lorem','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer mattis auctor ullamcorper. Integer et sollicitudin enim, et eleifend felis. Curabitur tempor auctor sapien, sollicitudin malesuada purus euismod non. Vestibulum ac vulputate nisi. Integer ultrices, lacus sit amet condimentum malesuada, ex odio eleifend mauris, vitae sollicitudin odio turpis vitae sapien. Vestibulum elementum ultrices cursus. Curabitur malesuada ut tellus ac pretium. Suspendisse dolor ligula, tristique nec semper nec, suscipit sit amet leo. Ut orci eros, congue non libero quis, hendrerit malesuada purus. Nam condimentum purus at ante posuere, at aliquam mauris condimentum. Suspendisse vitae quam aliquam, ullamcorper velit vitae, pretium augue. Donec ac velit purus. Sed id diam est.\r\n\r\nVivamus venenatis, nisi non cursus faucibus, dolor nibh laoreet mi, ut bibendum nulla lorem a libero. Morbi posuere ullamcorper turpis, non auctor nunc eleifend vitae. Suspendisse eu massa in sem vulputate pharetra. Cras fringilla felis sed efficitur aliquet. Etiam ullamcorper semper nunc et imperdiet. Morbi rutrum at neque in tincidunt. Nunc pretium tincidunt justo, et porttitor ipsum eleifend vitae. Morbi efficitur, ante quis mattis venenatis, urna erat consectetur nibh, sit amet aliquet est tortor eget nibh. Cras a dui velit. Vivamus tellus dui, mattis quis varius vel, scelerisque vitae risus.','2018-01-25 20:50:26',1,2),(41,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer mattis auctor ullamcorper. ','Donec ac velit purus. Sed id diam est. Vivamus venenatis, nisi non cursus faucibus, dolor nibh laoreet mi, ut bibendum nulla lorem a libero. Morbi posuere ullamcorper turpis, non auctor nunc eleifend vitae. Suspendisse eu massa in sem vulputate pharetra. Cras fringilla felis sed efficitur aliquet. Etiam ullamcorper semper nunc et imperdiet. Morbi rutrum at neque in tincidunt. Nunc pretium tincidunt justo, et porttitor ipsum eleifend vitae. Morbi efficitur, ante quis mattis venenatis, urna erat consectetur nibh, sit amet aliquet est tortor eget nibh. Cras a dui velit. Vivamus tellus dui, mattis quis varius vel, scelerisque vitae risus.','2018-02-01 17:16:30',1,3),(42,'Vestibulum elementum ultrices cursus. Curabitur malesuada ut tellus ac pretium. ','Ut orci eros, congue non libero quis, hendrerit malesuada purus. Nam condimentum purus at ante posuere, at aliquam mauris condimentum. Suspendisse vitae quam aliquam, ullamcorper velit vitae, pretium augue. ','2018-02-01 17:19:33',1,1),(43,'Morbi posuere ullamcorper turpis, non auctor nunc eleifend vitae. Suspendisse eu massa in sem vulputate pharetra. Cras fringilla felis sed efficitur aliquet.','Morbi rutrum at neque in tincidunt. Nunc pretium tincidunt justo, et porttitor ipsum eleifend vitae. Morbi efficitur, ante quis mattis venenatis, urna erat consectetur nibh, sit amet aliquet est tortor eget nibh. Cras a dui velit. Vivamus tellus dui, mattis quis varius vel, scelerisque vitae risus.','2018-02-01 17:20:02',1,5),(44,'Suspendisse eu massa in sem vulputate pharetra. Cras fringilla felis sed efficitur aliquet. ','Etiam ullamcorper semper nunc et imperdiet. Morbi rutrum at neque in tincidunt. Nunc pretium tincidunt justo, et porttitor ipsum eleifend vitae. Morbi efficitur, ante quis mattis venenatis, urna erat consectetur nibh, sit amet aliquet est tortor eget nibh. Cras a dui velit. Vivamus tellus dui, mattis quis varius vel, scelerisque vitae risus.','2018-02-01 17:20:38',1,4),(45,'Cras fringilla felis sed efficitur aliquet. Etiam ullamcorper semper nunc et imperdiet.','Nunc pretium tincidunt justo, et porttitor ipsum eleifend vitae. Morbi efficitur, ante quis mattis venenatis, urna erat consectetur nibh, sit amet aliquet est tortor eget nibh.','2018-02-01 17:21:28',1,16),(46,'Morbi efficitur','Nunc pretium tincidunt justo, et porttitor ipsum eleifend vitae. Morbi efficitur, ante quis mattis venenatis, urna erat consectetur nibh, sit amet aliquet est tortor eget nibh.','2018-02-01 17:23:04',3,4),(47,'Curabitur malesuada ut tellus ac pretium. Suspendisse dolor ligula, tristique nec semper nec, suscipit sit amet leo. ','Nunc pretium tincidunt justo, et porttitor ipsum eleifend vitae. Morbi efficitur, ante quis mattis venenatis, urna erat consectetur nibh, sit amet aliquet est tortor eget nibh. Cras a dui velit. Vivamus tellus dui, mattis quis varius vel, scelerisque vitae risus.','2018-02-01 17:23:49',3,5),(48,'Etiam ullamcorper semper nunc et imperdiet.','Morbi rutrum at neque in tincidunt. Nunc pretium tincidunt justo, et porttitor ipsum eleifend vitae. ','2018-02-01 17:24:45',1,4);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'universal identifierof user',
  `user_name` varchar(50) NOT NULL COMMENT 'name of user',
  `user_email` varchar(250) NOT NULL COMMENT 'user email',
  `user_login` varchar(50) NOT NULL COMMENT 'login of user',
  `user_password` varchar(64) NOT NULL COMMENT 'password hash ',
  `user_password_salt` varchar(64) NOT NULL COMMENT 'salt for password hash',
  `user_likes` int(11) DEFAULT '0' COMMENT 'likes for messages which user wrote',
  `user_about` longtext COMMENT 'additional information about user?, which he wrote by himself',
  `user_is_banned` bit(1) DEFAULT b'0' COMMENT 'state of user. Default - not banned',
  `user_role` enum('ADMIN','USER','SUPER_ADMIN') NOT NULL DEFAULT 'USER',
  `user_img` varchar(256) NOT NULL DEFAULT 'like_it_profile.jpg',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_login_UNIQUE` (`user_login`),
  KEY `user_name_INDEX` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='User of network';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Tema','artyom.sths5@gmail.com','temaq15','552148560dfa24bb6cebc075777b2271c47f1d96664267b83fa200e1b4eafdee','0948add46a395077c953ae02701c6fad257ee4c95cb75b04729fe823f040c8c8',16,'Der ds sd','\0','SUPER_ADMIN','8uDimjTtebc.jpg'),(2,'KalaJa','Kala@mail.com','kalakala','756987331fa5fdd93dba487b03729b9d68b72080ac5d25434729fbac87d75a24','8b672f17b07325bed231e018c7754a5b4e4efb8c9d84afd121ddf86e5623eb24',8,'Just Kala','\0','USER','like_it_profile.jpg'),(3,'QweRty','qwer@gmail.com','qweqweqwe','d938d60885b54094f665b22617482d00145536a0a5de570b4d9b31e6169c91d8','ccdbe9878feb312a7dcbbf5a1a17a6d9e4b84e359f554b0a4fc7ffbf36171d32',0,'Im Qwe Rty. I like baseball & chicken :)','\0','ADMIN','Screenshot_20180105-235936.png'),(4,'Hazel','hazelololo@gmail.com','hazelololo','f38768050b851e7f966bdadcfcef313f61dd70d5377a0a6bc466321501cb6526','eeb7d89ef8dbb245f01a03afaf45dfa4612629085574dc05bb014b2a86fb40fe',0,NULL,'\0','USER','like_it_profile.jpg'),(5,'ZxcZxc','artyom.stan0905@gmail.com','zxczxczxc','9c738e7efdf9503717b0e64fc62935fdf061762b143df1f756d32dc99f7e32dc','10e6950874d5f3c4d6f5f4ec9e5a4ceb1fe6777af461df3dc3bbbb46cc154cef',0,NULL,'\0','USER','like_it_profile.jpg'),(6,'AsdAsd','artyom.stan0905@gmail.com','asdasdasd','99018a3dd897260458c3cbd3651c28cde487cad4c36fd60fcda8ec581a8c0bd6','75541ed44348430d6bfa17ce6e32dbfd73c6a6779a3ea5d9ea01e7024d0e5582',0,NULL,'\0','USER','like_it_profile.jpg'),(7,'RtyRty','artyom.stan0905@gmail.com','rtyrty','b53a3a52e94238fcdea5a75603fea28a005f1362dc5a19d98c33e696bd567ed9','c784bff5a066cc9e96d736d62fdcbfd0333d8c234fa86ee2ce192a4e3ede7832',0,NULL,'\0','USER','like_it_profile.jpg'),(8,'FghFgh','artyom.stan0905@gmail.com','fghfgh','26a0b36fc4ade0b871d1f11490c424cc54511692a60f3c00780f4ce9d41fb6fa','2d5689403a31293f6bf7455441431981d5e7abe370190ff3feb19ca68c6082cf',0,NULL,'\0','USER','like_it_profile.jpg'),(9,'UioUio','artyom.stan0905@gmail.com','uiouio','d00b96b84deae9465781aa284bb0e2024f912d81dfa06c396da903f56b439ca1','04e983c3321989292f57672913fe3eb5532db9d5ce36fd4b0187109815284944',0,NULL,'\0','USER','like_it_profile.jpg'),(10,'JklJKl','artyom.stan0905@gmail.com','jkljkl','8156730f65ac74ee2b679ae0688265a78051410ef6b46b48a8fc6176aa3bf815','4985a7b29cb530a8ae1b6d7a6f28e925b560c4d7861c6d3797a62ed762eddc39',0,NULL,'\0','USER','like_it_profile.jpg'),(11,'EwqEwq','artyom.stan0905@gmail.com','ewqewq','36936607463f8e1021d753b66ee498641099b5afba5d2e04b236f206e447880c','3dc51838816628fd3644c5d112ce4ed3ffee6c691a2984d8cd10d007f5b413a2',0,NULL,'\0','USER','like_it_profile.jpg'),(12,'IpIn','ipin@gmail.com','ipimq','fb4c0e32d5a77bf9683c73be67be5dab363e4eb20a06756f368daf70a8534740','3d0819c2b2cbd1a0167421ede117550e64433c7de4517696f7eb4be8c2e4a308',0,'Ёп Ян','\0','USER','like_it_profile.jpg'),(13,'Artem','artyom.stan0905@gmail.com','Kelli','b3bdd88a7df1319fc9f2d62b5d5681e95a399168aadc031f229d6d584482e11e','d2fc2c4403c7edc42b853cd25d0bcf18d92d0e2118143fc86d5129ba3dec2f9a',0,NULL,'\0','USER','like_it_profile.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `like_it`.`user_AFTER_INSERT` AFTER INSERT ON `user` FOR EACH ROW
BEGIN
	INSERT INTO `auth` (`user_id`) VALUES (NEW.user_id);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'like_it'
--

--
-- Dumping routines for database 'like_it'
--
/*!50003 DROP PROCEDURE IF EXISTS `addMessage` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addMessage`(
	IN message_context LONGTEXT,
	IN user_id INT,
    IN topic_id INT
)
BEGIN
	INSERT INTO message (message.message_context, message.user_id, message.topic_id) VALUES (message_context, user_id, topic_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addNewThemeId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewThemeId`(
	OUT theme_id INT
)
BEGIN
	INSERT INTO theme (theme.theme_is_blocked) values (false);
	SELECT last_insert_id();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addThemeLocalizedName` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addThemeLocalizedName`(
	IN theme_id INT,
    IN lang_name VARCHAR(50),
    IN theme_name VARCHAR(50)
)
BEGIN
	INSERT INTO theme_has_language (
		theme_has_language.theme_id,
        theme_has_language.lang_id,
        theme_has_language.locale_theme_name
    )
    VALUES (
		theme_id,
        (
			SELECT language.lang_id
            FROM language
            WHERE language.lang_full_name = lang_name
        ),
        theme_name
    );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addTopic` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addTopic`(
	IN topic_header VARCHAR(250),
    IN topic_context LONGTEXT,
    IN user_id INT,
    IN theme_id INT
)
BEGIN
	INSERT INTO topic (topic.topic_header, topic.topic_context, topic.user_id, topic.theme_id) VALUES (topic_header, topic_context, user_id, theme_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addUser`(
	IN user_name VARCHAR(50),
    IN user_email VARCHAR(250),
    IN user_login VARCHAR(50),
    IN user_password VARCHAR(64),
    IN user_password_salt VARCHAR(64)    
)
BEGIN
	INSERT INTO user (
		user.user_name, 
        user.user_email, 
        user.user_login, 
        user.user_password,
        user.user_password_salt
	) 
        VALUES (
			user_name, 
			user_email, 
			user_login, 
			user_password,
			user_password_salt
        );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `banUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `banUser`(
	IN user_id INT
)
BEGIN
	UPDATE user SET user.user_is_banned = true WHERE user.user_id = user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `changeProfileInfo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `changeProfileInfo`(
	IN user_id INT,
    IN user_name VARCHAR(50),
    IN user_email VARCHAR(250),
    IN user_about LONGTEXT
)
BEGIN
	UPDATE user SET user.user_name = user_name, user.user_email = user_email, user.user_about = user_about WHERE user.user_id = user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `countMessagesOfUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `countMessagesOfUser`(
	IN user_id INT,
    OUT count INT
)
BEGIN
	SELECT COUNT(message.topic_id) FROM message WHERE message.user_id = user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `countTopicsOfUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `countTopicsOfUser`(
	IN user_id INT,
    OUT count INT
)
BEGIN
	SELECT COUNT(topic.topic_id) FROM topic WHERE topic.user_id = user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deleteMessage` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteMessage`(
	IN message_id INT
)
BEGIN
	DELETE FROM message WHERE message.message_id = message_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deleteTheme` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteTheme`(
	IN theme_id INT
)
BEGIN
	DELETE FROM theme WHERE theme.theme_id = theme_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deleteTopic` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteTopic`(
	IN topic_id INT
)
BEGIN
	DELETE FROM topic WHERE topic.topic_id = topic_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getAdmins` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAdmins`(
	IN from_id INT,
    IN count_user INT,
    OUT user_id INT,
    OUT user_name VARCHAR(50),
    OUT user_login VARCHAR(50),
    OUT user_email VARCHAR(250),
    OUT user_role ENUM('ADMIN', 'USER', 'SUPER_ADMIN'),
    OUT user_is_banned BIT(1)
)
BEGIN
	SELECT user.user_id, 
			user.user_name,
            user.user_login,
            user.user_email,
            user.user_role,
            user.user_is_banned            
	FROM user
    JOIN (SELECT user.user_id 
			FROM user 
			WHERE user.user_role = 'ADMIN' OR user.user_role = 'SUPER_ADMIN' 
            ORDER BY user.user_id DESC 
            LIMIT from_id, count_user
            ) as t ON t.user_id = user.user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getAllThemes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllThemes`(
	IN lang VARCHAR(3),
    OUT theme_id INT,
    OUT locale_theme_name VARCHAR(50)
)
BEGIN
	SELECT themes.theme_id, themes.locale_theme_name 
    FROM theme_has_language as themes 
    JOIN language ON themes.lang_id = language.lang_id
    JOIN theme ON themes.theme_id = theme.theme_id
    WHERE language.lang_short_name = lang AND theme.theme_is_blocked = 0;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getAllTopics` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllTopics`(
	IN locale VARCHAR(3),
    IN from_id INT,
    IN count_topic INT,
	OUT topic_id INT,
    OUT topic_header VARCHAR(250),
    OUT topic_context LONGTEXT,
    OUT topic_creating_date TIMESTAMP,
    OUT user_id INT,
    OUT user_name VARCHAR(50),
    OUT theme_id INT,
    OUT theme_name VARCHAR(50),
    OUT user_img VARCHAR(256)
)
BEGIN
	SELECT  topic.topic_id,
			topic.topic_header, 
            topic.topic_context, 
            topic.topic_creating_date, 
            user.user_id, 
            user.user_name, 
            topic.theme_id, 
            (
				SELECT locale_theme_name 
                FROM theme_has_language as themes 
                JOIN language ON themes.lang_id = language.lang_id 
                WHERE language.lang_short_name = locale AND themes.theme_id = topic.theme_id
			) as theme_name,
            user.user_img
	FROM topic
	JOIN (SELECT topic.topic_id 
			FROM topic 
            JOIN theme ON topic.theme_id = theme.theme_id
			WHERE theme.theme_is_blocked = 0 
            ORDER BY topic.topic_id DESC 
            LIMIT from_id, count_topic
            ) as t ON t.topic_id = topic.topic_id
    JOIN user ON topic.user_id = user.user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getIdByAccessToken` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getIdByAccessToken`(
	IN token VARCHAR(100),
    OUT user_id INT
)
BEGIN
	SELECT auth.user_id FROM auth WHERE auth.access_token=token AND auth.access_token_expiration>=now();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getIdByRefreshToken` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getIdByRefreshToken`(
	IN token VARCHAR(100),
    OUT user_id INT
)
BEGIN
	SELECT auth.user_id FROM auth WHERE auth.refresh_token=token AND auth.refresh_token_expiration>=now();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getInfoForSignInByLogin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getInfoForSignInByLogin`(
	IN user_login VARCHAR(50),
    OUT user_id INT,
    OUT user_password VARCHAR(64),
    OUT user_password_salt VARCHAR(64),
    OUT user_role ENUM('ADMIN', 'USER','SUPER_ADMIN')
)
BEGIN
	SELECT user.user_id, user.user_password, user.user_password_salt, user.user_role FROM user WHERE user.user_login = user_login;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getLanguageIdByName` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getLanguageIdByName`(
	IN lang_short_name VARCHAR(3),
    OUT lang_id INT
)
BEGIN
	SELECT language.lang_id FROM language WHERE language.lang_short_name=lang_short_name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getMessagesByTopicId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getMessagesByTopicId`(
	IN topic_id INT,
	OUT message_id INT,
    OUT message_context LONGTEXT,
    OUT message_creating_date TIMESTAMP,
    OUT message_likes INT,
    OUT user_id INT,
    OUT user_name VARCHAR(50),
    OUT liked_users_id TEXT
)
BEGIN
	SELECT  message.message_id, 
            message.message_context, 
            message.message_creating_date, 
            message.message_likes,
            user.user_id, 
            user.user_name,
            (
				SELECT group_concat(likes.user_id) FROM likes WHERE likes.message_id=message.message_id
            ) as liked_users_id
    FROM message JOIN user ON message.user_id = user.user_id
    WHERE message.topic_id = topic_id
	ORDER BY message.message_creating_date;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getMessagesByUserId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getMessagesByUserId`(
	IN user_id INT,
	OUT message_id INT,
    OUT message_context LONGTEXT,
    OUT message_creating_date TIMESTAMP,
    OUT message_likes INT,
    OUT topic_id INT,
    OUT liked_users_id TEXT
)
BEGIN
	SELECT message.message_id,
			message.message_context, 
            message.message_creating_date, 
            message.message_likes,
            message.topic_id,
            (
				SELECT group_concat(likes.user_id) FROM likes WHERE likes.message_id=message.message_id
            ) as liked_users_id
	FROM message 
    WHERE message.user_id = user_id
    ORDER BY message.topic_id DESC, message_creating_date ASC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getPasswordAndSaltById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getPasswordAndSaltById`(
	IN user_id INT,
    OUT user_password VARCHAR(64),
    OUT user_password_salt VARCHAR(64)
)
BEGIN
	SELECT user.user_password, user.user_password_salt FROM user WHERE user.user_id = user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getThemeById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getThemeById`(
	IN lang VARCHAR(3),
    IN theme_id INT,
    OUT locale_theme_name VARCHAR(50)
)
BEGIN
	SELECT themes.locale_theme_name 
    FROM theme_has_language as themes 
    JOIN language ON themes.lang_id = language.lang_id
    JOIN theme ON themes.theme_id = theme.theme_id
    WHERE language.lang_short_name = lang AND themes.theme_id = theme_id AND theme.theme_is_blocked = 0;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getThemesInfo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getThemesInfo`(
	OUT theme_id INT,
    OUT lang_id INT,
    OUT lang_name VARCHAR(50),
    OUT theme_name VARCHAR(50),
    OUT theme_is_blocked BIT
)
BEGIN
	SELECT t.theme_id, t.lang_id, (
		SELECT language.lang_full_name FROM language where language.lang_id=t.lang_id
	) as lang, t.locale_theme_name, theme.theme_is_blocked
	FROM theme_has_language as t
	JOIN theme ON t.theme_id = theme.theme_id
	ORDER BY t.theme_id, t.lang_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getTopicById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getTopicById`(
	IN topic_id INT,
    IN locale VARCHAR(3),
    OUT topic_header VARCHAR(250),
    OUT topic_context LONGTEXT,
    OUT topic_creating_date TIMESTAMP,
    OUT user_id INT,
    OUT user_name VARCHAR(50),
    OUT theme_id INT,
    OUT theme_name VARCHAR(50),
    OUT user_img VARCHAR(256)
    
)
BEGIN
	SELECT  topic.topic_header, 
			topic.topic_context,
            topic.topic_creating_date,
            user.user_id,
            user.user_name,
            topic.theme_id, 
            (
				SELECT locale_theme_name 
                FROM theme_has_language as themes 
                JOIN language ON themes.lang_id = language.lang_id 
                WHERE language.lang_short_name = locale AND themes.theme_id = topic.theme_id
			) as theme_name,
            user.user_img
	FROM topic
    JOIN user ON topic.user_id = user.user_id
    JOIN theme ON topic.theme_id = theme.theme_id
    WHERE topic.topic_id = topic_id AND theme.theme_is_blocked = 0;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getTopicsByThemeId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getTopicsByThemeId`(
	IN theme_id INT,
    IN from_id INT,
    IN count_topic INT,
    IN locale VARCHAR(3),
    OUT topic_id INT,
    OUT topic_header VARCHAR(250),
    OUT topic_context LONGTEXT,
    OUT topic_creating_date TIMESTAMP,
    OUT user_id INT,
    OUT user_name VARCHAR(50),
    OUT theme_name VARCHAR(50),
    OUT user_img VARCHAR(256)
)
BEGIN
	SELECT topic.topic_id,
			topic.topic_header,
            topic.topic_context,
            topic.topic_creating_date,
            user.user_id,
            user.user_name,
            (
				SELECT locale_theme_name 
                FROM theme_has_language as themes 
                JOIN language ON themes.lang_id = language.lang_id 
                WHERE language.lang_short_name = locale AND themes.theme_id = topic.theme_id
			) as theme_name,
            user.user_img
	FROM topic 
    JOIN (SELECT topic.topic_id 
			FROM topic 
            JOIN theme ON topic.theme_id = theme.theme_id
			WHERE theme.theme_is_blocked = 0 AND topic.theme_id = theme_id
            ORDER BY topic.topic_id DESC 
            LIMIT from_id, count_topic
            ) as t ON t.topic_id = topic.topic_id
    JOIN user ON topic.user_id = user.user_id
	ORDER BY topic.topic_creating_date DESC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getTopicsByUserId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getTopicsByUserId`(
	IN user_id INT,
    IN locale VARCHAR(3),
    OUT topic_id INT,
    OUT topic_header VARCHAR(250),
    OUT topic_context LONGTEXT,
    OUT topic_creating_date TIMESTAMP,
    OUT topic_user_id INT,
    OUT topic_user_name VARCHAR(50),
    OUT theme_id INT,
    OUT theme_name VARCHAR(50)
)
BEGIN
	SELECT topic.topic_id,
			topic.topic_header,
            topic.topic_context,
            topic.topic_creating_date,
            user.user_id,
            user.user_name,
            topic.theme_id,
            (
				SELECT locale_theme_name 
                FROM theme_has_language as themes 
                JOIN language ON themes.lang_id = language.lang_id 
                WHERE language.lang_short_name = locale AND themes.theme_id = topic.theme_id
			) as theme_name
	FROM
	topic JOIN user ON topic.user_id = user.user_id
    WHERE topic.user_id = user_id
	ORDER BY topic.topic_creating_date DESC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getTopicsWhichCommendedByUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getTopicsWhichCommendedByUser`(
	IN user_id INT,
    IN locale VARCHAR(3),
    OUT topic_id INT,
    OUT topic_header VARCHAR(250),
    OUT topic_context LONGTEXT,
    OUT topic_creating_date TIMESTAMP,
    OUT topic_user_id INT,
    OUT topic_user_name VARCHAR(50),
    OUT theme_id INT,
    OUT theme_name VARCHAR(50)
)
BEGIN
	SELECT topic.topic_id,
			topic.topic_header,
            topic.topic_context,
            topic.topic_creating_date,
            user.user_id, 
            user.user_name,
            topic.theme_id,
            (
				SELECT locale_theme_name 
                FROM theme_has_language as themes 
                JOIN language ON themes.lang_id = language.lang_id 
                WHERE language.lang_short_name = locale AND themes.theme_id = topic.theme_id
			) as theme_name
	FROM topic
	JOIN message ON message.topic_id = topic.topic_id
	JOIN user ON topic.user_id = user.user_id
	WHERE message.user_id=user_id GROUP BY message.topic_id ORDER BY message.topic_id DESC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getUserById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserById`(
	IN user_id INT,
    OUT user_name VARCHAR(50),
    OUT user_email VARCHAR(250),
    OUT user_likes INT,
    OUT user_about LONGTEXT,
    OUT user_role ENUM('ADMIN', 'USER','SUPER_ADMIN'),
    OUT user_is_banned BIT(1),
    OUT user_img VARCHAR(256)
)
BEGIN
	SELECT user.user_name,
			user.user_email,
            user.user_likes,
            user.user_about,
            user.user_role,
            user.user_is_banned,
            user.user_img
	FROM user
    WHERE user.user_id = user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getUserByLogin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserByLogin`(IN login VARCHAR(50), OUT id INT)
BEGIN
	SELECT user_id INTO id FROM user WHERE user_login=login;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getUserByLoginOrName` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserByLoginOrName`(
	IN expression VARCHAR(50),
    IN from_id INT,
    IN count_user INT,
    OUT user_id INT,
    OUT user_name VARCHAR(50),
    OUT user_login VARCHAR(50),
    OUT user_email VARCHAR(250),
    OUT user_role ENUM('ADMIN', 'USER','SUPER_ADMIN'),
    OUT user_is_banned BIT(1)
)
BEGIN
	SELECT user.user_id, 
			user.user_name,
            user.user_login,
            user.user_email,
            user.user_role,
            user.user_is_banned
	FROM user
    JOIN (SELECT user.user_id 
			FROM user 
			WHERE user.user_name LIKE expression OR user.user_login LIKE expression 
            ORDER BY user.user_id DESC 
            LIMIT from_id, count_user
            ) as t ON t.user_id = user.user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getUsersByBannedState` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUsersByBannedState`(
	IN banned BIT(1),
    IN from_id INT,
    IN count_user INT,
    OUT user_id INT,
    OUT user_name VARCHAR(50),
    OUT user_login VARCHAR(50),
    OUT user_email VARCHAR(250),
    OUT user_role ENUM('ADMIN', 'USER','SUPER_ADMIN')
)
BEGIN
	SELECT user.user_id, 
			user.user_name,
            user.user_login,
            user.user_email,
            user.user_role
	FROM user
    JOIN (SELECT user.user_id 
			FROM user 
			WHERE user.user_is_banned = banned 
            ORDER BY user.user_id DESC 
            LIMIT from_id, count_user
            ) as t ON t.user_id = user.user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `hideTheme` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `hideTheme`(
	IN theme_id INT
)
BEGIN
	UPDATE theme SET theme.theme_is_blocked = true WHERE theme.theme_id = theme_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `likeMessage` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `likeMessage`(
	IN user_id INT,
    IN message_id INT
)
BEGIN
	INSERT INTO likes (likes.user_id, likes.message_id) VALUES (user_id, message_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `searchTopics` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `searchTopics`(
	IN expression VARCHAR(250),
    IN locale VARCHAR(3),
    IN from_id INT,
    IN count_topic INT,
    OUT topic_id INT,
    OUT topic_header VARCHAR(250),
    OUT topic_context LONGTEXT,
    OUT topic_creating_date TIMESTAMP,
    OUT user_id INT,
    OUT user_name VARCHAR(50),
    OUT theme_id INT,
    OUT theme_name VARCHAR(50),
    OUT user_img VARCHAR(256)
)
BEGIN
	SELECT  topic.topic_id,
			topic.topic_header, 
            topic.topic_context, 
            topic.topic_creating_date, 
            user.user_id, 
            user.user_name, 
            topic.theme_id, 
            (
				SELECT locale_theme_name 
                FROM theme_has_language as themes 
                JOIN language ON themes.lang_id = language.lang_id 
                WHERE language.lang_short_name = locale AND themes.theme_id = topic.theme_id
			) as theme_name,
            user.user_img
	FROM topic
	JOIN (SELECT topic.topic_id 
			FROM topic 
            JOIN theme ON topic.theme_id = theme.theme_id
			WHERE theme.theme_is_blocked = 0 
            AND
            MATCH(topic.topic_header, topic.topic_context) AGAINST(expression IN BOOLEAN MODE)
            LIMIT from_id, count_topic
            ) as t ON t.topic_id = topic.topic_id
    JOIN user ON topic.user_id = user.user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `setAdminToUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `setAdminToUser`(
	IN user_id INT
)
BEGIN
	UPDATE user SET user.user_role = 'USER' WHERE user.user_id = user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `setUserToAdmin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `setUserToAdmin`(
	IN user_id INT
)
BEGIN
	UPDATE user SET user.user_role = 'ADMIN' WHERE user.user_id = user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showTheme` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showTheme`(
	IN theme_id INT
)
BEGIN
	UPDATE theme SET theme.theme_is_blocked = false WHERE theme.theme_id = theme_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `unbanUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `unbanUser`(
	IN user_id INT
)
BEGIN
	UPDATE user SET user.user_is_banned = false WHERE user.user_id = user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `unlikeMessage` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `unlikeMessage`(
	IN user_id INT,
    IN message_id INT
)
BEGIN
	DELETE FROM likes WHERE likes.user_id = user_id AND likes.message_id = message_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updatePasswordById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePasswordById`(
	IN user_id INT,
    IN user_password VARCHAR(64)
)
BEGIN
	UPDATE user SET user.user_password=user_password WHERE user.user_id=user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateThemeLocalizedName` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateThemeLocalizedName`(
	IN theme_id INT,
    IN lang_name VARCHAR(50),
    IN theme_name VARCHAR(50)
)
BEGIN
	UPDATE theme_has_language
    SET theme_has_language.locale_theme_name = theme_name
    WHERE theme_has_language.theme_id = theme_id
			AND
			theme_has_language.lang_id = (
				SELECT language.lang_id
				FROM language
				WHERE language.lang_full_name = lang_name
            );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateTokensById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateTokensById`(
	IN access_token VARCHAR(100),
    IN refresh_token VARCHAR(100),
    IN user_id INT
)
BEGIN
	UPDATE auth 
    SET auth.access_token=access_token,
		auth.access_token_expiration=now()+interval 2 hour,
        auth.refresh_token=refresh_token,
        auth.refresh_token_expiration=now()+interval 1 year 
	WHERE auth.user_id=user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateUserImg` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateUserImg`(
	IN img VARCHAR(256),
    IN id INT
)
BEGIN
    UPDATE user SET user.user_img = img WHERE user.user_id = id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-06  9:59:50
