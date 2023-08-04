set foreign_key_checks = 0;
set foreign_key_checks = 1;

-- rbac------------------------------------------------------------
delete from rbac_tenant where true;
insert into rbac_tenant (id, tenant_name, other_desc, create_time, update_time)
values (1, 'admin', '平台租户', curdate(), curdate()),
       (2, 'team1', '测试租户1', curdate(), curdate()),
       (3, 'team2', '测试租户2', curdate(), curdate());

delete from rbac_user where true;
insert into rbac_user (id, user_name, user_passwd, user_email, user_tel, other_desc, create_time, update_time)
values (1, 'platform_admin','1', 'platform_admin@126.com',  '13124567890',  '平台管理员', curdate(), curdate()),
       (2, 'platform_op',   '1', 'platform_op@126.com',     '13124567890',  '平台运维', curdate(), curdate()),
       (3, 'tenant1_admin', '1', 'tenant1_admin@126.com',   '13124567890',  'team1租户管理员', curdate(), curdate()),
       (4, 'tenant1_op',    '1', 'tenant1_op@126.com',      '13124567890',  'team1租户运维', curdate(), curdate()),
       (5, 'app1_admin',    '1', 'app1_admin@126.com',      '13124567890',  '应用1管理员', curdate(), curdate()),
       (6, 'app1_op',       '1', 'app1_op@126.com',         '13124567890',  '应用1运维', curdate(), curdate()),
       (7, 'app2_admin',    '1', 'app2_admin@126.com',      '13124567890',  '应用2管理员', curdate(), curdate()),
       (8, 'app2_op',       '1', 'app2_op@126.com',         '13124567890',  '应用2运维', curdate(), curdate()),
       (9, 'user1',         '1', 'user1@126.com',           '13124567890',  'team1中作为应用1管理员,team2中作为应用3运维', curdate(), curdate()),
       (10, 'tenant2_admin','1', 'tenant2_admin@126.com',   '13124567890',  'team2租户管理员', curdate(), curdate()),
       (11, 'tenant2_op',   '1', 'tenant2_op@126.com',      '13124567890',  'team2租户运维', curdate(), curdate()),
       (12, 'app3_admin',   '1', 'app3_admin@126.com',      '13124567890',  '应用3管理员', curdate(), curdate()),
       (13, 'app3_op',      '1', 'app3_op@126.com',         '13124567890',  '应用3运维', curdate(), curdate()),
       (14, 'app4_admin',   '1', 'app4_admin@126.com',      '13124567890',  '应用4管理员', curdate(), curdate()),
       (15, 'app4_op',      '1', 'app4_op@126.com',         '13124567890',  '应用4运维', curdate(), curdate());

delete from rbac_role where true;
insert into rbac_role (id, role_name, other_desc, create_time, update_time)
values (1, 'platform_admin', '平台管理员', curdate() , curdate()),
       (2, 'platform_op', '平台运维', curdate(), curdate()),
       (3, 'tenant_admin', '租户管理员', curdate(), curdate()),
       (4, 'tenant_op', '租户运维', curdate(), curdate()),
       (5, 'app_admin', '应用管理员', curdate(), curdate()),
       (6, 'app_op', '应用运维', curdate(), curdate());

delete from rbac_user_role_tenant_relation where true;
insert into rbac_user_role_tenant_relation
    (user_id, role_id, tenant_id, other_desc, create_time, update_time)
values (1,      1,      1,     '平台管理员', curdate(), curdate()),
       (2,      2,      1,     '平台运维', curdate(), curdate()),
       (3,      3,      2,     '租户1管理员', curdate(), curdate()),
       (4,      4,      2,     '租户1运维', curdate(), curdate()),
       (5,      5,      2,     '应用1管理员', curdate(), curdate()),
       (6,      6,      2,     '应用1运维', curdate(), curdate()),
       (7,      5,      2,     '应用2管理员', curdate(), curdate()),
       (8,      6,      2,     '应用2运维', curdate(), curdate()),
       (9,      5,      2,     '应用1管理员', curdate(), curdate()),
       (9,      6,      3,     '应用3运维', curdate(), curdate()),
       (10,     3,      3,     '租户2管理员', curdate(), curdate()),
       (11,     4,      3,     '租户2运维', curdate(), curdate()),
       (12,     5,      3,     '应用3管理员', curdate(), curdate()),
       (13,     6,      3,     '应用3运维', curdate(), curdate()),
       (14,     5,      3,     '应用4管理员', curdate(), curdate()),
       (15,     6,      3,     '应用4运维', curdate(), curdate());

