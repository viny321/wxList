/*
Navicat MySQL Data Transfer

Source Server         : localServer
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : vlist

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-05-27 12:46:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for vevent
-- ----------------------------
DROP TABLE IF EXISTS `vevent`;
CREATE TABLE `vevent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `info` varchar(255) DEFAULT NULL,
  `vdate` date NOT NULL,
  `vtime` time DEFAULT NULL,
  `priority` tinyint(4) DEFAULT NULL,
  `is_done` tinyint(4) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `label_id` int(11) DEFAULT NULL,
  `create_ts` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `alter_ts` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `label_id` (`label_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `label_id` FOREIGN KEY (`label_id`) REFERENCES `vlabel` (`l_id`),
  CONSTRAINT `u_id` FOREIGN KEY (`u_id`) REFERENCES `vuser` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vevent
-- ----------------------------
INSERT INTO `vevent` VALUES ('1', '完成小程序后台开发', '2018-04-21', '14:45:00', '2', '0', '1', '1', '2018-04-21 09:44:53', '2018-04-21 09:44:57');
INSERT INTO `vevent` VALUES ('2', '测试时间格式', '2018-05-27', '10:26:58', '3', '0', '1', '2', '2018-05-27 12:27:14', null);

-- ----------------------------
-- Table structure for vlabel
-- ----------------------------
DROP TABLE IF EXISTS `vlabel`;
CREATE TABLE `vlabel` (
  `l_id` int(11) NOT NULL AUTO_INCREMENT,
  `label_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vlabel
-- ----------------------------
INSERT INTO `vlabel` VALUES ('1', '个人');
INSERT INTO `vlabel` VALUES ('2', '学习');
INSERT INTO `vlabel` VALUES ('3', '工作');
INSERT INTO `vlabel` VALUES ('4', '其它');

-- ----------------------------
-- Table structure for vuser
-- ----------------------------
DROP TABLE IF EXISTS `vuser`;
CREATE TABLE `vuser` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_num` varchar(255) NOT NULL,
  `user_pwd` varchar(255) NOT NULL,
  `phone_info` varchar(255) DEFAULT NULL,
  `role` tinyint(4) DEFAULT NULL,
  `create_ts` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `alter_ts` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vuser
-- ----------------------------
INSERT INTO `vuser` VALUES ('1', '3115002750', '123456', '11223', '5', '2018-05-01 19:29:49', '2018-05-01 19:29:52');
