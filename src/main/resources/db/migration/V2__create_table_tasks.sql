create table tasks(
	id uuid primary key,
	title varchar(120) not null,
	description varchar(200),
	"date" date not null,
	favorite boolean,
	finished boolean,
	id_user uuid references users(id) not null,
	created_at date not null default now(),
	updated_at timestamp not null default now()
);