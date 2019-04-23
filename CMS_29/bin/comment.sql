-- 创建文章评论留言表
drop table if exists t_comment; 
create table t_comment(
	id integer not null auto_increment,
	name varchar(200),
	email varchar(50),
	site varchar(200),
	articleId integer,
	memberId integer,
	content longtext,
	commentTime datetime,
	primary key(id)
);
