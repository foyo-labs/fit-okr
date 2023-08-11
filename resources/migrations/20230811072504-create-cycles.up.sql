-- 周期
CREATE TABLE IF NOT EXISTS cycles(
    id bigserial NOT NULL PRIMARY KEY,
    name varchar(50) not null,
    description varchar(200),
    start_date timestamp without time zone NOT NULL,
    end_date timestamp without time zone NOT NULL,
    period varchar(10) not null default 'Y', -- Y/Q/M/W/C
    created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);