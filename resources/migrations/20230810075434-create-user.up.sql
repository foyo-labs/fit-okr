-- 用户
CREATE TABLE IF NOT EXISTS users(
    id bigserial NOT NULL PRIMARY KEY,
    email varchar(20) not null,
    name varchar(50),
    password varchar(500) not null,
    actived boolean not null default false,
    created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);