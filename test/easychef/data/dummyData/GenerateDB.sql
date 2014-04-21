--  Generate fresh database for the application

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+08:00";

--
-- Database: easysystems
--  

DROP DATABASE IF EXISTS easysystems;

CREATE DATABASE IF NOT EXISTS easysystems DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE easysystems;

--------------------------------------------
--
-- Table structure for table carddiscount
--

DROP TABLE IF EXISTS carddiscount;
CREATE TABLE IF NOT EXISTS carddiscount (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  percentage float NOT NULL,
  conditions int(11) NOT NULL COMMENT 'heden tugrugnii bolzol hangaj baij ene 5 ezemshig erhtei boloh ve',
  ctid int(11) NOT NULL COMMENT 'CardTypeID',
  PRIMARY KEY (id),
  KEY ctid (ctid)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table cardtype
--

DROP TABLE IF EXISTS cardtype;
CREATE TABLE IF NOT EXISTS cardtype (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table changes
--

DROP TABLE IF EXISTS changes;
CREATE TABLE IF NOT EXISTS changes (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table dorderdetails
--

DROP TABLE IF EXISTS dorderdetails;
CREATE TABLE IF NOT EXISTS dorderdetails (
  id bigint(20) NOT NULL,
  drinkid int(11) NOT NULL,
  price double NOT NULL,
  isdelivered int(11) NOT NULL DEFAULT '0',
  ordertime time NOT NULL,
  cancelbyw int(11) NOT NULL DEFAULT '0' COMMENT 'zuugchuus hool tsutsalsan eseh',
  cancelbych int(11) NOT NULL DEFAULT '0' COMMENT 'Barmenaas hool tsutsalsan eseh',
  mcid int(11) NOT NULL COMMENT 'MainCategory ID',
  orderid bigint(20) NOT NULL COMMENT 'Orders ID',
  PRIMARY KEY (id),
  KEY oid (orderid),
  KEY drinkid (drinkid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table drinkcategory
--

DROP TABLE IF EXISTS drinkcategory;
CREATE TABLE IF NOT EXISTS drinkcategory (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(80) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table drinkdetail
--

DROP TABLE IF EXISTS drinkdetail;
CREATE TABLE IF NOT EXISTS drinkdetail (
  id int(11) NOT NULL AUTO_INCREMENT,
  dmid int(11) NOT NULL COMMENT 'DrinkMenu ID',
  mid int(11) NOT NULL COMMENT 'Material ID',
  quantity float NOT NULL,
  PRIMARY KEY (id),
  KEY dmid (dmid),
  KEY mid (mid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table drinkmenu
--

DROP TABLE IF EXISTS drinkmenu;
CREATE TABLE IF NOT EXISTS drinkmenu (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(60) NOT NULL,
  price int(11) NOT NULL,
  isonthemenu tinyint(1) NOT NULL,
  isset tinyint(1) NOT NULL,
  dcid int(11) NOT NULL COMMENT 'DrinkCategory ID',
  createdat datetime NOT NULL,
  createdby int(11) NOT NULL,
  mcid int(11) NOT NULL DEFAULT '2' COMMENT 'MainCategoryId',
  PRIMARY KEY (id),
  KEY dcid (dcid),
  KEY mcid (mcid)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=15 ;

-- --------------------------------------------------------

--
-- Table structure for table employee
--

DROP TABLE IF EXISTS employee;
CREATE TABLE IF NOT EXISTS employee (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  firstname varchar(50) NOT NULL,
  lastname varchar(50) DEFAULT NULL,
  picture varchar(300) DEFAULT NULL,
  register varchar(15) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  mobile int(11) DEFAULT NULL,
  phone int(11) DEFAULT NULL,
  undsen double NOT NULL DEFAULT '0' COMMENT 'Undsen tsalin',
  bodogdoh double NOT NULL DEFAULT '0' COMMENT 'Bodogdoh tsalin',
  bodoh int(11) NOT NULL DEFAULT '1' COMMENT '1=undsen; 2=bodogdoh tsalingaas bodoh',
  isactive tinyint(1) NOT NULL DEFAULT '0' COMMENT 'systemd nevtreh erhtei eseh',
  roleid int(11) NOT NULL,
  PRIMARY KEY (id),
  KEY roleid (roleid)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table foodcategory
--

DROP TABLE IF EXISTS foodcategory;
CREATE TABLE IF NOT EXISTS foodcategory (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  mcid int(11) NOT NULL COMMENT 'MainCategory ID',
  PRIMARY KEY (id),
  KEY mcid (mcid)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table foodchange
--

DROP TABLE IF EXISTS foodchange;
CREATE TABLE IF NOT EXISTS foodchange (
  id int(11) NOT NULL AUTO_INCREMENT,
  changesid int(11) NOT NULL,
  foodmenuid int(11) NOT NULL,
  PRIMARY KEY (id),
  KEY changesid (changesid),
  KEY foodmenuid (foodmenuid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table fooddetails
--

DROP TABLE IF EXISTS fooddetails;
CREATE TABLE IF NOT EXISTS fooddetails (
  id int(11) NOT NULL AUTO_INCREMENT,
  fmid int(11) NOT NULL COMMENT 'FoodMenu ID',
  mid int(11) NOT NULL COMMENT 'Material ID',
  quantity int(11) NOT NULL,
  PRIMARY KEY (id),
  KEY fmid (fmid),
  KEY mid (mid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table foodmenu
--

DROP TABLE IF EXISTS foodmenu;
CREATE TABLE IF NOT EXISTS foodmenu (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  price int(11) NOT NULL,
  isonthemenu tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Zuugchiin menu-nd haraghah eseh',
  isset tinyint(1) NOT NULL DEFAULT '0' COMMENT 'SET hool mun eseh',
  fcit int(11) NOT NULL COMMENT 'FoodCategory ID',
  createdat datetime NOT NULL,
  createdby int(11) NOT NULL,
  isdetails int(11) NOT NULL DEFAULT '0' COMMENT 'materialiin zadargaa hiih bolomjtoi eseh',
  mcid int(11) NOT NULL DEFAULT '1' COMMENT 'MainCategoryId',
  PRIMARY KEY (id),
  KEY fcit (fcit),
  KEY mcid (mcid)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=15 ;

-- --------------------------------------------------------

--
-- Table structure for table forderdetails
--

DROP TABLE IF EXISTS forderdetails;
CREATE TABLE IF NOT EXISTS forderdetails (
  id bigint(20) NOT NULL,
  foodid int(11) NOT NULL,
  haschange tinyint(4) NOT NULL DEFAULT '0' COMMENT 'If this order has specific change',
  price double NOT NULL,
  isdelivered tinyint(1) NOT NULL DEFAULT '0',
  ordertime time NOT NULL,
  cancelbyw tinyint(1) NOT NULL DEFAULT '0' COMMENT 'zuugchuus hool tsutsalsan eseh',
  cancelbych tinyint(1) NOT NULL DEFAULT '0' COMMENT 'togoochoos hool tsutsalsan eseh',
  mcid int(11) NOT NULL COMMENT 'MainCategory ID',
  orderid bigint(20) NOT NULL COMMENT 'Orders ID',
  PRIMARY KEY (id),
  KEY oid (orderid),
  KEY foodid (foodid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table maincategory
--

DROP TABLE IF EXISTS maincategory;
CREATE TABLE IF NOT EXISTS maincategory (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table material
--

DROP TABLE IF EXISTS material;
CREATE TABLE IF NOT EXISTS material (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  uldegdel float NOT NULL DEFAULT '0',
  createdat datetime NOT NULL,
  createdby int(11) NOT NULL,
  mscid int(11) NOT NULL COMMENT 'MaterialSubCategory ID',
  utid int(11) NOT NULL COMMENT 'UnitType ID',
  PRIMARY KEY (id),
  KEY mscid (mscid),
  KEY utid (utid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table materialcategory
--

DROP TABLE IF EXISTS materialcategory;
CREATE TABLE IF NOT EXISTS materialcategory (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table materialsubcategory
--

DROP TABLE IF EXISTS materialsubcategory;
CREATE TABLE IF NOT EXISTS materialsubcategory (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(80) NOT NULL,
  mcid int(11) NOT NULL COMMENT 'MaterialCategory ID',
  PRIMARY KEY (id),
  KEY mcid (mcid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table members
--

DROP TABLE IF EXISTS members;
CREATE TABLE IF NOT EXISTS members (
  id int(11) NOT NULL AUTO_INCREMENT,
  firstname varchar(50) DEFAULT NULL,
  lastname varchar(50) DEFAULT NULL,
  username varchar(50) NOT NULL,
  password varchar(50) DEFAULT NULL,
  register varchar(20) DEFAULT NULL,
  mobile int(11) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  isactive tinyint(1) NOT NULL DEFAULT '0' COMMENT 'idevhitei eseh',
  numbervisit int(11) NOT NULL DEFAULT '0' COMMENT 'niit uilchluulsen too',
  totalspent double NOT NULL DEFAULT '0' COMMENT 'uilchluulsen niit mungun dun',
  cardnumber varchar(100) DEFAULT NULL,
  bonus double DEFAULT '0',
  cdid int(11) NOT NULL COMMENT 'cardDiscountID',
  PRIMARY KEY (id),
  KEY cdid (cdid)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table orderchange
--

DROP TABLE IF EXISTS orderchange;
CREATE TABLE IF NOT EXISTS orderchange (
  fodid bigint(20) NOT NULL COMMENT 'Food order detail ID which has some extra changes',
  cid int(11) NOT NULL COMMENT 'Change ID if this order has some changes to do '
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Hoolnii zahialga deer uilchluulegch uurchlult hiisen tohioldold holboh ';

-- --------------------------------------------------------

--
-- Table structure for table orders
--

DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders (
  id bigint(20) NOT NULL,
  ordertime datetime NOT NULL,
  totalprice double NOT NULL COMMENT 'Zahialgiin niit mungun dun',
  vat double NOT NULL DEFAULT '0' COMMENT 'Niit mungun dungees VAT bodson mungun dun',
  discount double NOT NULL DEFAULT '0' COMMENT 'Hyamdral edelsen mungun dun',
  cash double NOT NULL DEFAULT '0' COMMENT 'belneer tooshoo hiisen mungun dun',
  card double NOT NULL DEFAULT '0' COMMENT 'CARD-r guilgee hiisen mungun dun',
  ispaid tinyint(1) NOT NULL DEFAULT '0',
  totalAmount double NOT NULL COMMENT 'VAT bolon niit mungun dungiin niilber buyu uilchluulegchiin niit tuluh mungun dun',
  tid int(11) NOT NULL COMMENT 'Table ID',
  mid int(11) NOT NULL COMMENT 'Member ID',
  uid int(11) NOT NULL COMMENT 'Zuugchiin ID',
  promocode varchar(50) NOT NULL DEFAULT '0',
  systemdate date NOT NULL,
  syn_status tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Remote server luu sync hiisen esehiig iltgeh field. Remote server luu sync hiigdsen bval 1 baina hiigdeegui bval 0 baina',
  PRIMARY KEY (id),
  KEY tid (tid),
  KEY mid (mid),
  KEY uid (uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table promocode
--

DROP TABLE IF EXISTS promocode;
CREATE TABLE IF NOT EXISTS promocode (
  id int(11) NOT NULL AUTO_INCREMENT,
  code varchar(50) NOT NULL,
  discount int(11) NOT NULL,
  starts datetime NOT NULL,
  ends datetime NOT NULL,
  isactive int(11) NOT NULL DEFAULT '1',
  pctid int(11) NOT NULL COMMENT 'PromoCodeTypeID',
  PRIMARY KEY (id),
  KEY pctid (pctid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table promocodetype
--

DROP TABLE IF EXISTS promocodetype;
CREATE TABLE IF NOT EXISTS promocodetype (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table systemdate
--

DROP TABLE IF EXISTS systemdate;
CREATE TABLE IF NOT EXISTS systemdate (
  id int(11) NOT NULL AUTO_INCREMENT,
  startdate date NOT NULL,
  totalincome double NOT NULL,
  nextdaycash double NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table system_settings
--

DROP TABLE IF EXISTS system_settings;
CREATE TABLE IF NOT EXISTS system_settings (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  parameter varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table tables
--

DROP TABLE IF EXISTS tables;
CREATE TABLE IF NOT EXISTS tables (
  id int(11) NOT NULL AUTO_INCREMENT,
  tableid int(11) NOT NULL,
  isreserved tinyint(1) NOT NULL DEFAULT '0',
  isfree tinyint(1) NOT NULL DEFAULT '0',
  totalperson int(11) DEFAULT NULL,
  merge int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table unittype
--

DROP TABLE IF EXISTS unittype;
CREATE TABLE IF NOT EXISTS unittype (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(80) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table userlog
--

DROP TABLE IF EXISTS userlog;
CREATE TABLE IF NOT EXISTS userlog (
  id int(11) NOT NULL AUTO_INCREMENT,
  type int(11) NOT NULL,
  uid int(11) NOT NULL,
  logdate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY uid (uid)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table userrole
--

DROP TABLE IF EXISTS userrole;
CREATE TABLE IF NOT EXISTS userrole (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
--  clientip addresses
--
CREATE TABLE IF NOT EXISTS clientip (
clientid int(11) NOT NULL,
address varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Хэрэглэгч холбогдсоны дараа өөрийн хаягийг бичнэ';

-- Constraints for dumped tables
--

--
-- Constraints for table carddiscount
--
ALTER TABLE carddiscount
  ADD CONSTRAINT carddiscount_ibfk_1 FOREIGN KEY (ctid) REFERENCES cardtype (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table dorderdetails
--
ALTER TABLE dorderdetails
  ADD CONSTRAINT dorderdetails_ibfk_2 FOREIGN KEY (drinkid) REFERENCES drinkmenu (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT dorderdetails_ibfk_3 FOREIGN KEY (orderid) REFERENCES orders (id) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table drinkdetail
--
ALTER TABLE drinkdetail
  ADD CONSTRAINT drinkdetail_ibfk_1 FOREIGN KEY (dmid) REFERENCES drinkmenu (id) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT drinkdetail_ibfk_2 FOREIGN KEY (mid) REFERENCES material (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table drinkmenu
--
ALTER TABLE drinkmenu
  ADD CONSTRAINT drinkmenu_ibfk_2 FOREIGN KEY (mcid) REFERENCES maincategory (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT drinkmenu_ibfk_1 FOREIGN KEY (dcid) REFERENCES drinkcategory (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table employee
--
ALTER TABLE employee
  ADD CONSTRAINT employee_ibfk_1 FOREIGN KEY (roleid) REFERENCES userrole (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table foodchange
--
ALTER TABLE foodchange
  ADD CONSTRAINT foodchange_ibfk_1 FOREIGN KEY (changesid) REFERENCES changes (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT foodchange_ibfk_2 FOREIGN KEY (foodmenuid) REFERENCES foodmenu (id) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table fooddetails
--
ALTER TABLE fooddetails
  ADD CONSTRAINT fooddetails_ibfk_1 FOREIGN KEY (fmid) REFERENCES foodmenu (id) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT fooddetails_ibfk_2 FOREIGN KEY (mid) REFERENCES material (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table foodmenu
--
ALTER TABLE foodmenu
  ADD CONSTRAINT foodmenu_ibfk_2 FOREIGN KEY (mcid) REFERENCES maincategory (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT foodmenu_ibfk_1 FOREIGN KEY (fcit) REFERENCES foodcategory (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table forderdetails
--
ALTER TABLE forderdetails
  ADD CONSTRAINT forderdetails_ibfk_2 FOREIGN KEY (foodid) REFERENCES foodmenu (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT forderdetails_ibfk_3 FOREIGN KEY (orderid) REFERENCES orders (id) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table material
--
ALTER TABLE material
  ADD CONSTRAINT material_ibfk_1 FOREIGN KEY (mscid) REFERENCES materialsubcategory (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT material_ibfk_2 FOREIGN KEY (utid) REFERENCES unittype (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table materialsubcategory
--
ALTER TABLE materialsubcategory
  ADD CONSTRAINT materialsubcategory_ibfk_1 FOREIGN KEY (mcid) REFERENCES materialcategory (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table members
--
ALTER TABLE members
  ADD CONSTRAINT members_ibfk_1 FOREIGN KEY (cdid) REFERENCES carddiscount (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table orders
--
ALTER TABLE orders
  ADD CONSTRAINT orders_ibfk_1 FOREIGN KEY (tid) REFERENCES tables (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT orders_ibfk_2 FOREIGN KEY (mid) REFERENCES members (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT orders_ibfk_3 FOREIGN KEY (uid) REFERENCES employee (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table promocode
--
ALTER TABLE promocode
  ADD CONSTRAINT promocode_ibfk_1 FOREIGN KEY (pctid) REFERENCES promocodetype (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table userlog
--
ALTER TABLE userlog
  ADD CONSTRAINT userlog_ibfk_1 FOREIGN KEY (uid) REFERENCES employee (id) ON DELETE CASCADE ON UPDATE NO ACTION;


--
-- Insert defaults to cardtype
--
INSERT INTO cardtype (id, name) VALUES
(1, 'DiscountCard'),
(2, 'BonusCard');

-- 
-- Insert defaults to carddiscount
-- 
INSERT INTO carddiscount (id, name, percentage, conditions, ctid) VALUES
(1, 'SYSTEM_DICOUNT', 0, 0, 1);

--
-- Insert defaults to maincategory
--

INSERT INTO maincategory (id, name) VALUES
(1, 'kitchen'),
(2, 'barman');

--
-- Insert defaults to foodcategory
--

INSERT INTO foodcategory (id, name, mcid) VALUES
(1, '1-р хоол', 1),
(2, '2-р хоол', 1),
(4, 'Дессерт', 1);

--
-- Insert defaults to drinkcategory
--
INSERT INTO drinkcategory (id, name) VALUES
(1, 'Архи '),
(2, 'Пиво'),
(3, 'Ундаа'),
(4, 'Ус'),
(5, 'Цай'),
(6, 'Шэйк / Shake '),
(7, 'Кофе');


--
-- Insert defaults to userrole
--

INSERT INTO userrole (id, name) VALUES (1, 'Manager'), (2, 'Тогооч'), (3, 'Зөөгч'), (4, 'SUPERADMIN'), (5, 'Админ');

--
-- Insert defaults to members
--

INSERT INTO members (id, firstname, lastname, username, password, register, mobile, email, isactive, numbervisit, totalspent, cardnumber, bonus, cdid) VALUES
(1, 'SYSTEM_USER', 'SYSTEM_USER', 'SYSTEM_USER', 'SYSTEM_USER', 'SYSTEM_USER', NULL, NULL, 0, 0, 0, NULL, 0, 1);

--
-- Insert defaults to employee
--

INSERT INTO employee (id, username, password, firstname, lastname, picture, register, email, mobile, phone, undsen, bodogdoh, bodoh, isactive, roleid) VALUES(1, 'Admin1', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2', 'Amarsanaa', 'S', NULL, 're', 'amaraa0909@gmail.com', 99193773, 1111111, 0, 0, 1, 1, 4);


--
--  Some dummy data
--

--
-- drinkmenu
--

INSERT INTO drinkmenu (id, name, price, isonthemenu, isset, dcid, createdat, createdby, mcid) VALUES
(1, 'Coca cola', 2000, 0, 0, 3, '2014-04-08 00:00:00', 1, 2),
(2, 'Pepci', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2),
(3, 'Pepci', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2),
(4, 'Sprite', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2),
(5, '7Up', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2),
(6, 'Fanta', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2),
(7, 'Mirinda', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2),
(8, 'Mountain Dew', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2),
(10, 'Genger-Ale', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2),
(11, 'Schweppes+C', 2000, 1, 0, 3, '2014-04-08 00:00:00', 1, 2),
(12, 'FUZE Tea', 1500, 1, 0, 3, '2014-04-08 00:00:00', 1, 2),
(13, 'Punch', 1500, 1, 0, 3, '2014-04-08 00:00:00', 1, 2),
(14, 'Bonaqua', 1000, 1, 0, 4, '2014-04-08 00:00:00', 1, 2);


--
-- employee
--

INSERT INTO employee (id, username, password, firstname, lastname, picture, register, email, mobile, phone, undsen, bodogdoh, bodoh, isactive, roleid) VALUES
(2, 'Tumee', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2', 'Tumee', 'E', '41e58923092e612542d11be10b09eeb1916b5be2', '123123', '12312', 3123, 123123, 123123, 123, 1, 1, 2),
(3, 'Waiter', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2', 'Waiter', 'W', 'c01136b0a8be660e7aaecaebdb49b0312604823c', '123', '123', 123, 3123, 123, 123123, 1, 1, 3);


--
-- foodmenu
--

INSERT INTO foodmenu (id, name, price, isonthemenu, isset, fcit, createdat, createdby, isdetails, mcid) VALUES
(1, 'Tonkotsu ramen', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1),
(2, 'Miso ramen /Гахай/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1),
(3, 'Miso ramen /Үхэр/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1),
(4, 'Tantanmen /Сам хорхой/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1),
(5, 'Tantanmen /Үхэр/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1),
(6, 'Tantanmen /Гахай/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1),
(7, 'Kanjuku /Гахай/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1),
(8, 'Kanjuku /Үхэр/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1),
(9, 'Shoyu /Гахай/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1),
(10, 'Shoyu /Үхэр/', 9800, 1, 0, 1, '2014-04-08 00:00:00', 1, 1, 1),
(11, 'Gyoza', 5800, 1, 0, 2, '2014-04-08 00:00:00', 1, 1, 1),
(12, 'Будаатай хуурга', 7800, 1, 0, 2, '2014-04-08 00:00:00', 1, 1, 1),
(13, 'Chicken teryaki', 8800, 1, 0, 2, '2014-04-08 00:00:00', 1, 1, 1),
(14, 'Kara age', 8800, 1, 0, 2, '2014-04-08 00:00:00', 1, 1, 1);


--
--  Tables
--
INSERT INTO tables(id, tableid, isreserved, isfree, totalperson, merge) VALUES (1,1,0,0,5,0);
INSERT INTO tables(id, tableid, isreserved, isfree, totalperson, merge) VALUES (2,2,0,0,5,0);
INSERT INTO tables(id, tableid, isreserved, isfree, totalperson, merge) VALUES (3,3,0,0,5,0);
INSERT INTO tables(id, tableid, isreserved, isfree, totalperson, merge) VALUES (4,4,0,0,5,0);
INSERT INTO tables(id, tableid, isreserved, isfree, totalperson, merge) VALUES (5,5,0,0,5,0);


--
--  Changes
--
INSERT INTO changes(id, name) VALUES (1,'Бага халуун ногоо');
INSERT INTO changes(id, name) VALUES (2,'Дундаж халуун ногоо');
INSERT INTO changes(id, name) VALUES (3,'Их халуун ногоо');


--
--  Food changes
--
INSERT INTO foodchange(id, changesid, foodmenuid) VALUES (1,1,1);
INSERT INTO foodchange(id, changesid, foodmenuid) VALUES (2,2,1);
INSERT INTO foodchange(id, changesid, foodmenuid) VALUES (3,3,1);
INSERT INTO foodchange(id, changesid, foodmenuid) VALUES (4,1,12);
INSERT INTO foodchange(id, changesid, foodmenuid) VALUES (5,2,12);
INSERT INTO foodchange(id, changesid, foodmenuid) VALUES (6,3,12);


--
--  Waiter connector from 192.168.120.12 suppose
--
INSERT INTO clientip(clientid, address) VALUES (3,'192.168.120.12')