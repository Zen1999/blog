create table comment
(
	id BIGINT auto_increment PRIMARY KEY,
	parent_id BIGINT not null,
	type INT not null,
	commentator BIGINT not null,
	gmt_create BIGINT not null,
	gmt_modified BIGINT not null,
	like_count BIGINT default 0
);
