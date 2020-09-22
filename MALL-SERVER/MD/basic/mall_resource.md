-- 资源管理

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `mall_resource`;
CREATE TABLE `mall_resource` (
  `id` varchar(50) NOT NULL,                        -- 主键ID
  `default_icon` varchar(100) DEFAULT NULL,         -- 资源图标ICON
  `menu` int(11) DEFAULT NULL,                      -- 未使用
  `name` varchar(50) DEFAULT NULL,                  -- 资源名称
  `parent_id` varchar(50) DEFAULT NULL,             -- 父资源ID
  `permission` varchar(50) DEFAULT NULL,            -- 权限
  `url` varchar(100) DEFAULT NULL,                  -- 页面url
  `sorts` int(11) DEFAULT NULL,                     -- 排序
  `type` int(1) DEFAULT NULL,                       -- 类型(1 菜单 0 按钮)
  `create_date` varchar(50) DEFAULT NULL,           -- 创建时间(标准字段)
  `create_by` varchar(50) DEFAULT NULL,             -- 创建人(标准字段)
  `modify_date` varchar(50) DEFAULT NULL,           -- 修改时间(标准字段)
  `modify_by` varchar(50) DEFAULT NULL,             -- 修改人(标准字段)
  `status` int(1) DEFAULT NULL,                     -- 状态(标准字段)
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `mall_resource` VALUES ('0000000000', 'BarChartOutlined', NULL, 'dashbordar', '0', 'dashbordar', '/dashbordar', 1, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0100000000', 'SettingOutlined', NULL, '系统配置', '0', 'basic', '/basic', 1, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0100100000', 'MenuUnfoldOutlined', NULL, '资源管理', '0100000000', 'basic:resource', '/basic/resource', 2, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0100200000', 'SecurityScanOutlined', NULL, '参数管理', '0100000000', 'basic:dict', '/basic/dict', 1, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0100300000', 'TeamOutlined', NULL, '用户管理', '0100000000', 'basic:user', '/basic/user', 1, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0100400000', 'UserOutlined', NULL, '角色管理', '0100000000', 'approve:client:list', '/basic/role', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200000000', 'UsergroupAddOutlined', NULL, '客户管理', '0', 'account', '/account', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200100000', 'PlusCircleOutlined', NULL, '用户信息', '0200000000', 'account:info', '/account/info', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200200000', 'PlayCircleOutlined', NULL, '用户详情', '0200000000', 'account:detail', '/account/detail', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200300000', 'PlusCircleOutlined', NULL, '用户操作', '0200000000', 'account:make', '/account/make', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200300100', 'BorderOuterOutlined', NULL, '删除用户', '0200300000', 'account:delete', '/account/delete', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200300200', 'ZoomOutOutlined', NULL, '测试1', '0200300000', 'account:test1', '/account/test1', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200300300', 'PlayCircleOutlined', NULL, '测试 2', '0200300000', 'account:test2', '/account/test2', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200300400', 'PlayCircleOutlined', NULL, '测试3', '0200300000', 'account:test3', '/account/test3', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200300500', 'PlayCircleOutlined', NULL, '测试4', '0200300000', 'account:test4', '/account/test4', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200300600', 'BorderOuterOutlined', NULL, '测试5', '0200300000', 'account:test5', '/account/test5', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200300700', 'PlayCircleOutlined', NULL, '测试7', '0200300000', 'account:test6', '/account/test6', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200300800', 'PlusCircleOutlined', NULL, '测试8', '0200300000', 'account:test7', '/account/test7', NULL, 0, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200300900', 'PlayCircleOutlined', NULL, '测试9', '0200300000', 'account:test8', '/account/test8', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200301000', 'ZoomOutOutlined', NULL, '测试10', '0200300000', 'account:test9', '/account/test9', NULL, 0, NULL, NULL, NULL, NULL, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;