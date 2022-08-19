SELECT employee_id, first_name, last_name,
            first_name ||'-'||last_name "이름-성"
FROM employees;


SELECT employee_id, salary,
            salary * 12
FROM employees;


SELECT employee_id,
            salary 기본급,
            salary + salary * commission_pct 실급여
FROM employees;


SELECT employee_id,
            salary 기본급,
            commission_pct 수당률,
            salary + salary * commission_pct 실급여
FROM employees;


SELECT employee_id,
            salary 기본급,
            commission_pct 수당률,
            salary + salary * commission_pct 실급여,
            salary + salary * NVL(commission_pct, 0)실급여2
FROM employees;


SELECT employee_id, salary
FROM employees


SELECT employee_id, salary
FROM employees
WHERE salary >= 3000; 

SELECT employee_id, salary
FROM employees
WHERE salary*12 >= 100000; 


SELECT employee_id,  first_name, last_name
FROM employees
WHERE first_name = 'William';

SELECT employee_id,  first_name, last_name
FROM employees
WHERE first_name <> 'William';

SELECT employee_id, first_name, department_id,salary
FROM employees
WHERE department_id (NOT IN (30, 70, 80)) AND salay NOT BETWEEN 10000 and 15000;


SELECT MOD(3,2)
FROM employees;

SELECT MOD(3,2)
FROM dual;

SELECT 3+2, 3-2, 3*2, 3/2,MOD(3,2) 
FROM dual;

SELECT ROUND(3/2) 
FROM dual;

SELECT ROUND(3/2) , ROUND(123.4567), ROUND(123.4567, 1)
FROM dual;

SELECT TRUNC(3/2), TRUNC(123.4567, 1)
FROM dual;


SELECT employee_id, salary
FROM employees
ORDER BY salary ASC;

SELECT employee_id, salary, commission_pct, salary*NVL(commission_pct, 0),
       salary+(salary*NVL(commission_pct, 0))
FROM employees
ORDER BY 4;

SELECT employee_id, salary, commission_pct, salary*NVL(commission_pct, 0),
       salary+(salary*NVL(commission_pct, 0))???
FROM employees
ORDER BY ???;

SELECT employee_id, salary, commission_pct, salary*NVL(commission_pct, 0),
       salary+(salary*NVL(commission_pct, 0)) ???
FROM employees
ORDER BY ??? DESC;

SELECT job_id, job_title, min_salary, max_salary, (min_salary+max_salary)/2
FROM jobs
ORDER BY 5 DESC;


SELECT employee_id, hire_date, SYSDATE - hire_Date
FROM employees
ORDER BY 3 DESC;

SELECT employee_id, hire_date, TRUNC(SYSDATE - hire_date, 1)||'?'????
FROM employees
ORDER BY 3 DESC;

SELECT employee_id, hire_date, salary, TRUNC(SYSDATE - hire_date, 1)||'?'????
FROM employees
ORDER BY 4 DESC;

SELECT employee_id, hire_date, salary, TRUNC(SYSDATE - hire_date, 1)||'?'????
FROM employees
ORDER BY 4 DESC, salary ASC; 


SELECT employee_id, first_name
FROM employees
WHERE first_name LIKE '%e%';


SELECT department_id
FROM employees;

SELECT DISTINCT department_id
FROM employees;

SELECT employee_id, department_id
FROM employees;

SELECT rownum, employee_id, salary
WHERE employees
ORDER BY salary;

SELECT DISTINCT department_id, employee_id
FROM employees;


SELECT rownum, employee_id
FROM employees;

SELECT rownum, employee_id
FROM employees
ORDER BY salary;

SELECT rownum, employee_id, salary
FROM employees
WHERE rownum <=10
ORDER BY salary DESC;

SELECT rownum, employee_id, salary
FROM employees
WHERE rownum BETWEEN 11 AND 20
ORDER BY salary DESC;


SELECT TRUNC(12345.12345, 2) FROM dual;
SELECT TRUNC(12345.12345, 0) FROM dual;
SELECT TRUNC(12345.12345, 0) FROM dual;
SELECT TRUNC(12345.12345, -1) FROM dual;
SELECT TRUNC(12345.12345, -2) FROM dual;


