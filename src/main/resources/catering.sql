/*
 Navicat Premium Data Transfer

 Source Server         : mysql_local
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : catering

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 01/05/2019 22:20:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cooking
-- ----------------------------
DROP TABLE IF EXISTS `cooking`;
CREATE TABLE `cooking`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `available_flag` bit(1) DEFAULT NULL,
  `cooking_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `style_of_cooking_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `storage_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cooking
-- ----------------------------
INSERT INTO `cooking` VALUES ('1122533974158082048', b'0', '11188', NULL, '1122534030927986688', '2019-04-29 00:12:10', NULL, NULL);
INSERT INTO `cooking` VALUES ('1122536773411405824', b'0', '11', 1137, '1122534030927986688', '2019-04-29 00:23:17', '112333', NULL);
INSERT INTO `cooking` VALUES ('1122540729466355712', b'0', '铁板烧2', 11, '1122876142513029120', '2019-04-29 00:39:00', '11', '1122540802027814912');
INSERT INTO `cooking` VALUES ('1122879588230234112', b'0', '铁板烧1', 12, '1122876142513029120', '2019-04-29 23:05:31', '111', '1122879587492036608');
INSERT INTO `cooking` VALUES ('1122879656060518400', b'0', '铁板烧3', 3, '1122876142513029120', '2019-04-29 23:05:47', '3', '1122879655494287360');
INSERT INTO `cooking` VALUES ('1122879703732977664', b'0', '铁板烧4', 12, '1122876142513029120', '2019-04-29 23:05:58', '12', '1122879703221272576');
INSERT INTO `cooking` VALUES ('1122880491624595456', b'0', '鱼香茄子', 12, '1122554406106038272', '2019-04-29 23:09:06', '12', '1122880491117084672');
INSERT INTO `cooking` VALUES ('1122880543344558080', b'0', '梅菜扣肉', 33, '1122554406106038272', '2019-04-29 23:09:18', '33', '1122880542841241600');
INSERT INTO `cooking` VALUES ('1122880611065790464', b'0', '酸菜大肠', 3, '1122554406106038272', '2019-04-29 23:09:34', '3', '1122880610524725248');
INSERT INTO `cooking` VALUES ('1122880669337255936', b'0', '酿苦瓜', 33, '1122554406106038272', '2019-04-29 23:09:48', '33', '1123476036495867904');
INSERT INTO `cooking` VALUES ('1123587644412919808', b'1', '333', 33, '1122876142513029120', '2019-05-01 21:59:04', '3', '1123587643318206464');
INSERT INTO `cooking` VALUES ('1123587698058067968', b'1', '444', 44, '1122876072522678272', '2019-05-01 21:59:17', '4', '1123587697558945792');

-- ----------------------------
-- Table structure for dining_table
-- ----------------------------
DROP TABLE IF EXISTS `dining_table`;
CREATE TABLE `dining_table`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dining_table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available_flag` bit(1) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `order_no` double DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dining_table
-- ----------------------------
INSERT INTO `dining_table` VALUES ('1123587319970922496', '33', '1', b'1', '2019-05-01 21:57:47', 3);
INSERT INTO `dining_table` VALUES ('1122554188195168256', '1', '1', b'1', '2019-04-29 01:32:29', 1);
INSERT INTO `dining_table` VALUES ('1122554201986039808', '2', '1', b'0', '2019-04-29 01:32:32', 2);

-- ----------------------------
-- Table structure for order_dishes_record
-- ----------------------------
DROP TABLE IF EXISTS `order_dishes_record`;
CREATE TABLE `order_dishes_record`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_dining_table_record_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cooking_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_dishes_time` datetime(0) DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_dishes_record
-- ----------------------------
INSERT INTO `order_dishes_record` VALUES ('1123592199162822656', '1123592161036599296', '1123587698058067968', '2019-05-01 22:17:10', '2');
INSERT INTO `order_dishes_record` VALUES ('1123592199158628352', '1123592161036599296', '1123587698058067968', '2019-05-01 22:17:10', '2');
INSERT INTO `order_dishes_record` VALUES ('1123587762667126784', '1123587729095917568', '1123587698058067968', '2019-05-01 21:59:33', '2');
INSERT INTO `order_dishes_record` VALUES ('1123587762658738176', '1123587729095917568', '1123587644412919808', '2019-05-01 21:59:33', '2');
INSERT INTO `order_dishes_record` VALUES ('1123476246005547008', '1123476231438729216', '1122880669337255936', '2019-05-01 14:36:25', '2');

-- ----------------------------
-- Table structure for place_order_record
-- ----------------------------
DROP TABLE IF EXISTS `place_order_record`;
CREATE TABLE `place_order_record`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_time` datetime(0) DEFAULT NULL,
  `consume` double DEFAULT NULL,
  `dining_table_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dining_table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `settle_account_time` datetime(0) DEFAULT NULL,
  `available_flag` bit(1) DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of place_order_record
-- ----------------------------
INSERT INTO `place_order_record` VALUES ('1123592161036599296', '2019-05-01 22:17:01', 88, '1123587319970922496', '33', '1123592161036599297', '2019-05-01 22:17:18', b'1', '4');
INSERT INTO `place_order_record` VALUES ('1123587729095917568', '2019-05-01 21:59:25', 77, '1123587319970922496', '33', '1123587729095917569', '2019-05-01 21:59:56', b'1', '4');
INSERT INTO `place_order_record` VALUES ('1123476231438729216', '2019-05-01 14:36:21', 33, '1122554188195168256', '1', '1123476231438729217', '2019-05-01 21:48:17', b'1', '4');
INSERT INTO `place_order_record` VALUES ('1123476086496165888', '2019-05-01 14:35:47', NULL, '1122554188195168256', '1', '1123476086496165889', NULL, b'1', '1');
INSERT INTO `place_order_record` VALUES ('1123475876973903872', '2019-05-01 14:34:57', NULL, '1122554188195168256', '1', '1123475876973903873', NULL, b'1', '1');
INSERT INTO `place_order_record` VALUES ('1123475567132278784', '2019-05-01 14:33:43', NULL, '1122554201986039808', '2', '1123475567132278785', NULL, b'1', '1');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `associate_biz` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `associate_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `upload_time` datetime(0) DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES ('1123587697558945792', NULL, NULL, '65091eebc48899298171c2eb6696fe27.jpg', 179318, 'image/jpeg', '2019-05-01 21:59:17', NULL, NULL);
INSERT INTO `storage` VALUES ('1123587643318206464', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-05-01 21:59:04', NULL, NULL);
INSERT INTO `storage` VALUES ('1123476036495867904', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-05-01 14:35:35', NULL, 0);

-- ----------------------------
-- Table structure for style_of_cooking
-- ----------------------------
DROP TABLE IF EXISTS `style_of_cooking`;
CREATE TABLE `style_of_cooking`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `available_flag` bit(1) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `style_of_cooking_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_no` double DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of style_of_cooking
-- ----------------------------
INSERT INTO `style_of_cooking` VALUES ('1122527420184788992', b'0', '2019-04-28 23:46:07', '6667', NULL);
INSERT INTO `style_of_cooking` VALUES ('1122528255845335040', b'0', '2019-04-28 23:49:26', '333', NULL);
INSERT INTO `style_of_cooking` VALUES ('1122534030927986688', b'0', '2019-04-29 00:12:23', '666', NULL);
INSERT INTO `style_of_cooking` VALUES ('1122554387395248128', b'1', '2019-04-29 01:33:17', '招牌菜', 1);
INSERT INTO `style_of_cooking` VALUES ('1122554406106038272', b'1', '2019-04-29 01:33:21', '粤式小吃', 2);
INSERT INTO `style_of_cooking` VALUES ('1122876072522678272', b'1', '2019-04-29 22:51:32', '各式炒菜', 3);
INSERT INTO `style_of_cooking` VALUES ('1122876142513029120', b'1', '2019-04-29 22:51:49', '铁板烧', 4);
INSERT INTO `style_of_cooking` VALUES ('1123587387767652352', b'0', '2019-05-01 21:58:03', 'dfdfa', 5);

SET FOREIGN_KEY_CHECKS = 1;
