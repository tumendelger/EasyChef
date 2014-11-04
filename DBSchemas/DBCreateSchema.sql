SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+08:00";


--
-- Database: `easysystems`
--
CREATE DATABASE IF NOT EXISTS `easysystems` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `easysystems`;

-- --------------------------------------------------------

--
-- Table structure for table `carddiscount`
--

DROP TABLE IF EXISTS `carddiscount`;
CREATE TABLE IF NOT EXISTS `carddiscount` (
`id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `percentage` float NOT NULL,
  `conditions` int(11) NOT NULL COMMENT 'heden tugrugnii bolzol hangaj baij ene 5 ezemshig erhtei boloh ve',
  `ctid` int(11) NOT NULL COMMENT 'CardTypeID',
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cardtype`
--

DROP TABLE IF EXISTS `cardtype`;
CREATE TABLE IF NOT EXISTS `cardtype` (
`id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `changes`
--

DROP TABLE IF EXISTS `changes`;
CREATE TABLE IF NOT EXISTS `changes` (
`id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `clientip`
--

DROP TABLE IF EXISTS `clientip`;
CREATE TABLE IF NOT EXISTS `clientip` (
`id` int(11) NOT NULL,
  `clientid` int(11) NOT NULL,
  `address` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Хэрэглэгч холбогдсоны дараа өөрийн хаягийг бичнэ';

-- --------------------------------------------------------

--
-- Table structure for table `dailystatistics`
--

DROP TABLE IF EXISTS `dailystatistics`;
CREATE TABLE IF NOT EXISTS `dailystatistics` (
`id` int(11) NOT NULL,
  `stat_type` int(11) NOT NULL COMMENT 'Statistics type 1=Low wait order, 2=Medium wait order, 3=High wait order, 4=Cancel with material cost, 5=Cancel without material cost',
  `noOfOrders` int(11) NOT NULL,
  `effectivedate` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Tuhain udriin statistics baga huleelttei ih huleelttei geh met statistics';

-- --------------------------------------------------------

--
-- Table structure for table `dorderdetails`
--

DROP TABLE IF EXISTS `dorderdetails`;
CREATE TABLE IF NOT EXISTS `dorderdetails` (
  `id` bigint(20) NOT NULL,
  `drinkid` int(11) NOT NULL,
  `price` double NOT NULL,
  `isdelivered` int(11) NOT NULL DEFAULT '0',
  `ordertime` time NOT NULL,
  `waittime` int(11) NOT NULL DEFAULT '0',
  `cancelbyw` int(11) NOT NULL DEFAULT '0' COMMENT 'zuugchuus hool tsutsalsan eseh',
  `cancelbych` int(11) NOT NULL DEFAULT '0' COMMENT 'Barmenaas hool tsutsalsan eseh',
  `mcid` int(11) NOT NULL COMMENT 'MainCategory ID',
  `orderid` bigint(20) NOT NULL COMMENT 'Orders ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `drinkcategory`
--

DROP TABLE IF EXISTS `drinkcategory`;
CREATE TABLE IF NOT EXISTS `drinkcategory` (
`id` int(11) NOT NULL,
  `name` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `drinkmenu`
--

DROP TABLE IF EXISTS `drinkmenu`;
CREATE TABLE IF NOT EXISTS `drinkmenu` (
`id` int(11) NOT NULL,
  `name` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `price` int(11) NOT NULL,
  `isonthemenu` tinyint(1) NOT NULL,
  `isset` tinyint(1) NOT NULL,
  `dcid` int(11) NOT NULL COMMENT 'DrinkCategory ID',
  `createdat` datetime NOT NULL,
  `createdby` int(11) NOT NULL,
  `mcid` int(11) NOT NULL DEFAULT '2' COMMENT 'MainCategoryId',
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `drink_sites`
--

DROP TABLE IF EXISTS `drink_sites`;
CREATE TABLE IF NOT EXISTS `drink_sites` (
`id` int(11) NOT NULL,
  `drinkmenuid` int(11) NOT NULL,
  `site` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
`id` int(11) NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `firstname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `lastname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `picture` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `register` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` int(11) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `undsen` double NOT NULL DEFAULT '0' COMMENT 'Undsen tsalin',
  `bodogdoh` double NOT NULL DEFAULT '0' COMMENT 'Bodogdoh tsalin',
  `bodoh` int(11) NOT NULL DEFAULT '1' COMMENT '1=undsen; 2=bodogdoh tsalingaas bodoh',
  `isactive` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'systemd nevtreh erhtei eseh',
  `roleid` int(11) NOT NULL,
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `foodcategory`
--

DROP TABLE IF EXISTS `foodcategory`;
CREATE TABLE IF NOT EXISTS `foodcategory` (
`id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `foodchange`
--

DROP TABLE IF EXISTS `foodchange`;
CREATE TABLE IF NOT EXISTS `foodchange` (
`id` int(11) NOT NULL,
  `changesid` int(11) NOT NULL,
  `foodmenuid` int(11) NOT NULL,
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `foodmenu`
--

DROP TABLE IF EXISTS `foodmenu`;
CREATE TABLE IF NOT EXISTS `foodmenu` (
`id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `price` int(11) NOT NULL,
  `isonthemenu` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Zuugchiin menu-nd haraghah eseh',
  `isset` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'SET hool mun eseh',
  `fcit` int(11) NOT NULL COMMENT 'FoodCategory ID',
  `createdat` datetime NOT NULL,
  `createdby` int(11) NOT NULL,
  `isdetails` int(11) NOT NULL DEFAULT '0' COMMENT 'materialiin zadargaa hiih bolomjtoi eseh',
  `mcid` int(11) NOT NULL DEFAULT '1' COMMENT 'MainCategoryId',
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `food_sites`
--

DROP TABLE IF EXISTS `food_sites`;
CREATE TABLE IF NOT EXISTS `food_sites` (
`id` int(11) NOT NULL,
  `foodmenuid` int(11) NOT NULL,
  `siteid` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `forderdetails`
--

DROP TABLE IF EXISTS `forderdetails`;
CREATE TABLE IF NOT EXISTS `forderdetails` (
  `id` bigint(20) NOT NULL,
  `foodid` int(11) NOT NULL,
  `haschange` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'If this order has specific change',
  `price` double NOT NULL,
  `isdelivered` tinyint(1) NOT NULL DEFAULT '0',
  `waittime` int(11) NOT NULL DEFAULT '0',
  `ordertime` time NOT NULL,
  `cancelbyw` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'zuugchuus hool tsutsalsan eseh',
  `cancelbych` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'togoochoos hool tsutsalsan eseh',
  `mcid` int(11) NOT NULL COMMENT 'MainCategory ID',
  `orderid` bigint(20) NOT NULL COMMENT 'Orders ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `maincategory`
--

DROP TABLE IF EXISTS `maincategory`;
CREATE TABLE IF NOT EXISTS `maincategory` (
`id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
CREATE TABLE IF NOT EXISTS `members` (
`id` int(11) NOT NULL,
  `firstname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `register` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` int(11) DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isactive` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'idevhitei eseh',
  `numbervisit` int(11) NOT NULL DEFAULT '0' COMMENT 'niit uilchluulsen too',
  `totalspent` double NOT NULL DEFAULT '0' COMMENT 'uilchluulsen niit mungun dun',
  `cardnumber` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bonus` double DEFAULT '0',
  `cdid` int(11) NOT NULL COMMENT 'cardDiscountID',
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orderchange`
--

DROP TABLE IF EXISTS `orderchange`;
CREATE TABLE IF NOT EXISTS `orderchange` (
`id` int(11) NOT NULL,
  `fodid` bigint(20) NOT NULL COMMENT 'Food order detail ID which has some extra changes',
  `cid` int(11) NOT NULL COMMENT 'Change ID if this order has some changes to do '
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Hoolnii zahialga deer uilchluulegch uurchlult hiisen tohioldold holboh ';

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint(20) NOT NULL,
  `ordertime` datetime NOT NULL,
  `totalprice` double NOT NULL COMMENT 'Zahialgiin niit mungun dun',
  `vat` double NOT NULL DEFAULT '0' COMMENT 'Niit mungun dungees VAT bodson mungun dun',
  `discount` double NOT NULL DEFAULT '0' COMMENT 'Hyamdral edelsen mungun dun',
  `cash` double NOT NULL DEFAULT '0' COMMENT 'belneer tooshoo hiisen mungun dun',
  `card` double NOT NULL DEFAULT '0' COMMENT 'CARD-r guilgee hiisen mungun dun',
  `ispaid` tinyint(1) NOT NULL DEFAULT '0',
  `totalAmount` double NOT NULL COMMENT 'VAT bolon niit mungun dungiin niilber buyu uilchluulegchiin niit tuluh mungun dun',
  `tid` int(11) NOT NULL COMMENT 'Table ID',
  `mid` int(11) NOT NULL COMMENT 'Member ID',
  `uid` int(11) NOT NULL COMMENT 'Zuugchiin ID',
  `promocode` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `systemdate` date NOT NULL,
  `syn_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Remote server luu sync hiisen esehiig iltgeh field. Remote server luu sync hiigdsen bval 1 baina hiigdeegui bval 0 baina'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promocode`
--

DROP TABLE IF EXISTS `promocode`;
CREATE TABLE IF NOT EXISTS `promocode` (
`id` int(11) NOT NULL,
  `code` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `discount` int(11) NOT NULL,
  `starts` datetime DEFAULT NULL,
  `ends` datetime DEFAULT NULL,
  `isactive` int(11) NOT NULL DEFAULT '1',
  `pctid` int(11) NOT NULL COMMENT 'PromoCodeTypeID',
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promocodetype`
--

DROP TABLE IF EXISTS `promocodetype`;
CREATE TABLE IF NOT EXISTS `promocodetype` (
`id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sites`
--

DROP TABLE IF EXISTS `sites`;
CREATE TABLE IF NOT EXISTS `sites` (
`id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `systemdate`
--

DROP TABLE IF EXISTS `systemdate`;
CREATE TABLE IF NOT EXISTS `systemdate` (
`id` int(11) NOT NULL,
  `startdate` date NOT NULL,
  `totalincome` double NOT NULL,
  `nextdaycash` double NOT NULL,
  `sync_status` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Udur undurluhud tuhain udriin medeeliig avhc ene table-d hiij system settings-iin systemdate utgand shine udriin on sar bichne';

-- --------------------------------------------------------

--
-- Table structure for table `system_settings`
--

DROP TABLE IF EXISTS `system_settings`;
CREATE TABLE IF NOT EXISTS `system_settings` (
`id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `parameter` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tables`
--

DROP TABLE IF EXISTS `tables`;
CREATE TABLE IF NOT EXISTS `tables` (
`id` int(11) NOT NULL,
  `tableid` int(11) NOT NULL,
  `isreserved` tinyint(1) NOT NULL DEFAULT '0',
  `isfree` int(1) NOT NULL DEFAULT '0',
  `totalperson` int(11) DEFAULT NULL,
  `merge` int(11) NOT NULL DEFAULT '0',
  `sites` int(11) NOT NULL DEFAULT '1',
  `action` int(11) NOT NULL DEFAULT '0' COMMENT '0=new; 1=update;2=delete',
  `actiontime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `userlog`
--

DROP TABLE IF EXISTS `userlog`;
CREATE TABLE IF NOT EXISTS `userlog` (
`id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `logdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sync_status` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
CREATE TABLE IF NOT EXISTS `userrole` (
`id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_session`
--

DROP TABLE IF EXISTS `user_session`;
CREATE TABLE IF NOT EXISTS `user_session` (
`id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL COMMENT 'Session owner id',
  `session_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Session ID auto generate',
  `site_id` int(11) NOT NULL COMMENT 'Current session site id',
  `last_action` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Update with user actions. Compare value with current time to determine user session timeout'
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Hereglegchiin session burtgeh table. Hereglegch amjilttai login hh ued';

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carddiscount`
--
ALTER TABLE `carddiscount`
 ADD PRIMARY KEY (`id`), ADD KEY `ctid` (`ctid`);

--
-- Indexes for table `cardtype`
--
ALTER TABLE `cardtype`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `changes`
--
ALTER TABLE `changes`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `clientip`
--
ALTER TABLE `clientip`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dailystatistics`
--
ALTER TABLE `dailystatistics`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dorderdetails`
--
ALTER TABLE `dorderdetails`
 ADD PRIMARY KEY (`id`), ADD KEY `oid` (`orderid`), ADD KEY `drinkid` (`drinkid`);

--
-- Indexes for table `drinkcategory`
--
ALTER TABLE `drinkcategory`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `drinkmenu`
--
ALTER TABLE `drinkmenu`
 ADD PRIMARY KEY (`id`), ADD KEY `dcid` (`dcid`), ADD KEY `mcid` (`mcid`);

--
-- Indexes for table `drink_sites`
--
ALTER TABLE `drink_sites`
 ADD PRIMARY KEY (`id`), ADD KEY `drinkmenuid` (`drinkmenuid`), ADD KEY `site` (`site`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
 ADD PRIMARY KEY (`id`), ADD KEY `roleid` (`roleid`);

--
-- Indexes for table `foodcategory`
--
ALTER TABLE `foodcategory`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `foodchange`
--
ALTER TABLE `foodchange`
 ADD PRIMARY KEY (`id`), ADD KEY `changesid` (`changesid`), ADD KEY `foodmenuid` (`foodmenuid`);

--
-- Indexes for table `foodmenu`
--
ALTER TABLE `foodmenu`
 ADD PRIMARY KEY (`id`), ADD KEY `fcit` (`fcit`), ADD KEY `mcid` (`mcid`);

--
-- Indexes for table `food_sites`
--
ALTER TABLE `food_sites`
 ADD PRIMARY KEY (`id`), ADD KEY `foodmenuid` (`foodmenuid`), ADD KEY `siteid` (`siteid`);

--
-- Indexes for table `forderdetails`
--
ALTER TABLE `forderdetails`
 ADD PRIMARY KEY (`id`), ADD KEY `oid` (`orderid`), ADD KEY `foodid` (`foodid`);

--
-- Indexes for table `maincategory`
--
ALTER TABLE `maincategory`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
 ADD PRIMARY KEY (`id`), ADD KEY `cdid` (`cdid`);

--
-- Indexes for table `orderchange`
--
ALTER TABLE `orderchange`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
 ADD PRIMARY KEY (`id`), ADD KEY `tid` (`tid`), ADD KEY `mid` (`mid`), ADD KEY `uid` (`uid`);

--
-- Indexes for table `promocode`
--
ALTER TABLE `promocode`
 ADD PRIMARY KEY (`id`), ADD KEY `pctid` (`pctid`);

--
-- Indexes for table `promocodetype`
--
ALTER TABLE `promocodetype`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sites`
--
ALTER TABLE `sites`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `systemdate`
--
ALTER TABLE `systemdate`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `system_settings`
--
ALTER TABLE `system_settings`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tables`
--
ALTER TABLE `tables`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userlog`
--
ALTER TABLE `userlog`
 ADD PRIMARY KEY (`id`), ADD KEY `uid` (`uid`);

--
-- Indexes for table `userrole`
--
ALTER TABLE `userrole`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_session`
--
ALTER TABLE `user_session`
 ADD PRIMARY KEY (`id`), ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `carddiscount`
--
ALTER TABLE `carddiscount`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `cardtype`
--
ALTER TABLE `cardtype`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `changes`
--
ALTER TABLE `changes`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `clientip`
--
ALTER TABLE `clientip`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `dailystatistics`
--
ALTER TABLE `dailystatistics`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `drinkcategory`
--
ALTER TABLE `drinkcategory`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `drinkmenu`
--
ALTER TABLE `drinkmenu`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `drink_sites`
--
ALTER TABLE `drink_sites`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=57;
--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `foodcategory`
--
ALTER TABLE `foodcategory`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `foodchange`
--
ALTER TABLE `foodchange`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `foodmenu`
--
ALTER TABLE `foodmenu`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `food_sites`
--
ALTER TABLE `food_sites`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=57;
--
-- AUTO_INCREMENT for table `maincategory`
--
ALTER TABLE `maincategory`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `members`
--
ALTER TABLE `members`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `orderchange`
--
ALTER TABLE `orderchange`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `promocode`
--
ALTER TABLE `promocode`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `promocodetype`
--
ALTER TABLE `promocodetype`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `sites`
--
ALTER TABLE `sites`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `systemdate`
--
ALTER TABLE `systemdate`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `system_settings`
--
ALTER TABLE `system_settings`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `tables`
--
ALTER TABLE `tables`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `userlog`
--
ALTER TABLE `userlog`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=86;
--
-- AUTO_INCREMENT for table `userrole`
--
ALTER TABLE `userrole`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `user_session`
--
ALTER TABLE `user_session`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=95;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `carddiscount`
--
ALTER TABLE `carddiscount`
ADD CONSTRAINT `carddiscount_ibfk_1` FOREIGN KEY (`ctid`) REFERENCES `cardtype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `dorderdetails`
--
ALTER TABLE `dorderdetails`
ADD CONSTRAINT `dorderdetails_ibfk_2` FOREIGN KEY (`drinkid`) REFERENCES `drinkmenu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `dorderdetails_ibfk_3` FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `drinkmenu`
--
ALTER TABLE `drinkmenu`
ADD CONSTRAINT `drinkmenu_ibfk_1` FOREIGN KEY (`dcid`) REFERENCES `drinkcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `drinkmenu_ibfk_2` FOREIGN KEY (`mcid`) REFERENCES `maincategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `userrole` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `foodchange`
--
ALTER TABLE `foodchange`
ADD CONSTRAINT `foodchange_ibfk_1` FOREIGN KEY (`changesid`) REFERENCES `changes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `foodchange_ibfk_2` FOREIGN KEY (`foodmenuid`) REFERENCES `foodmenu` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `foodmenu`
--
ALTER TABLE `foodmenu`
ADD CONSTRAINT `foodmenu_ibfk_1` FOREIGN KEY (`fcit`) REFERENCES `foodcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `foodmenu_ibfk_2` FOREIGN KEY (`mcid`) REFERENCES `maincategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `food_sites`
--
ALTER TABLE `food_sites`
ADD CONSTRAINT `food_sites_ibfk_1` FOREIGN KEY (`foodmenuid`) REFERENCES `foodmenu` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
ADD CONSTRAINT `food_sites_ibfk_2` FOREIGN KEY (`siteid`) REFERENCES `sites` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `forderdetails`
--
ALTER TABLE `forderdetails`
ADD CONSTRAINT `forderdetails_ibfk_2` FOREIGN KEY (`foodid`) REFERENCES `foodmenu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `forderdetails_ibfk_3` FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `members`
--
ALTER TABLE `members`
ADD CONSTRAINT `members_ibfk_1` FOREIGN KEY (`cdid`) REFERENCES `carddiscount` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `tables` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `members` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`uid`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `promocode`
--
ALTER TABLE `promocode`
ADD CONSTRAINT `promocode_ibfk_1` FOREIGN KEY (`pctid`) REFERENCES `promocodetype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `userlog`
--
ALTER TABLE `userlog`
ADD CONSTRAINT `userlog_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `user_session`
--
ALTER TABLE `user_session`
ADD CONSTRAINT `user_session_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

