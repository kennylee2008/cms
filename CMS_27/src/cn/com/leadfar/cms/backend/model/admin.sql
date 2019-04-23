create table t_admin(
	id integer not null auto_increment,
	username varchar(255) not null unique,
	password varchar(255),
	primary key(id)
);