SELECT INSTR('가나다가나다', '나'), INSTR('가나다가나다', '마')FROM dual;
SELECT SUBSTR('가나다가나다', 2, 3) FROM dual;
SELECT LENGTH('가나다가나다'), LENGTHB('가나다가나다') FROM dual;
SELECT LPAD('ABC', 10, '*') FROM dual;
SELECT RPAD('ABC', 10, '*') FROM dual;
SELECT 'TEST' || LTRIM('   ABC') FROM dual;
SELECT 'bEGIN' || LTRIM('   ABC    ')|| 'END' FROM dual;
SELECT 'bEGIN' || TRIM('   ABC    ')|| 'END' FROM dual;

sELECT SYSDATE FROM dual;

SELECT SYSDATE, SYSDATE+1 내일날짜, SYSDATE+3-SYSDATE
FROM dual;

SELECT SYSDATE, SYSDATE+1 내일날짜, SYSDATE+3-SYSDATE, SYSDATE-1 어제날짜
FROM dual;

SELECT SYSDATE, SYSDATE+1 내일날짜, SYSDATE+3-SYSDATE, SYSDATE-1 어제날짜,
       SYSDATE -TO_DATE('22/04/18')수업일수
FROM dual;

SELECT * FROM employees WHERE department_id = '050';
SELECT * FROM employees WHERE hire_date = '04/07/18';

SELECT TO_DATE('22/02/28'),
           TO_DATE('2022/02/28'),
           TO_DATE('22-02-28'),
           TO_DATE('02/28/22' , 'mm/dd/yy'),
           SYSDATE,
           TO_CHAR(SYSDATE, 'yyyy-mm-dd AM HH:MI:SS'),
           TO_CHAR(SYSDATE, 'yyyy-mm-dd HH24:MI:SS')
FROM dual;

MONTHS_BETWEEN() : 개월수
SELECT MONTHS_BETWEEN(TO_DATE('2022/04/18'),SYSDATE),
       MONTHS_BETWEEN(SYSDATE,TO_DATE('22/04/18'))
FROM dual;

SELECT ADD_MONTHS(SYSDATE, 1)"한달 후 날짜",
       ADD_MONTHS(SYSDATE, -1)"한달 전 날짜",
       ADD_MONTHS(SYSDATE, -6)"6개월전 날짜"
FROM dual;

SELECT LAST_DAY(TO_DATE('22/02/01')),
       LAST_DAY('22/02/01')
FROM dual;

SELECT NEXT_DAY(TO_DATE('22/12/25'), '일요일'),
       NEXT_DAY('22/12/25', '일요일')
FROM dual;

SELECT TO_CHAR(SYSDATE, 'yy-mm-dd` am HH:MI:DD day')
FROM dual;

SELECT TO_NUMBER('1,234.5', '9,999.9')            FROM dual;
SELECT TO_NUMBER('1,234.5', '9,999.999')         FROM dual; 
SELECT TO_NUMBER('1,234.5', '9,999')               FROM dual; --X 자리수 모자람
SELECT TO_NUMBER('1,234,567.8', '9,999,999,999.9')  FROM dual;

SELECT TO_CHAR(1234.5, 'L99,999.9')                  FROM dual;


SELECT NVL(commission_pct, 0) FROM employees;
SELECT commission_pct, NVL2(commission_pct, '수당있음', '수당없음') FROM employees;

SELECT employee_id, commission_pct
FROM employees
WHERE commission_pct IS NOT NULL;

SELECT NULLIF(10,10) FROM dual;

SELECT DECODE(commission_pct, null, '수당없음', TO_CHAR(commission_pct))
FROM employees;

SELECT commission_pct,DECODE(TRUNC(commission_pct, 1), null, '수당없음', 0.1, 'A등급',
0.2, 'B등급', 0.3, 'C등급', 0.4, 'D등급', 0.5, 'E등급','F등급')
FROM employees;

SELECT commission_pct, CASE WHEN commission_pct IS NULL THEN '수당없음'
			               WHEN commission_pct >= 0.6 THEN 'F'
                           WHEN commission_pct >= 0.5 THEN 'E'
                           WHEN commission_pct >= 0.4 THEN 'D'
                           WHEN commission_pct >= 0.3 THEN 'C'
                           WHEN commission_pct >= 0.2 THEN 'B'
                           WHEN commission_pct >= 0.1 THEN 'A'
                       END
