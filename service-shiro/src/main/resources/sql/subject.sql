create table IF NOT EXISTS shirodb.subject_info
(
    id          INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    version     INT UNSIGNED             NOT NULL DEFAULT 0 COMMENT '乐观锁版本',
    valid       TINYINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '有效标志位 0-无效,1-有效',
    create_time DATETIME                 NOT NULL COMMENT '创建时间',
    update_time DATETIME                 NOT NULL COMMENT '修改时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

insert into shirodb.subject_info(version, valid, create_time, update_time) VALUE (0, 0, now(), now()), (1, 1, now(), now());

update shirodb.subject_info
set valid=1
where id = 1;
# alter table TOPIC_INFO
#     add constraint UK_TOPIC_INFO unique (TOPIC_URL);
#
# ALTER TABLE TOPIC_INFO
#     ADD INDEX TOPIC_STATUS_IDX (TOPIC_STATUS);