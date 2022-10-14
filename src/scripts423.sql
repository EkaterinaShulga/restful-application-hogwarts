select student.name, student.age, faculty.name
from student
         full join faculty ON student.faculty = faculty.id;


select student.name, student.age
from avatar
         inner join student ON avatar.student_id = student.id;