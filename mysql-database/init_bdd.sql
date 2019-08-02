create table user (
    id int not null auto_increment,
    firstname VARCHAR(100) not null,
    lastname VARCHAR(100) not null,
    login VARCHAR(50) not null,
    password VARCHAR(200) not null,
    is_admin bool not null default false,
    primary key (id)
);

create table token (
  id int not null auto_increment,
  token_value VARCHAR(100) not null,
  primary key (id)
);


insert into user(firstname, lastname, login, password, is_admin) values ('Bilel', 'KHARBECHE', 'bilel', '$2a$10$.oqwnPxCdVRCHyGhrnaLb.pgqbLpDjrVswi9ORsksi1g/r9LvHBxO', true);
insert into user(firstname, lastname, login, password) values ('Olivier', 'LASBLEIS', 'olivier', '$2a$10$.oqwnPxCdVRCHyGhrnaLb.pgqbLpDjrVswi9ORsksi1g/r9LvHBxO');
insert into user(firstname, lastname, login, password) values ('Jean-baptiste', 'MERAND', 'jb', '$2a$10$.oqwnPxCdVRCHyGhrnaLb.pgqbLpDjrVswi9ORsksi1g/r9LvHBxO');
insert into user(firstname, lastname, login, password) values ('Cécile', 'PEYRAS', 'cecile', '$2a$10$.oqwnPxCdVRCHyGhrnaLb.pgqbLpDjrVswi9ORsksi1g/r9LvHBxO');
insert into user(firstname, lastname, login, password) values ('Guillaume', 'PIERRE', 'guillaume', '$2a$10$.oqwnPxCdVRCHyGhrnaLb.pgqbLpDjrVswi9ORsksi1g/r9LvHBxO');
insert into user(firstname, lastname, login, password) values ('Kévin', 'SEGUINEAU', 'kevin', '$2a$10$.oqwnPxCdVRCHyGhrnaLb.pgqbLpDjrVswi9ORsksi1g/r9LvHBxO');
insert into user(firstname, lastname, login, password) values ('Eloi', 'TURPIN', 'eloi', '$2a$10$.oqwnPxCdVRCHyGhrnaLb.pgqbLpDjrVswi9ORsksi1g/r9LvHBxO');

insert into token(token_value) values ('ceci est un secret inaccessible!!!');
