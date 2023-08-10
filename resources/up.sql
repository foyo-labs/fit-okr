create table if not exists objectives(
    id        serial       not null primary key,
    owner     integer      not null,
    name      varchar(100) not null,
    cycle     varchar(50)  not null,
    position  integer,
    status    integer      not null,
    parent    integer,
    created   timestamp default now(),
    updated   timestamp default now()
);

create table if not exists keyresults(
    id        serial       not null primary key,
    oid       integer      not null,
    name      varchar(100) not null,
    position  integer      not null,
    weight    numeric(10, 2) default 0.00,
    progress  numeric(10, 2) default 0.00,
    unit      varchar(50),
    svalue integer    not null default 0,
    tvalue integer   not null default 0,
    completion integer  default 0,
    created   timestamp default now(),
    updated   timestamp default now()
);

create table users
(
    id          serial  not null primary key,
    email       text not null,
    name        varchar(255),
    password    text not null,
    status      integer default 0,
    created     timestamp default now(),
    updated     timestamp default now()
);

create table groups
(
    id          serial  not null primary key,
    name        varchar(100) not null,
    parent      integer not null default 0,
    pos         integer not null default 0,
    code        varchar(100) not null,
    own         integer not null,
    created     timestamp default now(),
    updated     timestamp default now()
);

