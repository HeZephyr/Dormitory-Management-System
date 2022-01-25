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
INSERT INTO `t_absencerecord` VALUES (1, '5720201402', '陈冠华', '软件工程学院', '软件开发181班', 6, '6611', '2020-01-01', '而非');
INSERT INTO `t_absencerecord` VALUES (2, '5720201404', '刘建龙', '能源与机械学院', '软件开发181班', 6, '6608', '2021-01-06', '晚上学习王辉亲了饶请伟若群');

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
INSERT INTO `t_academy` VALUES (1, '软件工程学院', 0);
INSERT INTO `t_academy` VALUES (2, '能源与机械学院', 0);
INSERT INTO `t_academy` VALUES (3, '商学院', 0);

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
INSERT INTO `t_admin` VALUES ('admin', '超级管理员', '111');

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
INSERT INTO `t_applyinrecord` VALUES (1, '陈冠沙发', 4, '发送', '2021-01-08 14:37:48', 1);
INSERT INTO `t_applyinrecord` VALUES (2, '沙发', 3, '发啊生', '2021-01-08 14:43:52', 0);
INSERT INTO `t_applyinrecord` VALUES (3, '还款卡', 3, '加不加', '2021-01-08 15:11:59', 0);
INSERT INTO `t_applyinrecord` VALUES (4, '李顺根', 1, '阿萨法', '2021-01-09 14:30:28', 0);
INSERT INTO `t_applyinrecord` VALUES (5, '李鸿章', 1, '沙发', '2021-01-09 14:35:50', 0);
INSERT INTO `t_applyinrecord` VALUES (6, '啊啊', 1, '阿发', '2021-01-09 14:46:03', 0);
INSERT INTO `t_applyinrecord` VALUES (7, '', 1, '发顺丰', '2021-01-09 14:46:25', 1);

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
INSERT INTO `t_changeroomrecord` VALUES (1, '5720181423', '王顺根', '6611', '5511', '2021-01-08 14:26:29', '士大夫撒', 1);
INSERT INTO `t_changeroomrecord` VALUES (2, '5720181423', '王顺根', '5511', '4411', '2021-01-09 15:16:11', '想换个地方', 0);

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
INSERT INTO `t_dormmaster` VALUES ('000001', '陈思敏', '000001', '女', '13907944444', 1, 0);
INSERT INTO `t_dormmaster` VALUES ('000002', '程军', '000002', '男', '91110', 2, 1);
INSERT INTO `t_dormmaster` VALUES ('000003', '李华', '000003', '男', '432431', 6, 1);
INSERT INTO `t_dormmaster` VALUES ('000004', '李焕英', '000004', '女', '123455', 4, 0);
INSERT INTO `t_dormmaster` VALUES ('000005', '刘东海', '000005', '男', '6876886', 5, 0);
INSERT INTO `t_dormmaster` VALUES ('000006', '程欢欢', '000006', '女', '5221', 3, 0);

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
INSERT INTO `t_repairpeople` VALUES ('001', '王工', '111', 0);

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
INSERT INTO `t_repairrecord` VALUES (1, '陈冠华', '6611', '2021-01-07', '上厕所把厕所堵了', 1);
INSERT INTO `t_repairrecord` VALUES (2, '杨永琪', '6611', '2021-01-07', '阿发', 1);
INSERT INTO `t_repairrecord` VALUES (3, '离去南', '6611', '2021-01-08', '沙发坏了', 1);
INSERT INTO `t_repairrecord` VALUES (4, '刘淑仪', '6611', '2021-01-08', '第三方分散', 0);

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
INSERT INTO `t_student` VALUES ('5720191408', '何志飞', '5720191408', '男', '133338', '江西景德镇', '软件工程学院', '软件开发191班', 5, '5529', 0);
INSERT INTO `t_student` VALUES ('5720191425', '孙欣', '5720191425', '女', '18807911234', '北京', '软件工程学院', '软件开发191班', 3, '3323', 0);
INSERT INTO `t_student` VALUES ('5720191427', '李焕英', '5720191426', '女', '123455', '江西永丰', '商学院', '软件造价191班', 4, '4215', 0);
INSERT INTO `t_student` VALUES ('5720191434', '尚志浩', '5720191434', '男', '188882', '江西赣州', '软件工程学院', '软件开发191班', 5, '5430', 0);
INSERT INTO `t_student` VALUES ('5720191530', '陈思敏', '5720191530', '女', '13907944444', '江苏南京', '商学院', '软件会计191班', 1, '1425', 0);
INSERT INTO `t_student` VALUES ('5720191533', '程潇', '5720191533', '女', '17756', '江西上饶', '软件工程学院', '软件电商191班', 1, '1403', 0);
INSERT INTO `t_student` VALUES ('5720191908', '刘薇', '5720191908', '女', '1233445', '江西南昌', '能源与机械学院', '能源动力191班', 3, '3423', 0);
INSERT INTO `t_student` VALUES ('5720201401', '蔡伟', '5720201401', '男', '13907933333', '湖南娄底', '软件工程学院', '软件开发201班', 6, '6529', 0);
INSERT INTO `t_student` VALUES ('5720201402', '陈冠华', '5720181402', '男', '3325325', '江西吉安', '软件工程学院', '软件开发201班', 6, '6611', 0);
INSERT INTO `t_student` VALUES ('5720201403', '李华', '5720201403', '男', '432431', '江西吉安', '软件工程学院', '软件开发201班', 6, '6611', 0);
INSERT INTO `t_student` VALUES ('5720201404', '刘建龙', '5720201404', '男', '313212412', '江西川水', '能源与机械学院', '软件电气201班', 6, '6608', 0);
INSERT INTO `t_student` VALUES ('5720201405', '刘璋', '5720201405', '男', '3121414', '江西吉安', '能源与机械学院', '能源动力201班', 6, '6611', 0);
INSERT INTO `t_student` VALUES ('5720201411', '张国瑞', '5720201411', '男', '13807922222', '湖北荆州', '软件工程学院', '虚拟现实201班', 6, '6213', 0);
INSERT INTO `t_student` VALUES ('5720201424', '魏延', '5720201424', '男', '313131', '江西临川', '软件工程学院', '软件开发201班', 3, '6609', 0);
INSERT INTO `t_student` VALUES ('5720201425', '戴鑫', '5720201425', '女', '3121314211', '江西吉安', '商学院', '软件电商201班', 1, '1706', 0);
INSERT INTO `t_student` VALUES ('5720201426', '杨永琪', '5720182026', '男', '1421414', '江西吉安', '软件工程学院', '软件开发201班', 6, '6611', 1);
INSERT INTO `t_student` VALUES ('5720201427', '杨永琪', '5720120427', '男', '21312312', '甘肃天水', '软件工程学院', '软件开发201班', 6, '6611', 0);
INSERT INTO `t_student` VALUES ('5720211303', '陈冠熙', '5720211303', '男', '18907011111', '福建厦门', '软件工程学院', '机器人211班', 2, '2234', 0);
INSERT INTO `t_student` VALUES ('5720211431', '尹一平', '5720211431', '男', '11199', '山西太原', '软件工程学院', '软件开发211班', 5, '5123', 0);
INSERT INTO `t_student` VALUES ('5720211611', '黄景林', '5720211611', '男', '1456790', '广东深圳', '能源与机械学院', '软件电气211班', 5, '5411', 0);
INSERT INTO `t_student` VALUES ('5720211622', '程欢欢', '5720211622', '女', '5221', '浙江杭州', '商学院', '电子商务211班', 3, '3101', 0);
INSERT INTO `t_student` VALUES ('5720211632', '杨慧慧', '5720211632', '女', '5220', '内蒙古', '商学院', '电子商务211班', 3, '3101', 0);
INSERT INTO `t_student` VALUES ('5720211712', '程军', '5720211712', '男', '91110', '广西南宁', '商学院', '电子商务211班', 2, '2421', 0);

SET FOREIGN_KEY_CHECKS = 1;
