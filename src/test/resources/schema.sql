--drop table if exists employee;

create table if not exists employee (
    id integer(4) not null ,
    name varchar(20) not null,
    email varchar(30) not null primary key,
    userName varchar(30) not null,
    age integer(3) not null,
    gender varchar(6) ,
    password varchar(16) not null
);

drop table if exists project;

create table if not exists project(

    empId integer(4) not null,
    name varchar(30) not null

);