delete from rbac_auth where true;
insert into rbac_auth (id, auth_name, other_desc, create_time, update_time)
values (1, 'read',  '读权限',    curdate() , curdate()),
       (2, 'write', '读写权限',  curdate(), curdate());

delete from rbac_role_auth_relation where true;
insert into rbac_role_auth_relation
       (id, role_id, auth_id, other_desc, create_time, update_time)
values (1,      1,      2,     '平台管理员',  curdate(), curdate()),
       (2,      2,      1,     '平台运维',    curdate(), curdate()),
       (3,      3,      2,     '租户1管理员', curdate(), curdate()),
       (4,      4,      1,     '租户1运维',   curdate(), curdate()),
       (5,      5,      2,     '应用1管理员', curdate(), curdate()),
       (6,      6,      1,     '应用1管理员', curdate(), curdate());

delete from rbac_element where true;
insert into rbac_element
       (id, el_path, el_level, el_name, create_time, update_time)
values (1,  '1/',       1,     '读权限',    curdate() , curdate()),
       (2,  '1/2',      2,     '读写权限',  curdate(), curdate()),
       (3,  '1/2/3',    3,     '读写权限',  curdate(), curdate()),
       (4,  '1/4',      2,     '读写权限',  curdate(), curdate()),
       (5,  '1/4/5',    3,     '读写权限',  curdate(), curdate()),
       (6,  '6/',       1,     '读写权限',  curdate(), curdate()),
       (7,  '6/7',      2,     '读写权限',  curdate(), curdate()),
       (8,  '6/8',      2,     '读写权限',  curdate(), curdate());

delete from rbac_auth_element_relation where true;
insert into rbac_auth_element_relation
       (id, auth_id, element_id, other_desc, create_time, update_time)
values (1,      1,       1,       '用户管理',      curdate(), curdate()),
       (2,      2,       6,       '应用管理',      curdate(), curdate());

delete from rbac_interface where true;
insert into rbac_interface
       (id, if_path,     if_method,   other_desc, create_time, update_time)
values (1, '/rbac/user',   'get',   '用户管理',  curdate() ,  curdate()),
       (2, '/rbac/user',   'post',  '用户管理',   curdate(),   curdate()),
       (3, '/rbac/tenant', 'get',   '租户管理',   curdate(),   curdate()),
       (4, '/rbac/tenant', 'post',  '租户管理',   curdate(),   curdate()),
       (5, '/rbac/role',   'get',   '角色管理',   curdate(),   curdate()),
       (6, '/rbac/role',   'post',  '角色管理',   curdate(),   curdate()),
       (7, '/meta/app',    'get',   '应用管理',   curdate(),   curdate()),
       (8, '/meta/app',    'post',  '应用管理',   curdate(),   curdate());

delete from rbac_auth_interface_relation where true;
insert into rbac_auth_interface_relation
       (id, auth_id, interface_id, other_desc, create_time, update_time)
values (1,      1,       1,       '用户管理',      curdate(), curdate()),
       (2,      1,       3,       '租户管理',      curdate(), curdate()),
       (3,      1,       5,       '角色管理',      curdate(), curdate()),
       (4,      2,       2,       '用户管理',      curdate(), curdate()),
       (5,      2,       4,       '租户管理',      curdate(), curdate()),
       (6,      2,       6,       '角色管理',      curdate(), curdate()),
       (7,      1,       7,       '应用管理',      curdate(), curdate()),
       (8,      2,       8,       '应用管理',      curdate(), curdate());
-- metadata------------------------------------------------------------
delete from meta_app where true;
insert into meta_app
       (id, app_name, tenant_id, app_path, start_path, stop_path, secret_key, create_time, update_time)
values (1, 'app1',  '2', '/home/ap/tomcat', './bin/start.sh', './bin/stop.sh', 'secretKey', curdate(), curdate()),
       (2, 'app2',  '2', '/home/ap/tomcat', './bin/start.sh', './bin/stop.sh', 'secretKey', curdate(), curdate()),
       (3, 'app3',  '3', '/home/ap/tomcat', './bin/start.sh', './bin/stop.sh', 'secretKey', curdate(), curdate()),
       (4, 'app4',  '3', '/home/ap/tomcat', './bin/start.sh', './bin/stop.sh', 'secretKey', curdate(), curdate());

delete from meta_cluster where true;
insert into meta_cluster
       (id, cluster_name, app_id, tenant_id, create_time, update_time)
