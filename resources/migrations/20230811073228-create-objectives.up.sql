-- 目标
CREATE TABLE IF NOT EXISTS objectives(
    id bigserial NOT NULL PRIMARY KEY,
    name varchar(50) not null,
    description varchar(200),
    parent_id bigint, -- 上层目标编号
    sequence int not null,
    actived boolean not null default true,
    dept_id bigint, -- 目标所属部门/分组
    user_id bigint, -- 目标所属用户
    cycle_id bigint not null,
    created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);