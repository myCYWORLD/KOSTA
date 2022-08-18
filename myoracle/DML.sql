--DML (데이터 조작)
--데이터 추가 (ISNERT INTO), 수정(UPDATE), 삭제(DELETE)
-------------------------------------------------------
--데이터추가
INSERT INTO customer(id, pwd, name, address, status) VALUES ('id1','p1','n1','a1',1);
INSERT INTO customer                                 VALUES ('id2','p2','n2','a2',1); --컬럼명 지정 안하면 컬럼 자료형대로 순서대로 작성해야함
INSERT INTO customer(id, pwd, name)                  VALUES ('id3','p3','n3'); --address/status 기본값이 null => 지정안할시에 자동 null값
SELECT * FROM customer;  

INSERt INTO product(prod_no, prod_name, prod_price) VALUES ('C0001','아메리카노',1000); 
INSERt INTO product                                 VALUES ('C0002','아이스아메리카노',1000, null, '');
INSERt INTO product(prod_no, prod_name, prod_price,prod_mfd) 
            VALUES ('G0001',  '텀블러',    3000,   '22/01/01'); --날짜타입 자동 형변환 
INSERt INTO product(prod_no, prod_name, prod_price,prod_mfd) 
            VALUES ('G0002',  '다이어리',   4000,    SYSDATE);  --sysdate 현재 날짜로 입력
SELECT * FROM product;  

--데이터변경
--primary값은 업데이트 X 
----------------------------------------
UPDATE customer SET name = '황초연' WHERE id='id1';  --UPDATE절에는 테이블명 SET절에는 변경할 컬럼명(=변경값) WHERE절에는 조건
UPDATE customer SET pwd = 'p22', name='한미래' WHERE id='id2';
UPDATE customer SET status = 1; --WHERE 절이 없으면 모든 절에 반영되기 때문에 주의
SELECT * FROM customer;
------------------------------------------
UPDATE product SET prod_price = prod_price + (prod_price*0.1);--모든 상품의 가격을 10%인상
SELECT * FROM product;
----------------------------------------------
DELETE FROM customer; -- delete는 테이블 구조는 남아있고 자료만 삭제 /drop은 테이블 자체를 삭제
INSERT INTO customer(id, pwd, name, address, status) VALUES ('id1','p1','n1','a1',1);
INSERT INTO customer                                 VALUES ('id2','p2','n2','a2',1); 
INSERT INTO customer(id, pwd, name)                  VALUES ('id3','p3','n3');
SELECT * FROM customer;
----------------------------------------------
--WHERE절에서 값 대신 subquery 활용할 수 있음(DELETE FROM customer WHERE 'id='id1';)
--DDL = 객체 생성 변경 제거
