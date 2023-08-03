set foreign_key_checks = 0;
set foreign_key_checks = 1;

truncate table account_tenant_list;

insert into account_tenant_list (id, tenant_name, other_desc, create_time, update_time)
values (1, 'admin', '平台租户', curdate(), curdate()),
       (2, 'team1', '测试租户1', curdate(), curdate()),
       (3, 'team2', '测试租户2', curdate(), curdate());
-- ------------------------------------------------------------
truncate table account_user_list;

insert into account_user_list (id, user_name, user_passwd, user_email, user_tel, tenant_id, other_desc, create_time,
                               update_time)
values (1, 'platform_admin', '1', 'platform_admin@126.com', '13124567890', 1, '平台管理员', curdate(), curdate()),
       (2, 'platform_op', '1', 'platform_op@126.com', '13124567890', 1, '平台运维', curdate(), curdate()),
       (3, 'tenant1_admin', '1', 'tenant1_admin@126.com', '13124567890', 2, 'team1租户管理员', curdate(), curdate()),
       (4, 'tenant1_op', '1', 'tenant1_op@126.com', '13124567890', 2, 'team1租户运维', curdate(), curdate()),
       (5, 'app1_admin', '1', 'app1_admin@126.com', '13124567890', 2, '应用1管理员', curdate(), curdate()),
       (6, 'app1_op', '1', 'app1_op@126.com', '13124567890', 2, '应用1运维', curdate(), curdate()),
       (7, 'app2_admin', '1', 'app2_admin@126.com', '13124567890', 2, '应用2管理员', curdate(), curdate()),
       (8, 'app2_op', '1', 'app2_op@126.com', '13124567890', 2, '应用2运维', curdate(), curdate()),
       (9, 'user1', '1', 'user1@126.com', '13124567890', 2, 'team1中作为应用1管理员', curdate(), curdate()),
       (10, 'tenant2_admin', '1', 'tenant2_admin@126.com', '13124567890', 3, 'team2租户管理员', curdate(), curdate()),
       (11, 'tenant2_op', '1', 'tenant2_op@126.com', '13124567890', 3, 'team2租户运维', curdate(), curdate()),
       (12, 'app3_admin', '1', 'app3_admin@126.com', '13124567890', 3, '应用3管理员', curdate(), curdate()),
       (13, 'app3_op', '1', 'app3_op@126.com', '13124567890', 3, '应用3运维', curdate(), curdate()),
       (14, 'app4_admin', '1', 'app4_admin@126.com', '13124567890', 3, '应用4管理员', curdate(), curdate()),
       (15, 'app4_op', '1', 'app4_op@126.com', '13124567890', 3, '应用4运维', curdate(), curdate()),
       (16, 'user1', '1', 'user1@126.com', '13124567890', 3, 'team2中作为应用3管理员', curdate(), curdate());

-- ------------------------------------------------------------
truncate table account_role_list;

insert into account_role_list (id, role_name, other_desc, create_time, update_time)
values (1, 'platform_admin', '平台管理员', curdate() , curdate()),
       (2, 'platform_op', '平台运维', curdate(), curdate()),
       (3, 'tenant1_admin', '租户管理员', curdate(), curdate()),
       (4, 'tenant1_op', '租户运维', curdate(), curdate()),
       (5, 'app1_admin', '应用管理员', curdate(), curdate()),
       (6, 'app1_op', '应用运维', curdate(), curdate());

-- ------------------------------------------------------------
truncate table account_user_and_role;

insert into account_user_and_role (user_id, role_id, other_desc, create_time, update_time)
values (1, 1, '平台管理员', curdate(), curdate()),
       (2, 2, '平台运维', curdate(), curdate()),
       (3, 3, '租户1管理员', curdate(), curdate()),
       (4, 4, '租户1运维', curdate(), curdate()),
       (5, 5, '应用1管理员', curdate(), curdate()),
       (6, 6, '应用1运维', curdate(), curdate()),
       (7, 5, '应用2管理员', curdate(), curdate()),
       (8, 6, '应用2运维', curdate(), curdate()),
       (9, 5, '应用1管理员', curdate(), curdate()),
       (10, 3, '租户2管理员', curdate(), curdate()),
       (11, 4, '租户2运维', curdate(), curdate()),
       (12, 5, '应用3管理员', curdate(), curdate()),
       (13, 6, '应用3运维', curdate(), curdate()),
       (14, 5, '应用4管理员', curdate(), curdate()),
       (15, 6, '应用4运维', curdate(), curdate()),
       (16, 5, '应用3管理员', curdate(), curdate());
