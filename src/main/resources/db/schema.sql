create database if not exists tomin
    default character set utf8mb4
    default collate utf8mb4_general_ci;
use tomin;

#account相关表，适用于单数据源单数据库单表，通过租户id实现行隔离级别的表结构
#一个用户可以属于多个租户，用户在不同租户下，会配置成不同的角色
create table if not exists account_tenant_list
(
    id           int auto_increment primary key,
    tenant_name  varchar(30)          not null comment '用户密码',
    is_available tinyint(1) default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)          null comment '备注说明',
    create_time  datetime             not null comment '创建时间',
    update_time  datetime             not null comment '更新时间',
    constraint uindex__account_tenant_list__tenant_name unique (tenant_name)
) comment '租户用户关联列表';

create table if not exists account_user_list
(
    id           int auto_increment primary key,
    user_name    varchar(30)          not null comment '用户名称',
    user_passwd  varchar(30)          not null comment '用户密码',
    user_email   varchar(30)          not null comment '用户密码',
    user_tel     varchar(20)          not null comment '用户密码',
    is_available tinyint(1) default 1 not null comment '是否可用，默认可用',
    tenant_id    int                  not null comment '所属租户id',
    other_desc   varchar(30)          null comment '备注说明',
    create_time  datetime             not null comment '创建时间',
    update_time  datetime             not null comment '更新时间',
    constraint uindex__account_user_list__user_name_and_tenant_id
        unique (user_name, tenant_id),
    constraint fk__account_user_list__account_tenant_list_id
        foreign key (tenant_id) references account_tenant_list (id)
) comment '用户列表';

create table if not exists account_role_list
(
    id           tinyint auto_increment primary key,
    role_name    varchar(30)          not null comment '角色名称',
    is_available tinyint(1) default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)          null comment '备注说明',
    create_time  datetime             not null comment '创建时间',
    update_time  datetime             not null comment '更新时间',
    constraint uindex__account_role_list__role_name unique (role_name)
) comment '租户列表';

# 租户用户关联列表
# create table if not exists account_tenant_and_user
# (
#     id          int auto_increment primary key,
#     tenant_id         int      not null comment '租户id',
#     uid         int      not null comment '用户id',
#     other_desc  varchar(30)                   comment '备注说明',
#     create_time datetime not null comment '创建时间',
#     update_time datetime not null comment '更新时间',
#     constraint fk__account_tenant_user__account_tenant_list_id
#         foreign key (tid) references account_tenant_list (id),
#     constraint fk__account_tenant_user__account_user_list_id
#         foreign key (uid) references account_user_list (id)
# ) comment '租户用户关联列表';

create table if not exists account_user_and_role
(
    id          int auto_increment primary key,
    user_id     int      not null comment '用户id',
    role_id     tinyint  not null comment '角色id',
    other_desc  varchar(30) comment '备注说明',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间',
    constraint fk__account_user_role__account_user_list_id
        foreign key (user_id) references account_user_list (id),
    constraint fk__account_user_role__account_role_list_id
        foreign key (role_id) references account_role_list (id)
) comment '用户角色关联列表';

