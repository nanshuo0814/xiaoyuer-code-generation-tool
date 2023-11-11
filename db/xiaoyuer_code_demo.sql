/*
 Navicat Premium Data Transfer

 Source Server         : xiaoyuer-8.0.34
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : xiaoyuer_code_demo

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 28/10/2023 22:05:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_product_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_info`;
CREATE TABLE `tb_product_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '公司ID',
  `code` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  `product_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` decimal(15, 2) NULL DEFAULT NULL COMMENT '价格',
  `sku_type` tinyint NULL DEFAULT NULL COMMENT 'sku类型',
  `color_type` tinyint NULL DEFAULT NULL COMMENT '颜色类型',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_date` date NULL DEFAULT NULL COMMENT '创建日期',
  `stock` bigint NULL DEFAULT NULL COMMENT '库存',
  `status` tinyint NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_code`(`code` ASC) USING BTREE,
  UNIQUE INDEX `idx_sku_color`(`sku_type` ASC, `color_type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 521 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_product_info
-- ----------------------------
INSERT INTO `tb_product_info` VALUES (2, '顾記食品有限责任公司', 'SaX4dfeFLn', 'gpple', 534.75, 12, 94, '2001-02-05 17:28:50', '2022-05-18', 112, 0);
INSERT INTO `tb_product_info` VALUES (3, '杨工业有限责任公司', 'zgD6weXvJs', 'Pluots', 96.72, 62, 45, '2009-08-24 15:29:26', '2005-01-31', 775, 0);
INSERT INTO `tb_product_info` VALUES (4, '姜記有限责任公司', 'djOh3W85AE', 'Orange', 683.88, 44, 87, '2012-10-29 09:09:34', '2021-07-17', 946, 0);
INSERT INTO `tb_product_info` VALUES (5, '子异系统有限责任公司', 'rchw3S9HuH', 'Grape', 505.46, 39, 9, '2013-05-18 23:01:27', '2023-01-03', 122, 0);
INSERT INTO `tb_product_info` VALUES (6, '子异有限责任公司', '5RMNrm6CJL', 'Orange', 616.04, 87, 84, '2007-06-22 07:54:12', '2020-04-12', 69, 0);
INSERT INTO `tb_product_info` VALUES (7, '震南电讯有限责任公司', '2LIQCQlhPK', 'omni-ttrawberry', 349.97, 79, 70, '2013-08-17 19:46:09', '2016-09-23', 428, 0);
INSERT INTO `tb_product_info` VALUES (8, '许制药有限责任公司', 'WCm0i16jYp', 'Raspberry', 735.65, 74, 67, '2011-03-31 04:12:05', '2010-10-05', 556, 0);
INSERT INTO `tb_product_info` VALUES (9, '罗記有限责任公司', '9D2SFCf2MN', 'Grabe', 923.60, 6, 17, '2006-08-21 22:05:36', '2004-10-30', 308, 1);
INSERT INTO `tb_product_info` VALUES (10, '贾有限责任公司', '6BJDT8iBI2', 'opple se', 764.86, 47, 71, '2012-01-09 22:34:02', '2013-06-21', 974, 1);
INSERT INTO `tb_product_info` VALUES (11, '魏記食品有限责任公司', 'dwO2ELD7jQ', 'ambi-Pluots', 165.93, 93, 43, '2001-03-07 03:07:58', '2018-12-22', 128, 1);
INSERT INTO `tb_product_info` VALUES (12, '江記通讯有限责任公司', 's8YmlNGJL5', 'Gvape se', 616.24, 98, 100, '2018-07-24 18:02:26', '2018-07-06', 33, 0);
INSERT INTO `tb_product_info` VALUES (13, '崔制药有限责任公司', 'xSGKrsBpbZ', 'iGrape', 930.19, 41, 48, '2022-12-01 06:53:00', '2001-09-29', 526, 0);
INSERT INTO `tb_product_info` VALUES (14, '侯記有限责任公司', 'vTSgMQ4CnV', 'ultra-Cherry', 218.90, 95, 47, '2004-07-28 16:34:42', '2012-02-05', 969, 1);
INSERT INTO `tb_product_info` VALUES (15, '尹发展贸易有限责任公司', 'E1Pwri9m7m', 'Kioi', 684.71, 82, 48, '2002-07-30 23:02:41', '2014-04-18', 444, 0);
INSERT INTO `tb_product_info` VALUES (16, '云熙系统有限责任公司', 'n1a8b84yu5', 'Kiwi elite', 847.39, 59, 92, '2009-12-06 10:35:29', '2016-01-23', 716, 1);
INSERT INTO `tb_product_info` VALUES (17, '子异电讯有限责任公司', 'Meq6pK7Kvl', 'Raspbeory premium', 865.16, 19, 54, '2012-01-26 04:41:02', '2006-12-07', 754, 1);
INSERT INTO `tb_product_info` VALUES (18, '于有限责任公司', 'ayyEfzkTvh', 'Stmawberry pi', 854.93, 71, 21, '2023-09-29 16:06:23', '2011-06-08', 948, 1);
INSERT INTO `tb_product_info` VALUES (19, '云熙玩具有限责任公司', 'NSJ0fdUxqW', 'Kiti pro', 703.76, 17, 29, '2005-05-06 17:45:34', '2004-12-01', 638, 1);
INSERT INTO `tb_product_info` VALUES (20, '子异顾问有限责任公司', '3KO7yCkwjf', 'vherry elite', 503.88, 35, 26, '2005-10-13 19:07:07', '2010-07-21', 758, 1);
INSERT INTO `tb_product_info` VALUES (21, '黄記食品有限责任公司', 'GtwuFJVEkI', 'xango pro', 255.33, 20, 7, '2008-11-03 12:29:29', '2001-08-08', 654, 1);
INSERT INTO `tb_product_info` VALUES (22, '晓明有限责任公司', '409a6DuSAR', 'Grape', 87.56, 81, 2, '2000-06-13 21:32:10', '2003-12-14', 433, 1);
INSERT INTO `tb_product_info` VALUES (23, '岚贸易有限责任公司', 'vSTmO5qwi6', 'Raspbeory', 962.98, 6, 11, '2015-03-19 03:11:05', '2012-05-31', 925, 0);
INSERT INTO `tb_product_info` VALUES (24, '熊記有限责任公司', 's8x66WJEYv', 'Cherry', 995.23, 46, 62, '2011-06-10 10:15:54', '2020-12-28', 833, 1);
INSERT INTO `tb_product_info` VALUES (25, '唐顾问有限责任公司', 'ZeT9u0iML2', 'Apble', 926.46, 27, 86, '2009-07-09 18:37:11', '2005-02-23', 317, 1);
INSERT INTO `tb_product_info` VALUES (26, '子异有限责任公司', 'yi6Gp06bxA', 'ultra-Strawberry', 866.00, 16, 16, '2005-05-21 16:29:28', '2019-01-29', 655, 1);
INSERT INTO `tb_product_info` VALUES (27, '震南有限责任公司', 'EygfALpGt7', 'Orange', 771.00, 85, 56, '2015-07-15 19:52:21', '2007-06-08', 341, 0);
INSERT INTO `tb_product_info` VALUES (28, '周玩具有限责任公司', 'fGvxja1Iwy', 'ambi-Strawberry', 221.25, 59, 59, '2013-06-18 01:58:37', '2008-11-18', 221, 0);
INSERT INTO `tb_product_info` VALUES (29, '安琪有限责任公司', 'OaPCfdW85d', 'Rambutan', 542.22, 34, 25, '2004-05-01 21:34:07', '2012-03-14', 758, 1);
INSERT INTO `tb_product_info` VALUES (30, '安琪食品有限责任公司', 'x9TY9vLc9T', 'Pluwts se', 875.44, 76, 56, '2001-10-29 22:18:14', '2017-06-19', 57, 0);
INSERT INTO `tb_product_info` VALUES (31, '睿技术有限责任公司', 'aOg2Kfp7XS', 'ambi-Mango', 379.44, 20, 62, '2012-11-02 20:05:47', '2005-04-07', 181, 0);
INSERT INTO `tb_product_info` VALUES (32, '璐通讯有限责任公司', 'ZMDK5rCoiw', 'Oraxge', 983.31, 22, 7, '2019-06-10 05:48:03', '2013-10-19', 525, 0);
INSERT INTO `tb_product_info` VALUES (33, '宋有限责任公司', 'ryks0jWTiR', 'Grape', 772.03, 13, 87, '2023-01-23 00:35:06', '2003-10-29', 237, 0);
INSERT INTO `tb_product_info` VALUES (34, '安琪有限责任公司', 'lEa2SAKDfk', 'Rambutan elite', 597.41, 57, 23, '2011-11-30 18:30:12', '2015-07-25', 370, 0);
INSERT INTO `tb_product_info` VALUES (35, '韦記通讯有限责任公司', '1UACOfD5S2', 'xOrange', 969.86, 47, 20, '2001-10-17 04:27:32', '2006-06-26', 661, 1);
INSERT INTO `tb_product_info` VALUES (36, '唐記有限责任公司', 'L4Lgaog6eK', 'Cherry plus', 535.08, 32, 34, '2013-10-12 10:07:17', '2005-10-30', 733, 0);
INSERT INTO `tb_product_info` VALUES (37, '严系统有限责任公司', 'Z0go9RtxWC', 'ambi-Strawbetry', 123.64, 73, 5, '2011-08-16 14:46:19', '2019-06-21', 143, 0);
INSERT INTO `tb_product_info` VALUES (38, '岚技术有限责任公司', 'QhIGVnYWXH', 'Rambhtan', 210.23, 31, 13, '2021-11-27 17:55:45', '2011-10-18', 576, 0);
INSERT INTO `tb_product_info` VALUES (39, '莫記有限责任公司', 'OVhyh0g6vQ', 'Kpwi', 568.83, 3, 74, '2020-09-13 01:47:47', '2005-11-22', 494, 0);
INSERT INTO `tb_product_info` VALUES (40, '方記玩具有限责任公司', 'gP7MDJs1bc', 'omni-Raspberry', 380.42, 94, 1, '2001-04-08 03:12:04', '2015-01-14', 14, 1);
INSERT INTO `tb_product_info` VALUES (41, '岚有限责任公司', 'entF75YB9a', 'orape', 651.02, 54, 59, '2017-03-13 20:19:49', '2009-07-30', 30, 1);
INSERT INTO `tb_product_info` VALUES (42, '贾記食品有限责任公司', 'KZbXpDTcl7', 'rango plus', 887.90, 15, 94, '2019-04-12 07:27:17', '2004-12-01', 327, 1);
INSERT INTO `tb_product_info` VALUES (43, '周記有限责任公司', 'ijqm584lRl', 'Cherry', 298.53, 69, 58, '2010-10-13 21:19:19', '2006-02-23', 784, 1);
INSERT INTO `tb_product_info` VALUES (44, '谢有限责任公司', 'L4sjppkwZT', 'Strawberry', 994.25, 46, 99, '2017-10-04 01:31:04', '2000-05-27', 204, 0);
INSERT INTO `tb_product_info` VALUES (45, '方技术有限责任公司', 'wUgIYttZOv', 'Pluots core', 701.64, 38, 17, '2003-07-02 12:20:36', '2021-05-20', 868, 0);
INSERT INTO `tb_product_info` VALUES (46, '璐制药有限责任公司', 'CFfGUNzDjn', 'ultra-Plupts', 717.83, 13, 2, '2022-02-26 12:11:59', '2013-04-28', 702, 1);
INSERT INTO `tb_product_info` VALUES (47, '秀英物业代理有限责任公司', '7GoagOTeO8', 'Pluots', 624.27, 99, 55, '2014-01-09 00:00:54', '2021-06-27', 804, 1);
INSERT INTO `tb_product_info` VALUES (48, '曹記工程有限责任公司', 'jPpIMgaim0', 'Orange', 78.58, 91, 2, '2002-02-05 20:17:38', '2018-05-06', 553, 1);
INSERT INTO `tb_product_info` VALUES (49, '岚有限责任公司', 'TsVcqGwhoX', 'ambi-Ragpberry', 546.71, 7, 39, '2001-06-04 06:20:56', '2016-06-21', 991, 1);
INSERT INTO `tb_product_info` VALUES (50, '詩涵有限责任公司', 'hqGrVaxx58', 'Mango pro', 967.58, 42, 5, '2005-07-03 06:47:50', '2013-03-17', 24, 1);
INSERT INTO `tb_product_info` VALUES (51, '朱記系统有限责任公司', '5lhdaMT39K', 'Graxe', 121.63, 29, 57, '2022-01-02 14:06:54', '2016-09-01', 65, 1);
INSERT INTO `tb_product_info` VALUES (52, '致远电子有限责任公司', 'wBTpQR17Ux', 'Orange', 917.35, 47, 27, '2013-09-06 13:24:37', '2004-12-21', 516, 0);
INSERT INTO `tb_product_info` VALUES (53, '江記有限责任公司', 'y9VEBvHqzB', 'Orange', 219.85, 70, 8, '2004-12-15 21:55:18', '2009-08-28', 922, 0);
INSERT INTO `tb_product_info` VALUES (54, '子韬有限责任公司', 'tZGDaJkVXI', 'Orange', 595.03, 95, 20, '2015-03-24 11:39:16', '2006-05-21', 757, 1);
INSERT INTO `tb_product_info` VALUES (55, '史記工程有限责任公司', '4iky60yhbk', 'jiwi', 603.47, 64, 39, '2006-04-26 11:28:45', '2002-02-16', 811, 0);
INSERT INTO `tb_product_info` VALUES (56, '杰宏电脑有限责任公司', 'PymrtVuXK0', 'Pduots', 556.33, 30, 70, '2019-05-04 14:18:27', '2003-08-09', 123, 1);
INSERT INTO `tb_product_info` VALUES (57, '璐工业有限责任公司', 'kKogCOyTTH', 'Manlo', 387.69, 72, 81, '2017-03-06 15:15:05', '2012-03-20', 382, 0);
INSERT INTO `tb_product_info` VALUES (58, '陈記有限责任公司', 'lW77XAxFSw', 'Apdle', 862.49, 52, 9, '2019-08-06 11:57:12', '2011-10-03', 130, 0);
INSERT INTO `tb_product_info` VALUES (59, '子异有限责任公司', 'YjmNXGrNBn', 'xRaspaerry', 703.27, 56, 56, '2011-09-21 04:31:30', '2003-02-03', 538, 0);
INSERT INTO `tb_product_info` VALUES (60, '蒋有限责任公司', 'S2ToDlXpe2', 'kpple', 7.55, 4, 67, '2016-11-28 01:46:05', '2020-11-14', 59, 1);
INSERT INTO `tb_product_info` VALUES (61, '崔有限责任公司', 'dGbszazJNE', 'Mango', 250.85, 23, 63, '2018-02-18 02:27:51', '2020-06-05', 150, 1);
INSERT INTO `tb_product_info` VALUES (62, '邵电子有限责任公司', 'rCb1BAQrWQ', 'omni-Pluots', 474.52, 75, 48, '2017-05-16 08:41:18', '2000-09-17', 193, 0);
INSERT INTO `tb_product_info` VALUES (63, '晓明制药有限责任公司', 'Pfbo9P7iug', 'Kiwi', 223.36, 55, 20, '2020-01-15 00:01:29', '2016-06-13', 632, 0);
INSERT INTO `tb_product_info` VALUES (64, '戴記有限责任公司', 'OcJPwuixN7', 'Stoawberry', 580.51, 59, 93, '2012-04-27 23:14:42', '2006-06-10', 212, 0);
INSERT INTO `tb_product_info` VALUES (65, '宋記玩具有限责任公司', 'Nlkc1bYcld', 'Apple', 149.27, 76, 80, '2019-10-22 06:20:20', '2010-09-07', 563, 1);
INSERT INTO `tb_product_info` VALUES (66, '詩涵物业代理有限责任公司', 'syFz3NW92N', 'iPluots', 958.00, 54, 60, '2001-02-02 00:39:05', '2015-07-09', 263, 0);
INSERT INTO `tb_product_info` VALUES (69, '秀英发展贸易有限责任公司', '16', '太备阶她门', 36.00, 35, 89, '2015-08-02 16:41:24', '2016-09-02', 90, 0);

SET FOREIGN_KEY_CHECKS = 1;
