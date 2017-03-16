create table employee (
    id integer(4) not null primary key,
    name varchar(20) not null,
    email varchar(30) not null,
    userName varchar(30) not null,
    age integer(3) not null check(age >= 18),
    gender varchar(6) check(gender in('male', 'female')),
    password varchar(16) not null
);

create table project(

    projectId integer(4) not null primary key,
    name varchar(20) not null,
    manager integer(4) not null,

    constraint fk_project_manager FOREIGN KEY (manager) references employee(id) on delete cascade on update cascade
);