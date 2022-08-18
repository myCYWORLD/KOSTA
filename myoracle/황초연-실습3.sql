--1. 급여가 4000 보다 많은 사원들의 부서별 급여평균를 출력하시오. 단 급여평균은
--소숫점이하 2 자리에서 반올림합니다.

SELECT d.department_id, ROUND(AVG(salary),1)
FROM departments d JOIN employees e ON (d.department_id = e.department_id)
WHERE salary > 4000
GROUP BY d.department_id;

--SELECT department_id, ROUND(AVG(salary),1)
--FROM employees
--WHERE salary > 4000
--GROUP BY department_id;

--2. 부서배치를 받지 않은 사원은 제외하고 부서별 급여평균을 출력하시오.단 급여평균은
--소숫점이하 2 자리에서 반올림합니다.

SELECT d.department_id, ROUND(AVG(salary), 1)
FROM departments d JOIN employees e ON (d.department_id = e.department_id)
GROUP BY d.department_id;

--SELECT department_id, ROUND(AVG(salary),1)
--FROM demployees
--WHERE department_id IS NOT NULL
--GROUP BY department_id;

--3. 부서배치를 받지 않은 사원은 제외하고 급여평균이 10000 이상인 부서별 급여평균을
--출력하시오.단 급여평균은 소숫점이하 2 자리에서 반올림합니다.

SELECT d.department_id 부서, ROUND(AVG(salary),1)급여평균
FROM departments d JOIN employees e ON (d.department_id = e.department_id)
WHERE salary > 10000
GROUP BY d.department_id;

SELECT department_id, ROUND(AVG(salary),1)
FROM emplyoees
WHERE department_id IS NOT NULL
GROUP BY department_id
HAVING AVG(salary)>=10000;

--4. 직책(job_title)이 'President'인 사원의 사번, 이름을 출력하시오: (NaturalJOIN 으로 표현)

SELECT employee_id, first_name, job_title
FROM employees NATURAL JOIN jobs 
WHERE job_title = 'President'; 

--5. 직책(job_title)이 'President'가 아닌 사원의 사번, 이름을 출력하시오(JOIN USING 으로 표현)
SELECT employee_id, first_name
FROM employees JOIN jobs USING(job_id)
WHERE job_title <> 'President'
ORDER BY 1;

--6. 직책(job_title)이 'President'가 아닌 사원의 사번, 이름을 출력하시오 (SubQuery 사용)

SELECT employee_id 사번, first_name 이름
FROM employees
WHERE job_id NOT IN(SELECT job_id FROM jobs WHERE job_title = 'President')
ORDER BY 1;

--7. 부서번호, 부서명, 부서별 급여총액과 평균급여를 출력하시오.
--부서없는 사원도 모두 출력하시오.
--단, 급여총액이 낮은 순으로 정렬하고,
--평균급여는 소수점 이하 1 자리까지 나타나도록 반올림해야 한다 (JOIN 사용)

SELECT e.department_id 부서번호, d.department_name 부서명, SUM(salary) 급여총액, ROUND(AVG(salary), 1)평균급여
FROM departments d Right OUTER JOIN employees e ON(d.department_id = e.department_id)
GROUP BY e.department_id,d.department_name
ORDER BY 3;

--8. 부서번호, 부서명, 부서별 급여총액과 평균급여를 출력하시오. (Scalar Query 사용)
--단, 급여총액이 낮은 순으로 정렬하고,
--평균급여는 소수점 이하 1 자리까지 나타나도록 반올림해야 한다.

SELECT department_id 부서번호, SUM(salary) 급여총액, ROUND(AVG(salary), 1) 평균급여, 
      (SELECT department_name
       FROM departments d
       WHERE d.department_id = e.department_id) 부서명
FROM employees e
GROUP BY department_id
ORDER BY 2;

--9. 'Seattle'과 'Toronto'도시에 근무하는
--사원들의 사번, 이름, 부서ID, 부서명을 출력하시오

SELECT e.employee_id 사번, e.first_name 이름, e.department_id 부서ID, d.department_name 부서명
FROM employees e, departments d
WHERE e.department_id IN(SELECT d.department_id FROM departments WHERE d.location_id 
                      IN(SELECT location_id FROM locations WHERE city = 'Seattle' or city = 'Toronto'));
                       
SELECT employee_id 사번, first_name 이름, e.department_id 부서ID, department_name 부서명
FROM employees e JOIN departments d ON(e.department_id = d.department_id)
                 JOIN locations l ON(d.location_id = l.location_id) 
WHERE city IN('Seattle', 'Toronto');

--10.성이 'Davies'인 사원과 같은 부서에 근무하는 사원들의 사번, 성, 이름을 출력하시오(SUBQUERY)

SELECT employee_id 사번, last_name 성, first_name 이름
FROM employees
WHERE department_id IN(SELECT department_id FROM employees 
                    WHERE last_name = 'Davies');
--AND last_name <> 'Davies'; 
-- and절을 넣어야 davies가 포함되지 않고 출력됨   
                    
--11.성이 'Davies'인 사원과 같은 부서에 근무하면서 부서평균급여보다
--많은 급여를 받는사원의 사번, 성, 이름, 급여를 출력하시오(SUBQUERY)   --13

SELECT employee_id 사번, last_name 성, first_name 이름, salary 급여 
FROM employees
WHERE department_id =(SELECT department_id FROM employees WHERE last_name = 'Davies')
                     AND salary > ANY(SELECT AVG(salary) FROM employees GROUP BY department_id)
ORDER BY salary;
