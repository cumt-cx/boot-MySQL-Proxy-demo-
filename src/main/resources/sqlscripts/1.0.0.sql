/*==============================================================*/
/* Table: tb_user                                               */
/*==============================================================*/
CREATE TABLE tb_user
(
  id          BIGINT      NOT NULL AUTO_INCREMENT
  COMMENT '用户',
  user_name   VARCHAR(50) NOT NULL DEFAULT ''
  COMMENT '用户名',
  pass_word   VARCHAR(50) NOT NULL DEFAULT ''
  COMMENT '用户密码',
  email       VARCHAR(50) NOT NULL DEFAULT ''
  COMMENT '职务',
  deleted     TINYINT     NOT NULL DEFAULT 0
  COMMENT '是否删除{0:否;1:是}',
  create_time TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  update_time TIMESTAMP   NOT NULL DEFAULT '2000-1-1'
  COMMENT '更新时间',
  read_from   VARCHAR(50) NOT NULL DEFAULT ''
  COMMENT 'readFrom',
  PRIMARY KEY (id)
);