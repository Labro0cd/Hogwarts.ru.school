select * from student;

select * from student s
where s.age<20 and s.age >10;

select s.name from student s

select * from student s
where name like '%i%';

select * from student s
where age<id;

select * from student s
order by age, name;

select f.name, f.id  from student as s, faculty  as f
where s.id = 1;