show databases;
use xmscode;
select * from xc_course
select * from xc_course limit 0,2;
desc xc_user;
use video02;
select * from xc_course_direction_content where cd_id=1;
select * from dept
select dept.*,emp.* from dept join emp on dept.deptno=emp.deptno where dept.deptno=1024
alter table xc_xc_course_direction_content rename to xc_course_direction_content;
select * from xc_course_content;
select * from xc_course_direction;
 select * from xc_course where direction_id=5 limit 0,10;
 
 select xc_course.* from xc_course join xc_course_direction_content on xc_course.direction_id
 =xc_course_direction_content.cd_id join xc_course_content on xc_course_direction_content.cc_id=xc_course_content.id
 where xc_course.direction_id=1;
 desc xc_course
  select xc_course_content.* from xc_course_content join xc_course_direction_content on xc_course_content.id
 =xc_course_direction_content.cc_id  where xc_course_direction_content.cd_id=1
 
 use test;
 show tables;
 	private Integer id;
	private String title;
	private String options;
	private String answer;
	private String type;
	create table question(
	id int primary key auto_increment,
	title varchar(100),
	options varchar(200),
	answer varchar(100),
	type char
	);
	insert into question values(null,'张','多','answer','M');
	insert into question values(null,'张','单','answer','S');
	insert into question values(null,'张','单','answer','T');
	
	
	
	select xc_item.c_id from xc_item join xc_car_item on xc_item.id=xc_car_item.i_id join xc_car on xc_car_item.c_id=xc_car.id where
xc_car.id=4
 select * from xc_item;
 show tables;
select * from xc_user
select * from xc_car
delete from xc_car;
delete from xc_car_item;
delete from xc_item
select * from xc_car_item
select xc_item.* from xc_item
select xc_item.* from xc_item join xc_car_item on xc_item.id=xc_car_item.i_id 
join xc_car on xc_car_item.c_id=xc_car.id where xc_car.u_id=1
show tables;
select count(*) from xc_course_direction_content where cc_id=1
select count(*) from xc_course where direction_id=2

 select * from xc_course_direction_content 
 where  cd_id=1
  
  and cc_id=#{contid}
 select * from xc_course
where 
 
content_id=4

 select count(*) from xc_course 
 <where>
 <if test="coudirid!=0">
  direction_id=#{coudirid}
  </if>
  <if test="contid!=0">
  and content_id=#{contid}
  
use xmscode;
  
  mysqldump -u root -p xmscode > xmscode.sql
  select * from xc_user;
  use test;
  show tables;
  create table today002(
  id int primary key auto_increment,
  vid int(4) 
  );
  create table toda(
  id int primary key auto_increment,
  ifg int(4) ,
  constraint kl foreign key(ifg) references today002(id)
  )charset=utf8;
  insert into today002 values(null,1025);
  insert into today002 values(null,1026);
  insert into today002 values(null,1024);
  select * from today002;
  select * from toda;
  insert into toda values(null,3);
  delete from today002 where id=3
  
  select * from xc_order;
  show tables;
  USE netctoss;
  
  
  
  