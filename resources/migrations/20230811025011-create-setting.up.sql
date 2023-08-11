CREATE TABLE IF NOT EXISTS settings(
    id bigserial NOT NULL PRIMARY KEY,
    label varchar(20) not null,
    content varchar(100) not null,
    dtype varchar(50) not null,
    section varchar(50) not null,
    created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);