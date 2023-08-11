-- 关键结果
CREATE TABLE IF NOT EXISTS key_results(
    id bigserial NOT NULL PRIMARY KEY,
    name varchar(50) not null,
    description varchar(200),
    position bigint not null,
    weight numeric(12,2) not null default 0.00, --　权重
    objective_id bigint not null,
    start_value numeric(12,2) not null default 0.00, 
    target_value numeric(12,2) not null default 0.00,
    unit varchar(10) not null,
    created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);