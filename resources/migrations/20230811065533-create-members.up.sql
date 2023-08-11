-- 部门成员
CREATE TABLE IF NOT EXISTS members(
    id bigserial NOT NULL PRIMARY KEY,
    dept_id int not null,
    user_id int not null,
    created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);