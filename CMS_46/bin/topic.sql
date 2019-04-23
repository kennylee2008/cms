-- 创建网站专题信息表
drop table if exists t_topic; 
create table t_topic(
	id integer not null auto_increment,
	name varchar(200),
	primary key(id)
);
