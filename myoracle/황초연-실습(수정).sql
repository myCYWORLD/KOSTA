--1. 급여가 10000이거나  14000인 사원들의 사번, 이름(first_name), 급여를 검색하시오.

SELECT employee_id, first_name, salary
FROM employees
WHERE salary = 10000 OR salary = 14000;
--WHERE salary IN(10000, 14000);

--2. first_name에 대소문자구별없이('e', 'E')를 포함하는 모든 사원을 검색하시오

SELECT first_name
FROM employees
WHERE UPPER (first_name)LIKE '%E%';
--WHERE first_name LIKE '%e%' OR first_name LIKE '%E%';
--WHERE INSTR(UPPER(first_name), 'E') >0;

--3. first_name이 'J’로 시작하고 n문자를 포함하는 사원의 사번, 이름을 검색하시오. 

SELECT employee_id, first_name
FROM employees
WHERE first_name LIKE 'J%' AND first_name LIKE '%n%';
-- WHERE first_name LIKE 'J%N%';

--4.사원의 사번, 이름, 관리자번호(manager_id),  관리자여부를 출력하시오.

SELECT employee_id, first_name, manager_id, 
       NVL2(manager_id, '관리자있음', '관리자없음')
--     DECODE(manager_id, null, 'X', 'O')"관리자 확인"
FROM employees
ORDER BY 3;

--5.급여가 10000이상인 사원의 사번, 부서번호, 이름, 급여, 수당을 출력하시오.단, 부서번호가 30번부서이거나 90번인 사원만 검색한다.

SELECT employee_id, department_id, first_name, salary, commission_pct
FROM employees
WHERE department_id IN(30, 90) AND salary >= 10000 ;

--6.급여가 10000이상인 사원의 사번, 부서번호, 이름, 급여, 수당을 출력하시오.단, 부서번호가 30번,60번, 90번인 부서는 제외하고 사원을 검색한다.

SELECT employee_id, department_id, first_name, salary, commission_pct
FROM employees
WHERE department_id NOT IN(30, 60, 90) AND salary >= 10000;


--7. 각 사원의 사번과 근무년수를 출력하시오. 단, 근무년수는 소숫점이하자리는 버리고 장기 근속자부터 출력하시오.

SELECT employee_id, TRUNC((SYSDATE - hire_Date)/365, 0)근무년수
--     TRUNC(MONTHS_BETEWWEN(SYSDATE, hire_Date)/12
FROM employees
ORDER BY 2 DESC;
