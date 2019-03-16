/*
Проект Бібліотека
Тихочнук Ярослав,  
*/

CREATE DATABASE if not exists Library;

use Library;
/* 
Таблиця Книги, призначена для зберігання інформації про:
	- назву, тип, к-сть сторінок та номер видданя.

Primary key - bookId; 
Foreign key - editionId (зв'язок із таблицею публікації)

*/
-- --------------------------------------------------------
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;

CREATE TABLE `Book` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `type` varchar(30) NOT NULL,
  `size` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `editionId` int(11) NOT NULL,
  PRIMARY KEY (`bookId`),
  KEY `editionId` (`editionId`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;


/* 

Таблиця Публікації, містить інформацію про дату публцікації та назву Видавництва

Primary key - editionId; 

*/
--
-- Table structure for table `Edition`
--
DROP TABLE IF EXISTS `Edition`;

CREATE TABLE IF NOT EXISTS `Edition` (
  `editionId` int(11) NOT NULL AUTO_INCREMENT,
  `dateOfPublication` date NOT NULL,
  `publisherName` varchar(100) NOT NULL,
  PRIMARY KEY (`editionId`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;


/* 

Таблиця Електронні копії, показує приналежність до якоїсь книги

Primary key - editionId; 

*/
--
-- Table structure for table `Ebook`
--
DROP TABLE IF EXISTS `Ebook`;

CREATE TABLE IF NOT EXISTS `Ebook` (
  `ebookId` int(11) NOT NULL AUTO_INCREMENT,
  `bookId` int(11) NOT NULL,
  PRIMARY KEY (`ebookId`),
  KEY `bookId` (`bookId`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;


------------------------------------------------
--                Dumping data                --
------------------------------------------------          


--
-- Dumping data for table `Book`
--

INSERT INTO `Book` (`bookId`, `name`, `author`, `type`, `size`, `amount`, `editionId`) VALUES
(1, 'Lord of the Rigs: Fellowship of the ring', 'Tolkien', 'fantasy', 505, 100, 3),
(2, '21 habbits of good sleep', 'bussinesman', 'motivation', 405, 200, 4),
(3, 'bukvar', 'MON','pidruchnuk' , 300, 100, 3),
(4, 'abetka', 'MON','pidruchnuk', 50, 100, 3);


--
-- Dumping data for table `Edition`
--

INSERT INTO `Edition` (`editionId`, `dateOfPublication`, `publisherName`) VALUES
(1, '2018-12-11', 'Osnovy'),
(2, '2019-01-11', 'Flagman'),
(3, '2018-10-03', 'BBC books publisher center'),
(4, '2018-07-11', 'Osnovy');

--
-- Dumping data for table `Ebook`
--

INSERT INTO `Ebook` (`ebookId`, `bookId`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 2),
(5, 2);





