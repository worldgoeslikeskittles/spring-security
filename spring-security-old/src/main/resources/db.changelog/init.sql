create table users
(
    id       bigserial primary key not null,
    username varchar               not null,
    password varchar               not null,
    role     varchar               not null
);

create table clients
(
    id        bigserial primary key not null,
    phone     varchar               not null,
    full_name varchar               not null,
    user_id   bigserial             not null
);

create table employees
(
    id            bigserial primary key not null,
    phone         varchar               not null,
    work_hours    varchar               not null,
    employee_type varchar               not null,
    user_id       bigserial             not null,
    speciality    varchar,
    full_name     varchar               not null

);

create table visits
(
    id          bigserial primary key not null,
    client_id   bigserial             not null,
    doctor_id   bigserial             not null,
    visit_notes  varchar,
    visit_Time  timestamp not null
);


insert into users values (1, 'admin', 'admin', 'ADMIN');
insert into employees values (1,'some phone', 'some work hours', 'MANAGER', 1, null, 'some full name');

insert into users values (2, 'client', 'client', 'CLIENT');
insert into clients values (1, 'some phone for client', 'some client full name', 2);

insert into users values (3, 'doctor', 'doctor', 'DOCTOR');
insert into employees values (2,'some doctor phone', 'some work hours', 'DOCTOR', 3, 'some speciality', 'some full name');

insert into users values (4, 'manager', 'manager', 'MANAGER');
insert into employees values (3,'some phone', 'some work hours', 'MANAGER', 4, null, 'some full name');