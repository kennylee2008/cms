-- 创建网站会员信息表
drop table if exists t_member; 
create table t_member(
	id integer not null auto_increment,
	name varchar(200),
	email varchar(50),
	nickName varchar(50),
	password varchar(20),
	qq varchar(20),
	phone varchar(20),
	address varchar(255),
	description longtext,
	primary key(id)
);
