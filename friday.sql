/*
Navicat MySQL Data Transfer

Source Server         : db_lazy
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : friday

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2020-05-27 01:02:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `css` varchar(30) DEFAULT NULL,
  `href` varchar(100) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `permission` varchar(50) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '0', '管理员管理', 'fa-users', null, '1', 'sys:admin', '1', null, '2020-03-20 14:20:08');
INSERT INTO `sys_permission` VALUES ('2', '1', '用户查询', 'fa-user', null, '1', '', '2', null, '2020-03-20 14:26:12');
INSERT INTO `sys_permission` VALUES ('3', '2', '查询', null, null, '2', 'sys:user:query', '100', null, null);
INSERT INTO `sys_permission` VALUES ('4', '2', '新增', null, null, '2', 'sys:user:add', '100', null, null);
INSERT INTO `sys_permission` VALUES ('5', '2', '删除', null, null, '2', 'sys:user:delete', '100', null, null);
INSERT INTO `sys_permission` VALUES ('6', '1', '修改密码', 'fa-pencil-square-o', null, '1', 'sys:user:password', '4', null, null);
INSERT INTO `sys_permission` VALUES ('7', '0', '系统设置', 'fa-gears', null, '1', null, '5', null, null);
INSERT INTO `sys_permission` VALUES ('8', '7', '菜单', 'fa-cog', null, '1', 'sys:menu', '6', null, '2020-03-20 14:12:10');
INSERT INTO `sys_permission` VALUES ('9', '8', '查询', null, null, '2', 'sys:menu:query', '100', null, null);
INSERT INTO `sys_permission` VALUES ('10', '8', '新增', null, null, '2', 'sys:menu:add', '100', null, null);
INSERT INTO `sys_permission` VALUES ('11', '8', '删除', null, null, '2', 'sys:menu:delete', '100', null, null);
INSERT INTO `sys_permission` VALUES ('12', '7', '角色', 'fa-user-secret', null, '1', 'sys:role', '7', null, '2020-03-20 14:11:50');
INSERT INTO `sys_permission` VALUES ('13', '12', '查询', null, null, '2', 'sys:role:query', '100', null, null);
INSERT INTO `sys_permission` VALUES ('14', '12', '新增', null, null, '2', 'sys:role:add', '100', null, null);
INSERT INTO `sys_permission` VALUES ('15', '12', '删除', null, null, '2', 'sys:role:delete', '100', null, null);
INSERT INTO `sys_permission` VALUES ('16', '0', '文件管理', 'fa-folder-open', null, '1', null, '8', null, null);
INSERT INTO `sys_permission` VALUES ('17', '16', '查询', null, null, '2', 'sys:file:query', '100', null, null);
INSERT INTO `sys_permission` VALUES ('18', '16', '删除', null, null, '2', 'sys:file:delete', '100', null, null);
INSERT INTO `sys_permission` VALUES ('19', '0', '数据源监控', 'fa-eye', null, '1', 'sys:druid', '9', null, '2020-03-20 14:10:15');
INSERT INTO `sys_permission` VALUES ('20', '0', '接口swagger', 'fa-file-pdf-0', null, '1', 'sys:swagger', '10', null, '2020-03-20 14:10:28');
INSERT INTO `sys_permission` VALUES ('23', '8', '修改', null, null, '2', 'sys:menu:edit', '100', null, null);
INSERT INTO `sys_permission` VALUES ('24', '12', '修改', null, null, '2', 'sys:role:edit', '100', null, null);
INSERT INTO `sys_permission` VALUES ('25', '2', '修改', null, null, '2', 'sys:user:edit', '100', null, null);
INSERT INTO `sys_permission` VALUES ('33', '0', '用户管理', '', '', '1', 'sys:user', '11', '2020-03-20 14:19:15', null);

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '管理员', '2020-03-09 15:06:28', '2020-03-13 21:16:31');
INSERT INTO `sys_role` VALUES ('2', 'USER', '用户', '2020-03-09 15:06:52', '2020-03-20 14:46:33');
INSERT INTO `sys_role` VALUES ('3', 'TEACHER', '老师', '2020-03-09 15:07:49', null);
INSERT INTO `sys_role` VALUES ('4', 'BUYER', '销售员', '2020-03-09 15:08:10', null);
INSERT INTO `sys_role` VALUES ('6', 'TEST', '测试需要', '2020-03-12 23:49:49', '2020-03-15 16:03:01');

-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `KF_permissionId` (`permission_id`),
  CONSTRAINT `KF_permissionId` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `KF_roleId_per` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('6', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('6', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('6', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('6', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
INSERT INTO `sys_role_permission` VALUES ('1', '16');
INSERT INTO `sys_role_permission` VALUES ('1', '17');
INSERT INTO `sys_role_permission` VALUES ('1', '18');
INSERT INTO `sys_role_permission` VALUES ('1', '19');
INSERT INTO `sys_role_permission` VALUES ('1', '20');
INSERT INTO `sys_role_permission` VALUES ('1', '23');
INSERT INTO `sys_role_permission` VALUES ('1', '24');
INSERT INTO `sys_role_permission` VALUES ('1', '25');
INSERT INTO `sys_role_permission` VALUES ('2', '33');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `headImgUrl` varchar(255) DEFAULT NULL,
  `nickname` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `telphone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `telphone` (`telphone`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$rhcaCFz41BVY7jPmr6rpqOXPO/U7AZb0h/UaC6q/QS10KipfigyL2', null, '管理员', '1', '2020-03-08', '19937127322', '1', '1562696017@qq.com', '我是管理员', '2020-03-06 23:24:51', '2020-03-12 02:01:09');
INSERT INTO `sys_user` VALUES ('2', 'admin2', 'admin2', null, 'writer', '0', null, null, '1', null, null, '2020-03-06 23:26:51', '2020-03-10 21:09:35');
INSERT INTO `sys_user` VALUES ('4', 'user1', 'user1', null, 'buyer', '1', null, null, '1', null, null, '2020-03-08 20:53:47', '2020-03-10 21:09:44');
INSERT INTO `sys_user` VALUES ('5', 'user2', 'user2', null, null, '0', null, null, '0', null, null, '2020-03-08 20:53:59', '2020-03-10 21:09:46');
INSERT INTO `sys_user` VALUES ('6', 'user3', 'user3', null, null, '0', null, null, '0', null, null, '2020-03-08 20:54:11', '2020-03-10 21:09:48');
INSERT INTO `sys_user` VALUES ('8', 'user5', 'user5', null, null, '1', null, null, '1', null, null, '2020-03-08 20:54:35', '2020-03-10 21:09:53');
INSERT INTO `sys_user` VALUES ('9', 'user6', 'user6', null, null, '0', null, null, '0', null, null, '2020-03-08 20:54:45', '2020-03-10 21:09:56');
INSERT INTO `sys_user` VALUES ('10', 'user7', 'user7', null, null, '1', null, null, '0', null, null, '2020-03-08 20:54:54', '2020-03-10 21:09:59');
INSERT INTO `sys_user` VALUES ('11', 'user8', '$10$rhcaCFz41BVY7jPmr6rpqOXPO/U7AZb0h/UaC6q/QS10KipfigyL2', null, null, '1', null, null, '1', null, null, '2020-03-08 20:55:07', '2020-03-10 21:10:01');
INSERT INTO `sys_user` VALUES ('12', 'user9', 'user9', null, null, '0', null, null, '1', null, null, '2020-03-08 20:55:18', '2020-03-10 21:10:03');
INSERT INTO `sys_user` VALUES ('13', 'user10', 'user10', null, null, '0', null, null, '0', null, null, '2020-03-08 20:55:27', '2020-03-10 21:10:06');
INSERT INTO `sys_user` VALUES ('14', 'user', '$2a$10$rhcaCFz41BVY7jPmr6rpqOXPO/U7AZb0h/UaC6q/QS10KipfigyL2', null, '用户', '1', '2020-03-03', '19937127320', '1', '123@qq.com', null, '2020-03-09 11:47:54', '2020-03-18 08:54:43');
INSERT INTO `sys_user` VALUES ('15', 'user12', '123456', null, null, '0', '2020-03-08', '15537659008', '1', '321@qq.com', null, '2020-03-09 12:10:03', '2020-03-10 21:10:11');
INSERT INTO `sys_user` VALUES ('16', 'user13', '123456', null, null, '0', '2020-03-08', '19973256211', '1', '231@qq.com', null, '2020-03-09 12:32:53', '2020-03-10 21:10:15');
INSERT INTO `sys_user` VALUES ('17', 'user14', '123123', null, null, '1', '2020-03-08', '16586498711', '1', '523@qq.com', null, '2020-03-09 12:51:14', '2020-03-10 21:10:23');
INSERT INTO `sys_user` VALUES ('23', 'user15', '123456', null, null, '1', '2020-03-09', '19937127333', '1', '6663@qq.com', null, '2020-03-10 05:55:59', '2020-03-10 21:10:27');
INSERT INTO `sys_user` VALUES ('24', 'user16', '123456', null, null, '0', '2020-03-09', '15698632145', '1', '987@qq.com', null, '2020-03-10 11:44:55', '2020-03-10 21:10:29');
INSERT INTO `sys_user` VALUES ('25', 'user17', '123456', null, null, '1', '2020-03-08', '15623698712', '1', '452@qq.com', null, '2020-03-10 13:16:18', '2020-03-18 08:43:15');
INSERT INTO `sys_user` VALUES ('26', 'user18', '123456', null, null, '1', '2020-03-10', '19937127311', '2', '222@qq.com', null, '2020-03-11 04:22:29', '2020-03-11 04:40:24');
INSERT INTO `sys_user` VALUES ('27', 'admin3', 'admin3', null, null, '0', '2020-05-26', '12356895647', null, '156@qq.com', null, null, null);

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `KF_roleId` (`role_id`),
  CONSTRAINT `KF_roleId` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `KF_userId` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '2020-03-10 19:43:57', '2020-03-12 02:01:09');
INSERT INTO `sys_user_role` VALUES ('2', '4', '2020-03-10 19:45:36', null);
INSERT INTO `sys_user_role` VALUES ('4', '2', '2020-03-10 19:46:28', null);
INSERT INTO `sys_user_role` VALUES ('5', '3', '2020-03-10 19:46:30', null);
INSERT INTO `sys_user_role` VALUES ('6', '2', '2020-03-10 19:46:32', null);
INSERT INTO `sys_user_role` VALUES ('8', '2', '2020-03-10 19:46:38', null);
INSERT INTO `sys_user_role` VALUES ('9', '3', '2020-03-10 19:46:41', null);
INSERT INTO `sys_user_role` VALUES ('10', '2', '2020-03-10 19:46:44', null);
INSERT INTO `sys_user_role` VALUES ('11', '2', '2020-03-09 11:47:54', null);
INSERT INTO `sys_user_role` VALUES ('11', '3', '2020-03-10 19:46:47', null);
INSERT INTO `sys_user_role` VALUES ('12', '2', '2020-03-10 19:47:10', null);
INSERT INTO `sys_user_role` VALUES ('13', '3', '2020-03-10 19:47:14', null);
INSERT INTO `sys_user_role` VALUES ('14', '2', '2020-03-10 19:47:17', '2020-03-18 08:54:43');
INSERT INTO `sys_user_role` VALUES ('15', '3', '2020-03-10 19:47:20', null);
INSERT INTO `sys_user_role` VALUES ('16', '2', '2020-03-10 19:47:26', null);
INSERT INTO `sys_user_role` VALUES ('17', '2', '2020-03-09 12:51:14', null);
INSERT INTO `sys_user_role` VALUES ('23', '2', '2020-03-10 05:55:59', null);
INSERT INTO `sys_user_role` VALUES ('24', '3', '2020-03-10 11:44:55', null);
INSERT INTO `sys_user_role` VALUES ('25', '2', '2020-03-10 13:16:18', '2020-03-18 08:43:15');
INSERT INTO `sys_user_role` VALUES ('26', '4', '2020-03-11 04:22:29', '2020-03-11 04:40:24');
INSERT INTO `sys_user_role` VALUES ('27', '6', null, null);

-- ----------------------------
-- Procedure structure for `userPorced`
-- ----------------------------
DROP PROCEDURE IF EXISTS `userPorced`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userPorced`()
begin 
		select * from sys_user;
	end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `userPorcedByStatus`
-- ----------------------------
DROP PROCEDURE IF EXISTS `userPorcedByStatus`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userPorcedByStatus`(
in a int(10)
)
begin 
		select * from sys_user where status>a;
	end
;;
DELIMITER ;
