CREATE OR REPLACE
PROCEDURE KOSEA.insert_products ( p_cate varchar2, p_name varchar2, p_color varchar2, p_size varchar2, p_sts varchar2, p_amt NUMBER, p_pri NUMBER, p_disc NUMBER, p_mpd NUMBER, p_dr NUMBER, p_ship varchar2 ) IS
BEGIN
	INSERT
	INTO
	PRODUCTS
VALUES ((
SELECT
	nvl(MAX(PRODUCT_CODE), 0)+ 1
FROM
	products
WHERE
	CATEGORY = p_cate), p_cate, p_name, p_color, p_size, p_sts, p_amt, p_pri, p_disc, p_mpd, p_dr, sysdate, p_ship);
END;





CREATE OR REPLACE
PROCEDURE KOSEA.insert_Customers ( c_name varchar2, c_id varchar2, c_address varchar2, c_phone varchar2, c_zip varchar2, c_ccc varchar2 ) IS
BEGIN
	INSERT
	INTO
	CUSTOMERS
VALUES ((
SELECT
	nvl(MAX(CUSTOMER_CODE), 0)+ 1
FROM
	CUSTOMERS), c_name, c_id, c_address, c_phone, c_zip, c_ccc);
END;




CREATE OR REPLACE
PROCEDURE KOSEA.insert_sales (C_CODE NUMBER, PAY NUMBER, MSG VARCHAR2, PFM VARCHAR2, NOTE VARCHAR2 ) IS
BEGIN
	INSERT
	INTO
	SALES
VALUES ((
SELECT
	nvl(max(SALES_CODE), 0)+ 1
FROM
	SALES s), SYSDATE, SYSDATE, NULL, C_CODE, PAY, MSG, PFM, NOTE);
END;





CREATE OR REPLACE
PROCEDURE KOSEA.insert_sales_details ( s_code NUMBER, p_code NUMBER, p_amount NUMBER, r_amount NUMBER) IS chk NUMBER;
BEGIN
	SELECT
	COUNT(*)
INTO
	chk
FROM
	SALES_DETAILS sd
WHERE
	sd.SALES_CODE = s_code
	AND sd.PRODUCT_CODE = p_code;
IF
	chk = 0 THEN
INSERT
	INTO
	SALES_DETAILS sd2 (sd2.SALES_CODE, sd2.PRODUCT_CODE, sd2.PURCHASE_AMOUNT, sd2.REFUND_AMOUNT )
VALUES (s_code, p_code, p_amount, 0);
ELSE
UPDATE
	SALES_DETAILS
SET
	PURCHASE_AMOUNT = P_AMOUNT;
END
IF;
END;




CREATE OR REPLACE
PROCEDURE KOSEA.update_products ( p_num NUMBER, p_cate varchar2, p_name varchar2, p_color varchar2, p_size varchar2, p_sts varchar2, p_amt NUMBER, p_pri NUMBER, p_disc NUMBER, p_mpd NUMBER, p_dr NUMBER, p_ship varchar2 ) IS
BEGIN
	UPDATE
	PRODUCTS
SET
	CATEGORY = p_cate,
	PRODUCT_NAME = p_name,
	PRODUCT_COLOR = p_color,
	PRODUCT_SIZE = p_size,
	STATUS = p_sts,
	AMOUNT = p_amt,
	ORIGINAL_PRICE = p_pri,
	DISCOUNT = p_disc,
	MULTI_PURCHASE_DISCOUNT = p_mpd,
	DISCOUNT_RATE = p_dr,
	REGISTER_DATE = sysdate,
	SHIPPING = p_ship
WHERE
	PRODUCT_CODE = p_num;
END;




CREATE OR REPLACE
PROCEDURE KOSEA.Update_Customers ( c_num NUMBER, c_name varchar2, c_id varchar2, c_address varchar2, c_phone varchar2, c_zip varchar2, c_ccc varchar2 ) IS
BEGIN
	UPDATE
	CUSTOMERS
SET
	CUSTOMER_NAME = c_name,
	Customer_id = c_id,
	ADDRESS = c_address,
	phone = c_phone,
	ZIP_CODE = c_zip,
	CUSTOM_CLEARANCE_CODE = c_ccc
WHERE
	CUSTOMER_CODE = c_num;
END;




CREATE OR REPLACE
PROCEDURE KOSEA.UPDATE_Sales ( s_code NUMBER, c_code NUMBER, pay NUMBER, msg varchar2, plf varchar2, nte varchar2 ) IS
BEGIN
	UPDATE
	SALES
