/*==============================================================*/
/* Table: tb_user                                               */
/*==============================================================*/
create table tb_user
(
   id                   bigint not null auto_increment comment '用户',
   user_name            varchar(50) not null default '' comment '用户名',
   pass_word            varchar(50) not null default '' comment '用户密码',
   email                varchar(50) not null default '' comment '职务',
   deleted              tinyint not null default 0 comment '是否删除{0:否;1:是}',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp not null default '2000-1-1' comment '更新时间',
   read_from            varchar(50) not null default '' comment 'readFrom',
   primary key (id)
);