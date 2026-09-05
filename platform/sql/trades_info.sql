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

 Date: 05/03/2023 09:55:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for trades_info
-- ----------------------------
DROP TABLE IF EXISTS `trades_info`;
CREATE TABLE `trades_info`  (
  `transaction_id` int NOT NULL AUTO_INCREMENT COMMENT '交易的唯一id',
  `launderer_id` int NOT NULL COMMENT '洗钱用户的id',
  `consumer_id` int NOT NULL COMMENT '消费用户的id',
  `transaction_type` int NOT NULL COMMENT '两种类型，0表示人民币交易，1表示游戏道具交易',
  `transaction_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '人民币交易的金额',
  `transaction_platform` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人民币交易平台',
  `game_id` int NULL DEFAULT NULL COMMENT '游戏道具交易的游戏id',
  `game_item` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '交易的游戏道具',
  `transaction_time` datetime NOT NULL COMMENT '交易时间',
  PRIMARY KEY (`transaction_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
