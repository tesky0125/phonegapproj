/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : data

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2015-05-13 22:33:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `advertise`
-- ----------------------------
DROP TABLE IF EXISTS `advertise`;
CREATE TABLE `advertise` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advertise
-- ----------------------------
INSERT INTO `advertise` VALUES ('2', '测试2', '测试2', '2015-05-10 23:42:46');
INSERT INTO `advertise` VALUES ('4', '测试4', '测试4', '2015-05-10 23:42:52');
INSERT INTO `advertise` VALUES ('6', '测试6', '测试6', '2015-05-10 23:42:57');
INSERT INTO `advertise` VALUES ('7', '测试7', '测试7', '2015-05-10 23:42:59');
INSERT INTO `advertise` VALUES ('8', '测试8', '测试8', '2015-05-10 23:43:02');
INSERT INTO `advertise` VALUES ('9', '测试9', '测试9', '2015-05-10 23:43:05');
INSERT INTO `advertise` VALUES ('10', '测试10', '测试10', '2015-05-10 23:43:08');
INSERT INTO `advertise` VALUES ('11', 'yang', 'yang22', '2015-05-11 17:18:17');
INSERT INTO `advertise` VALUES ('12', 'yan', '222', '2015-05-10 23:42:46');
INSERT INTO `advertise` VALUES ('13', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `advertise` VALUES ('14', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `advertise` VALUES ('15', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `advertise` VALUES ('16', '222', '222', '2015-05-10 23:42:46');

-- ----------------------------
-- Table structure for `dynamic_mesg`
-- ----------------------------
DROP TABLE IF EXISTS `dynamic_mesg`;
CREATE TABLE `dynamic_mesg` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT '',
  `content` text,
  `date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dynamic_mesg
-- ----------------------------
INSERT INTO `dynamic_mesg` VALUES ('2', 'yang', 'yang22', '2015-05-11 17:24:59');
INSERT INTO `dynamic_mesg` VALUES ('4', '测试4', '测试测试测试测试4', '2015-05-10 23:37:55');
INSERT INTO `dynamic_mesg` VALUES ('6', '测试6', '测试测试测试测试6', '2015-05-10 23:38:02');
INSERT INTO `dynamic_mesg` VALUES ('7', '测试7', '测试测试测试测试7', '2015-05-10 23:38:04');
INSERT INTO `dynamic_mesg` VALUES ('8', '测试8', '测试测试测试测试8', '2015-05-10 23:38:08');
INSERT INTO `dynamic_mesg` VALUES ('9', '测试9', '测试测试测试测试9', '2015-05-10 23:38:10');
INSERT INTO `dynamic_mesg` VALUES ('10', '测试10', '测试测试测试测试10', '2015-05-10 23:38:12');
INSERT INTO `dynamic_mesg` VALUES ('11', 'yang', 'yang22', '2015-05-11 17:24:59');
INSERT INTO `dynamic_mesg` VALUES ('12', '11', '111', '2015-05-10 23:42:46');
INSERT INTO `dynamic_mesg` VALUES ('13', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `dynamic_mesg` VALUES ('14', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `dynamic_mesg` VALUES ('15', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `dynamic_mesg` VALUES ('16', '111', '222', '2015-05-10 23:42:46');

-- ----------------------------
-- Table structure for `employ_guide`
-- ----------------------------
DROP TABLE IF EXISTS `employ_guide`;
CREATE TABLE `employ_guide` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employ_guide
-- ----------------------------
INSERT INTO `employ_guide` VALUES ('2', 'yang', 'yang22', '2015-05-11 17:22:50');
INSERT INTO `employ_guide` VALUES ('4', '测试4', '测试4', '2015-05-10 23:44:59');
INSERT INTO `employ_guide` VALUES ('6', '测试6', '测试6', '2015-05-10 23:44:59');
INSERT INTO `employ_guide` VALUES ('7', '测试7', '测试7', '2015-05-10 23:44:59');
INSERT INTO `employ_guide` VALUES ('8', '测试8', '测试8', '2015-05-10 23:44:59');
INSERT INTO `employ_guide` VALUES ('9', '测试9', '测试9', '2015-05-10 23:44:59');
INSERT INTO `employ_guide` VALUES ('10', '测试10', '测试10', '2015-05-10 23:44:59');
INSERT INTO `employ_guide` VALUES ('11', 'yang', 'yang22', '2015-05-11 17:22:50');
INSERT INTO `employ_guide` VALUES ('12', '11', '111', '2015-05-10 23:42:46');
INSERT INTO `employ_guide` VALUES ('13', '11', '111', '2015-05-10 23:42:46');
INSERT INTO `employ_guide` VALUES ('14', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `employ_guide` VALUES ('15', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `employ_guide` VALUES ('16', '111', '222', '2015-05-10 23:42:46');

-- ----------------------------
-- Table structure for `life_service`
-- ----------------------------
DROP TABLE IF EXISTS `life_service`;
CREATE TABLE `life_service` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of life_service
-- ----------------------------
INSERT INTO `life_service` VALUES ('3', '测试3', '测试3', '2015-05-10 23:44:59');
INSERT INTO `life_service` VALUES ('4', '测试4', '测试4', '2015-05-10 23:44:59');
INSERT INTO `life_service` VALUES ('6', '测试6', '测试6', '2015-05-10 23:44:59');
INSERT INTO `life_service` VALUES ('7', '测试7', '测试7', '2015-05-10 23:44:59');
INSERT INTO `life_service` VALUES ('8', '测试8', '测试8', '2015-05-10 23:44:59');
INSERT INTO `life_service` VALUES ('9', '测试1', '测试9', '2015-05-10 23:44:59');
INSERT INTO `life_service` VALUES ('10', '测试10', '测试10', '2015-05-10 23:44:59');
INSERT INTO `life_service` VALUES ('11', 'yang', 'yang22', '2015-05-11 17:23:22');
INSERT INTO `life_service` VALUES ('12', '222', '222', '2015-05-10 23:42:46');
INSERT INTO `life_service` VALUES ('13', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `life_service` VALUES ('14', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `life_service` VALUES ('15', '111', '222', '2015-05-10 23:42:46');

-- ----------------------------
-- Table structure for `online_train`
-- ----------------------------
DROP TABLE IF EXISTS `online_train`;
CREATE TABLE `online_train` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of online_train
-- ----------------------------
INSERT INTO `online_train` VALUES ('3', '测试3', '测试3', '2015-05-10 23:44:59');
INSERT INTO `online_train` VALUES ('4', '测试4', '测试4', '2015-05-10 23:44:59');
INSERT INTO `online_train` VALUES ('6', '测试6', '测试6', '2015-05-10 23:44:59');
INSERT INTO `online_train` VALUES ('7', '测试7', '测试7', '2015-05-10 23:44:59');
INSERT INTO `online_train` VALUES ('8', '测试8', '测试8', '2015-05-10 23:44:59');
INSERT INTO `online_train` VALUES ('9', '测试9', '测试9', '2015-05-10 23:44:59');
INSERT INTO `online_train` VALUES ('10', '测试10', '测试10', '2015-05-10 23:44:59');
INSERT INTO `online_train` VALUES ('11', 'yang', 'yang22', '2015-05-11 17:23:48');
INSERT INTO `online_train` VALUES ('12', '222', '222', '2015-05-10 23:42:46');
INSERT INTO `online_train` VALUES ('13', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `online_train` VALUES ('14', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `online_train` VALUES ('15', '111', '222', '2015-05-10 23:42:46');

-- ----------------------------
-- Table structure for `recruit_info`
-- ----------------------------
DROP TABLE IF EXISTS `recruit_info`;
CREATE TABLE `recruit_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruit_info
-- ----------------------------
INSERT INTO `recruit_info` VALUES ('3', '测试3', '测试3', '2015-05-10 23:44:59');
INSERT INTO `recruit_info` VALUES ('4', '测试4', '测试4', '2015-05-10 23:44:59');
INSERT INTO `recruit_info` VALUES ('6', '测试6', '测试6', '2015-05-10 23:44:59');
INSERT INTO `recruit_info` VALUES ('7', '测试7', '测试7', '2015-05-10 23:44:59');
INSERT INTO `recruit_info` VALUES ('8', '测试8', '测试8', '2015-05-10 23:44:59');
INSERT INTO `recruit_info` VALUES ('9', '测试9', '测试9', '2015-05-10 23:44:59');
INSERT INTO `recruit_info` VALUES ('10', '测试10', '测试10', '2015-05-10 23:44:59');
INSERT INTO `recruit_info` VALUES ('11', 'yang', 'yang22', '2015-05-11 17:24:00');
INSERT INTO `recruit_info` VALUES ('12', '222', '222', '2015-05-10 23:42:46');
INSERT INTO `recruit_info` VALUES ('13', '222', '222', '2015-05-10 23:42:46');
INSERT INTO `recruit_info` VALUES ('14', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `recruit_info` VALUES ('15', '111', '222', '2015-05-10 23:42:46');
INSERT INTO `recruit_info` VALUES ('16', '111', '222', '2015-05-10 23:42:46');

-- ----------------------------
-- Table structure for `scroll_show`
-- ----------------------------
DROP TABLE IF EXISTS `scroll_show`;
CREATE TABLE `scroll_show` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `image` varchar(50) DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scroll_show
-- ----------------------------
INSERT INTO `scroll_show` VALUES ('3', 'Chrysanthemum.jpg', 'Chrysanthemum.jpg', 'Chrysanthemum.jpg', '2015-05-10 23:44:59');
INSERT INTO `scroll_show` VALUES ('4', 'Desert.jpg', 'Desert.jpg', 'Desert.jpg', '2015-05-10 23:44:59');
INSERT INTO `scroll_show` VALUES ('6', 'Hydrangeas.jpg', 'Hydrangeas.jpg', 'Hydrangeas.jpg', '2015-05-11 17:24:13');
INSERT INTO `scroll_show` VALUES ('7', 'Jellyfish.jpg', 'Jellyfish.jpg', 'Jellyfish.jpg', '2015-05-10 23:42:46');
INSERT INTO `scroll_show` VALUES ('8', 'Koala.jpg', 'Koala.jpg', 'Koala.jpg', '2015-05-10 23:42:46');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user` varchar(50) DEFAULT '',
  `pwd` varchar(50) DEFAULT '',
  `email` varchar(50) DEFAULT '',
  `date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('3', 'yang', 'yang', 'weiling@qq.com', '2015-05-11 12:34:32');
INSERT INTO `user_info` VALUES ('4', 'yang', 'yang', 'yang@qq.com', '2015-05-11 12:40:32');
INSERT INTO `user_info` VALUES ('6', 'yang', 'yang', 'yang@qq.com', '2015-05-11 16:32:24');
INSERT INTO `user_info` VALUES ('7', 'yang', 'yang22', 'yang@qq.com', '2015-05-11 17:01:14');
INSERT INTO `user_info` VALUES ('8', 'yang', 'yang22', 'yang@qq.com', '2015-05-11 17:02:34');
INSERT INTO `user_info` VALUES ('11', 'yang', 'yangxx', 'yang@qq.com', '2015-05-11 17:57:03');
INSERT INTO `user_info` VALUES ('12', 'yang', 'yangxx', 'yang@qq.com', '2015-05-11 17:57:41');
INSERT INTO `user_info` VALUES ('13', 'yang', 'yangxx', 'yang@qq.com', '2015-05-11 17:57:45');
INSERT INTO `user_info` VALUES ('14', 'yang', 'yangxx', 'yang@qq.com', '2015-05-11 17:57:50');
INSERT INTO `user_info` VALUES ('15', 'xiaoyan', 'xiaoyan', 'xiaoyan@qq.com', '2015-05-10 23:42:46');
INSERT INTO `user_info` VALUES ('16', '111', '111', '111', '2015-05-10 23:42:46');
INSERT INTO `user_info` VALUES ('17', '111', '111', '111', '2015-05-10 23:42:46');
INSERT INTO `user_info` VALUES ('18', '111', '111', '111', '2015-05-10 23:42:46');
INSERT INTO `user_info` VALUES ('19', '111', '111', '111', '2015-05-10 23:42:46');
INSERT INTO `user_info` VALUES ('20', '111', '111', '111', '2015-05-10 23:42:46');
INSERT INTO `user_info` VALUES ('21', '111', '111', '111', '2015-05-10 23:42:46');
