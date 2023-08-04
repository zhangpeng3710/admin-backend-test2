create database if not exists tomin
    default character set utf8mb4
    default collate utf8mb4_general_ci;
use tomin;

#ALTER TABLE table_name NOCHECK CONSTRAINT constraint_name;
#ALTER TABLE table_name CHECK CONSTRAINT constraint_name;

#account相关表，适用于单数据源单数据库单表，通过租户id实现行隔离级别的表结构
#一个用户可以属于多个租户，用户在不同租户下，会配置成不同的角色
create table if not exists rbac_tenant
(
    id           int2 unsigned auto_increment primary key,
    tenant_name  varchar(30)             not null comment '租户名称',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__rbac_tenant__tenant_name unique (tenant_name)
) comment '租户用户关联列表';

create table if not exists rbac_user
(
    id           int2 unsigned auto_increment primary key,
    user_name    varchar(30)             not null comment '用户名称',
    user_passwd  varchar(30)             not null comment '用户密码',
    user_email   varchar(30)             not null comment '用户密码',
    user_tel     varchar(20)             not null comment '用户密码',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__rbac_user__user_name_and_tenant_id
        unique (user_name)
) comment '用户列表';

create table if not exists rbac_role
(
    id           int1 unsigned auto_increment primary key,
    role_name    varchar(30)             not null comment '角色名称',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__rbac_role__role_name unique (role_name)
) comment '租户列表';

create table if not exists rbac_user_role_tenant_relation
(
    id          int2 unsigned auto_increment primary key,
    user_id     int2 unsigned not null comment '用户id',
    role_id     int1 unsigned not null comment '角色id',
    tenant_id   int2 unsigned not null comment '所属租户id',
    other_desc  varchar(30)   null comment '备注说明',
    create_time datetime      not null comment '创建时间',
    update_time datetime      not null comment '更新时间',
    constraint uk__rbac_user_role_tenant_relation__user_id_role_id
        unique (user_id, role_id, tenant_id),
    constraint fk__rbac_user_role_tenant_relation__rbac_user_id
        foreign key (user_id) references rbac_user (id),
    constraint fk__rbac_user_role_tenant_relation__rbac_role_id
        foreign key (role_id) references rbac_role (id),
    constraint fk__rbac_user_role_tenant_relation__rbac_tenant_id
        foreign key (tenant_id) references rbac_tenant (id)
) comment '用户角色关联列表';

create table if not exists rbac_auth
(
    id           int unsigned auto_increment primary key,
    auth_name    varchar(30)             null comment '权限名称',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__rbac_auth__auth_name unique (auth_name)
) comment '权限列表';

create table if not exists rbac_role_auth_relation
(
    id           int unsigned auto_increment primary key,
    role_id      int1 unsigned           not null comment '角色名称',
    auth_id      int unsigned            not null comment '权限名称',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__rbac_role_auth_relation__role_auth_id unique (role_id, auth_id),
    constraint fk__rbac_role_auth_relation__rbac_role_id
        foreign key (role_id) references rbac_role (id),
    constraint fk__rbac_role_auth_relation__rbac_auth_id
        foreign key (auth_id) references rbac_auth (id)
) comment '角色权限关联列表';

create table if not exists rbac_element
(
    id           int unsigned auto_increment primary key,
    el_path      varchar(30)             not null comment '继承路径',
    el_level     int1 unsigned default 1 not null comment '层级',
    el_name      varchar(30)             not null comment '页面元素名称',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__rbac_element__el_path unique (el_path)
) comment '页面元素列表';

create table if not exists rbac_auth_element_relation
(
    id           int unsigned auto_increment primary key,
    auth_id      int unsigned            not null comment '权限id',
    element_id   int unsigned            not null comment '页面元素id',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__rbac_auth_element_relation__auth_interface_id
        unique (auth_id, element_id),
    constraint fk__rbac_auth_element_relation__rbac_auth_id
        foreign key (auth_id) references rbac_auth (id),
    constraint fk__rbac_auth_element_relation__rbac_element_id
        foreign key (element_id) references rbac_element (id)
) comment '权限页面元素关联列表';