values (1,  'cluster-1',     1,         1,    curdate(), curdate()),
       (2,  'cluster-2',     1,         1,    curdate(), curdate()),
       (3,  'cluster-1',     2,         1,    curdate(), curdate()),
       (4,  'cluster-2',     2,         1,    curdate(), curdate()),
       (5,  'cluster-1',     3,         2,    curdate(), curdate()),
       (6,  'cluster-1',     4,         2,    curdate(), curdate()),
       (7,  'cluster-2',     4,         2,    curdate(), curdate()),
       (8,  'cluster-3',     4,         2,    curdate(), curdate());

delete from meta_tomcat where true;
insert into meta_tomcat
       (id, tomcat_version, create_time, update_time)
values (1, '9.0.72', curdate(), curdate()),
       (2, '9.0.76', curdate(), curdate()),
       (3, '9.0.79', curdate(), curdate());

delete from meta_baseline where true;
insert into meta_baseline
    (id, bl_version, app_id, tenant_id, parent_id, other_desc,create_time, update_time)
values (1,  '1.0.0',   0,        1,        0,     '平台基线',  curdate(), curdate()),
       (2,  '1.0.1',   0,        1,        1,     '平台基线',  curdate(), curdate()),
       (3,  '0.1',     0,        2,        1,     '租户基线',  curdate(), curdate()),
       (4,  '0.2',     0,        2,        3,     '租户基线',  curdate(), curdate()),
       (5,  '1.0',     1,        2,        3,     '应用基线',  curdate(), curdate()),
       (6,  '1.1',     1,        2,        5,     '应用基线',  curdate(), curdate()),
       (7,  '2.0',     2,        2,        4,     '应用基线',  curdate(), curdate()),
       (8,  '2.1',     2,        2,        4,     '应用基线',  curdate(), curdate()),
       (9,  '0.1',     0,        3,        1,     '租户基线',  curdate(), curdate()),
       (10, '0.3',     0,        3,        2,     '租户基线',  curdate(), curdate()),
       (11, '1.0',     3,        3,        10,    '应用基线',  curdate(), curdate()),
       (12, '1.1',     3,        3,        11,    '应用基线',  curdate(), curdate()),
       (13, '2.0',     4,        3,        2,     '应用基线',  curdate(), curdate()),
       (14, '2.1',     4,        3,        2,     '应用基线',  curdate(), curdate());

delete from meta_tomcat_baseline_relation where true;
insert into meta_tomcat_baseline_relation
       (id, tomcat_id, bl_id, other_desc, create_time, update_time)
values (1,      1,      1,    '适配9.0.72',             curdate(), curdate()),
       (2,      1,      3,    '适配9.0.72',             curdate(), curdate()),
       (3,      1,      5,    '适配9.0.72',             curdate(), curdate()),
       (4,      2,      4,    '适配9.0.76，与72参数兼容', curdate(), curdate()),
       (5,      2,      6,    '适配9.0.76，与72参数兼容', curdate(), curdate()),
       (6,      3,      2,    '适配9.0.76，与72参数兼容', curdate(), curdate()),
       (7,      3,      10,   '适配9.0.79，与76不兼容',   curdate(), curdate()),
       (8,      3,      11,   '适配9.0.79，与76不兼容',   curdate(), curdate());


delete from meta_baseline_detail where true;
insert into meta_baseline_detail
       (id, bl_id, config_details, create_time, update_time)
values (1,   1,          'admin',    curdate(), curdate()),
       (2,   2,          'admin',    curdate(), curdate()),
       (3,   3,          'team1',    curdate(), curdate()),
       (4,   4,          'team1',    curdate(), curdate()),
       (5,   5,          'team1',    curdate(), curdate()),
       (6,   6,          'team1',    curdate(), curdate()),
       (7,   7,          'team1',    curdate(), curdate()),
       (8,   8,          'team1',    curdate(), curdate()),
       (9,   9,          'team2',    curdate(), curdate()),
       (10,  10,         'team2',    curdate(), curdate()),
       (11,  11,         'team2',    curdate(), curdate()),
       (12,  12,         'team2',    curdate(), curdate()),
       (13,  13,         'team2',    curdate(), curdate()),
       (14,  14,         'team2',    curdate(), curdate());

delete from meta_agent where true;
insert into meta_agent
       (id, agent_version, create_time, update_time)
values (1, '1.0', curdate(), curdate()),
       (2, '2.0', curdate(), curdate());

delete from meta_agent_config where true;
insert into meta_agent_config
       (id, config_name, agent_id, parent_id, config_details, create_time, update_time)
values (1,  'v1.0',         1,      0,           '1.0',        curdate(), curdate()),
       (2,  'v2.0',         2,      1,           '2.0',        curdate(), curdate());

delete from meta_agent_config_relation where true;
insert into meta_agent_config_relation
       (id, agent_id, config_id, other_desc, create_time, update_time)
