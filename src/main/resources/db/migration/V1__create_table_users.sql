create table users(
	id uuid primary key,
	name varchar(40) not null,
	email varchar(60) not null unique,
	password varchar(30) not null,
	token_login varchar(50),
	created_at date not null default now(),
	updated_at timestamp not null default now()
);