create table if not exists rbac_interface
(
    id           int unsigned auto_increment primary key,
    if_path      varchar(150)            not null comment '接口路径',
    if_method    varchar(150)            not null comment '接口方法',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__rbac_interface__path unique (if_path, if_method)
) comment '接口列表';

create table if not exists rbac_auth_interface_relation
(
    id           int unsigned auto_increment primary key,
    auth_id      int unsigned            not null comment '权限id',
    interface_id int unsigned            not null comment '接口id',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__rbac_auth_interface_relation__auth_interface_id
        unique (auth_id, interface_id),
    constraint fk__rbac_auth_interface_relation__rbac_role_id
        foreign key (auth_id) references rbac_auth (id),
    constraint fk__rbac_auth_interface_relation__rbac_auth_id
        foreign key (interface_id) references rbac_interface (id)
) comment '权限接口关联列表';
# ==============================元数据模块=============================================
create table if not exists meta_app
(
    id           int unsigned auto_increment primary key,
    app_name     varchar(30)             not null comment '应用名称',
    tenant_id    int2 unsigned           not null comment '所属租户id',
    app_path     varchar(30)             not null comment '应用存放路径',
    start_path   varchar(30)             not null comment '启动脚本路径',
    stop_path    varchar(30)             not null comment '停止脚本路径',
    secret_key   varchar(30)             not null comment '应用密钥',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__meta_app__app_name_tenant_id unique (app_name, tenant_id),
    constraint fk__meta_app__rbac_tenant_id
        foreign key (tenant_id) references rbac_tenant (id)
) comment '应用数据';

create table if not exists meta_cluster
(
    id           int unsigned auto_increment primary key,
    cluster_name varchar(30)             not null comment '集群名称',
    app_id       int unsigned            not null comment '所属应用名称',
    tenant_id    int2 unsigned           not null comment '所属租户id',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__meta_cluster__meta_cluster_name_app_tenant_id
        unique (app_id, tenant_id, cluster_name),
    constraint fk__meta_cluster__meta_app_id
        foreign key (app_id) references meta_app (id)
) comment '集群数据';

create table if not exists meta_tomcat
(
    id             int2 unsigned auto_increment primary key,
    tomcat_version varchar(30)             not null comment '版本',
    is_available   int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc     varchar(30)             null comment '备注说明',
    create_time    datetime                not null comment '创建时间',
    update_time    datetime                not null comment '更新时间',
    constraint uk__meta_tomcat__tomcat_version unique (tomcat_version)
) comment 'tomcat版本列表';

create table if not exists meta_baseline
(
    id           int2 unsigned auto_increment primary key,
    bl_version   varchar(30)             not null comment '基线版本',
    app_id       int unsigned            null comment '所属应用名称',
    tenant_id    int2 unsigned           not null comment '所属租户id',
    parent_id    int2 unsigned default 0 not null comment '父版本',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__meta_baseline__config_version_app_tenant_id
        unique (bl_version, app_id, tenant_id),
    constraint fk__meta_baseline__rbac_tenant_name
        foreign key (tenant_id) references rbac_tenant (id)
) comment 'tomcat配置版本';

create table meta_tomcat_baseline_relation
(
    id           int unsigned auto_increment primary key,
    tomcat_id    int2 unsigned           not null comment 'tomcat版本id',
    bl_id        int2 unsigned           not null comment '基线版本id',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__meta_tomcat_baseline_relation__tomcat_bl_id
        unique (tomcat_id, bl_id),
    constraint fk__meta_tomcat_baseline_relation__meta_tomcat_id
        foreign key (tomcat_id) references meta_tomcat (id),
    constraint fk__meta_tomcat_baseline_relation__meta_baseline_id
        foreign key (bl_id) references meta_baseline (id)
) comment 'tomcat和配置基线关联列表';

create table if not exists meta_baseline_detail
(
    id             int2 unsigned auto_increment primary key,
    bl_id          int2 unsigned not null comment '基线id',
    config_details text          not null comment '配置详情，待确认拆分',
    other_desc     varchar(30)   null comment '备注说明',
    create_time    datetime      not null comment '创建时间',
    update_time    datetime      not null comment '更新时间',
    constraint uk__meta_baseline_detail__bl_id
        unique (bl_id),
    constraint fk__meta_baseline_detail__meta_baseline_id
        foreign key (bl_id) references meta_baseline (id)
) comment 'tomcat版本详细列表';

