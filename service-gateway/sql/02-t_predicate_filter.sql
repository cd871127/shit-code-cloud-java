create table IF NOT EXISTS service_gateway_db.t_route_accessory
(
    name        VARCHAR(128)                NOT NULL,
    args        VARCHAR(128),
    type        enum ('PREDICATE','FILTER') NOT NULL,
    id          varchar(36)                 NOT NULL COMMENT '路由id',
    unique_id   varchar(36) COMMENT '唯一键',
    version     INT UNSIGNED            NOT NULL DEFAULT 0 COMMENT '乐观锁版本',
    valid       TINYINT UNSIGNED        NOT NULL DEFAULT 0 COMMENT '有效标志位 0-无效,1-有效',
    create_by   VARCHAR(64)             NOT NULL COMMENT '创建人',
    create_time DATETIME                NOT NULL default now() COMMENT '创建时间',
    update_by   VARCHAR(64)             NOT NULL COMMENT '修改人',
    update_time DATETIME                NOT NULL default now() COMMENT '修改时间',
    foreign key (id) references service_gateway_db.t_route (id) ON DELETE CASCADE,
    INDEX (name),
    INDEX (id),
    UNIQUE (unique_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


DELIMITER //
CREATE TRIGGER service_gateway_db.t_route_accessory_before_insert
    BEFORE INSERT
    ON service_gateway_db.t_route_accessory
    FOR EACH ROW
begin
    if (new.unique_id = '' or new.unique_id is null) then
        set new.unique_id = uuid();
    end if;
end
//
CREATE TRIGGER service_gateway_db.t_route_accessory_before_update
    before UPDATE
    ON service_gateway_db.t_route_accessory
    FOR EACH ROW
begin
    set NEW.update_time = now(),NEW.version = OLD.version + 1;
end
//

DELIMITER ;