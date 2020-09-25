-- 资源管理

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `mall_resource`;
CREATE TABLE `mall_resource` (
  `id` varchar(50) NOT NULL,                        -- 主键ID
  `defaultIcon` varchar(100) DEFAULT NULL,         -- 资源图标ICON
  `menu` int(11) DEFAULT NULL,                      -- 未使用
  `name` varchar(50) DEFAULT NULL,                  -- 资源名称
  `parentId` varchar(50) DEFAULT NULL,             -- 父资源ID
  `permission` varchar(50) DEFAULT NULL,            -- 权限
  `url` varchar(100) DEFAULT NULL,                  -- 页面url
  `sorts` int(11) DEFAULT NULL,                     -- 排序
  `type` int(1) DEFAULT NULL,                       -- 类型(1 菜单 0 按钮)
  `createDate` varchar(50) DEFAULT NULL,           -- 创建时间(标准字段)
  `createBy` varchar(50) DEFAULT NULL,             -- 创建人(标准字段)
  `modifyDate` varchar(50) DEFAULT NULL,           -- 修改时间(标准字段)
  `modifyBy` varchar(50) DEFAULT NULL,             -- 修改人(标准字段)
  `status` int(1) DEFAULT NULL,                     -- 状态(标准字段)
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `mall_resource` VALUES ('0000000000', 'BarChartOutlined', NULL, 'dashbordar', '0', 'dashbordar', '/dashbordar', 1, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0100000000', 'SettingOutlined', NULL, '系统配置', '0', 'basic', '/basic', 1, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0100100000', 'MenuUnfoldOutlined', NULL, '资源管理', '0100000000', 'basic:resource', '/basic/resource', 2, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0100200000', 'SecurityScanOutlined', NULL, '参数管理', '0100000000', 'basic:dict', '/basic/dict', 1, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0100300000', 'TeamOutlined', NULL, '用户管理', '0100000000', 'basic:user', '/basic/user', 1, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0100400000', 'UserOutlined', NULL, '角色管理', '0100000000', 'approve:client', '/basic/role', NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0100500000', 'SettingOutlined', NULL, '页面配置', '0100000000', 'basic:template', '/basic/template', 1, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_resource` VALUES ('0200000000', 'UsergroupAddOutlined', NULL, '客户管理', '0', 'account', '/account', NULL, 1, NULL, NULL, NULL, NULL, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;