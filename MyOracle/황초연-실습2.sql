--1. 급여가 4000보다 많은 사원들의 부서별 급여평균를 출력하시오. 단 급여평균은 소숫점이하2자리에서 반올림합니다.

SELECT department_id, ROUND(AVG(salary), 1)급여평균
FROM employees
WHERE salary > 4000    -- where절에서 걸러내야할건 먼저 필터링하고 그룹핑하는게 처리속도 더 빠름
GROUP BY department_id
ORDER BY AVG(salary);  -- "부서별"이어서 GROUP BY 사용해서 그룹핑

--2. 부서들의 부서번호, 평균급여, 최대급여, 최소급여를 출력하시오
--단 부서없는 사원과 30번부서사원, 50번부서사원은 제외한다

SELECT department_id, AVG(salary), MAX(salary), MIN(salary)
FROM employees
WHERE department_id NOT IN (30,50) AND department_id IS NOT NULL  
GROUP BY department_id;
-- department_id <> null AND department_id <> 30 OR department_id <>50
-- NOT IN 연산자는 <> 와 같아서 NOT IN과 null값 함께 사용하면 처리 결과는 한 행도 나오지 않음
--null인 경우에 0으로 바꿔서 사용하면 NOT IN과 사용 가능 -> WHERE NVL(department_id,0) NOT IN (0,30,50)

--3. 다음과 같은  20번부서 총급여, 50번부서 총급여, 80번부서 총급여, 90번부서 총급여, 전사원 총급여가  나타나도록 그룹함수와 DECODE함수를 사용하시오.

SELECT SUM(DECODE(department_id, 20, salary)) "20번부서 총급여", 
       SUM(DECODE(department_id, 50, salary)) "50번부서 총급여",
	   SUM(DECODE(department_id, 80, salary)) "80번부서 총급여",
	   SUM(DECODE(department_id, 90, salary)) "90번부서 총급여",
       SUM(salary) "전사원총급여"
FROM employees;

--SUM은 집계함수이고 DECODE는 일반함수 (일반함수는 각각의 결과값을 가져옴)
--SELECT SUM(DECODE(department_id, 20, salary),
--          (DECODE(department_id, 50, salary)
--FROM employees; --107
--SELECT salary
--FROM employees
--WHERE department_id IN(20,50) --20번 부서 행 수 + 50번 부서 행 수로 결과값 가져옴


--4.지역별 지역번호(location_id), 부서수를 출력하시오. 부서수가 10개 이상인 지역만 출력한다

SELECT location_id, COUNT(department_id)
FROM departments
GROUP BY location_id
HAVING COUNT(department_id) >= 10;

--5.년도별 입사자수를 출력하시오, 단 최근년도부터 출력한다

SELECT TO_CHAR(hire_Date, 'YYYY')입사년도, COUNT(*)입사자수
FROM employees
GROUP BY TO_CHAR(hire_Date, 'YYYY')
ORDER BY 1 DESC;

--6. 하반기(7~12월) 월별 입사자수를 출력하시오.입사자수가 5명이상인 경우만 출력한다

SELECT  TO_NUMBER(TO_CHAR(hire_Date, 'MM'))||'월', COUNT(*)||'명'
FROM employees
WHERE TO_NUMBER(TO_CHAR(hire_Date, 'MM'))>=7   
--TO CHAR로만 적용했을 때 문자형을 반환하면 7=>07이 되고 문자열과 '07'을 비교하는 것이 됨
-- 7말고 처음부터 '07'로 써도 괜찮음
GROUP BY TO_CHAR(hire_Date, 'MM')
--오라클에서 제공하는 함수 EXTRACT 사용할 수도 있음 ex) EXTRACT(year FROM hire_date)
HAVING COUNT (*) >=5
ORDER BY TO_NUMBER(TO_CHAR(hire_Date, 'MM'));





