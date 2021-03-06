--
-- Insert new orders for testing
--

use easysystems;

INSERT INTO orders(id, ordertime, totalprice, vat, discount, cash, card, ispaid, totalAmount, tid, mid, uid, promocode, systemdate, syn_status) VALUES (1234567891,NOW(),68800,6880,0,0,0,0,75680,1,1,2,'0',NOW(),0);


--
--  Kitchen orders
--

INSERT INTO forderdetails(id, foodid, haschange, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12341,2,0,9800,0,NOW(),0,0, 1,1234567891);
INSERT INTO forderdetails(id, foodid, haschange, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12342,3,0,9800,0,NOW(),0,0, 1,1234567891);
INSERT INTO forderdetails(id, foodid, haschange, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12343,4,0,9800,0,NOW(),0,0, 1,1234567891);
INSERT INTO forderdetails(id, foodid, haschange, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12344,5,0,9800,0,NOW(),0,0, 1,1234567891);
INSERT INTO forderdetails(id, foodid, haschange, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12345,6,0,9800,0,NOW(),0,0, 1,1234567891);
INSERT INTO forderdetails(id, foodid, haschange, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12346,7,0,9800,0,NOW(),0,0, 1,1234567891);

--
--  Drink orders
--
INSERT INTO dorderdetails(id, drinkid, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12341,1,2000,0,NOW(),0,0,1,1234567891);
INSERT INTO dorderdetails(id, drinkid, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12342,2,2000,0,NOW(),0,0,2,1234567891);
INSERT INTO dorderdetails(id, drinkid, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12343,3,2000,0,NOW(),0,0,1,1234567891);
INSERT INTO dorderdetails(id, drinkid, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12344,4,2000,0,NOW(),0,0,2,1234567891);
INSERT INTO dorderdetails(id, drinkid, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12345,5,2000,0,NOW(),0,0,2,1234567891);

--
-- This order some detail has changes
--

INSERT INTO orders(id, ordertime, totalprice, vat, discount, cash, card, ispaid, totalAmount, tid, mid, uid, promocode, systemdate, syn_status) VALUES (1234567892,NOW(),29400,2940,0,0,0,0,32340,1,1,2,'0',NOW(),0);

INSERT INTO forderdetails(id, foodid, haschange, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12347,2,0,9800,0,NOW(),0,0, 1,1234567892);
INSERT INTO forderdetails(id, foodid, haschange, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12348,4,0,9800,0,NOW(),0,0, 1,1234567892);
INSERT INTO forderdetails(id, foodid, haschange, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12349,3,0,9800,0,NOW(),0,0, 1,1234567892);


--
--
--

INSERT INTO orders(id, ordertime, totalprice, vat, discount, cash, card, ispaid, totalAmount, tid, mid, uid, promocode, systemdate, syn_status) VALUES (1234567893,NOW(),29400,2940,0,0,0,0,32340,1,1,2,'0',NOW(),0);

INSERT INTO forderdetails(id, foodid, haschange, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12350,2,0,9800,0,NOW(),0,0, 1,1234567893);
INSERT INTO forderdetails(id, foodid, haschange, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12351,4,0,9800,0,NOW(),0,0, 1,1234567893);
INSERT INTO forderdetails(id, foodid, haschange, price, isdelivered, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (12352,3,0,9800,0,NOW(),0,0, 1,1234567893);
--
--  2 order has changes
--
INSERT INTO `orderchange`(`fodid`, `cid`) VALUES (12347,2);
INSERT INTO `orderchange`(`fodid`, `cid`) VALUES (12348,3);