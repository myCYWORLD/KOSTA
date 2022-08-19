--트랜잭션
--계좌테이블
CREATE TABLE ACCOUNT (no CHAR(3) primary key, balance number(10));
-- INSERT 구문 실행되면서 트랜잭션 시작
INSERT INTO account(no, balance) VALUES ('101', 1000); --tx-1
INSERT INTO account(no, balance) VALUES ('102', 1000); --tx-2
commit; --트랜잭션 마무리
SELECT * FROM account;

UPDATE account SET balance = balance - 100 WHERE no = '101';
SELECT * FROM account;
UPDATE account SET balance = balance + 100 WHERE no = '102';
SELECT * FROM account;
commit;

--101계좌 100출금 
UPDATE account SET balance = balance -100 WHERE no = '101';
--999계좌 100입금 
UPDATE account SET balance = balance +100 WHERE no = '999';
SELECT * FROM account;
ROLLBACK; --복구 명령어

----PL/SQL
--SQL로는 변수선언, 조건처리, 반복처리불가
--PL/SQL은 SQL, 변수선언, 조건처리, 반복처리 가능
SELECT a_func()
FROM dual;
--프로시져-----------
CREATE OR REPLACE PROCEDURE a_proc(num1 number, num2 number)
IS num3 number := 0;
BEGIN
    num3 := num1 + num2;
    DBMS_OUTPUT.PUT_LINE('숫자 :' || num3);
END;
------------------------------------
EXEC a_proc(1,2);
EXEC a_proc(5,6);
------------------------------------
CREATE OR REPLACE PROCEDURE b_proc(num number)
IS
BEGIN
    IF MOD(num, 2)= 1 THEN
        DBMS_OUTPUT.PUT_LINE('홀수입니다');
    ELSE
        DBMS_OUTPUT.PUT_LINE('짝수입니다');
    END IF;
    
    IF  num > 10 THEN
        DBMS_OUTPUT.PUT_LINE('10보다 큽니다');
    ELSIF NUM > 5 THEN
        DBMS_OUTPUT.PUT_LINE('5보다 큽니다');
    ELSE 
        DBMS_OUTPUT.PUT_LINE('5이하입니다');
    END IF;
END;    
/
EXEC b_proc(15);
EXEC b_proc(8);
EXEC b_proc(0);
------------
CREATE OR REPLACE PROCEDURE c_proc
IS
BEGIN
    FOR i IN 1..10 LOOP
    DBMS_OUTPUT.PUT_LINE(i);
    END LOOP;
END;
/
EXEC c_proc;
--------------------
CREATE OR REPLACE PROCEDURE d_proc(v_department_id employees.department_id%TYPE)
IS v_sum NUMBER;
   v_department_name departments.department_name%TYPE;
BEGIN
    SELECT SUM(salary) INTO v_sum
    FROM employees
    WHERE department_id = v_department_id;
    DBMS_OUTPUT.PUT_LINE(v_department_id || '부서의 급여합은' || v_sum);

    SELECT department_name INTO v_department_name
    FROM departments 
    WHERE department_id = v_department_id;
    DBMS_OUTPUT.PUT_LINE(v_department_id || '부서이름은 ' || v_department_name);

    INSERT INTO a_tbl(a) VALUES (v_department_id);
END;
/
EXEC d_proc(50);
SELECT * FROM a_tbl;
----------------------------------------------
CREATE OR REPLACE PROCEDURE e_proc(v_salary employees.salary%TYPE)
IS v_salary2 employees.salary%TYPE;
BEGIN
    SELECT salary INTO v_salary2
    FROM employees
    WHERE salary > v_salary;
    DBMS_OUTPUT.PUT_LINE(v_salary2);
END;
/
EXEC e_proc(5000);----X

CREATE OR REPLACE PROCEDURE e_proc(v_salary employees.salary%TYPE)
IS 
    CURSOR c1 IS
    SELECT salary    
    FROM employees
    WHERE salary > v_salary;
BEGIN
    FOR e IN c1 LOOP
        DBMS_OUTPUT.PUT_LINE(e.salary);
    END LOOP;
END;
/
EXEC e_proc(5000);
----5000보다 큰 모든 사원의 급여를 출력----
CREATE OR REPLACE PROCEDURE e_proc(v_salary employees.salary%TYPE)
IS 
    CURSOR c1 IS
    SELECT *    --모든 사원의 급여를 출력하고 싶으면 *로 바꾸고 LINE절(e.employee_id ||'-'||e.salary)로 바꿔준다
    FROM employees
    WHERE salary > v_salary;
BEGIN
    FOR e IN c1 LOOP
        DBMS_OUTPUT.PUT_LINE(e.employee_id ||'-'||e.salary);
    END LOOP;
END;
/
EXEC e_proc(5000);