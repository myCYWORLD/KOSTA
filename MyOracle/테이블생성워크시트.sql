DROP TABLE product;
CREATE TABLE product (
    prod_no VARCHAR2(5),
    prod_name VARCHAR2(30) NOT NULL,
    prod_price NUMBER(6),
    prod_info VARCHAR2(100),
    prod_mfd DATE,
    CONSTRAINT prod_no_pk PRIMARY KEY (prod_no),
    CONSTRAINT prod_price_ck CHECK (prod_price >= 0)
);

/*
ALTER TABLE product
ADD CONSTRAINT prod_no_pk PRIMARY KEY (prod_no);
*/

CREATE TABLE customer (
    id VARCHAR2(5),
    pwd VARCHAR2(5) NOT NULL,
    name VARCHAR2(30) NOT NULL,
    address VARCHAR2(30),
    status NUMBER(1),
    CONSTRAINT id_pk PRIMARY KEY (id),
    CONSTRAINT status_ck CHECK (status IN (0, 1))
);

CREATE TABLE order_info (
    order_no NUMBER,
    order_id VARCHAR2(5) NOT NULL,
    order_dt DATE NOT NULL,
    CONSTRAINT order_no_pk PRIMARY KEY (order_no),
    CONSTRAINT order_id_fk FOREIGN KEY (order_id) REFERENCES customer (id)
);

SELECT * FROM user_constraints;

CREATE TABLE order_line (
    order_no NUMBER,
    order_prod_no VARCHAR2(5),
    order_quantity NUMBER(3),
    CONSTRAINT order_lines_no_pk PRIMARY KEY (order_no, order_prod_no),
    CONSTRAINT order_lines_no_fk FOREIGN KEY (order_no) REFERENCES order_info (order_no),
    CONSTRAINT order_prod_no_fk FOREIGN KEY (order_prod_no) REFERENCES product (prod_no),
    CONSTRAINT order_quantity_ck CHECK (order_quantity >= 1)
);



