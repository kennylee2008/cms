drop table if exists t_channel; 
create table t_channel(
	id integer not null auto_increment,
	name varchar(200),
	description longtext,
	primary key(id)
);

/*
��ʼ��һЩƵ��������԰����Լ�����Ը������Ƶ������Ϣ
*/
insert into t_channel (name) values ('JavaSE');
insert into t_channel (name) values ('JavaEE');
insert into t_channel (name) values ('JBPM');
insert into t_channel (name) values ('Android');
insert into t_channel (name) values ('OpenSources');
insert into t_channel (name) values ('���������');