SET
	CUSTOMER_CODE = c_code,
	PAYMENT = pay,
	MESSAGE = msg,
	PLATFORM = plf,
	NOTE = nte
WHERE
	SALES_CODE = s_code;
END;




CREATE OR REPLACE
PROCEDURE KOSEA.Update_sales_details ( S_code NUMBER, p_code NUMBER, p_amt NUMBER, r_amt NUMBER ) IS
BEGIN
	UPDATE
	SALES_DETAILS
SET
	purchase_AMOUNT = p_amt,
	REFUND_AMOUNT = r_amt
WHERE
	SALES_CODE = s_code
	AND PRODUCT_CODE = p_code;
END;




CREATE OR REPLACE
PROCEDURE KOSEA.refund ( s_number NUMBER, p_number NUMBER, r_amount NUMBER, s_msg varchar2 ) IS
BEGIN
	UPDATE
	sales s
SET
	s.note = '환불(' || s_msg || ')'
WHERE
	s.SALES_CODE = s_number;

UPDATE
	SALES_DETAILS sd
SET
	sd.REFUND_AMOUNT = sd.REFUND_AMOUNT + r_amount,
	sd.PURCHASE_AMOUNT = sd.PURCHASE_AMOUNT - R_AMOUNT
WHERE
	sd.Sales_code = s_number
	AND sd.PRODUCT_CODE = p_number;
END;




CREATE OR REPLACE PROCEDURE KOSEA.cancel_refund ( s_number NUMBER, p_number NUMBER) 
IS
r_amount number(3);
BEGIN
	SELECT
	sd2.REFUND_AMOUNT
INTO
	r_amount
FROM
	SALES_DETAILS sd2
WHERE
	sd2.SALES_CODE = s_number
	AND sd2.PRODUCT_CODE = p_number;

UPDATE
	sales s
SET
	s.note = ' '
WHERE
	s.SALES_CODE = s_number;

UPDATE
	SALES_DETAILS sd
SET
	sd.PURCHASE_AMOUNT = sd.PURCHASE_AMOUNT + r_amount,
	sd.REFUND_AMOUNT = sd.REFUND_AMOUNT - r_amount
WHERE
	sd.Sales_code = s_number
	AND sd.PRODUCT_CODE = p_number;
END;




CREATE OR REPLACE
PROCEDURE KOSEA.finish_sales ( s_code NUMBER ) IS c_amount NUMBER(3);

c_refund NUMBER(7);

c_message varchar(100);
BEGIN
	SELECT
	s2.note
INTO
	c_message
FROM
	SALES s2
WHERE
	s2.SALES_CODE = s_code;
IF
	c_message NOT LIKE '%완료%'
	OR c_message IS NULL THEN
SELECT
	rcd.TOTAL_REFUND
INTO
	c_refund
FROM
	REFUNDS_CUSTOMERS_DETAILS rcd
WHERE
	rcd.SALES_CODE = s_code;

FOR p_list IN (
SELECT
	*
FROM
	SALES_DETAILS sd
WHERE
	sd.SALES_CODE = s_code)
LOOP
IF
		c_refund > 0 THEN
SELECT
	sd2.REFUND_AMOUNT
INTO
	c_amount
FROM
	SALES_DETAILS sd2
WHERE
	sd2.PRODUCT_CODE = p_list.product_code
	AND sd2.SALES_CODE = s_code;

UPDATE
	products p
SET
	p.AMOUNT = p.amount + c_amount
WHERE
	p.PRODUCT_CODE = p_list.product_code;

UPDATE
	sales s
SET
	s.NOTE = '환불 완료' ,
	s.SHIP_DATE = SYSDATE
WHERE
	s.SALES_CODE = s_code;
ELSE
SELECT
	sd2.PURCHASE_AMOUNT
INTO
	c_amount
FROM
	SALES_DETAILS sd2
WHERE
	sd2.PRODUCT_CODE = p_list.product_code
	AND sd2.SALES_CODE = s_code;

UPDATE
	products p
SET
	p.AMOUNT = p.amount - c_amount
WHERE
	p.PRODUCT_CODE = p_list.product_code;

UPDATE
	sales s
SET
	s.NOTE = '거래 완료' ,
	s.SHIP_DATE = SYSDATE
WHERE
	s.SALES_CODE = s_code;
END
IF;
END
LOOP;
END
IF;
END;






CREATE OR REPLACE
PROCEDURE KOSEA.import_product( p_code NUMBER, i_amount NUMBER ) IS
BEGIN
	UPDATE
	products
SET
	amount = amount + nvl(i_amount, 0)
WHERE
	PRODUCT_CODE = p_code;
END;