create table if not exists meta_agent
(
    id            int2 unsigned auto_increment primary key,
    agent_version varchar(30)             not null comment '版本',
    is_available  int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc    varchar(30)             null comment '备注说明',
    create_time   datetime                not null comment '创建时间',
    update_time   datetime                not null comment '更新时间',
    constraint uk__meta_agent__agent_version unique (agent_version)
) comment 'agent版本列表';

create table if not exists meta_agent_config
(
    id             int2 unsigned auto_increment primary key,
    config_name    varchar(30)             not null comment '配置名称',
    agent_id       int2 unsigned           not null comment 'agent版本id',
    parent_id      int2 unsigned default 0 not null comment '父版本',
    is_available   int1 unsigned default 1 not null comment '是否可用，默认可用',
    config_details text                    not null comment '配置详情，待确认拆分',
    other_desc     varchar(30)             null comment '备注说明',
    create_time    datetime                not null comment '创建时间',
    update_time    datetime                not null comment '更新时间',
    constraint uk__meta_agent_config__config_name unique (config_name),
    constraint fk__meta_agent_config__meta_agent
        foreign key (agent_id) references meta_agent (id)
) comment 'agent配置列表';

create table meta_agent_config_relation
(
    id           int unsigned auto_increment primary key,
    agent_id     int2 unsigned           not null comment 'agent版本id',
    config_id    int2 unsigned           not null comment 'agent配置版本id',
    is_available int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc   varchar(30)             null comment '备注说明',
    create_time  datetime                not null comment '创建时间',
    update_time  datetime                not null comment '更新时间',
    constraint uk__meta_agent_config_relation__tomcat_bl_id
        unique (agent_id, config_id),
    constraint fk__meta_agent_config_relation__meta_agent_id
        foreign key (agent_id) references meta_agent (id),
    constraint fk__meta_agent_config_relation__meta_agent_config_id
        foreign key (config_id) references meta_agent_config (id)
) comment 'agent版本和agent配置关联列表';

# ==============================实例管理模块,基于meta_app============================
create table if not exists mgr_inst
(
    id            int8 unsigned auto_increment primary key,
    inst_addr     varchar(21)             not null comment '实例ip:port',
    inst_ip       varchar(15)             not null comment '实例ip',
    inst_port     varchar(5)              not null comment '实例port',
    inst_path     varchar(30)             not null comment '实例path',
    inst_status   int1 unsigned default 0 not null comment '实例状态',
    config_status int1 unsigned default 0 not null comment '实例配置状态',
    tenant_id     int2 unsigned           not null comment '所属租户id',
    app_id        int unsigned            not null comment '所属应用名称',
    cluster_id    int unsigned            null comment '所属集群id',
    tomcat_id     int2 unsigned           null comment '所属tomcat版本id',
    bl_id         int2 unsigned           null comment '所属基线版本id',
    is_available  int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc    varchar(30)             null comment '备注说明',
    create_time   datetime                not null comment '创建时间',
    update_time   datetime                not null comment '更新时间',
    constraint uk__mgr_inst__inst_address
        unique (inst_addr),
    constraint fk__mgr_inst__meta_app_id
        foreign key (app_id) references meta_app (id),
    constraint fk__mgr_inst__meta_cluster_id
        foreign key (cluster_id) references meta_cluster (id),
    constraint fk__mgr_inst__rbac_tenant_id
        foreign key (tenant_id) references rbac_tenant (id)
) comment '实例列表';

create table if not exists mgr_app_view
(
    id           int unsigned auto_increment primary key,
    app_name     varchar(30)            not null comment '应用名',
    tenant_name  varchar(30)            not null comment '租户名',
    cluster_num  int unsigned default 0 not null comment '集群数量',
    instance_num int unsigned default 0 not null comment '实例数量',
    other_desc   varchar(30)            null comment '备注说明',
    create_time  datetime               not null comment '创建时间',
    update_time  datetime               not null comment '更新时间',
    constraint uk__mgr_app_view__mgr_app_tenant_id unique (app_name, tenant_name)
) comment '应用管理展示列表';

