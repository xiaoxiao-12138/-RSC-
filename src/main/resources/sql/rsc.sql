/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 60011
Source Host           : localhost:3306
Source Database       : rsc

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2020-12-16 15:35:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'c4ca4238a0b923820dcc509a6f75849b');

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '出勤表id',
  `pid` int(11) DEFAULT NULL COMMENT '快递员id',
  `year` int(11) DEFAULT NULL COMMENT '年',
  `month` int(11) DEFAULT NULL COMMENT '月',
  `day` int(11) DEFAULT NULL,
  `duty` int(11) DEFAULT NULL COMMENT '值班',
  `leaves` int(11) DEFAULT NULL COMMENT '请假',
  `overtime` int(11) DEFAULT NULL COMMENT '加班',
  `vacation` int(11) DEFAULT NULL COMMENT '休假',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('1', '1', '2020', '11', '14', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('2', '2', '2020', '11', '14', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('3', '3', '2020', '12', '14', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('4', '1', '2020', '12', '15', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('5', '1', '2020', '12', '1', '0', '1', '0', '0');
INSERT INTO `attendance` VALUES ('6', '1', '2020', '12', '2', '0', '0', '1', '0');
INSERT INTO `attendance` VALUES ('7', '1', '2020', '12', '3', '0', '0', '0', '1');
INSERT INTO `attendance` VALUES ('8', '1', '2020', '11', '3', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('9', '1', '2020', '10', '1', '0', '0', '0', '1');
INSERT INTO `attendance` VALUES ('10', '1', '2020', '9', '6', '0', '1', '0', '0');
INSERT INTO `attendance` VALUES ('11', '1', '2020', '8', '7', '0', '0', '1', '0');
INSERT INTO `attendance` VALUES ('12', '1', '2020', '7', '7', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('13', '1', '2020', '6', '10', '0', '0', '1', '0');
INSERT INTO `attendance` VALUES ('14', '1', '2020', '12', '1', '0', '0', '1', '0');
INSERT INTO `attendance` VALUES ('15', '1', '2020', '12', '2', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('16', '1', '2020', '12', '3', '0', '0', '1', '0');
INSERT INTO `attendance` VALUES ('17', '1', '2020', '12', '4', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('18', '1', '2020', '12', '5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('19', '1', '2020', '12', '6', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('20', '1', '2020', '12', '7', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('21', '1', '2020', '12', '8', '0', '0', '0', '1');
INSERT INTO `attendance` VALUES ('22', '1', '2020', '12', '9', '0', '0', '1', '0');
INSERT INTO `attendance` VALUES ('23', '1', '2020', '12', '10', '0', '1', '0', '0');
INSERT INTO `attendance` VALUES ('24', '1', '2020', '12', '16', '1', '0', '0', '0');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '寄件人id',
  `phone` varchar(255) DEFAULT NULL COMMENT '寄件人电话/账号',
  `password` varchar(255) DEFAULT NULL COMMENT '寄件人密码',
  `name` varchar(255) DEFAULT NULL COMMENT '寄件人名字',
  `address` varchar(255) DEFAULT NULL COMMENT '寄件人地址',
  `region_id` int(11) DEFAULT NULL COMMENT '寄件人所在区域id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '15819878404', 'e10adc3949ba59abbe56e057f20f883e', '小小12138', '广州大学华软软件学院', '1');
INSERT INTO `customer` VALUES ('2', '15220574901', 'e10adc3949ba59abbe56e057f20f883e', '雷欧斯', '梁化', '1');
INSERT INTO `customer` VALUES ('4', '12345678910', 'e10adc3949ba59abbe56e057f20f883e', '螺丝刀', '镇魂街25号', '2');
INSERT INTO `customer` VALUES ('5', '12345678910', 'e10adc3949ba59abbe56e057f20f883e', '瓶浩邪', '山卡拉85号', '1');
INSERT INTO `customer` VALUES ('6', '9876543210', 'e10adc3949ba59abbe56e057f20f883e', '瓶浩邪', '山卡拉85号', '1');
INSERT INTO `customer` VALUES ('9', '13535397214', 'c4ca4238a0b923820dcc509a6f75849b', 'Elana', '广州大学', '1');

-- ----------------------------
-- Table structure for mail
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '邮件表id',
  `addressee_name` varchar(255) DEFAULT NULL COMMENT '收件人名字',
  `addressee_phone` varchar(255) DEFAULT NULL COMMENT '收件人电话',
  `address` varchar(255) DEFAULT NULL COMMENT '收件人地址',
  `customer_id` int(11) DEFAULT NULL COMMENT '寄件人id',
  `receive_rid` int(11) DEFAULT NULL COMMENT '收件区域id',
  `assign_rid` int(11) DEFAULT NULL COMMENT '派件区域id',
  `receive_pid` int(11) DEFAULT NULL COMMENT '收件快递员id',
  `assign_pid` int(11) DEFAULT NULL COMMENT '寄件快递员id',
  `receive_state` int(11) DEFAULT NULL COMMENT '收件状态',
  `assign_state` int(11) DEFAULT NULL COMMENT '派件状态',
  `receive_frequency` int(11) DEFAULT '1' COMMENT '收件次数',
  `assign_frequency` int(11) DEFAULT '1' COMMENT '派件次数',
  `reason` varchar(255) DEFAULT NULL COMMENT '原因',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  `receive_time` datetime DEFAULT NULL COMMENT '收件时间',
  `assign_time` datetime DEFAULT NULL COMMENT '派件时间',
  `delete_state` int(11) DEFAULT NULL COMMENT '是否删除订单',
  `distribute_receive_time` datetime DEFAULT NULL COMMENT '分配收件时间',
  `distribute_assign_time` datetime DEFAULT NULL COMMENT '分配派件时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail
-- ----------------------------
INSERT INTO `mail` VALUES ('7', '萝卜苗', '15987465652', '山卡拉85号', '1', '1', '1', '1', '1', '0', '1', '0', '0', null, '2020-11-22 18:32:30', '2020-12-15 11:24:09', '2020-12-24 15:08:58', '0', '2020-12-14 19:52:42', '2020-12-16 15:01:20');
INSERT INTO `mail` VALUES ('12', '老司机', '15220274621', '惠东县', '1', '2', '2', '3', '3', '0', '0', null, null, null, '2020-12-09 16:03:48', null, null, null, null, null);
INSERT INTO `mail` VALUES ('13', '科技馆', '12345678910', '街口30号', '2', '1', '2', '2', '2', '1', '0', null, null, null, '2020-12-10 16:07:44', '2020-12-15 11:30:57', null, null, '2020-12-16 00:20:19', '2020-12-14 19:59:54');
INSERT INTO `mail` VALUES ('14', '考试酷', '45421115454', '嘉禾望岗', '1', '1', '2', '1', '2', '0', '0', null, null, null, '2020-12-10 16:14:48', '2020-12-15 14:59:08', null, null, '2020-12-15 14:58:33', '2020-12-16 00:15:30');

-- ----------------------------
-- Table structure for mail_state
-- ----------------------------
DROP TABLE IF EXISTS `mail_state`;
CREATE TABLE `mail_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '邮件状态id',
  `state` varchar(255) DEFAULT NULL COMMENT '邮件状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail_state
-- ----------------------------
INSERT INTO `mail_state` VALUES ('0', '等待分配');
INSERT INTO `mail_state` VALUES ('1', '正在揽件');
INSERT INTO `mail_state` VALUES ('2', '已完成');
INSERT INTO `mail_state` VALUES ('3', '失败');

-- ----------------------------
-- Table structure for postman
-- ----------------------------
DROP TABLE IF EXISTS `postman`;
CREATE TABLE `postman` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '快递员id',
  `phone` varchar(255) NOT NULL COMMENT '快递员电话/账号',
  `password` varchar(255) DEFAULT NULL COMMENT '快递员登录密码',
  `name` varchar(255) DEFAULT NULL COMMENT '快递员名字',
  `region_id` int(11) DEFAULT NULL COMMENT '快递员配送区域',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=667 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of postman
-- ----------------------------
INSERT INTO `postman` VALUES ('1', '15580155117', 'e10adc3949ba59abbe56e057f20f883e', '快递员1', '1');
INSERT INTO `postman` VALUES ('2', '12345678910', 'e10adc3949ba59abbe56e057f20f883e', '快递员2', '2');
INSERT INTO `postman` VALUES ('3', '123456', 'workloadUpdateWrapper', '快递员3', '2');
INSERT INTO `postman` VALUES ('666', '666', null, '无', null);

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '区域id',
  `region` varchar(255) DEFAULT NULL COMMENT '区域名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of region
-- ----------------------------
INSERT INTO `region` VALUES ('1', '太平镇');
INSERT INTO `region` VALUES ('2', '良口镇');

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工资id',
  `pid` int(11) DEFAULT NULL COMMENT '快递员id',
  `year` int(11) DEFAULT NULL COMMENT '年',
  `month` int(11) DEFAULT NULL COMMENT '月',
  `basic` double DEFAULT NULL COMMENT '底薪',
  `assessment` double DEFAULT NULL COMMENT '提成',
  `total` double DEFAULT NULL COMMENT '总工资',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salary
-- ----------------------------
INSERT INTO `salary` VALUES ('2', '1', '2020', '12', '2000', '10', '10');
INSERT INTO `salary` VALUES ('3', '2', '2020', '11', '3000', '15', '15');
INSERT INTO `salary` VALUES ('4', '1', '2020', '10', '3500', '10', '15');

-- ----------------------------
-- Table structure for workload
-- ----------------------------
DROP TABLE IF EXISTS `workload`;
CREATE TABLE `workload` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工作量表id',
  `pid` int(11) DEFAULT NULL COMMENT '快递员id',
  `year` int(11) DEFAULT NULL COMMENT '年',
  `month` int(11) DEFAULT NULL COMMENT '月',
  `date` int(11) DEFAULT NULL COMMENT '日',
  `receive_workload` int(11) DEFAULT NULL COMMENT '收件工作量',
  `assign_workload` int(11) DEFAULT NULL COMMENT '派件工作量',
  `expectation_workload` int(11) DEFAULT NULL COMMENT '预期工作量',
  `total_workload` int(11) DEFAULT NULL COMMENT '全部工作量',
  `receive_fault` int(11) DEFAULT NULL COMMENT '收件失败数量',
  `assign_fault` int(11) DEFAULT NULL COMMENT '派件失败数量',
  `total_fault` int(11) DEFAULT NULL COMMENT '全部失败数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workload
-- ----------------------------
INSERT INTO `workload` VALUES ('1', '1', '2020', '12', '11', '0', '0', '2', '0', '1', '0', '1');
INSERT INTO `workload` VALUES ('2', '1', '2020', '12', '15', '12', '12', '32', '24', '1', '1', '1');
INSERT INTO `workload` VALUES ('3', '1', '2020', '12', '14', '15', '16', '30', '31', '0', '0', '0');
INSERT INTO `workload` VALUES ('4', '2', '2020', '12', '10', '11', '10', '31', '21', '0', '0', '0');
INSERT INTO `workload` VALUES ('5', '1', '2020', '11', '11', '8', '6', '15', '14', '1', '0', '0');
INSERT INTO `workload` VALUES ('6', '2', '2020', '11', '15', '10', '8', '20', '18', '0', '0', '0');
INSERT INTO `workload` VALUES ('7', '2', '2020', '10', '19', '5', '6', '20', '11', '1', '1', '0');
INSERT INTO `workload` VALUES ('8', '1', '2020', '9', '21', '11', '15', '30', '26', '0', '1', '0');
INSERT INTO `workload` VALUES ('9', '1', '2020', '8', '22', '10', '4', '20', '14', '0', '0', '1');
INSERT INTO `workload` VALUES ('10', '2', '2020', '7', '26', '6', '8', '15', '14', '0', '0', '0');
INSERT INTO `workload` VALUES ('11', '1', '2020', '6', '29', '7', '7', '20', '14', '0', '1', '1');
INSERT INTO `workload` VALUES ('12', '1', '2020', '5', '1', '11', '11', '30', '22', '0', '0', '0');
INSERT INTO `workload` VALUES ('13', '2', '2020', '4', '16', '5', '6', '15', '11', '0', '1', '1');
INSERT INTO `workload` VALUES ('14', '1', '2020', '3', '14', '4', '6', '11', '10', '0', '0', '1');
INSERT INTO `workload` VALUES ('15', '1', '2020', '2', '15', '8', '9', '20', '17', '3', '2', '1');
INSERT INTO `workload` VALUES ('16', '2', '2020', '1', '4', '7', '4', '15', '11', '3', '0', '1');
INSERT INTO `workload` VALUES ('17', '3', '2020', '1', '5', '8', '9', '20', '17', '1', '1', '1');
INSERT INTO `workload` VALUES ('18', '3', '2020', '2', '5', '7', '6', '15', '13', '0', '1', '1');
INSERT INTO `workload` VALUES ('19', '3', '2020', '3', '8', '6', '6', '15', '12', '0', '0', '0');
INSERT INTO `workload` VALUES ('20', '3', '2020', '4', '15', '7', '10', '20', '17', '0', '0', '1');
INSERT INTO `workload` VALUES ('21', '3', '2020', '5', '21', '10', '15', '25', '25', '0', '0', '0');
INSERT INTO `workload` VALUES ('22', '3', '2020', '6', '18', '14', '3', '20', '17', '0', '0', '0');
INSERT INTO `workload` VALUES ('23', '3', '2020', '7', '20', '10', '12', '24', '22', '0', '1', '0');
INSERT INTO `workload` VALUES ('24', '3', '2020', '8', '15', '4', '6', '15', '10', '0', '0', '0');
