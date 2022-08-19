--뷰 : 가상테이블
--복잡한 sql 구문을 뷰로 저장
/*
SELECT d.department_id, d.department_name, j.job_id,j.job_title, count(employee_id)
FROM employees e JOIN departments d ON (e.department_id=d.department_id)
                 JOIN jobs j ON (e.job_id=j.job_id)
WHERE salary >= 3000
GROUP BY d.department_id, d.department_name, j.job_id, j.job_title
HAVING count(employee_id)>=2
ORDER BY count(employee_id);
*/
CREATE VIEW a_view
AS
SELECT d.department_id, d.department_name, j.job_id,j.job_title,
       count(employee_id)employee_cnt --집계 함수 별칭 정해줘야 오류가 나지 않음
FROM employees e JOIN departments d ON (e.department_id=d.department_id)
                 JOIN jobs j ON (e.job_id=j.job_id)
WHERE salary >= 3000
GROUP BY d.department_id, d.department_name, j.job_id, j.job_title
HAVING count(employee_id)>=2
ORDER BY count(employee_id);
--
SELECT * FROM a_view;  --view 볼 수 있음

--복잡한 sql구문이 여러곳에서 재사용할 가능성이 있으며 view로 저장해놓는것이 좋음
--만약 department_id 의 별칭을 did로 저장했을 경우 검색할 때에도 별칭으로 검색해줘야함
--view는 목적이 보여주는 것이므로 강제로 변경(자료형 변경,추가같은)이나 삭제 등은 할 수 있으나 별로,,하기도 복잡함
--view는 alter로 테이블 구조변경 안됨 
--------view 생성 or 내용 변경--------------
CREATE OR REPLACE VIEW a_view  --없으면 새로 만들고 있으면 변경하라 (view 만들려면 CREATE만 쓰지말고 처음부터 create OR REPLACE VIEW로 생성)
AS
SELECT employee_id, first_name
FROM employees;

SELECT * FROM a_view;
---------view 삭제-------------------
DROP VIEW a_view; --view는 구조 자체가 없기 때문에 alter가 없음

-------시퀀스-----------
--시퀀스 : 일련번호값
CREATE SEQUENCE a_seq; --증가값 설정 안할 경우에는 기본값 1부터 1씩 증가
CREATE SEQUENCE b_seq --4부터 2씩 증가, 최대값30 ,최소값3(최대값에 도달하면 최소값으로 돌아가거나 에러나오게 설정할 수 있음)
START WITH 4 --시작값 (시작값은 최소값보다 크게 설정해야한다)
INCREMENT BY 2 --증가값 설정
MAXVALUE 50 --최대값
CYCLE  --최소값으로 돌아가게 하는 설정
MINVALUE 3; --최소값은 10으로 10부터 시작
DROP SEQUENCE b_seq;  --drop sequence 시퀀스 제거
--시퀀스 일련번호 값 얻기
SELECT b_seq.NEXTVAL FROM dual; --계속해서 실행하면 진행됨 (값 증가)
SELECT b_seq.NEXTVAL FROM dual; --6
--:
SELECT b_seq.NEXTVAL FROM dual; --50
SELECT b_seq.NEXTVAL FROM dual; --3
SELECT b_seq.NEXTVAL FROM dual; --5

SELECT b_seq.CURRVAL FROM dual; -- 시퀀스 현재값 확인 가능
 --주문용 시퀀스객체--------------------
CREATE SEQUENCE order_seq;
INSERT INTO order_info(order_no,    order_id, order_dt)
            VALUES (order_seq.NEXTVAL, 'id1', SYSDATE);  SELECT b_seq.CURRVAL FROM dual;

INSERT INTO order_line(order_no,          order_prod_no, order_quantity)
                 VALUES (order_seq.CURRVAL, 'C0002',        7); 
                 
INSERT INTO order_line(order_no,          order_prod_no, order_quantity)
                 VALUES (order_seq.CURRVAL, 'C0001',        7); 
SELECT * FROM 
--새로 접속했을 때 할 수 없음