create table if not exists mgr_cluster_view
(
    id           int unsigned auto_increment primary key,
    cluster_name varchar(30)   not null comment '集群名称',
    app_name     varchar(30)   not null comment '应用名称',
    tenant_name  varchar(30)   not null comment '租户名称',
    instance_num int8 unsigned not null comment '实例数量',
    other_desc   varchar(30)   null comment '备注说明',
    create_time  datetime      not null comment '创建时间',
    update_time  datetime      not null comment '更新时间',
    constraint uk__mgr_cluster_view__mgr_cluster_app_tenant_id
        unique (app_name, tenant_name, cluster_name)
) comment '集群管理展示列表';

create table if not exists mgr_agent
(
    id               int8 unsigned auto_increment primary key,
    agent_addr       varchar(30)             not null comment '代理地址ip:port',
    agent_ip         varchar(15)             not null comment '代理ip',
    agent_port       varchar(5)              not null comment '代理ip',
    agent_path       varchar(30)             not null comment '代理ip',
    agent_status     int1 unsigned default 0 not null comment '代理状态',
    agent_version_id int2 unsigned           not null comment '代理版本id',
    agent_config_id  int2 unsigned           not null comment '代理版本id',
    app_id           int unsigned            not null comment '应用元数据id',
    tenant_id        int2 unsigned           not null comment '所属租户id',
    is_available     int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc       varchar(30)             null comment '备注说明',
    create_time      datetime                not null comment '创建时间',
    update_time      datetime                not null comment '更新时间',
    constraint uk__mgr_agent_view__agent_address unique (agent_addr),
    constraint fk__mgr_agent_view__meta_app_id
        foreign key (app_id) references meta_app (id),
    constraint fk__mgr_agent_view__rbac_tenant_id
        foreign key (tenant_id) references rbac_tenant (id)
) comment 'agent管理列表';

# ==============================任务管理模块=============================================
create table if not exists mgr_task
(
    id            int unsigned auto_increment primary key,
    task_name     varchar(30)             not null comment '任务名称',
    task_type     varchar(15)             not null comment '任务类型',
    batch_num     int1 unsigned default 1 not null comment '当前任务批次',
    batch_total   int1 unsigned default 1 not null comment '任务批次总数',
    tomcat_id     int2 unsigned           null comment '目标tomcat版本id',
    bl_id         int2 unsigned           null comment '目标基线版本id',
    agent_id      int2 unsigned           null comment '目标代理版本id',
    task_status   int1 unsigned default 0 not null comment '任务状态',
    task_progress int1 unsigned default 0 not null comment '任务状态',
    app_id        int unsigned            null comment '应用元数据id',
    tenant_id     int2 unsigned           not null comment '所属租户id',
    is_available  int1 unsigned default 1 not null comment '是否可用，默认可用',
    other_desc    varchar(30)             null comment '备注说明',
    create_time   datetime                not null comment '创建时间',
    update_time   datetime                not null comment '更新时间',
    constraint uk__mgr_task__task_name_type unique (task_name, task_type),
    constraint fk__mgr_task__meta_tomcat_id
        foreign key (tomcat_id) references meta_tomcat (id),
    constraint fk__mgr_task__meta_baseline_id
        foreign key (bl_id) references meta_baseline (id),
    constraint fk__mgr_task__meta_agent_id
        foreign key (agent_id) references meta_agent (id),
    constraint fk__mgr_task__rbac_tenant_id
        foreign key (tenant_id) references rbac_tenant (id),
    constraint fk__mgr_task__meta_app_id
        foreign key (app_id) references meta_app (id)
) comment '任务列表';

