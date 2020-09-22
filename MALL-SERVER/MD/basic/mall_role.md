-- 角色基础表

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `mall_role`;
CREATE TABLE `mall_role` (
  `id` varchar(50) NOT NULL,                    -- 主键ID
  `code` varchar(50) DEFAULT NULL,              -- 角色编码(全局唯一)
  `name` varchar(50) DEFAULT NULL,              -- 角色名称
  `sn` varchar(50) DEFAULT NULL,                -- 国际化
  `category_id` varchar(50) DEFAULT NULL,       -- 组别ID
  `category_name` varchar(50) DEFAULT NULL,     -- 组别名称
  `create_date` varchar(50) DEFAULT NULL,       -- 创建时间(标准字段)
  `create_by` varchar(50) DEFAULT NULL,         -- 创建人(标准字段)
  `modify_date` varchar(50) DEFAULT NULL,       -- 修改时间(标准字段)
  `modify_by` varchar(50) DEFAULT NULL,         -- 修改人(标准字段)
  `status` int(1) DEFAULT NULL,                 -- 状态(标准字段)
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `mall_role` VALUES ('111', '111', '管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `mall_role` VALUES ('222', '222', '数据员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;