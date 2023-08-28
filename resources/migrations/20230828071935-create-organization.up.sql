-- 组织
CREATE TABLE IF NOT EXISTS organizations(
    id bigserial NOT NULL PRIMARY KEY,
    name varchar(20) not null,
    description varchar(100),
    domain varchar(50) not null,
    email varchar(30) not null,
    active varchar(50) not null default false,
    created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);