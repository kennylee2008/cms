drop table if exists t_attachment;
create table t_attachment(
	id integer not null auto_increment,
	articleId integer,	
	name varchar(200),
	contentType varchar(100),
	uploadTime datetime,
	primary key(id)
);
