/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : db_farm

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 29/01/2019 13:13:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for farm_device
-- ----------------------------
DROP TABLE IF EXISTS `farm_device`;
CREATE TABLE `farm_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备id',
  `did` varchar(32) NOT NULL COMMENT '硬件ID',
  `model` int(11) NOT NULL COMMENT '设备型号',
  `token` varchar(36) DEFAULT NULL COMMENT '认证token',
  `name` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `lng` float DEFAULT '180' COMMENT '经度',
  `lat` float DEFAULT '36' COMMENT '纬度',
  PRIMARY KEY (`id`),
  UNIQUE KEY `farm_device_did_uindex` (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for farm_greenhouse
-- ----------------------------
DROP TABLE IF EXISTS `farm_greenhouse`;
CREATE TABLE `farm_greenhouse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(36) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8 COMMENT='大棚管理';

-- ----------------------------
-- Table structure for farm_greenhouse_device
-- ----------------------------
DROP TABLE IF EXISTS `farm_greenhouse_device`;
CREATE TABLE `farm_greenhouse_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `greenhouse_id` bigint(20) NOT NULL,
  `device_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='将大棚与设备关联';

-- ----------------------------
-- Table structure for farm_humidity
-- ----------------------------
DROP TABLE IF EXISTS `farm_humidity`;
CREATE TABLE `farm_humidity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NOT NULL COMMENT '设备表主键',
  `data` float NOT NULL COMMENT '湿度',
  `create_time` datetime DEFAULT NULL COMMENT '存库日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9634 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for farm_temperature
-- ----------------------------
DROP TABLE IF EXISTS `farm_temperature`;
CREATE TABLE `farm_temperature` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NOT NULL COMMENT '硬件ID',
  `data` float NOT NULL COMMENT '温度',
  `create_time` datetime DEFAULT NULL COMMENT '存库日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10060 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for farm_threshold
-- ----------------------------
DROP TABLE IF EXISTS `farm_threshold`;
CREATE TABLE `farm_threshold` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_id` int(11) DEFAULT NULL COMMENT '设备主键\n',
  `type` int(11) DEFAULT NULL COMMENT '0 : temperature\n1 : humidity\n',
  `user_id` int(11) DEFAULT NULL COMMENT '不同用户可以设置不同的报警值',
  `max_value` float DEFAULT NULL COMMENT '最大值',
  `min_value` float DEFAULT NULL COMMENT '最小值',
  `flag` int(11) DEFAULT NULL COMMENT '是在最小值和最大值时报警,\n还是说不在这个范围的时候报警.\n0: 区间报警\n1: 非区间报警',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for farm_user
-- ----------------------------
DROP TABLE IF EXISTS `farm_user`;
CREATE TABLE `farm_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `status` int(4) NOT NULL COMMENT '角色0-在线,1-离线,2-禁用',
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `role` int(4) NOT NULL COMMENT '角色0-管理员,1-普通用户',
  `avatar` varchar(100) DEFAULT 'default.jpg' COMMENT '头像',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `weixin` varchar(100) DEFAULT NULL COMMENT '微信名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for farm_user_device
-- ----------------------------
DROP TABLE IF EXISTS `farm_user_device`;
CREATE TABLE `farm_user_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `device_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户设备关联表';

SET FOREIGN_KEY_CHECKS = 1;
