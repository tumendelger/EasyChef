SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+08:00";

--
-- Database: `easysystems`
--


--
-- Dumping data for table `maincategory`
--

INSERT INTO `maincategory` (`id`, `name`) VALUES(1, 'kitchen');
INSERT INTO `maincategory` (`id`, `name`) VALUES(2, 'barman');

--
-- Dumping data for table `foodcategory`
--

INSERT INTO `foodcategory` (`id`, `name`, `action`, `actiontime`) VALUES(1, '1-р хоол', 0, NOW());
INSERT INTO `foodcategory` (`id`, `name`, `action`, `actiontime`) VALUES(2, '2-р хоол', 0, NOW());
INSERT INTO `foodcategory` (`id`, `name`, `action`, `actiontime`) VALUES(4, 'Дессерт', 0, NOW());


--
-- Dumping data for table `drinkcategory`
--

INSERT INTO `drinkcategory` (`id`, `name`, `action`, `actiontime`) VALUES(1, 'Архи ', 0, NOW());
INSERT INTO `drinkcategory` (`id`, `name`, `action`, `actiontime`) VALUES(2, 'Пиво', 0, NOW());
INSERT INTO `drinkcategory` (`id`, `name`, `action`, `actiontime`) VALUES(3, 'Ундаа', 0, NOW());
INSERT INTO `drinkcategory` (`id`, `name`, `action`, `actiontime`) VALUES(4, 'Ус', 0, NOW());
INSERT INTO `drinkcategory` (`id`, `name`, `action`, `actiontime`) VALUES(5, 'Цай', 0, NOW());
INSERT INTO `drinkcategory` (`id`, `name`, `action`, `actiontime`) VALUES(6, 'Шэйк / Shake ', 0, NOW());
INSERT INTO `drinkcategory` (`id`, `name`, `action`, `actiontime`) VALUES(7, 'Кофе', 0, NOW());

-- Dumping data for table `foodmenu`
--

INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(1, 'Tonkotsu ramen', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());
INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(2, 'Miso ramen /Гахай/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());
INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(3, 'Miso ramen /Үхэр/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());
INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(4, 'Tantanmen /Сам хорхой/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());
INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(5, 'Tantanmen /Үхэр/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());
INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(6, 'Tantanmen /Гахай/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());
INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(7, 'Kanjuku /Гахай/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());
INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(8, 'Kanjuku /Үхэр/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());
INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(9, 'Shoyu /Гахай/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());
INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(10, 'Shoyu /Үхэр/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());
INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(11, 'Gyoza', 5800, 1, 0, 2, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());
INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(12, 'Будаатай хуурга', 7800, 1, 0, 2, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());
INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(13, 'Chicken teryaki', 8800, 1, 0, 2, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());
INSERT INTO `foodmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `fcit`, `createdat`, `createdby`, `isdetails`, `mcid`, `action`, `actiontime`) VALUES(14, 'Kara age', 8800, 1, 0, 2, '2014-04-08 00:00:00', 1, 1, 1, 0, NOW());

--
-- Dumping data for table `drinkmenu`
--

INSERT INTO `drinkmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `dcid`, `createdat`, `createdby`, `mcid`, `action`, `actiontime`) VALUES(1, 'Coca cola', 2000, 0, 0, 3, '2014-04-08 00:00:00', 1, 2, 0, NOW());
INSERT INTO `drinkmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `dcid`, `createdat`, `createdby`, `mcid`, `action`, `actiontime`) VALUES(2, 'Pepci', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2, 0, NOW());
INSERT INTO `drinkmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `dcid`, `createdat`, `createdby`, `mcid`, `action`, `actiontime`) VALUES(3, 'Pepci', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2, 0, NOW());
INSERT INTO `drinkmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `dcid`, `createdat`, `createdby`, `mcid`, `action`, `actiontime`) VALUES(4, 'Sprite', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2, 0, NOW());
INSERT INTO `drinkmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `dcid`, `createdat`, `createdby`, `mcid`, `action`, `actiontime`) VALUES(5, '7Up', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2, 0, NOW());
INSERT INTO `drinkmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `dcid`, `createdat`, `createdby`, `mcid`, `action`, `actiontime`) VALUES(6, 'Fanta', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2, 0, NOW());
INSERT INTO `drinkmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `dcid`, `createdat`, `createdby`, `mcid`, `action`, `actiontime`) VALUES(7, 'Mirinda', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2, 0, NOW());
INSERT INTO `drinkmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `dcid`, `createdat`, `createdby`, `mcid`, `action`, `actiontime`) VALUES(8, 'Mountain Dew', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2, 0, NOW());
INSERT INTO `drinkmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `dcid`, `createdat`, `createdby`, `mcid`, `action`, `actiontime`) VALUES(10, 'Genger-Ale', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2, 0, NOW());
INSERT INTO `drinkmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `dcid`, `createdat`, `createdby`, `mcid`, `action`, `actiontime`) VALUES(11, 'Schweppes+C', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2, 0, NOW());
INSERT INTO `drinkmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `dcid`, `createdat`, `createdby`, `mcid`, `action`, `actiontime`) VALUES(12, 'FUZE Tea', 1500, 1, 0, 3, '2014-04-08 00:00:00', 1, 2, 0, NOW());
INSERT INTO `drinkmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `dcid`, `createdat`, `createdby`, `mcid`, `action`, `actiontime`) VALUES(13, 'Punch', 1500, 1, 0, 3, '2014-04-08 00:00:00', 1, 2, 0, NOW());
INSERT INTO `drinkmenu` (`id`, `name`, `price`, `isonthemenu`, `isset`, `dcid`, `createdat`, `createdby`, `mcid`, `action`, `actiontime`) VALUES(14, 'Bonaqua', 1000, 1, 0, 4, '2014-04-08 00:00:00', 1, 2, 0, NOW());

--
-- Dumping data for table `sites`
--

INSERT INTO `sites` (`id`, `name`, `action`, `actiontime`) VALUES(1, 'Ойши 1', 0, NOW());
INSERT INTO `sites` (`id`, `name`, `action`, `actiontime`) VALUES(2, 'Ойши 2', 0, NOW());

--
-- Dumping data for table `food_sites`
--

INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(1, 1, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(2, 2, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(3, 3, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(4, 4, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(5, 5, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(6, 6, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(7, 7, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(8, 8, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(9, 9, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(10, 10, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(11, 11, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(12, 12, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(13, 13, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(14, 14, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(15, 1, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(16, 2, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(17, 3, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(18, 4, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(19, 5, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(20, 6, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(21, 7, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(22, 8, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(23, 9, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(24, 10, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(25, 11, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(26, 12, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(27, 13, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(28, 14, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(29, 1, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(30, 2, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(31, 3, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(32, 4, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(33, 5, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(34, 6, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(35, 7, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(36, 8, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(37, 9, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(38, 10, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(39, 11, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(40, 12, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(41, 13, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(42, 14, 1, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(43, 1, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(44, 2, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(45, 3, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(46, 4, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(47, 5, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(48, 6, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(49, 7, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(50, 8, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(51, 9, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(52, 10, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(53, 11, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(54, 12, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(55, 13, 2, 1, 0, NOW());
INSERT INTO `food_sites` (`id`, `foodmenuid`, `siteid`, `status`, `action`, `actiontime`) VALUES(56, 14, 2, 1, 0, NOW());

--
-- Dumping data for table `drink_sites`
--

INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(1, 1, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(2, 2, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(3, 3, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(4, 4, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(5, 5, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(6, 6, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(7, 7, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(8, 8, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(9, 9, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(10, 10, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(11, 11, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(12, 12, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(13, 13, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(14, 14, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(15, 1, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(16, 2, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(17, 3, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(18, 4, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(19, 5, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(20, 6, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(21, 7, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(22, 8, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(23, 9, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(24, 10, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(25, 11, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(26, 12, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(27, 13, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(28, 14, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(29, 1, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(30, 2, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(31, 3, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(32, 4, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(33, 5, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(34, 6, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(35, 7, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(36, 8, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(37, 9, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(38, 10, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(39, 11, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(40, 12, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(41, 13, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(42, 14, 1, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(43, 1, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(44, 2, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(45, 3, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(46, 4, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(47, 5, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(48, 6, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(49, 7, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(50, 8, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(51, 9, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(52, 10, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(53, 11, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(54, 12, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(55, 13, 2, 1, 0, NOW());
INSERT INTO `drink_sites` (`id`, `drinkmenuid`, `site`, `status`, `action`, `actiontime`) VALUES(56, 14, 2, 1, 0, NOW());

--
-- Dumping data for table `changes`
--

INSERT INTO `changes` (`id`, `name`, `action`, `actiontime`) VALUES(1, 'Бага халуун ногоо', 0, NOW());
INSERT INTO `changes` (`id`, `name`, `action`, `actiontime`) VALUES(2, 'Дундаж халуун ногоо', 0, NOW());
INSERT INTO `changes` (`id`, `name`, `action`, `actiontime`) VALUES(3, 'Их халуун ногоо', 0, NOW());

--
-- Dumping data for table `foodchange`
--

INSERT INTO `foodchange` (`id`, `changesid`, `foodmenuid`, `action`, `actiontime`) VALUES(1, 1, 1, 0, NOW());
INSERT INTO `foodchange` (`id`, `changesid`, `foodmenuid`, `action`, `actiontime`) VALUES(2, 2, 1, 0, NOW());
INSERT INTO `foodchange` (`id`, `changesid`, `foodmenuid`, `action`, `actiontime`) VALUES(3, 3, 1, 0, NOW());
INSERT INTO `foodchange` (`id`, `changesid`, `foodmenuid`, `action`, `actiontime`) VALUES(4, 1, 12, 0, NOW());
INSERT INTO `foodchange` (`id`, `changesid`, `foodmenuid`, `action`, `actiontime`) VALUES(5, 2, 12, 0, NOW());
INSERT INTO `foodchange` (`id`, `changesid`, `foodmenuid`, `action`, `actiontime`) VALUES(6, 3, 12, 0, NOW());


--
-- Dumping data for table `cardtype`
--

INSERT INTO `cardtype` (`id`, `name`) VALUES(1, 'DiscountCard');
INSERT INTO `cardtype` (`id`, `name`) VALUES(2, 'BonusCard');

--
-- Dumping data for table `carddiscount`
--

INSERT INTO `carddiscount` (`id`, `name`, `percentage`, `conditions`, `ctid`, `action`, `actiontime`) VALUES(1, 'SYSTEM_DICOUNT', 0, 0, 1, 0, NOW());
INSERT INTO `carddiscount` (`id`, `name`, `percentage`, `conditions`, `ctid`, `action`, `actiontime`) VALUES(2, 'Membership card 5%', 5, 100000, 1, 0, NOW());
INSERT INTO `carddiscount` (`id`, `name`, `percentage`, `conditions`, `ctid`, `action`, `actiontime`) VALUES(3, 'Membership card 10%', 10, 500000, 1, 0, NOW());
INSERT INTO `carddiscount` (`id`, `name`, `percentage`, `conditions`, `ctid`, `action`, `actiontime`) VALUES(4, 'Membership card 5%', 5, 100000, 1, 0, NOW());
INSERT INTO `carddiscount` (`id`, `name`, `percentage`, `conditions`, `ctid`, `action`, `actiontime`) VALUES(5, 'Membership card 5%', 5, 100000, 1, 0, NOW());
INSERT INTO `carddiscount` (`id`, `name`, `percentage`, `conditions`, `ctid`, `action`, `actiontime`) VALUES(6, 'Membership card 10%', 10, 500000, 1, 0, NOW());

--
-- Dumping data for table `promocodetype`
--

INSERT INTO `promocodetype` (`id`, `name`, `action`, `actiontime`) VALUES(1, 'Нөхцөлт промо код', 0, NOW());
INSERT INTO `promocodetype` (`id`, `name`, `action`, `actiontime`) VALUES(2, 'Нэг удаа ашиглах промо код', 0, NOW());

--
-- Dumping data for table `promocode`
--

INSERT INTO `promocode` (`id`, `code`, `discount`, `starts`, `ends`, `isactive`, `pctid`, `action`, `actiontime`) VALUES(1, 'OISHISV15', 15, '2014-05-20 00:00:00', '2014-05-31 00:00:00', 0, 1, 0, NOW());

--
-- Dumping data for table `tables`
--

INSERT INTO `tables` (`id`, `tableid`, `isreserved`, `isfree`, `totalperson`, `merge`, `sites`, `action`, `actiontime`) VALUES(1, 1, 0, 0, 5, 0, 1, 0, NOW());
INSERT INTO `tables` (`id`, `tableid`, `isreserved`, `isfree`, `totalperson`, `merge`, `sites`, `action`, `actiontime`) VALUES(2, 2, 0, 0, 5, 0, 1, 0, NOW());
INSERT INTO `tables` (`id`, `tableid`, `isreserved`, `isfree`, `totalperson`, `merge`, `sites`, `action`, `actiontime`) VALUES(3, 3, 0, 0, 5, 0, 1, 0, NOW());
INSERT INTO `tables` (`id`, `tableid`, `isreserved`, `isfree`, `totalperson`, `merge`, `sites`, `action`, `actiontime`) VALUES(4, 4, 0, 0, 5, 0, 1, 0, NOW());
INSERT INTO `tables` (`id`, `tableid`, `isreserved`, `isfree`, `totalperson`, `merge`, `sites`, `action`, `actiontime`) VALUES(5, 5, 0, 0, 5, 0, 1, 0, NOW());
INSERT INTO `tables` (`id`, `tableid`, `isreserved`, `isfree`, `totalperson`, `merge`, `sites`, `action`, `actiontime`) VALUES(6, 1, 0, 0, 5, 0, 1, 0, NOW());
INSERT INTO `tables` (`id`, `tableid`, `isreserved`, `isfree`, `totalperson`, `merge`, `sites`, `action`, `actiontime`) VALUES(7, 2, 0, 0, 5, 0, 1, 0, NOW());
INSERT INTO `tables` (`id`, `tableid`, `isreserved`, `isfree`, `totalperson`, `merge`, `sites`, `action`, `actiontime`) VALUES(8, 3, 0, 0, 5, 0, 2, 0, NOW());
INSERT INTO `tables` (`id`, `tableid`, `isreserved`, `isfree`, `totalperson`, `merge`, `sites`, `action`, `actiontime`) VALUES(9, 4, 0, 0, 5, 0, 2, 0, NOW());
INSERT INTO `tables` (`id`, `tableid`, `isreserved`, `isfree`, `totalperson`, `merge`, `sites`, `action`, `actiontime`) VALUES(10, 5, 0, 0, 5, 0, 2, 0, NOW());

--
-- Dumping data for table `system_settings`
--

INSERT INTO `system_settings` (`id`, `name`, `parameter`) VALUES(1, 'SYSTEMDATE', '2014-10-27');
INSERT INTO `system_settings` (`id`, `name`, `parameter`) VALUES(2, 'NEXTCASH', '15000');
INSERT INTO `system_settings` (`id`, `name`, `parameter`) VALUES(3, 'SITEID', '1');
INSERT INTO `system_settings` (`id`, `name`, `parameter`) VALUES(4, 'COLOR_LW', '#35AA74');
INSERT INTO `system_settings` (`id`, `name`, `parameter`) VALUES(5, 'COLOR_MW', '#FFB848');
INSERT INTO `system_settings` (`id`, `name`, `parameter`) VALUES(6, 'COLOR_HW', '#FF3F3F');
INSERT INTO `system_settings` (`id`, `name`, `parameter`) VALUES(7, 'COLOR_CH', '#4D90FE');
INSERT INTO `system_settings` (`id`, `name`, `parameter`) VALUES(8, 'PRINTER', 'printername');
INSERT INTO `system_settings` (`id`, `name`, `parameter`) VALUES(9, 'LAST_UPDATE', '2014-10-28 17:56:12');
INSERT INTO `system_settings` (`id`, `name`, `parameter`) VALUES(10, 'VAT', '0');

--
-- Dumping data for table `userrole`
--

INSERT INTO `userrole` (`id`, `name`) VALUES(1, 'Manager');
INSERT INTO `userrole` (`id`, `name`) VALUES(2, 'Тогооч');
INSERT INTO `userrole` (`id`, `name`) VALUES(3, 'WAITER');
INSERT INTO `userrole` (`id`, `name`) VALUES(4, 'SUPERADMIN');
INSERT INTO `userrole` (`id`, `name`) VALUES(5, 'Админ');

-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `username`, `password`, `firstname`, `lastname`, `picture`, `register`, `email`, `mobile`, `phone`, `undsen`, `bodogdoh`, `bodoh`, `isactive`, `roleid`, `action`, `actiontime`) VALUES(1, 'tumee', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2', 'Tumee', 'E', NULL, NULL, NULL, NULL, NULL, 0, 0, 1, 1, 2, 0, NOW());
INSERT INTO `employee` (`id`, `username`, `password`, `firstname`, `lastname`, `picture`, `register`, `email`, `mobile`, `phone`, `undsen`, `bodogdoh`, `bodoh`, `isactive`, `roleid`, `action`, `actiontime`) VALUES(2, 'test', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2', 'Test', 'E', NULL, NULL, NULL, NULL, NULL, 0, 0, 1, 1, 3, 0, NOW());
INSERT INTO `employee` (`id`, `username`, `password`, `firstname`, `lastname`, `picture`, `register`, `email`, `mobile`, `phone`, `undsen`, `bodogdoh`, `bodoh`, `isactive`, `roleid`, `action`, `actiontime`) VALUES(3, 'tumee', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2', 'Tumee', 'E', NULL, NULL, NULL, NULL, NULL, 0, 0, 1, 1, 2, 0, NOW());
INSERT INTO `employee` (`id`, `username`, `password`, `firstname`, `lastname`, `picture`, `register`, `email`, `mobile`, `phone`, `undsen`, `bodogdoh`, `bodoh`, `isactive`, `roleid`, `action`, `actiontime`) VALUES(4, 'test', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2', 'Test', 'E', NULL, NULL, NULL, NULL, NULL, 0, 0, 1, 1, 3, 0, NOW());

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`id`, `firstname`, `lastname`, `username`, `password`, `register`, `mobile`, `email`, `isactive`, `numbervisit`, `totalspent`, `cardnumber`, `bonus`, `cdid`, `action`, `actiontime`) VALUES(1, 'SYSTEM_USER', 'SYSTEM_USER', 'SYSTEM_USER', 'SYSTEM_USER', 'SYSTEM_USER', NULL, NULL, 0, 0, 0, NULL, 0, 1, 0, NOW());
INSERT INTO `members` (`id`, `firstname`, `lastname`, `username`, `password`, `register`, `mobile`, `email`, `isactive`, `numbervisit`, `totalspent`, `cardnumber`, `bonus`, `cdid`, `action`, `actiontime`) VALUES(2, 'Tumee', 'E', 'tumee', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2', 'ЧИ86053132', 94000746, 'tum_mgl@yahoo.com', 0, 0, 0, 'H12M7264359f', 0, 2, 0, NOW());
INSERT INTO `members` (`id`, `firstname`, `lastname`, `username`, `password`, `register`, `mobile`, `email`, `isactive`, `numbervisit`, `totalspent`, `cardnumber`, `bonus`, `cdid`, `action`, `actiontime`) VALUES(3, 'Test', 'E', 'test', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2', 'ЧИ86213132', 94030746, 'test@yahoo.com', 0, 0, 0, 'H12M7264358f', 10, 3, 0, NOW());














