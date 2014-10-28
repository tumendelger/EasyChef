USE easysystems;

--
--  Set delivered orders back to not delivered
--

DELETE FROM dailystatistics;
UPDATE dorderdetails SET isdelivered=0, cancelbyw=0,cancelbych=0;
UPDATE forderdetails SET isdelivered=0, cancelbyw=0, cancelbych=0;