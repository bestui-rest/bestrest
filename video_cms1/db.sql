
create table privilege(
	id int primary key auto_increment,
	name varchar(30)
)   ;

create table role_privilege(
	id int primary key auto_increment,
	role_id int,
	privilege_id int
)   ;


create table product(
	id int primary key auto_increment,
	course_id int,
	lore_id int,
	name varchar(30),
	description varchar(30),
	status char(1),
	price double(7,2),
	image varchar(30),
	video varchar(30),
	creater varchar(30),
	createtime datetime,
	mender varchar(30),
	modifytime datetime
)  ;





create table course(
	id int primary key auto_increment,
	name varchar(30)
)   ;


create table lore(
	id int primary key auto_increment,
	name varchar(30),
	course_id int
)   ;

create table product(
	id int primary key auto_increment,
	course_id int,
	lore_id int,
	name varchar(30),
	description varchar(30),
	status char(1),
	price double(7,2),
	image varchar(30),
	video varchar(30),
	creater varchar(30),
	createtime datetime,
	mender varchar(30),
	modifytime datetime
)
   ;




create table admin(
	id int primary key auto_increment,
	username varchar(30),
	pwd varchar(30),
	name varchar(30)
)   ;

create table role(
	id int primary key auto_increment,
	name varchar(30)
)   ;

create table admin_role(
	id int primary key auto_increment,
	admin_id int,
	role_id int
)   ;

create table user(
	id int primary key auto_increment,
	username varchar(30),
	email varchar(30),
	registtime  datetime
)   ;
insert into user values (null,"jack","1111111@qq.com",now());
insert into user values (null,"rose","2222222@qq.com",now());
insert into user values (null,"lucy","3333333@qq.com",now());

insert into privilege values (null,'管理员模块');
insert into privilege values (null,'角色管理模块');
insert into privilege values (null,'会员模块');
insert into privilege values (null,'视频查看模块');
insert into privilege values (null,'视频上传模块');

insert into role_privilege values(null,1,1);
insert into role_privilege values(null,1,4);

insert into course values(null,'JAVA');
insert into course values(null,'UID');

insert into lore values (null,'java基础',1);
insert into lore values (null,'java核心',1);
insert into lore values (null,'ajax',1);
insert into lore values (null,'视觉设计',2);
insert into lore values (null,'ps',2);

insert into admin values (null,'red','1234','小红');
insert into admin values (null,'black','1234','小黑');

insert into role values (null,'普通管理员');
insert into role values (null,'超级管理员');
insert into role values (null,'用户管理员');
insert into role values (null,'产品管理员');

insert into admin_role values (null,1,3);
insert into admin_role values (null,1,4);
insert into admin_role values (null,2,2);

commit;