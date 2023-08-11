-- 部门/组
CREATE TABLE IF NOT EXISTS departments(
    id bigserial NOT NULL PRIMARY KEY,
    name varchar(20) not null,
    description varchar(100),
    parent bigint not null,
    position varchar(50) not null,
    code varchar(50) not null,
    ownid bigint not null,
    created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);