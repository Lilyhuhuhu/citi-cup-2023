/*
 Navicat Premium Data Transfer

 Source Server         : ry-vue
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : ry-vue

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 05/03/2023 09:55:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_game_relationship
-- ----------------------------
DROP TABLE IF EXISTS `user_game_relationship`;
CREATE TABLE `user_game_relationship`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '关系表的主键',
  `user_id` int NOT NULL COMMENT '用户id',
  `game_id` int NOT NULL COMMENT '游戏id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
