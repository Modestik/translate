create table users
(
	"user" text
		constraint users_pk
			primary key,
	password text not null
);

create table words
(
    id            serial  not null
        constraint words_pk
            primary key,
    word          text    not null,
    transcription text,
    "user"        integer not null
);

create table phrases
(
	id serial not null
		constraint phrases_pk
			primary key,
	phrase text not null,
	"user" text not null
);

create table dictionary
(
	id serial not null
		constraint dictionary_pk
			primary key,
	text text not null,
	pos text,
	translate text not null
);

