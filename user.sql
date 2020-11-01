/*
Navicat MySQL Data Transfer

Source Server         : mysql2
Source Server Version : 80020
Source Host           : localhost:3306
Source Database       : text

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-11-01 11:30:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'wang', '11', 'admin');
INSERT INTO `user` VALUES ('2', 'update', '11', 'admin');
INSERT INTO `user` VALUES ('3', '11', '11', 'user');
