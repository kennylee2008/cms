drop table if exists t_channel; 
create table t_channel(
	id integer not null auto_increment,
	name varchar(200),
	description longtext,
	primary key(id)
);

/*
初始化一些频道，你可以按照自己的意愿来创建频道的信息
*/
insert into t_channel (name) values ('JavaSE');
insert into t_channel (name) values ('JavaEE');
insert into t_channel (name) values ('JBPM');
insert into t_channel (name) values ('Android');
insert into t_channel (name) values ('OpenSources');
insert into t_channel (name) values ('分析与设计');