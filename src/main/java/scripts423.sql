select student.name, student.age, student.faculty_id
from student
INNER JOIN public.faculty f on f.id = student.faculty_id;

select student.name, student.avatar_id, avatar.media_type
from student
INNER join avatar on student.avatar_id = avatar.id