FROM employees;

SELECT SUM(commission_pct)
FROM employees;

SELECT SUM(commission_pct), COUNT(commission_pct), COUNT(*), AVG(commission_pct)  
FROM employees; 

SELECT SUM(commission_pct), COUNT(commission_pct), COUNT(*), AVG(commission_pct), 
	MIN(commission_pct), MAX(commission_pct)
FROM employees;


SELECT department_id, COUNT(*)  
FROM employees
GROUP BY department_id;

SELECT MAX(salary) , MIN(salary) , AVG(salary)
FROM employees
GROUP BY department_id;

SELECT department_id, MAX(salary) , MIN(salary) , AVG(salary)
FROM employees
GROUP BY department_id;

SELECT department_id, MAX(salary) , MIN(salary) , AVG(salary)
FROM employees
WHERE department_id IS NOT NULL
GROUP BY department_id;

SELECT department_id, AVG(salary), MAX(salary) , MIN(salary) 
FROM employees
WHERE department_id IN (30,50)
GROUP BY department_id;

SELECT department_id, AVG(salary), MAX(salary) , MIN(salary) 
FROM employees
WHERE AVG(salary) >= 10000     
GROUP BY department_id;

SELECT department_id, AVG(salary), MAX(salary) , MIN(salary)
FROM employees
GROUP BY department_id
HAVING AVG(salary) >= 10000;

SELECT department_id, job_id, AVG(salary)
FROM employees
GROUP BY department_id, job_id
ORDER BY AVG(salary) DESC;

SELECT department_id, job_id, AVG(salary)
FROM employees
GROUP BY department_id, job_id
ORDER BY department_id, AVG(salary) DESC;

SELECT department_id, job_id, AVG(salary), SUM(salary)
FROM employees
GROUP BY ROLLUP(department_id, job_id);

SELECT department_id, job_id, AVG(salary), SUM(salary)
FROM employees
GROUP BY CUBE(department_id, job_id)
ORDER BY department_id;

SELECT department_id 부서번호, 
           NVL2(job_id, ''||job_id, '소계') 직무번호,
           SUM(salary) 급여합
FROM employees
GROUP BY  ROLLUP(department_id, job_id);


SELECT employee_id, first_name, salary, department_id
FROM employees;
SELECT department_id, department_name
FROM departments;

SELECT employee_id, department_name
FROM employees, departments;

SELECT employee_id, department_name
FROM employees, departments
WHERE employees.department_id = departments.department_id;

SELECT employee_id, department_name
FROM employees JOIN departments ON(employees.department_id = departments.department_id);

SELECT employee_id, first_name, salary, 
       e.department_id,
       d.department_id,
       department_name
FROM employees e JOIN departments d ON (e.department_id = d.department_id)
ORDER BY employee_id;

SELECT employee_id, job_id, job_title
FROM employees NATURAL JOIN jobs;

SELECT employee_id, j.job_id, job_title
FROM employees e JOIN jobs j ON (e.job_id = j.job_id );

SELECT employee_id, job_id, job_title
FROM employees JOIN jobs USING(job_id);

SELECT employee_id, department_id, department_name
FROM employees NATURAL JOIN departments;

SELECT employee_id, department_id, department_name
FROM employees JOIN departments USING (department_id);

SELECT d.department_id, department_name, manager_id, first_name
FROM departments d JOIN employees e USING (manager_id);


SELECT d.department_id, l.city, c.country_name
FROM departments d JOIN locations l ON(d.location_id=l.location_id)
 		           JOIN countries c ON(l.country_id=c.country_id);

SELECT  e.employee_id, e.first_name, e.department_id, 
        d.department_name, 
        j.job_id, job_title
   FROM employees e JOIN departments d ON (e.department_id = d.department_id)
                    JOIN jobs j ON(e.job_id= j.job_id)
   WHERE job_title LIKE '%Manager%'
   ORDER BY 5, 4;
   
SELECT e.department_id, department_name, COUNT(e.employee_id), AVG(e.salary)
FROM departments d JOIN employees e ON(d.department_id = e.department_id)
GROUP BY e.department_id, d.department_name;
   