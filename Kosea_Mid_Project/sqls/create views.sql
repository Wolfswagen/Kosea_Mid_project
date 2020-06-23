
  CREATE OR REPLACE FORCE VIEW "SALES_PRODUCTS_DETAILS" ("SALES_CODE", "PRODUCT_CODE", "PRODUCT_NAME", "PRODUCT_COLOR", "PRODUCT_SIZE", "PURCHASE_AMOUNT", "PRICE", "REFUND_AMOUNT", "SHIPPING") AS 
  SELECT
	sd.SALES_CODE,
	p.PRODUCT_CODE,
	p.PRODUCT_NAME,
	p.PRODUCT_COLOR,
	p.PRODUCT_SIZE,
	sd.PURCHASE_AMOUNT,
	(p.ORIGINAL_PRICE-p.DISCOUNT-p.MULTI_PURCHASE_DISCOUNT-(p.ORIGINAL_PRICE*p.DISCOUNT_RATE)) price,
	sd.REFUND_AMOUNT,
	p.SHIPPING
FROM
	SALES_DETAILS sd,
	PRODUCTS p
WHERE
	sd.PRODUCT_CODE = p.PRODUCT_CODE
order BY SALES_CODE;
 



CREATE OR REPLACE FUNCTION KOSEA.cal_total(s_code NUMBER)
	RETURN NUMBER 
IS
	total_price number(7);
	ship_count number(1);
BEGIN
	SELECT sum(spd.PRICE * spd.PURCHASE_AMOUNT) INTO total_price FROM SALES_PRODUCTS_DETAILS spd GROUP BY spd.SALES_CODE HAVING spd.sales_code = s_CODE ;
	SELECT count(spd.SHIPPING) INTO ship_count FROM SALES_PRODUCTS_DETAILS spd WHERE shipping = '조건부 무료' AND spd.SALES_CODE = s_code;
	IF SHIP_COUNT > 0 AND total_price < 150000 
	THEN total_price := TOTAL_PRICE + 2500;
	END IF;
	RETURN TOTAL_PRICE;
END;




CREATE OR REPLACE VIEW KOSEA.SALES_CUSTOMERS_DETAILS
(SALES_CODE,CUSTOMER_CODE,CUSTOMER_NAME,CUSTOMER_ID,ADDRESS,PHONE,CUSTOM_CLEARANCE_CODE,SALES_DATE,ORDER_DATE,SHIP_DATE,TOTAL,PAYMENT,MESSAGE,PLATFORM,NOTE)
AS
SELECT
	s2.SALES_CODE,
	c.CUSTOMER_CODE,
	c.CUSTOMER_NAME,
	c.CUSTOMER_ID,
	c.ADDRESS,
	c.PHONE,
	c.CUSTOM_CLEARANCE_CODE,
	s2.SALES_DATE,
	s2.ORDER_DATE,
	s2.SHIP_DATE,
	cal_total(s2.SALES_CODE) total,
	s2.PAYMENT,
	s2.MESSAGE,
	s2.PLATFORM,
	s2.NOTE
FROM
	dual,
	SALES s2,
	CUSTOMERS c
WHERE 
	s2.CUSTOMER_CODE = c.CUSTOMER_CODE;





  CREATE OR REPLACE FORCE VIEW "REFUNDS_PRODUCTS_DETAILS" ("SALES_CODE", "PRODUCT_CODE", "PRODUCT_NAME", "PRODUCT_COLOR", "PRODUCT_SIZE", "PRICE", "PURCHASE_AMOUNT", "REFUND_AMOUNT", "REFUND_PRICE") AS 
  SELECT
	s.SALES_CODE,
	spd.PRODUCT_CODE,
	spd.PRODUCT_NAME,
	spd.PRODUCT_COLOR,
	spd.PRODUCT_SIZE,
	spd.PRICE,
	spd.PURCHASE_AMOUNT,
	spd.REFUND_AMOUNT ,
	spd.PRICE * spd.REFUND_AMOUNT refund_price
FROM
	SALES_PRODUCTS_DETAILS spd,
	SALES s
WHERE
	s.SALES_CODE = spd.SALES_CODE;
 



CREATE OR REPLACE FUNCTION KOSEA.cal_refund ( s_code NUMBER, fault varchar )
	RETURN NUMBER
IS
	total_refund NUMBER(7);
BEGIN
	SELECT
	sum(rpd.REFUND_PRICE)
INTO
	total_refund
FROM
	REFUNDS_PRODUCTS_DETAILS rpd
GROUP BY
	rpd.SALES_CODE
HAVING
	rpd.SALES_CODE = s_code;
IF
	fault LIKE '%업체%' THEN total_refund := total_refund + 5000;
END
IF;
RETURN total_refund;
END;




  CREATE OR REPLACE FORCE VIEW "REFUNDS_CUSTOMERS_DETAILS"
 ("SALES_CODE", "CUSTOMER_ID", "CUSTOMER_NAME", "ADDRESS", "PHONE", "TOTAL_REFUND", "MESSAGE", "NOTE") 
 AS 
  SELECT
	scd.SALES_CODE,
	scd.CUSTOMER_ID,
	scd.CUSTOMER_NAME,
	scd.ADDRESS,
	scd.PHONE,
	cal_refund(scd.SALES_CODE, scd.MESSAGE ) total_refund,
	scd.message,
	scd.NOTE
FROM
	SALES_CUSTOMERS_DETAILS scd,
	dual;
 




 CREATE OR REPLACE
FORCE VIEW "IMPORT_PRODUCTS" 
("PRODUCT_CODE", "PRODUCT_NAME", "PRODUCT_COLOR", "PRODUCT_SIZE", "AMOUNT") 
AS
SELECT
	PRODUCT_CODE,
	PRODUCT_NAME,
	PRODUCT_COLOR,
	PRODUCT_SIZE,
	amount
FROM
	products
WHERE
	amount < 0;




