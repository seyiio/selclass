/*
 Navicat Premium Data Transfer

 Source Server         : 1
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : selclass

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 08/03/2023 18:09:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class`  (
  `classname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `teacher` bigint NULL DEFAULT NULL,
  `point` double(10, 2) NULL DEFAULT NULL,
  `classid` int NOT NULL,
  `number` int NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `teachername` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `selected` int NULL DEFAULT NULL,
  PRIMARY KEY (`classid`) USING BTREE,
  INDEX `teacher`(`teacher` ASC) USING BTREE,
  INDEX `teachername`(`teachername` ASC) USING BTREE,
  CONSTRAINT `teacher` FOREIGN KEY (`teacher`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teachername` FOREIGN KEY (`teachername`) REFERENCES `t_user` (`name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES ('C语言课程设计', 3, 2.00, 0, 120, '第1-16周周三第二节 第1-16周周五第三节', '李四', 0);
INSERT INTO `t_class` VALUES ('操作系统', 3, 2.00, 1, 120, '第1-16周周二第二节 第1-16周周五第二节', '李四', 0);

-- ----------------------------
-- Table structure for t_cs
-- ----------------------------
DROP TABLE IF EXISTS `t_cs`;
CREATE TABLE `t_cs`  (
  `studentid` bigint NOT NULL,
  `classid` int NOT NULL,
  `fraction` double NULL DEFAULT NULL,
  INDEX `classid`(`classid` ASC) USING BTREE,
  INDEX `id`(`studentid` ASC) USING BTREE,
  CONSTRAINT `classid` FOREIGN KEY (`classid`) REFERENCES `t_class` (`classid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `id` FOREIGN KEY (`studentid`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cs
-- ----------------------------
INSERT INTO `t_cs` VALUES (2, 0, NULL);
INSERT INTO `t_cs` VALUES (2, 1, NULL);

-- ----------------------------
-- Table structure for t_time
-- ----------------------------
DROP TABLE IF EXISTS `t_time`;
CREATE TABLE `t_time`  (
  `starttime` datetime NULL DEFAULT NULL,
  `endtime` datetime NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_time
-- ----------------------------
INSERT INTO `t_time` VALUES ('2023-03-01 00:00:00', '2023-03-11 00:00:00');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `faculty` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `grade` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '$2a$10$bOPhevbeIHBZKNsTlNuwnO.GKjHozupK8KJEBcUKx8WmHkKsgAWu2', 'admin', '管理员', '信息学院', '5', '11111111', '0', 'admin');
INSERT INTO `t_user` VALUES (2, '$2a$10$bOPhevbeIHBZKNsTlNuwnO.GKjHozupK8KJEBcUKx8WmHkKsgAWu2', '123456', '计算机', '信息学院', '3', '123456789', '3', '张三');
INSERT INTO `t_user` VALUES (3, '$2a$10$bOPhevbeIHBZKNsTlNuwnO.GKjHozupK8KJEBcUKx8WmHkKsgAWu2', '11', '教师', '信息学院', '0', '112', '2', '李四');

SET FOREIGN_KEY_CHECKS = 1;
