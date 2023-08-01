
create table r_tenant_user
(
    id          int auto_increment
        primary key,
    tid         int      not null comment '租户id',
    uid         int      not null comment '用户id',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间',
    constraint r_tenant_user_r_tenant_list_id_fk
        foreign key (tid) references r_tenant_list (id),
    constraint r_tenant_user_r_user_list_id_fk
        foreign key (uid) references r_user_list (id)
)
    comment '租户用户关联列表';

create table r_user_list
(
    id           int auto_increment
        primary key,
    user_name    varchar(30)          not null comment '用户名称',
    user_passwd  varchar(30)          not null comment '用户密码',
    user_email   varchar(30)          not null comment '用户密码',
    user_tel     varchar(20)          not null comment '用户密码',
    is_available tinyint(1) default 1 not null comment '是否可用，默认可用',
    create_time  datetime             not null comment '创建时间',
    update_time  datetime             not null comment '更新时间',
    constraint user_list_tenant_name_uindex
        unique (user_name)
)
    comment '用户列表';

create table r_tenant_user
(
    id          int auto_increment
        primary key,
    tid         int      not null comment '租户id',
    uid         int      not null comment '用户id',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间',
    constraint r_tenant_user_r_tenant_list_id_fk
        foreign key (tid) references r_tenant_list (id),
    constraint r_tenant_user_r_user_list_id_fk
        foreign key (uid) references r_user_list (id)
)
    comment '租户用户关联列表';

create table r_role_list
(
    id           tinyint auto_increment
        primary key,
    role_name    varchar(30)          not null comment '角色名称',
    is_available tinyint(1) default 1 not null comment '是否可用，默认可用',
    create_time  datetime             not null comment '创建时间',
    update_time  datetime             not null comment '更新时间',
    constraint role_list_tenant_name_uindex
        unique (role_name)
)
    comment '租户列表';

create table r_user_role
(
    id          int auto_increment
        primary key,
    uid         int      not null comment '用户id',
    rid         tinyint  not null comment '角色id',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间',
    constraint r_user_role_r_role_list_id_fk
        foreign key (rid) references r_role_list (id),
    constraint r_user_role_r_user_list_id_fk
        foreign key (uid) references r_user_list (id)
)
    comment '用户角色关联列表';
