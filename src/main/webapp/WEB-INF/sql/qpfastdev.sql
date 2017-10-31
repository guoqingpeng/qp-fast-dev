/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : qpfastdev

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-10-31 08:51:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `pid` int(10) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `urlType` int(1) NOT NULL,
  `url` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `position` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '采购管理', '1', '', '1');
INSERT INTO `menu` VALUES ('2', '0', '考勤管理', '0', 'g2.do', '2');
INSERT INTO `menu` VALUES ('3', '0', '人事管理', '1', '', '3');
INSERT INTO `menu` VALUES ('4', '0', '留学管理', '1', '', '4');
INSERT INTO `menu` VALUES ('5', '0', '考试管理', '1', '', '5');
INSERT INTO `menu` VALUES ('6', '0', '商机管理', '1', '', '6');
INSERT INTO `menu` VALUES ('7', '1', '采购管理', '1', '', '2');
INSERT INTO `menu` VALUES ('8', '1', '采购管理', '1', '', '1');
INSERT INTO `menu` VALUES ('9', '1', '采购管理', '1', '', '3');
INSERT INTO `menu` VALUES ('10', '1', '采购管理1', '0', 'g1.do', '4');
INSERT INTO `menu` VALUES ('11', '6', '采购管理', '0', 'g2.do', '1');
INSERT INTO `menu` VALUES ('12', '6', '采购管理', '0', 'g1.do', '2');
INSERT INTO `menu` VALUES ('13', '6', '采购管理', '0', 'g2.do', '4');
INSERT INTO `menu` VALUES ('14', '6', '采购管理', '0', 'g1.do', '3');
INSERT INTO `menu` VALUES ('15', '6', '采购管理', '0', 'g1.do', '5');
INSERT INTO `menu` VALUES ('16', '3', '采购管理', '0', 'g2.do', '1');
INSERT INTO `menu` VALUES ('17', '4', '采购管理', '0', 'g1.do', '3');
INSERT INTO `menu` VALUES ('18', '4', '采购管理', '0', 'g2.do', '2');
INSERT INTO `menu` VALUES ('19', '4', '采购管理', '0', 'g1.do', '1');
INSERT INTO `menu` VALUES ('20', '5', '采购管理', '0', 'g1.do', '1');
INSERT INTO `menu` VALUES ('21', '5', '采购管理', '0', 'g2.do', '2');
INSERT INTO `menu` VALUES ('22', '6', '采购管理', '0', 'g1.do', '2');
INSERT INTO `menu` VALUES ('23', '6', '采购管理', '0', 'g1.do', '1');
INSERT INTO `menu` VALUES ('24', '7', '采购管理', '0', 'g2.do', '4');
INSERT INTO `menu` VALUES ('25', '7', '采购管理', '0', 'g1.do', '5');
INSERT INTO `menu` VALUES ('26', '8', '采购管理', '0', 'g1.do', '3');
INSERT INTO `menu` VALUES ('27', '8', '采购管理', '0', 'g2.do', '2');
INSERT INTO `menu` VALUES ('28', '8', '采购管理', '0', 'g2.do', '4');
INSERT INTO `menu` VALUES ('29', '8', '采购管理', '0', 'g2.do', '1');
INSERT INTO `menu` VALUES ('30', '9', '采购管理', '0', 'g1.do', '1');
INSERT INTO `menu` VALUES ('31', '10', 'gfhf', '0', null, null);
