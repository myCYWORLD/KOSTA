SELECT employee_id, first_name, department_id, salary
FROM employees
WHERE salary >= 10000 AND salary<=15000;

SELECT employee_id, first_name, department_id, salary
FROM employees
WHERE salary BETWEEN 10000 AND 15000


SELECT employee_id, first_name, department_id, salary
FROM employees
WHERE salary >= 10000 AND salary<=15000;

SELECT employee_id, first_name, department_id, salary
FROM employees
WHERE salary BETWEEN 10000 AND 15000;


SELECT employee_id, first_name,department_id, salary
FROM employees
WHERE department_id=30 OR department_id=70 OR department_id=80;

SELECT employee_id, first_name, department_id, salary
FROM employees
WHERE department_id IN(30,70,80);

SELECT employee_id, first_name, department_id, salary
FROM employees
WHERE (department_id=30 OR department_id=70 OR department_id=80) AND salary >=10000 AND salary<= 15000;

