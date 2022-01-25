/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : db_dormsystem

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 28/12/2021 14:55:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_absencerecord
-- ----------------------------
DROP TABLE IF EXISTS `t_absencerecord`;
CREATE TABLE `t_absencerecord`  (
  `recordId` int(6) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `studentId` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `academy` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院',
  `major_and_class` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业班级',
  `dorm_id` int(6) NOT NULL COMMENT '宿舍楼',
  `room_id` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '寝室号',
  `absenceTime` date NOT NULL COMMENT '晚归时间',
  `remark` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`recordId`) USING BTREE,
  INDEX `existsStudentId`(`studentId`) USING BTREE,
  INDEX `esistsAcademy`(`academy`) USING BTREE,
  CONSTRAINT `esistsAcademy` FOREIGN KEY (`academy`) REFERENCES `t_academy` (`academy`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `existsStudentId` FOREIGN KEY (`studentId`) REFERENCES `t_student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_absencerecord
-- ----------------------------

-- ----------------------------
-- Table structure for t_academy
-- ----------------------------
DROP TABLE IF EXISTS `t_academy`;
CREATE TABLE `t_academy`  (
  `academy_id` int(6) NOT NULL,
  `academy` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_effective` int(2) NULL DEFAULT 0,
  PRIMARY KEY (`academy_id`) USING BTREE,
  UNIQUE INDEX `academyUnique`(`academy`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_academy
-- ----------------------------


-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `adminId` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`adminId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin
-- ----------------------------

-- ----------------------------
-- Table structure for t_applyinrecord
-- ----------------------------
DROP TABLE IF EXISTS `t_applyinrecord`;
CREATE TABLE `t_applyinrecord`  (
  `applyId` int(6) NOT NULL AUTO_INCREMENT,
  `applyName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dest_visit_dorm_id` int(6) NOT NULL,
  `visitDest` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `visit_time` datetime NOT NULL,
  `is_effective` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`applyId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_applyinrecord
-- ----------------------------

-- ----------------------------
-- Table structure for t_changeroomrecord
-- ----------------------------
DROP TABLE IF EXISTS `t_changeroomrecord`;
CREATE TABLE `t_changeroomrecord`  (
  `record_id` int(6) NOT NULL AUTO_INCREMENT,
  `applyStudentId` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `applyUserName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `oldRoom_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `newRoom_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `record_time` datetime NOT NULL,
  `applyReason` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isAgree` int(2) NOT NULL DEFAULT 0 COMMENT '0-未审批 1-同意 2-不同意',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_changeroomrecord
-- ----------------------------

-- ----------------------------
-- Table structure for t_dorm
-- ----------------------------
DROP TABLE IF EXISTS `t_dorm`;
CREATE TABLE `t_dorm`  (
  `dorm_id` int(6) NOT NULL,
  `dorm_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宿舍楼名字',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_effective` int(6) NULL DEFAULT NULL,
  PRIMARY KEY (`dorm_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_dorm
-- ----------------------------
INSERT INTO `t_dorm` VALUES (1, '学生第1公寓', '女寝', 0);
INSERT INTO `t_dorm` VALUES (2, '学生第2公寓', '男寝', 0);
INSERT INTO `t_dorm` VALUES (3, '学生第3公寓', '女寝', 0);
INSERT INTO `t_dorm` VALUES (4, '学生第4公寓', '女寝', 0);
INSERT INTO `t_dorm` VALUES (5, '学生第5公寓', '男寝', 0);
INSERT INTO `t_dorm` VALUES (6, '学生第6公寓', '男寝', 0);

-- ----------------------------
-- Table structure for t_dormmaster
-- ----------------------------
DROP TABLE IF EXISTS `t_dormmaster`;
CREATE TABLE `t_dormmaster`  (
  `dormmasterId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
  `userName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宿管名字',
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `dorm_id` int(6) NULL DEFAULT NULL COMMENT '所管辖的宿舍楼',
  `is_effective` int(1) NOT NULL DEFAULT 0 COMMENT '是否有效',
  PRIMARY KEY (`dormmasterId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_dormmaster
-- ----------------------------

-- ----------------------------
-- Table structure for t_repairpeople
-- ----------------------------
DROP TABLE IF EXISTS `t_repairpeople`;
CREATE TABLE `t_repairpeople`  (
  `work_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_effective` int(6) NOT NULL DEFAULT 0,
  PRIMARY KEY (`work_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_repairpeople
-- ----------------------------

-- ----------------------------
-- Table structure for t_repairrecord
-- ----------------------------
DROP TABLE IF EXISTS `t_repairrecord`;
CREATE TABLE `t_repairrecord`  (
  `record_id` int(6) NOT NULL AUTO_INCREMENT,
  `userName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `room_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `record_time` date NOT NULL,
  `repair_remark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_deal` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_repairrecord
-- ----------------------------

-- ----------------------------
-- Table structure for t_room
-- ----------------------------
DROP TABLE IF EXISTS `t_room`;
CREATE TABLE `t_room`  (
  `room_id` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `belong_dormId` int(6) NOT NULL,
  `room_hygiene` int(2) NOT NULL,
  `room_hygiene_remark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`room_id`) USING BTREE,
  INDEX `existsDorm`(`belong_dormId`) USING BTREE,
  CONSTRAINT `existsDorm` FOREIGN KEY (`belong_dormId`) REFERENCES `t_dorm` (`dorm_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_room
-- ----------------------------
INSERT INTO `t_room` VALUES ('5511', 6, 3, '垃圾乱放，被子不跌');
INSERT INTO `t_room` VALUES ('6608', 6, 1, '还好');
INSERT INTO `t_room` VALUES ('6611', 6, 0, '寝室卫生相当整洁');

-- ----------------------------
-- Table structure for t_stayapplication
-- ----------------------------
DROP TABLE IF EXISTS `t_stayapplication`;
CREATE TABLE `t_stayapplication`  (
  `applicationId` int(6) NOT NULL,
  `studentId` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `academy` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major_and_class` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time_remark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请留校时间备注',
  PRIMARY KEY (`applicationId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `studentId` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `userName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `native_place` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `academy` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院',
  `major_and_class` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业班级',
  `dorm_id` int(6) NULL DEFAULT NULL COMMENT '宿舍楼id',
  `room_id` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '寝室号',
  `is_effective` int(1) NOT NULL DEFAULT 0 COMMENT '是否有效',
  PRIMARY KEY (`studentId`) USING BTREE,
  INDEX `exists_dorm`(`dorm_id`) USING BTREE,
  INDEX `exists_academy`(`academy`) USING BTREE,
  CONSTRAINT `exists_dorm` FOREIGN KEY (`dorm_id`) REFERENCES `t_dorm` (`dorm_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_student
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
