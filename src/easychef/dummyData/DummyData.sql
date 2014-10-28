--  Insert dummy data to remote server

USE remote;

--
--  Insert carddiscount
--
INSERT INTO carddiscount(name, percentage, conditions, ctid) VALUES ('Membership card 5%',5.0,100000,1), ('Membership card 10%',10.0,500000,1);

-- -----------------------------------------------
--
--  Insert promocode
--
INSERT INTO promocode(id, code, discount, starts, ends, isactive, pctid) VALUES (1,'OISHISV15',15,'2014-05-20','2014-05-31',0,1);

-- -----------------------------------------------
--
--  Insert sites
--
INSERT INTO sites(id, name) VALUES (1,'Ойши 1');
INSERT INTO sites(id, name) VALUES (2,'Ойши 2');

-- -----------------------------------------------
--
--  Insert Employee
--
INSERT INTO employee(username, password, firstname, lastname, picture, register, email, mobile, phone, undsen, bodogdoh, bodoh, isactive, roleid) VALUES ('tumee', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2', 'Tumee', 'E', NULL, 'register', 'tume@gmail.com', 94000746, 11364531, 0, 0, 1, 1, 2), ('test', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2', 'Test', 'E', NULL, 'register', 'test@gmail.com', 9999999, 11364531, 0, 0, 1, 1, 3);

-- -----------------------------------------------
--
--  Insert Members
--
INSERT INTO members(firstname, lastname, username, password, register, mobile, email, isactive, numbervisit, totalspent, cardnumber, bonus, cdid) VALUES ('Tumee', 'E', 'tumee', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2', 'ЧИ86053132', 94000746, 'tum_mgl@yahoo.com', 0, 0, 0, 'H12M7264359f', 0, 2), ('Test', 'E', 'test', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2', 'ЧИ86213132', 94030746, 'test@yahoo.com', 0, 0, 0, 'H12M7264358f', 0, 3);

-- -----------------------------------------------
--
--  Insert Tables
--
INSERT INTO tables(id, tableid, isreserved, isfree, totalperson, merge, sites) VALUES (1,1,0,0,5,0,1), (2,2,0,0,5,0,1), (3,3,0,0,5,0,1), (4,4,0,0,5,0,1), (5,5,0,0,5,0,1), (6,1,0,0,5,0,2), (7,2,0,0,5,0,2), (8,3,0,0,5,0,2), (9,4,0,0,5,0,2), (10,5,0,0,5,0,2);

-- -----------------------------------------------
--
-- Insert foodcategory
--

INSERT INTO foodcategory (id, name) VALUES
(1, '1-р хоол'),
(2, '2-р хоол'),
(4, 'Дессерт');

-- -----------------------------------------------
--
-- Insert drinkcategory
--
INSERT INTO drinkcategory (id, name) VALUES
(1, 'Архи '),
(2, 'Пиво'),
(3, 'Ундаа'),
(4, 'Ус'),
(5, 'Цай'),
(6, 'Шэйк / Shake '),
(7, 'Кофе');

-- -----------------------------------------------
--
--  Insert FoodMenu
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

-- -----------------------------------------------
--
--  Insert DrinkMenu
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

-- -----------------------------------------------
--
--  Insert food_sites
--
INSERT INTO food_sites(foodmenuid, siteid) VALUES (1,1), (2,1), (3,1), (4,1), (5,1), (6,1), (7,1), (8,1), (9,1), (10,1), (11,1), (12,1), (13,1), (14,1), (1,2), (2,2), (3,2), (4,2), (5,2), (6,2), (7,2), (8,2), (9,2), (10,2), (11,2), (12,2), (13,2), (14,2);

-- -----------------------------------------------
--
--  Insert drink_sites
--
INSERT INTO drink_sites(drinkmenuid, site) VALUES (1,1), (2,1), (3,1), (4,1), (5,1), (6,1), (7,1), (8,1), (9,1), (10,1), (11,1), (12,1), (13,1), (14,1), (1,2), (2,2), (3,2), (4,2), (5,2), (6,2), (7,2), (8,2), (9,2), (10,2), (11,2), (12,2), (13,2), (14,2);

-- -----------------------------------------------
--
--  Changes
--
INSERT INTO changes(id, name) VALUES (1,'Бага халуун ногоо');
INSERT INTO changes(id, name) VALUES (2,'Дундаж халуун ногоо');
INSERT INTO changes(id, name) VALUES (3,'Их халуун ногоо');

-- -----------------------------------------------
--
--  Insert food_changes
--
INSERT INTO foodchange(id, changesid, foodmenuid) VALUES (1,1,1);
INSERT INTO foodchange(id, changesid, foodmenuid) VALUES (2,2,1);
INSERT INTO foodchange(id, changesid, foodmenuid) VALUES (3,3,1);
INSERT INTO foodchange(id, changesid, foodmenuid) VALUES (4,1,12);
INSERT INTO foodchange(id, changesid, foodmenuid) VALUES (5,2,12);
INSERT INTO foodchange(id, changesid, foodmenuid) VALUES (6,3,12);