values (1,      1,       1,       'admin',    curdate(), curdate()),
       (2,      2,       2,       'admin',    curdate(), curdate());

-- manager------------------------------------------------------------
delete from mgr_inst where true;
insert into mgr_inst
       (id, inst_addr, inst_ip, inst_port, inst_path, inst_status, config_status, tenant_id, app_id, cluster_id, tomcat_id, bl_id, create_time, update_time)
values (1, '10.1.1.11:8080', '10.1.1.11', '8080', '/home/ap/agent', 0,       0,          1,    1,       1,           1,    6,     curdate(), curdate()),
       (2, '10.1.1.12:8080', '10.1.1.12', '8080', '/home/ap/agent', 0,       0,          1,    1,       2,           1,    6,     curdate(), curdate()),
       (3, '10.1.1.13:8080', '10.1.1.13', '8080', '/home/ap/agent', 0,       0,          1,    2,       3,           1,    8,     curdate(), curdate()),
       (4, '10.1.1.14:8080', '10.1.1.14', '8080', '/home/ap/agent', 0,       0,          1,    2,       4,           1,    8,     curdate(), curdate()),
       (5, '10.1.1.15:8080', '10.1.1.15', '8080', '/home/ap/agent', 0,       0,          2,    3,       5,           2,    12,    curdate(), curdate()),
       (6, '10.1.1.16:8080', '10.1.1.16', '8080', '/home/ap/agent', 0,       0,          2,    3,       6,           2,    12,    curdate(), curdate()),
       (7, '10.1.1.17:8080', '10.1.1.17', '8080', '/home/ap/agent', 0,       0,          2,    4,       7,           2,    14,    curdate(), curdate()),
       (8, '10.1.1.18:8080', '10.1.1.18', '8080', '/home/ap/agent', 0,       0,          2,    4,       7,           2,    14,    curdate(), curdate());


delete from mgr_agent where true;
insert into mgr_agent
       (id, agent_addr, agent_ip, agent_port, agent_path, agent_status, agent_version_id, agent_config_id, app_id, tenant_id, create_time, update_time)
values (1, '10.1.1.11:6666', '10.1.1.11', '6666', '/home/ap/agent', 0,       1,             1,              1,         1,        curdate(), curdate()),
       (2, '10.1.1.12:6666', '10.1.1.12', '6666', '/home/ap/agent', 0,       1,             1,              1,         1,        curdate(), curdate()),
       (3, '10.1.1.13:6666', '10.1.1.13', '6666', '/home/ap/agent', 0,       1,             1,              2,         1,        curdate(), curdate()),
       (4, '10.1.1.14:6666', '10.1.1.14', '6666', '/home/ap/agent', 0,       1,             1,              2,         1,        curdate(), curdate()),
       (5, '10.1.1.15:6666', '10.1.1.15', '6666', '/home/ap/agent', 0,       2,             1,              3,         2,        curdate(), curdate()),
       (6, '10.1.1.16:6666', '10.1.1.16', '6666', '/home/ap/agent', 0,       2,             1,              3,         2,        curdate(), curdate()),
       (7, '10.1.1.17:6666', '10.1.1.17', '6666', '/home/ap/agent', 0,       2,             1,              4,         2,        curdate(), curdate()),
       (8, '10.1.1.18:6666', '10.1.1.18', '6666', '/home/ap/agent', 0,       2,             1,              4,         2,        curdate(), curdate());

delete from mgr_task where true;
insert into mgr_task
       (id, task_name, task_type, batch_num, batch_total, tomcat_id, bl_id, agent_id, task_status, task_progress, app_id, tenant_id, create_time, update_time)
values (1,  'task-1',   'tomcat',   3,          3,          2,       null,   null,        1,        1,              1,          1,    curdate(),   curdate()),
       (2,  'task-2',   'baseline', 3,          3,          null,    2,      null,        1,        1,              2,          1,    curdate(),   curdate()),
       (3,  'task-3',   'agent',    3,          3,          null,    null,      2,        1,        1,              3,          2,    curdate(),   curdate());

delete from mgr_task_detail where true;
insert into mgr_task_detail
       (id, instance_addr, origin_version, target_version, upgrade_status, task_id, batch_num, create_time, update_time)
values (1,  '10.1.1.11:8080',    '9.0.72',   '9.0.76',          1,          1,        1,        curdate(),   curdate()),
       (2,  '10.1.1.12:8080',    '1.0.0',    '1.0.1',           1,          2,        1,        curdate(),   curdate()),
       (3,  '10.1.1.13:8080',    '1.0',      '2.0',             1,          3,        1,        curdate(),   curdate());