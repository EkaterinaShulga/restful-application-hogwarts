select *
from student
where age BETWEEN 11 and 14;


select name from student;


select * from student
where student.name LIKE '%e%';


SELECT * from student
where age < student.id;


select * from student
ORDER BY age;