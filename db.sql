
CREATE TABLE users
( id number(20) not null,
  name varchar2(255 char) not null,
  user_name varchar2(255 char) not null ,
  email varchar2(255 char) not null,
  password varchar2(100 char) not null ,
  staffType varchar2(100 char) null,
  avatar varchar2(255 char) null,
  firstName varchar2(255 char) null,
  middleName varchar(255 char) null,
  lastName varchar2(255 char) null,
  phone varchar2(255 char) null,
  identityCard varchar2(255 char) null,
  homeTown varchar2(255 char) null,
  education varchar2(255 char) null,
  school varchar2(255 char) null,
  specialized varchar2(255 char) null,
  emailVerified number(1) null,
  dob TimesTamp(6) null,
  sex varchar(10) null,
  CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE SEQUENCE user_auto_id;
CREATE TRIGGER user_auto
    BEFORE INSERT ON USERS
    FOR EACH ROW
BEGIN
    SELECT user_auto_id.nextval
    INTO :new.id
    FROM dual;
END;

create table role
( id number(20) not null ,
  name varchar2(50 char) not null ,
  CONSTRAINT role_pk primary key (id)
);

CREATE SEQUENCE role_auto_id;
CREATE TRIGGER role_auto
    BEFORE INSERT ON ROLE
    FOR EACH ROW
BEGIN
    SELECT role_auto_id.nextval
    INTO :new.id
    FROM dual;
END;

create table user_role
(
    user_id number not null ,
    role_id number not null ,
);

insert into role(name) values ('ROLE_USER');
insert into role(name) values ('ROLE_PM');
insert into role(name) values ('ROLE_MANAGER');
insert into role(name) values ('ROLE_ADMIN');

create table timelog
( id number(20) not null ,
  time TimesTamp(6) null ,
  CONSTRAINT timelog_pk primary key (id)
);

CREATE SEQUENCE timelog_auto_id;
CREATE TRIGGER timelog_auto
    BEFORE INSERT ON timelog
    FOR EACH ROW
BEGIN
    SELECT timelog_auto_id.nextval
    INTO :new.id
    FROM dual;
END;

create table project
( id number(20) not null ,
  name varchar2(255 char) null ,
  description varchar2(255 char) null,
  status number(10) null,
  timeStart TimesTamp(6) null,
  timeEnd TimesTamp(6) null,
  CONSTRAINT project_pk primary key (id)
);

CREATE SEQUENCE project_auto_id;
CREATE TRIGGER project_auto
    BEFORE INSERT ON project
    FOR EACH ROW
BEGIN
    SELECT project_auto_id.nextval
    INTO :new.id
    FROM dual;
END;

create table issue
( id number(20) not null ,
  status number(10) null ,
  assignee number(10) null,
  CONSTRAINT issue_pk primary key (id)
);

CREATE SEQUENCE issue_auto_id;
CREATE TRIGGER issue_auto
    BEFORE INSERT ON issue
    FOR EACH ROW
BEGIN
    SELECT issue_auto_id.nextval
    INTO :new.id
    FROM dual;
END;

create table image
( id number(20) not null ,
  name varchar2(255 char) null ,
  thumbnail varchaar2(255 char) null,
  CONSTRAINT image_pk primary key (id)
);

CREATE SEQUENCE image_auto_id;
CREATE TRIGGER image_auto
    BEFORE INSERT ON image
    FOR EACH ROW
BEGIN
    SELECT image_auto_id.nextval
    INTO :new.id
    FROM dual;
END;

create table cftoken
( id number(20) not null ,
  confirmation_token varchar2(255 char) null ,
  created_date TimesTamp(6) null,
expiry_date TimesTamp(6) null,
  CONSTRAINT cftoken_pk primary key (id)
);

CREATE SEQUENCE cftoken_auto_id;
CREATE TRIGGER cftoken_auto
    BEFORE INSERT ON cftoken
    FOR EACH ROW
BEGIN
    SELECT cftoken_auto_id.nextval
    INTO :new.id
    FROM dual;
END;