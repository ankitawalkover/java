-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 22, 2015 at 11:12 AM
-- Server version: 5.6.19-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mailer`
--
CREATE DATABASE mailer;
-- --------------------------------------------------------

--
-- Table structure for table `email_queue`
--

CREATE TABLE IF NOT EXISTS `email_queue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_email_address` varchar(45) NOT NULL,
  `to_email_address` varchar(45) NOT NULL,
  `subject` text,
  `body` text,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `retry` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `email_queue`
--

INSERT INTO `email_queue` (`id`, `from_email_address`, `to_email_address`, `subject`, `body`, `status`, `retry`) VALUES
(1, 'ankita@holidayiq.com', 'indoreankita@gmail.com', 'test1', 'Test Message 1', 0, 0),
(2, 'ankita@holidayiq.com', 'indoreankita@gmail.com', 'test2', 'Test Message 2', 0, 0),
(3, 'ankita@holidayiq.com', 'indoreankita@gmail.com', 'test3', 'Test Message 3', 0, 0),
(4, 'ankita@holidayiq.com', 'indoreankita@gmail.com', 'test4', 'Test Message 4', 0, 0),
(5, 'ankita@holidayiq.com', 'indoreankita@gmail.com', 'test5', 'Test Message 5', 0, 0),
(6, 'ankita@holidayiq.com', 'indoreankita@gmail.com', 'test6', 'Test Message 6', 0, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;