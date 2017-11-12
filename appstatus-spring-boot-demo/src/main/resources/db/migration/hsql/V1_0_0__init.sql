SET DATABASE SQL SYNTAX PGS TRUE

create sequence seq_sample_table start with 1;

create table sample_table(
	id_sample_table bigint PRIMARY KEY default nextval('seq_sample_table'),
	text varchar(200) not null,
);