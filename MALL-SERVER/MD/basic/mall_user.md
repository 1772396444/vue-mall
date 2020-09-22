-- 用户基础表

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `mall_user`;
CREATE TABLE `mall_user` (
  `id` varchar(50) NOT NULL,                    -- 主键ID
  `name` varchar(50) DEFAULT NULL,              -- 用户名
  `code` varchar(50) DEFAULT NULL,              -- 用户编码(全局唯一)
  `password` varchar(50) DEFAULT NULL,          -- 密码(明文密码加盐 MD5加密)
  `mobile` varchar(11) DEFAULT NULL,            -- 手机号
  `email` varchar(50) DEFAULT NULL,             -- 邮箱
  `sex` varchar(2) DEFAULT NULL,                -- 性别
  `salt` varchar(50) DEFAULT NULL,              -- 密码盐
  `client` varchar(2) DEFAULT NULL,             -- 是否是客服(用作在线聊天权限)
  `create_date` varchar(50) DEFAULT NULL,       -- 创建时间(标准字段)
  `create_by` varchar(50) DEFAULT NULL,         -- 创建人(标准字段)
  `modify_date` varchar(50) DEFAULT NULL,       -- 修改时间(标准字段)
  `modify_by` varchar(50) DEFAULT NULL,         -- 修改人(标准字段)
  `status` int(1) DEFAULT NULL,                 -- 状态(标准字段)
  `cp_user_id` varchar(100) DEFAULT NULL,       -- 暂时没有实际用途
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `mall_user` VALUES ('123', 'Gladys', '0001', '81dc9bdb52d04dc20036dbd8313ed055', '18656440316', '122@qq.com', 'M', NULL, 'Y', '2020-05-20 17:25:58', NULL, NULL, NULL, 1, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;