create table if not exists mgr_task_detail
(
    id             int2 unsigned auto_increment primary key,
    instance_addr  varchar(21)             not null comment '实例ip:port',
    origin_version varchar(30)             not null comment '原版本',
    target_version varchar(30)             not null comment '目标版本',
    upgrade_status int1 unsigned default 0 not null comment '实例升级状态',
    task_id        int unsigned            not null comment '任务名称',
    batch_num      int1 unsigned default 1 not null comment '任务批次',
    other_desc     varchar(30)             null comment '备注说明',
    create_time    datetime                not null comment '创建时间',
    update_time    datetime                not null comment '更新时间',
    constraint fk__mgr_task_detail__mgr_instance_ip_port
        foreign key (instance_addr) references mgr_inst (inst_addr),
    constraint fk__mgr_task_detail__mgr_task_id
        foreign key (task_id) references mgr_task (id)
) comment '任务详情列表';

# ==============================系统管理模块=============================================
create table if not exists sys_audit_login
(
    id          int8 unsigned auto_increment primary key,
    user_name   varchar(30)             not null comment '用户名',
    tenant_name varchar(30)             not null comment '租户',
    session_id  varchar(30)             not null comment '会话ID',
    client_ip   varchar(15)             not null comment '客户端ip',
    login_time  datetime                not null comment '登陆时间',
    logout_time datetime                null comment '退出时间',
    is_online   int1 unsigned default 1 not null comment '是否在线，登陆默认在线',
    other_desc  varchar(30)             null comment '备注说明',
    create_time datetime                not null comment '创建时间',
    update_time datetime                not null comment '更新时间',
    constraint fk__sys_audit_login__rbac_tenant_name
        foreign key (tenant_name) references rbac_tenant (tenant_name),
    constraint fk__sys_audit_login__rbac_user
        foreign key (user_name) references rbac_user (user_name)
) comment '登陆日志';

create table if not exists sys_audit_access
(
    id            int8 unsigned auto_increment primary key,
    user_name     varchar(30) not null comment '用户名',
    tenant_name   varchar(30) not null comment '租户',
    access_url    varchar(30) not null comment '访问url',
    client_ip     varchar(15) not null comment '客户端ip',
    access_time   datetime    not null comment '访问时间',
    response_time varchar(15) not null comment '响应时间',
    response_code varchar(15) not null comment '响应状态码',
    other_desc    varchar(30) null comment '备注说明',
    create_time   datetime    not null comment '创建时间',
    update_time   datetime    not null comment '更新时间',
    constraint fk__sys_audit_access__rbac_tenant_name
        foreign key (tenant_name) references rbac_tenant (tenant_name),
    constraint fk__sys_audit_access__rbac_user
        foreign key (user_name) references rbac_user (user_name)
) comment '访问日志';

create table if not exists sys_audit_data
(
    id            int8 unsigned auto_increment primary key,
    user_name     varchar(30) not null comment '用户名',
    tenant_name   varchar(30) not null comment '租户',
    access_module varchar(30) not null comment '访问模块',
    access_url    varchar(30) not null comment '访问url',
    client_ip     varchar(15) not null comment '客户端ip',
    access_time   datetime    not null comment '访问时间',
    data_origin   text        not null comment '原数据',
    data_updated  text        not null comment '修改后数据',
    response_time varchar(15) not null comment '响应时间',
    response_code varchar(15) not null comment '响应状态码',
    other_desc    varchar(30) null comment '备注说明',
    create_time   datetime    not null comment '创建时间',
    update_time   datetime    not null comment '更新时间',
    constraint fk__sys_audit_data__rbac_tenant_name
        foreign key (tenant_name) references rbac_tenant (tenant_name),
    constraint fk__sys_audit_data__rbac_user
        foreign key (user_name) references rbac_user (user_name)
) comment '数据变更日志';

create table if not exists sys_message
(
    id              int8 unsigned auto_increment primary key,
    message_name    varchar(30)             not null comment '消息标题',
    message_content varchar(30)             not null comment '消息内容',
    message_type    varchar(30)             not null comment '消息类型',
    message_status  varchar(30)             not null comment '消息状态',
    message_origin  varchar(30)             not null comment '消息来源',
    message_time    datetime                not null comment '消息接收时间',
    is_available    int1 unsigned default 1 not null comment '是否可用',
    other_desc      varchar(30)             null comment '备注说明',
    create_time     datetime                not null comment '创建时间',
    update_time     datetime                not null comment '更新时间'
) comment '消息记录';

