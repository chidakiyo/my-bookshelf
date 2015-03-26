drop table if exists book;
create sequence book_id_seq start with 1;
create table book (
  id bigint not null default nextval('book_id_seq') primary key,
  name varchar(255) not null,
  isbn varchar(25) not null,
  created_timestamp timestamp not null
);

insert into book (name, isbn, created_timestamp) values ('Book1', 'B1234', current_timestamp);
insert into book (name, isbn, created_timestamp) values ('Book2', 'B5678', current_timestamp);
