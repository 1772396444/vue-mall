-- 用户基础表

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `mall_user_role`;
CREATE TABLE `mall_user_role` (
  `id` varchar(50) NOT NULL,                    -- 主键ID
  `role_id` varchar(50) DEFAULT NULL,           -- 角色ID
  `user_id` varchar(50) DEFAULT NULL,           -- 用户ID
  `create_date` varchar(50) DEFAULT NULL,       -- 创建时间(标准字段)
  `create_by` varchar(50) DEFAULT NULL,         -- 创建人(标准字段)
  `modify_date` varchar(50) DEFAULT NULL,       -- 修改时间(标准字段)
  `modify_by` varchar(50) DEFAULT NULL,         -- 修改人(标准字段)
  `status` int(1) DEFAULT NULL,                 -- 状态(标准字段)
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `mall_user_role` VALUES ('123', '111', '123', NULL, NULL, NULL, NULL, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;