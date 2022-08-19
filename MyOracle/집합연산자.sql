--집합연산자
--이전직무정보
SELECT *
FROM job_history
ORDER BY employee_id, start_date;
--102 01/01/13/ 06/07/24 IT_PROG 60

--현재직무정보
SELECT employee_id, job_id
FROM employees
ORDER BY employee_id;
-- 102 AD_VP

--사원의 이전 직무와 현재 직무를 모두 출력하시오 --115건 출력

--첫번째 컬럼 테이블 자료형과 두번째 컬럼 자료형이 같아야함
SELECT employee_id, job_id
FROM job_history
UNION
SELECT employee_id, job_id
FROM employees;

--사원의 이전직무와 현재직무를 모두 출력하시오 --117건 출력
SELECT employee_id, job_id
FROM job_history
UNION ALL
SELECT employee_id, job_id
FROM employees;

--첫번째 SELECT절 두번째 SELECT의 컬럼 갯수 같아야함
SELECT employee_id, job_id, start_date, end_date
FROM job_history
UNION ALL
SELECT employee_id, job_id, null, null
FROM employees
ORDER BY 사번, start_date;
--ORDER BY 구문 컬럼명 같게
SELECT employee_id 사번, job_id, start_date, end_date
FROM job_history
UNION ALL
SELECT employee_id, job_id, null, null
FROM employees
ORDER BY 사번, start_date;
--첫번째 두번째 컬럼 자료형 동일하게
SELECT employee_id 사번, job_id, TO_CHAR(start_date), end_date
FROM job_history
UNION ALL
SELECT employee_id, job_id, '현재직무', null
FROM employees
ORDER BY 사번, 3;

--이전 직무를 현재직무로 담당하는 사원들을 출력하시오
--교집합
SELECT employee_id, job_id
FROM employees
INTERSECT
SELECT employee_id, job_id
FROM job_history;

--입사 후 직무가 변경되지 않은 사원들을 출력하시오
--차집합
SELECT employee_id, job_id
FROM employees
MINUS
SELECT employee_id, job_id
FROM job_history;
--사원이 없는 부서번호를 출력하시오
SELECT department_id
FROM departments
MINUS
SELECT department_id
FROM employees;
--subquery
SELECT * 
FROM departments
WHERE NOT EXISTS
(SELECT * FROM employees
 WHERE department_id = departments.department_id);

