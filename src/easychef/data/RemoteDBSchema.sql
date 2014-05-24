--  Generate fresh database for the application
--  Энэ өгөгдлийн сангийн схем нь remote буюу админ апп-д ашиглагдах схем
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+08:00";

--
-- Database: remote
--  

DROP DATABASE IF EXISTS remote;

CREATE DATABASE IF NOT EXISTS remote DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE remote;

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
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  waittime int(11) NOT NULL DEFAULT 0,
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
  name varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  name varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  price int(11) NOT NULL,
  isonthemenu tinyint(1) NOT NULL,
  isset tinyint(1) NOT NULL,
  dcid int(11) NOT NULL COMMENT 'DrinkCategory ID',
  createdat datetime NOT NULL,
  createdby int(11) NOT NULL,
  mcid int(11) NOT NULL DEFAULT '2' COMMENT 'MainCategoryId',
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY dcid (dcid),
  KEY mcid (mcid)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table drink_sites
--

DROP TABLE IF EXISTS drink_sites;
CREATE TABLE IF NOT EXISTS drink_sites (
  drinkmenuid int(11) NOT NULL,
  site int(11) NOT NULL,
  status int(11) NOT NULL DEFAULT '1',
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY drinkmenuid (drinkmenuid),
  KEY site (site)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table employee
--

DROP TABLE IF EXISTS employee;
CREATE TABLE IF NOT EXISTS employee (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  password varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  firstname varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  lastname varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  picture varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  register varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  email varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  mobile int(11) DEFAULT NULL,
  phone int(11) DEFAULT NULL,
  undsen double NOT NULL DEFAULT '0' COMMENT 'Undsen tsalin',
  bodogdoh double NOT NULL DEFAULT '0' COMMENT 'Bodogdoh tsalin',
  bodoh int(11) NOT NULL DEFAULT '1' COMMENT '1=undsen; 2=bodogdoh tsalingaas bodoh',
  isactive tinyint(1) NOT NULL DEFAULT '0' COMMENT 'systemd nevtreh erhtei eseh',
  roleid int(11) NOT NULL,
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  name varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
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
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  name varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  price int(11) NOT NULL,
  isonthemenu tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Zuugchiin menu-nd haraghah eseh',
  isset tinyint(1) NOT NULL DEFAULT '0' COMMENT 'SET hool mun eseh',
  fcit int(11) NOT NULL COMMENT 'FoodCategory ID',
  createdat datetime NOT NULL,
  createdby int(11) NOT NULL,
  isdetails int(11) NOT NULL DEFAULT '0' COMMENT 'materialiin zadargaa hiih bolomjtoi eseh',
  mcid int(11) NOT NULL DEFAULT '1' COMMENT 'MainCategoryId',
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY fcit (fcit),
  KEY mcid (mcid)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table food_sites
--

DROP TABLE IF EXISTS food_sites;
CREATE TABLE IF NOT EXISTS food_sites (
  foodmenuid int(11) NOT NULL,
  siteid int(11) NOT NULL,
  status int(11) NOT NULL DEFAULT '1',
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY foodmenuid (foodmenuid),
  KEY siteid (siteid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  waittime int(11) NOT NULL DEFAULT 0,
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
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  name varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table materialsubcategory
--

DROP TABLE IF EXISTS materialsubcategory;
CREATE TABLE IF NOT EXISTS materialsubcategory (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  mcid int(11) NOT NULL COMMENT 'MaterialCategory ID',
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  sites int(11) NOT NULL DEFAULT 1 COMMENT 'Ali site ve',
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
  code varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  discount int(11) NOT NULL,
  starts datetime DEFAULT NULL,
  ends datetime DEFAULT NULL,
  isactive int(11) NOT NULL DEFAULT '1',
  pctid int(11) NOT NULL COMMENT 'PromoCodeTypeID',
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  name varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table sitebalance
--

DROP TABLE IF EXISTS sitebalance;
CREATE TABLE IF NOT EXISTS sitebalance (
  id int(11) NOT NULL AUTO_INCREMENT,
  materialid int(11) NOT NULL,
  uldegdel float NOT NULL,
  siteid int(11) NOT NULL,
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table sites
--

DROP TABLE IF EXISTS sites;
CREATE TABLE IF NOT EXISTS sites (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
  sync_status int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 COMMENT 'Udur undurluhud tuhain udriin medeeliig avhc ene table-d hiij system settings-iin systemdate utgand shine udriin on sar bichne';

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
-- Table structure for table system_table_update
--

DROP TABLE IF EXISTS system_table_update;
CREATE TABLE IF NOT EXISTS system_table_update (
  id int(11) NOT NULL AUTO_INCREMENT,
  tablename varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  updatedate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Table update hiisen ued date-iig ene table-s hiine' AUTO_INCREMENT=22 ;

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
  sites int(11) NOT NULL DEFAULT '1',
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table unittype
--

DROP TABLE IF EXISTS unittype;
CREATE TABLE IF NOT EXISTS unittype (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  action int(11) NOT NULL DEFAULT 0 COMMENT '0=new; 1=update;2=delete',
  actiontime datetime NOT NULL,
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
  sync_status tinyint(1) NOT NULL DEFAULT 0,
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

-- --------------------------------------------------------

--
-- Table structure for table dailystatistics
--

DROP TABLE IF EXISTS dailystatistics;
CREATE TABLE IF NOT EXISTS dailystatistics (
  id int(11) NOT NULL AUTO_INCREMENT,
  stat_type int(11) NOT NULL COMMENT 'Statistics type 1=Low wait order, 2=Medium wait order, 3=High wait order, 4=Cancel with material cost, 5=Cancel without material cost',
  noOfOrders int(11) NOT NULL,
  effectivedate date NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Tuhain udriin statistics baga huleelttei ih huleelttei geh met statistics' AUTO_INCREMENT=1 ;


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
-- Constraints for table food_sites
--
ALTER TABLE food_sites
  ADD CONSTRAINT food_sites_ibfk_1 FOREIGN KEY (foodmenuid) REFERENCES foodmenu (id) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT food_sites_ibfk_2 FOREIGN KEY (siteid) REFERENCES sites (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
--  Insert defaults into system_settings
--
INSERT INTO system_settings(name, parameter) VALUES 
('SYSTEMDATE','2014-05-05'),
('NEXTCASH', '15000'),
('SITEID', '1'),
('COLOR_LW', '#35AA74'),
('COLOR_MW', '#FFB848'),
('COLOR_HW', '#FF3F3F'),
('COLOR_CH', '#4D90FE'),
('PRINTER', 'printername'),
('LAST_UPDATE', '2014-05-18');

--
-- Insert defaults to cardtype
--
INSERT INTO cardtype (id, name) VALUES
(1, 'DiscountCard'),
(2, 'BonusCard');

-- 
-- Insert defaults to promocode type
--
INSERT INTO promocodetype(id, name) VALUES (1,'Нөхцөлт промо код')
,(2,'Нэг удаа ашиглах промо код');

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
-- Insert defaults to userrole
--

INSERT INTO userrole (id, name) VALUES (1, 'Manager'), (2, 'Тогооч'), (3, 'Зөөгч'), (4, 'SUPERADMIN'), (5, 'Админ');

--
-- Insert defaults to members
--

INSERT INTO members (id, firstname, lastname, username, password, register, mobile, email, isactive, numbervisit, totalspent, cardnumber, bonus, cdid) VALUES
(1, 'SYSTEM_USER', 'SYSTEM_USER', 'SYSTEM_USER', 'SYSTEM_USER', 'SYSTEM_USER', NULL, NULL, 0, 0, 0, NULL, 0, 1);

--
-- Insert defaults to system_table_update
--
INSERT INTO system_table_update(id, tablename) VALUES (1,'carddiscount'), (2,'promocode'), (3,'employee'), (4,'members'), (5,'tables'), (6,'foodcategory'), (7,'drinkcategory'), (8,'foodmenu'), (9,'drinkmenu'), (10,'food_sites'), (11,'drink_sites'), (12, 'foodchange');