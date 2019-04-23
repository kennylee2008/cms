create table t_article(
	id integer not null auto_increment,
	title varchar(200),
	content longtext,
	source varchar(255),
	createtime datetime,
	updatetime datetime,
	deploytime datetime,
	primary key(id)
);