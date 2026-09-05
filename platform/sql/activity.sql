/*
 Navicat Premium Data Transfer

 Source Server         : ry-vue
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : ry-vue

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 25/03/2023 07:55:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `activity_id` int NOT NULL COMMENT '交易主键id',
  `nft_id` int NULL DEFAULT NULL COMMENT '外键',
  `price_usd` double NULL DEFAULT NULL COMMENT 'price 美元',
  `price_eth` double NULL DEFAULT NULL COMMENT 'price 以太坊',
  `held` datetime NULL DEFAULT NULL COMMENT '交易发生时间',
  `seller` int NULL DEFAULT NULL COMMENT '外键 卖家id',
  `buyer` int NULL DEFAULT NULL COMMENT '外键 买家id',
  PRIMARY KEY (`activity_id`) USING BTREE,
  INDEX `nft`(`nft_id` ASC) USING BTREE,
  INDEX `seller`(`seller` ASC) USING BTREE,
  INDEX `buyer`(`buyer` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
