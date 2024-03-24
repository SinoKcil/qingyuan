/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : software1

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 18/03/2024 23:38:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for abnormalities
-- ----------------------------
DROP TABLE IF EXISTS `abnormalities`;
CREATE TABLE `abnormalities`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `region_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `x` int NOT NULL,
  `y` int NOT NULL,
  `label` int NOT NULL,
  `time` datetime NOT NULL,
  `status` int NULL DEFAULT NULL,
  `Layers` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `region_name`(`region_name` ASC) USING BTREE,
  CONSTRAINT `abnormalities_ibfk_1` FOREIGN KEY (`region_name`) REFERENCES `regions` (`region_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 463 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of abnormalities
-- ----------------------------
INSERT INTO `abnormalities` VALUES (1, 'Shanghai', 10, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (2, 'Shanghai', 10, 2, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (3, 'Shanghai', 10, 3, 2, '2023-11-08 13:04:00', 1, 1);
INSERT INTO `abnormalities` VALUES (4, 'Shanghai', 10, 4, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (5, 'Shanghai', 10, 5, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (6, 'Shanghai', 11, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (7, 'Shanghai', 11, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (8, 'Shanghai', 12, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (9, 'Shanghai', 12, 5, 2, '2023-11-08 13:05:00', 1, 1);
INSERT INTO `abnormalities` VALUES (10, 'Shanghai', 13, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (11, 'Shanghai', 13, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (12, 'Shanghai', 14, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (13, 'Shanghai', 14, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (14, 'Shanghai', 15, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (15, 'Shanghai', 15, 5, 2, '2023-11-08 13:05:00', 1, 1);
INSERT INTO `abnormalities` VALUES (16, 'Shanghai', 16, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (17, 'Shanghai', 16, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (18, 'Shanghai', 17, 1, 0, '2023-11-08 13:03:00', 0, 1);
INSERT INTO `abnormalities` VALUES (19, 'Shanghai', 17, 2, 2, '2023-11-08 13:04:00', 1, 1);
INSERT INTO `abnormalities` VALUES (20, 'Shanghai', 17, 3, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (21, 'Shanghai', 17, 4, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (22, 'Shanghai', 17, 5, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (23, 'Shanghai', 10, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (24, 'Shanghai', 10, 2, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (25, 'Shanghai', 10, 3, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (26, 'Shanghai', 10, 4, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (27, 'Shanghai', 10, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (28, 'Shanghai', 11, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (29, 'Shanghai', 11, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (30, 'Shanghai', 12, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (31, 'Shanghai', 12, 5, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (32, 'Shanghai', 13, 1, 0, '2023-11-08 13:12:00', 2, 2);
INSERT INTO `abnormalities` VALUES (33, 'Shanghai', 13, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (34, 'Shanghai', 14, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (35, 'Shanghai', 14, 5, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (36, 'Shanghai', 15, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (37, 'Shanghai', 15, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (38, 'Shanghai', 16, 1, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (39, 'Shanghai', 16, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (40, 'Shanghai', 17, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (41, 'Shanghai', 17, 2, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (42, 'Shanghai', 17, 3, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (43, 'Shanghai', 17, 4, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (44, 'Shanghai', 17, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (45, 'Shanghai', 10, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (46, 'Shanghai', 10, 2, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (47, 'Shanghai', 10, 3, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (48, 'Shanghai', 10, 4, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (49, 'Shanghai', 10, 5, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (50, 'Shanghai', 11, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (51, 'Shanghai', 11, 5, 2, '2023-11-08 13:21:00', 1, 3);
INSERT INTO `abnormalities` VALUES (52, 'Shanghai', 12, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (53, 'Shanghai', 12, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (54, 'Shanghai', 13, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (55, 'Shanghai', 13, 5, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (56, 'Shanghai', 14, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (57, 'Shanghai', 14, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (58, 'Shanghai', 15, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (59, 'Shanghai', 15, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (60, 'Shanghai', 16, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (61, 'Shanghai', 16, 5, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (62, 'Shanghai', 17, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (63, 'Shanghai', 17, 2, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (64, 'Shanghai', 17, 3, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (65, 'Shanghai', 17, 4, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (66, 'Shanghai', 17, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (67, 'Suzhou', 10, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (68, 'Suzhou', 10, 2, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (69, 'Suzhou', 10, 3, 2, '2023-11-08 13:04:00', 1, 1);
INSERT INTO `abnormalities` VALUES (70, 'Suzhou', 10, 4, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (71, 'Suzhou', 10, 5, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (72, 'Suzhou', 11, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (73, 'Suzhou', 11, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (74, 'Suzhou', 12, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (75, 'Suzhou', 12, 5, 2, '2023-11-08 13:05:00', 1, 1);
INSERT INTO `abnormalities` VALUES (76, 'Suzhou', 13, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (77, 'Suzhou', 13, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (78, 'Suzhou', 14, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (79, 'Suzhou', 14, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (80, 'Suzhou', 15, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (81, 'Suzhou', 15, 5, 2, '2023-11-08 13:05:00', 1, 1);
INSERT INTO `abnormalities` VALUES (82, 'Suzhou', 16, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (83, 'Suzhou', 16, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (84, 'Suzhou', 17, 1, 0, '2023-11-08 13:03:00', 0, 1);
INSERT INTO `abnormalities` VALUES (85, 'Suzhou', 17, 2, 2, '2023-11-08 13:04:00', 1, 1);
INSERT INTO `abnormalities` VALUES (86, 'Suzhou', 17, 3, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (87, 'Suzhou', 17, 4, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (88, 'Suzhou', 17, 5, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (89, 'Suzhou', 10, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (90, 'Suzhou', 10, 2, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (91, 'Suzhou', 10, 3, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (92, 'Suzhou', 10, 4, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (93, 'Suzhou', 10, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (94, 'Suzhou', 11, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (95, 'Suzhou', 11, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (96, 'Suzhou', 12, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (97, 'Suzhou', 12, 5, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (98, 'Suzhou', 13, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (99, 'Suzhou', 13, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (100, 'Suzhou', 14, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (101, 'Suzhou', 14, 5, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (102, 'Suzhou', 15, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (103, 'Suzhou', 15, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (104, 'Suzhou', 16, 1, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (105, 'Suzhou', 16, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (106, 'Suzhou', 17, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (107, 'Suzhou', 17, 2, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (108, 'Suzhou', 17, 3, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (109, 'Suzhou', 17, 4, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (110, 'Suzhou', 17, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (111, 'Suzhou', 10, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (112, 'Suzhou', 10, 2, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (113, 'Suzhou', 10, 3, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (114, 'Suzhou', 10, 4, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (115, 'Suzhou', 10, 5, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (116, 'Suzhou', 11, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (117, 'Suzhou', 11, 5, 2, '2023-11-08 13:21:00', 1, 3);
INSERT INTO `abnormalities` VALUES (118, 'Suzhou', 12, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (119, 'Suzhou', 12, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (120, 'Suzhou', 13, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (121, 'Suzhou', 13, 5, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (122, 'Suzhou', 14, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (123, 'Suzhou', 14, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (124, 'Suzhou', 15, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (125, 'Suzhou', 15, 5, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (126, 'Suzhou', 16, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (127, 'Suzhou', 16, 5, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (128, 'Suzhou', 17, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (129, 'Suzhou', 17, 2, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (130, 'Suzhou', 17, 3, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (131, 'Suzhou', 17, 4, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (132, 'Suzhou', 17, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (133, 'Suzhou', 10, 1, 0, '2023-11-08 13:28:00', 0, 4);
INSERT INTO `abnormalities` VALUES (134, 'Suzhou', 10, 2, 2, '2023-11-08 13:28:00', 1, 4);
INSERT INTO `abnormalities` VALUES (135, 'Suzhou', 10, 3, 2, '2023-11-08 13:28:00', 1, 4);
INSERT INTO `abnormalities` VALUES (136, 'Suzhou', 10, 4, 0, '2023-11-08 13:28:00', 0, 4);
INSERT INTO `abnormalities` VALUES (137, 'Suzhou', 10, 5, 0, '2023-11-08 13:28:00', 0, 4);
INSERT INTO `abnormalities` VALUES (138, 'Suzhou', 11, 1, 2, '2023-11-08 13:28:00', 1, 4);
INSERT INTO `abnormalities` VALUES (139, 'Suzhou', 11, 5, 0, '2023-11-08 13:28:00', 0, 4);
INSERT INTO `abnormalities` VALUES (140, 'Suzhou', 12, 1, 0, '2023-11-08 13:28:00', 0, 4);
INSERT INTO `abnormalities` VALUES (141, 'Suzhou', 12, 5, 0, '2023-11-08 13:28:00', 0, 4);
INSERT INTO `abnormalities` VALUES (142, 'Suzhou', 13, 1, 2, '2023-11-08 13:28:00', 1, 4);
INSERT INTO `abnormalities` VALUES (143, 'Suzhou', 13, 5, 0, '2023-11-08 13:28:00', 0, 4);
INSERT INTO `abnormalities` VALUES (144, 'Suzhou', 14, 1, 2, '2023-11-08 13:28:00', 1, 4);
INSERT INTO `abnormalities` VALUES (145, 'Suzhou', 14, 5, 0, '2023-11-08 13:28:00', 0, 4);
INSERT INTO `abnormalities` VALUES (146, 'Suzhou', 15, 1, 0, '2023-11-08 13:29:00', 0, 4);
INSERT INTO `abnormalities` VALUES (147, 'Suzhou', 15, 5, 0, '2023-11-08 13:28:00', 0, 4);
INSERT INTO `abnormalities` VALUES (148, 'Suzhou', 16, 1, 0, '2023-11-08 13:29:00', 0, 4);
INSERT INTO `abnormalities` VALUES (149, 'Suzhou', 16, 5, 2, '2023-11-08 13:28:00', 1, 4);
INSERT INTO `abnormalities` VALUES (150, 'Suzhou', 17, 1, 0, '2023-11-08 13:29:00', 0, 4);
INSERT INTO `abnormalities` VALUES (151, 'Suzhou', 17, 2, 2, '2023-11-08 13:29:00', 1, 4);
INSERT INTO `abnormalities` VALUES (152, 'Suzhou', 17, 3, 2, '2023-11-08 13:29:00', 1, 4);
INSERT INTO `abnormalities` VALUES (153, 'Suzhou', 17, 4, 0, '2023-11-08 13:29:00', 0, 4);
INSERT INTO `abnormalities` VALUES (154, 'Suzhou', 17, 5, 0, '2023-11-08 13:29:00', 0, 4);
INSERT INTO `abnormalities` VALUES (155, 'Suzhou', 10, 1, 0, '2023-11-08 13:43:00', 0, 5);
INSERT INTO `abnormalities` VALUES (156, 'Suzhou', 10, 2, 0, '2023-11-08 13:43:00', 0, 5);
INSERT INTO `abnormalities` VALUES (157, 'Suzhou', 10, 3, 2, '2023-11-08 13:43:00', 1, 5);
INSERT INTO `abnormalities` VALUES (158, 'Suzhou', 10, 4, 2, '2023-11-08 13:43:00', 1, 5);
INSERT INTO `abnormalities` VALUES (159, 'Suzhou', 10, 5, 0, '2023-11-08 13:43:00', 0, 5);
INSERT INTO `abnormalities` VALUES (160, 'Suzhou', 11, 1, 0, '2023-11-08 13:44:00', 0, 5);
INSERT INTO `abnormalities` VALUES (161, 'Suzhou', 11, 5, 0, '2023-11-08 13:43:00', 0, 5);
INSERT INTO `abnormalities` VALUES (162, 'Suzhou', 12, 1, 0, '2023-11-08 13:44:00', 0, 5);
INSERT INTO `abnormalities` VALUES (163, 'Suzhou', 12, 5, 0, '2023-11-08 13:43:00', 0, 5);
INSERT INTO `abnormalities` VALUES (164, 'Suzhou', 13, 1, 0, '2023-11-08 13:44:00', 0, 5);
INSERT INTO `abnormalities` VALUES (165, 'Suzhou', 13, 5, 0, '2023-11-08 13:43:00', 0, 5);
INSERT INTO `abnormalities` VALUES (166, 'Suzhou', 14, 1, 2, '2023-11-08 13:44:00', 1, 5);
INSERT INTO `abnormalities` VALUES (167, 'Suzhou', 14, 5, 0, '2023-11-08 13:43:00', 0, 5);
INSERT INTO `abnormalities` VALUES (168, 'Suzhou', 15, 1, 0, '2023-11-08 13:44:00', 0, 5);
INSERT INTO `abnormalities` VALUES (169, 'Suzhou', 15, 5, 0, '2023-11-08 13:43:00', 0, 5);
INSERT INTO `abnormalities` VALUES (170, 'Suzhou', 16, 1, 0, '2023-11-08 13:44:00', 0, 5);
INSERT INTO `abnormalities` VALUES (171, 'Suzhou', 16, 5, 0, '2023-11-08 13:43:00', 0, 5);
INSERT INTO `abnormalities` VALUES (172, 'Suzhou', 17, 1, 0, '2023-11-08 13:43:00', 0, 5);
INSERT INTO `abnormalities` VALUES (173, 'Suzhou', 17, 2, 2, '2023-11-08 13:43:00', 1, 5);
INSERT INTO `abnormalities` VALUES (174, 'Suzhou', 17, 3, 2, '2023-11-08 13:43:00', 1, 5);
INSERT INTO `abnormalities` VALUES (175, 'Suzhou', 17, 4, 2, '2023-11-08 13:43:00', 1, 5);
INSERT INTO `abnormalities` VALUES (176, 'Suzhou', 17, 5, 0, '2023-11-08 13:43:00', 0, 5);
INSERT INTO `abnormalities` VALUES (177, 'Suzhou', 10, 1, 0, '2023-11-08 13:51:00', 0, 6);
INSERT INTO `abnormalities` VALUES (178, 'Suzhou', 10, 2, 0, '2023-11-08 13:52:00', 0, 6);
INSERT INTO `abnormalities` VALUES (179, 'Suzhou', 10, 3, 0, '2023-11-08 13:52:00', 0, 6);
INSERT INTO `abnormalities` VALUES (180, 'Suzhou', 10, 4, 0, '2023-11-08 13:52:00', 0, 6);
INSERT INTO `abnormalities` VALUES (181, 'Suzhou', 10, 5, 0, '2023-11-08 13:52:00', 0, 6);
INSERT INTO `abnormalities` VALUES (182, 'Suzhou', 11, 1, 0, '2023-11-08 13:51:00', 0, 6);
INSERT INTO `abnormalities` VALUES (183, 'Suzhou', 11, 5, 0, '2023-11-08 13:52:00', 0, 6);
INSERT INTO `abnormalities` VALUES (184, 'Suzhou', 12, 1, 0, '2023-11-08 13:51:00', 0, 6);
INSERT INTO `abnormalities` VALUES (185, 'Suzhou', 12, 5, 0, '2023-11-08 13:52:00', 0, 6);
INSERT INTO `abnormalities` VALUES (186, 'Suzhou', 13, 1, 0, '2023-11-08 13:51:00', 0, 6);
INSERT INTO `abnormalities` VALUES (187, 'Suzhou', 13, 5, 0, '2023-11-08 13:52:00', 0, 6);
INSERT INTO `abnormalities` VALUES (188, 'Suzhou', 14, 1, 0, '2023-11-08 13:51:00', 0, 6);
INSERT INTO `abnormalities` VALUES (189, 'Suzhou', 14, 5, 0, '2023-11-08 13:52:00', 0, 6);
INSERT INTO `abnormalities` VALUES (190, 'Suzhou', 15, 1, 2, '2023-11-08 13:51:00', 1, 6);
INSERT INTO `abnormalities` VALUES (191, 'Suzhou', 15, 5, 0, '2023-11-08 13:52:00', 0, 6);
INSERT INTO `abnormalities` VALUES (192, 'Suzhou', 16, 1, 0, '2023-11-08 13:51:00', 0, 6);
INSERT INTO `abnormalities` VALUES (193, 'Suzhou', 16, 5, 0, '2023-11-08 13:51:00', 0, 6);
INSERT INTO `abnormalities` VALUES (194, 'Suzhou', 17, 1, 0, '2023-11-08 13:51:00', 0, 6);
INSERT INTO `abnormalities` VALUES (195, 'Suzhou', 17, 2, 0, '2023-11-08 13:51:00', 0, 6);
INSERT INTO `abnormalities` VALUES (196, 'Suzhou', 17, 3, 2, '2023-11-08 13:51:00', 1, 6);
INSERT INTO `abnormalities` VALUES (197, 'Suzhou', 17, 4, 0, '2023-11-08 13:51:00', 0, 6);
INSERT INTO `abnormalities` VALUES (198, 'Suzhou', 17, 5, 0, '2023-11-08 13:51:00', 0, 6);
INSERT INTO `abnormalities` VALUES (199, 'Suzhou', 10, 1, 0, '2023-11-08 14:16:00', 0, 7);
INSERT INTO `abnormalities` VALUES (200, 'Suzhou', 10, 2, 2, '2023-11-08 14:16:00', 1, 7);
INSERT INTO `abnormalities` VALUES (201, 'Suzhou', 10, 3, 0, '2023-11-08 14:16:00', 0, 7);
INSERT INTO `abnormalities` VALUES (202, 'Suzhou', 10, 4, 2, '2023-11-08 14:16:00', 1, 7);
INSERT INTO `abnormalities` VALUES (203, 'Suzhou', 10, 5, 0, '2023-11-08 14:16:00', 0, 7);
INSERT INTO `abnormalities` VALUES (204, 'Suzhou', 11, 1, 0, '2023-11-08 14:16:00', 0, 7);
INSERT INTO `abnormalities` VALUES (205, 'Suzhou', 11, 5, 0, '2023-11-08 14:16:00', 0, 7);
INSERT INTO `abnormalities` VALUES (206, 'Suzhou', 12, 1, 0, '2023-11-08 14:16:00', 0, 7);
INSERT INTO `abnormalities` VALUES (207, 'Suzhou', 12, 5, 0, '2023-11-08 14:16:00', 0, 7);
INSERT INTO `abnormalities` VALUES (208, 'Suzhou', 13, 1, 0, '2023-11-08 14:16:00', 0, 7);
INSERT INTO `abnormalities` VALUES (209, 'Suzhou', 13, 5, 0, '2023-11-08 14:16:00', 0, 7);
INSERT INTO `abnormalities` VALUES (210, 'Suzhou', 14, 1, 0, '2023-11-08 14:16:00', 0, 7);
INSERT INTO `abnormalities` VALUES (211, 'Suzhou', 14, 5, 0, '2023-11-08 14:17:00', 0, 7);
INSERT INTO `abnormalities` VALUES (212, 'Suzhou', 15, 1, 2, '2023-11-08 14:17:00', 1, 7);
INSERT INTO `abnormalities` VALUES (213, 'Suzhou', 15, 5, 0, '2023-11-08 14:17:00', 0, 7);
INSERT INTO `abnormalities` VALUES (214, 'Suzhou', 16, 1, 0, '2023-11-08 14:17:00', 0, 7);
INSERT INTO `abnormalities` VALUES (215, 'Suzhou', 16, 5, 0, '2023-11-08 14:17:00', 0, 7);
INSERT INTO `abnormalities` VALUES (216, 'Suzhou', 17, 1, 0, '2023-11-08 14:17:00', 0, 7);
INSERT INTO `abnormalities` VALUES (217, 'Suzhou', 17, 2, 2, '2023-11-08 14:17:00', 1, 7);
INSERT INTO `abnormalities` VALUES (218, 'Suzhou', 17, 3, 0, '2023-11-08 14:17:00', 0, 7);
INSERT INTO `abnormalities` VALUES (219, 'Suzhou', 17, 4, 2, '2023-11-08 14:17:00', 1, 7);
INSERT INTO `abnormalities` VALUES (220, 'Suzhou', 17, 5, 0, '2023-11-08 14:17:00', 0, 7);
INSERT INTO `abnormalities` VALUES (221, 'Suzhou', 10, 1, 0, '2023-11-08 14:24:00', 0, 8);
INSERT INTO `abnormalities` VALUES (222, 'Suzhou', 10, 2, 0, '2023-11-08 14:24:00', 0, 8);
INSERT INTO `abnormalities` VALUES (223, 'Suzhou', 10, 3, 2, '2023-11-08 14:24:00', 1, 8);
INSERT INTO `abnormalities` VALUES (224, 'Suzhou', 10, 4, 2, '2023-11-08 14:24:00', 1, 8);
INSERT INTO `abnormalities` VALUES (225, 'Suzhou', 10, 5, 0, '2023-11-08 14:23:00', 0, 8);
INSERT INTO `abnormalities` VALUES (226, 'Suzhou', 11, 1, 0, '2023-11-08 14:24:00', 0, 8);
INSERT INTO `abnormalities` VALUES (227, 'Suzhou', 11, 5, 2, '2023-11-08 14:23:00', 1, 8);
INSERT INTO `abnormalities` VALUES (228, 'Suzhou', 12, 1, 0, '2023-11-08 14:24:00', 0, 8);
INSERT INTO `abnormalities` VALUES (229, 'Suzhou', 12, 5, 0, '2023-11-08 14:23:00', 0, 8);
INSERT INTO `abnormalities` VALUES (230, 'Suzhou', 13, 1, 0, '2023-11-08 14:24:00', 0, 8);
INSERT INTO `abnormalities` VALUES (231, 'Suzhou', 13, 5, 0, '2023-11-08 14:23:00', 0, 8);
INSERT INTO `abnormalities` VALUES (232, 'Suzhou', 14, 1, 0, '2023-11-08 14:24:00', 0, 8);
INSERT INTO `abnormalities` VALUES (233, 'Suzhou', 14, 5, 0, '2023-11-08 14:23:00', 0, 8);
INSERT INTO `abnormalities` VALUES (234, 'Suzhou', 15, 1, 0, '2023-11-08 14:24:00', 0, 8);
INSERT INTO `abnormalities` VALUES (235, 'Suzhou', 15, 5, 0, '2023-11-08 14:23:00', 0, 8);
INSERT INTO `abnormalities` VALUES (236, 'Suzhou', 16, 1, 2, '2023-11-08 14:24:00', 1, 8);
INSERT INTO `abnormalities` VALUES (237, 'Suzhou', 16, 5, 2, '2023-11-08 14:23:00', 1, 8);
INSERT INTO `abnormalities` VALUES (238, 'Suzhou', 17, 1, 0, '2023-11-08 14:24:00', 0, 8);
INSERT INTO `abnormalities` VALUES (239, 'Suzhou', 17, 2, 0, '2023-11-08 14:24:00', 0, 8);
INSERT INTO `abnormalities` VALUES (240, 'Suzhou', 17, 3, 2, '2023-11-08 14:24:00', 1, 8);
INSERT INTO `abnormalities` VALUES (241, 'Suzhou', 17, 4, 0, '2023-11-08 14:24:00', 0, 8);
INSERT INTO `abnormalities` VALUES (242, 'Suzhou', 17, 5, 0, '2023-11-08 14:23:00', 0, 8);
INSERT INTO `abnormalities` VALUES (243, 'Suzhou', 10, 1, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (244, 'Suzhou', 10, 2, 2, '2023-11-08 14:31:00', 1, 9);
INSERT INTO `abnormalities` VALUES (245, 'Suzhou', 10, 3, 2, '2023-11-08 14:31:00', 1, 9);
INSERT INTO `abnormalities` VALUES (246, 'Suzhou', 10, 4, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (247, 'Suzhou', 10, 5, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (248, 'Suzhou', 11, 1, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (249, 'Suzhou', 11, 5, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (250, 'Suzhou', 12, 1, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (251, 'Suzhou', 12, 5, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (252, 'Suzhou', 13, 1, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (253, 'Suzhou', 13, 5, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (254, 'Suzhou', 14, 1, 2, '2023-11-08 14:31:00', 1, 9);
INSERT INTO `abnormalities` VALUES (255, 'Suzhou', 14, 5, 2, '2023-11-08 14:31:00', 1, 9);
INSERT INTO `abnormalities` VALUES (256, 'Suzhou', 15, 1, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (257, 'Suzhou', 15, 5, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (258, 'Suzhou', 16, 1, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (259, 'Suzhou', 16, 5, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (260, 'Suzhou', 17, 1, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (261, 'Suzhou', 17, 2, 2, '2023-11-08 14:31:00', 1, 9);
INSERT INTO `abnormalities` VALUES (262, 'Suzhou', 17, 3, 2, '2023-11-08 14:31:00', 1, 9);
INSERT INTO `abnormalities` VALUES (263, 'Suzhou', 17, 4, 2, '2023-11-08 14:31:00', 1, 9);
INSERT INTO `abnormalities` VALUES (264, 'Suzhou', 17, 5, 0, '2023-11-08 14:31:00', 0, 9);
INSERT INTO `abnormalities` VALUES (265, 'Default', 10, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (266, 'Default', 10, 2, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (267, 'Default', 10, 3, 2, '2023-11-08 13:04:00', 1, 1);
INSERT INTO `abnormalities` VALUES (268, 'Default', 10, 4, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (269, 'Default', 10, 5, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (270, 'Default', 11, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (271, 'Default', 11, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (272, 'Default', 12, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (273, 'Default', 12, 5, 2, '2023-11-08 13:05:00', 1, 1);
INSERT INTO `abnormalities` VALUES (274, 'Default', 13, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (275, 'Default', 13, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (276, 'Default', 14, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (277, 'Default', 14, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (278, 'Default', 15, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (279, 'Default', 15, 5, 2, '2023-11-08 13:05:00', 1, 1);
INSERT INTO `abnormalities` VALUES (280, 'Default', 16, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (281, 'Default', 16, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (282, 'Default', 17, 1, 0, '2023-11-08 13:03:00', 0, 1);
INSERT INTO `abnormalities` VALUES (283, 'Default', 17, 2, 2, '2023-11-08 13:04:00', 1, 1);
INSERT INTO `abnormalities` VALUES (284, 'Default', 17, 3, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (285, 'Default', 17, 4, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (286, 'Default', 17, 5, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (287, 'Default', 10, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (288, 'Default', 10, 2, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (289, 'Default', 10, 3, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (290, 'Default', 10, 4, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (291, 'Default', 10, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (292, 'Default', 11, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (293, 'Default', 11, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (294, 'Default', 12, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (295, 'Default', 12, 5, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (296, 'Default', 13, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (297, 'Default', 13, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (298, 'Default', 14, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (299, 'Default', 14, 5, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (300, 'Default', 15, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (301, 'Default', 15, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (302, 'Default', 16, 1, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (303, 'Default', 16, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (304, 'Default', 17, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (305, 'Default', 17, 2, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (306, 'Default', 17, 3, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (307, 'Default', 17, 4, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (308, 'Default', 17, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (309, 'Default', 10, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (310, 'Default', 10, 2, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (311, 'Default', 10, 3, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (312, 'Default', 10, 4, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (313, 'Default', 10, 5, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (314, 'Default', 11, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (315, 'Default', 11, 5, 2, '2023-11-08 13:21:00', 1, 3);
INSERT INTO `abnormalities` VALUES (316, 'Default', 12, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (317, 'Default', 12, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (318, 'Default', 13, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (319, 'Default', 13, 5, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (320, 'Default', 14, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (321, 'Default', 14, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (322, 'Default', 15, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (323, 'Default', 15, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (324, 'Default', 16, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (325, 'Default', 16, 5, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (326, 'Default', 17, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (327, 'Default', 17, 2, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (328, 'Default', 17, 3, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (329, 'Default', 17, 4, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (330, 'Default', 17, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (331, 'Default', 10, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (332, 'Default', 10, 2, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (333, 'Default', 10, 3, 2, '2023-11-08 13:04:00', 1, 1);
INSERT INTO `abnormalities` VALUES (334, 'Default', 10, 4, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (335, 'Default', 10, 5, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (336, 'Default', 11, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (337, 'Default', 11, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (338, 'Default', 12, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (339, 'Default', 12, 5, 2, '2023-11-08 13:05:00', 1, 1);
INSERT INTO `abnormalities` VALUES (340, 'Default', 13, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (341, 'Default', 13, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (342, 'Default', 14, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (343, 'Default', 14, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (344, 'Default', 15, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (345, 'Default', 15, 5, 2, '2023-11-08 13:05:00', 1, 1);
INSERT INTO `abnormalities` VALUES (346, 'Default', 16, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (347, 'Default', 16, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (348, 'Default', 17, 1, 0, '2023-11-08 13:03:00', 0, 1);
INSERT INTO `abnormalities` VALUES (349, 'Default', 17, 2, 2, '2023-11-08 13:04:00', 1, 1);
INSERT INTO `abnormalities` VALUES (350, 'Default', 17, 3, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (351, 'Default', 17, 4, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (352, 'Default', 17, 5, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (353, 'Default', 10, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (354, 'Default', 10, 2, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (355, 'Default', 10, 3, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (356, 'Default', 10, 4, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (357, 'Default', 10, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (358, 'Default', 11, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (359, 'Default', 11, 5, 0, '2023-11-08 13:12:00', 2, 2);
INSERT INTO `abnormalities` VALUES (360, 'Default', 12, 1, 0, '2023-11-08 13:12:00', 2, 2);
INSERT INTO `abnormalities` VALUES (361, 'Default', 12, 5, 2, '2023-11-08 13:12:00', 2, 2);
INSERT INTO `abnormalities` VALUES (362, 'Default', 13, 1, 0, '2023-11-08 13:12:00', 2, 2);
INSERT INTO `abnormalities` VALUES (363, 'Default', 13, 5, 0, '2023-11-08 13:12:00', 2, 2);
INSERT INTO `abnormalities` VALUES (364, 'Default', 14, 1, 0, '2023-11-08 13:12:00', 2, 2);
INSERT INTO `abnormalities` VALUES (365, 'Default', 14, 5, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (366, 'Default', 15, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (367, 'Default', 15, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (368, 'Default', 16, 1, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (369, 'Default', 16, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (370, 'Default', 17, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (371, 'Default', 17, 2, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (372, 'Default', 17, 3, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (373, 'Default', 17, 4, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (374, 'Default', 17, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (375, 'Default', 10, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (376, 'Default', 10, 2, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (377, 'Default', 10, 3, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (378, 'Default', 10, 4, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (379, 'Default', 10, 5, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (380, 'Default', 11, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (381, 'Default', 11, 5, 2, '2023-11-08 13:21:00', 1, 3);
INSERT INTO `abnormalities` VALUES (382, 'Default', 12, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (383, 'Default', 12, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (384, 'Default', 13, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (385, 'Default', 13, 5, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (386, 'Default', 14, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (387, 'Default', 14, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (388, 'Default', 15, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (389, 'Default', 15, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (390, 'Default', 16, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (391, 'Default', 16, 5, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (392, 'Default', 17, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (393, 'Default', 17, 2, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (394, 'Default', 17, 3, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (395, 'Default', 17, 4, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (396, 'Default', 17, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (397, 'Zhangjiahe', 10, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (398, 'Zhangjiahe', 10, 2, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (399, 'Zhangjiahe', 10, 3, 2, '2023-11-08 13:04:00', 1, 1);
INSERT INTO `abnormalities` VALUES (400, 'Zhangjiahe', 10, 4, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (401, 'Zhangjiahe', 10, 5, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (402, 'Zhangjiahe', 11, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (403, 'Zhangjiahe', 11, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (404, 'Zhangjiahe', 12, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (405, 'Zhangjiahe', 12, 5, 2, '2023-11-08 13:05:00', 1, 1);
INSERT INTO `abnormalities` VALUES (406, 'Zhangjiahe', 13, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (407, 'Zhangjiahe', 13, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (408, 'Zhangjiahe', 14, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (409, 'Zhangjiahe', 14, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (410, 'Zhangjiahe', 15, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (411, 'Zhangjiahe', 15, 5, 2, '2023-11-08 13:05:00', 1, 1);
INSERT INTO `abnormalities` VALUES (412, 'Zhangjiahe', 16, 1, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (413, 'Zhangjiahe', 16, 5, 0, '2023-11-08 13:05:00', 0, 1);
INSERT INTO `abnormalities` VALUES (414, 'Zhangjiahe', 17, 1, 0, '2023-11-08 13:03:00', 0, 1);
INSERT INTO `abnormalities` VALUES (415, 'Zhangjiahe', 17, 2, 2, '2023-11-08 13:04:00', 1, 1);
INSERT INTO `abnormalities` VALUES (416, 'Zhangjiahe', 17, 3, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (417, 'Zhangjiahe', 17, 4, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (418, 'Zhangjiahe', 17, 5, 0, '2023-11-08 13:04:00', 0, 1);
INSERT INTO `abnormalities` VALUES (419, 'Zhangjiahe', 10, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (420, 'Zhangjiahe', 10, 2, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (421, 'Zhangjiahe', 10, 3, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (422, 'Zhangjiahe', 10, 4, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (423, 'Zhangjiahe', 10, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (424, 'Zhangjiahe', 11, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (425, 'Zhangjiahe', 11, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (426, 'Zhangjiahe', 12, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (427, 'Zhangjiahe', 12, 5, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (428, 'Zhangjiahe', 13, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (429, 'Zhangjiahe', 13, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (430, 'Zhangjiahe', 14, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (431, 'Zhangjiahe', 14, 5, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (432, 'Zhangjiahe', 15, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (433, 'Zhangjiahe', 15, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (434, 'Zhangjiahe', 16, 1, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (435, 'Zhangjiahe', 16, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (436, 'Zhangjiahe', 17, 1, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (437, 'Zhangjiahe', 17, 2, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (438, 'Zhangjiahe', 17, 3, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (439, 'Zhangjiahe', 17, 4, 2, '2023-11-08 13:12:00', 1, 2);
INSERT INTO `abnormalities` VALUES (440, 'Zhangjiahe', 17, 5, 0, '2023-11-08 13:12:00', 0, 2);
INSERT INTO `abnormalities` VALUES (441, 'Zhangjiahe', 10, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (442, 'Zhangjiahe', 10, 2, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (443, 'Zhangjiahe', 10, 3, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (444, 'Zhangjiahe', 10, 4, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (445, 'Zhangjiahe', 10, 5, 0, '2023-11-08 13:21:00', 0, 3);
INSERT INTO `abnormalities` VALUES (446, 'Zhangjiahe', 11, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (447, 'Zhangjiahe', 11, 5, 2, '2023-11-08 13:21:00', 1, 3);
INSERT INTO `abnormalities` VALUES (448, 'Zhangjiahe', 12, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (449, 'Zhangjiahe', 12, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (450, 'Zhangjiahe', 13, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (451, 'Zhangjiahe', 13, 5, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (452, 'Zhangjiahe', 14, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (453, 'Zhangjiahe', 14, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (454, 'Zhangjiahe', 15, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (455, 'Zhangjiahe', 15, 5, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (456, 'Zhangjiahe', 16, 1, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (457, 'Zhangjiahe', 16, 5, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (458, 'Zhangjiahe', 17, 1, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (459, 'Zhangjiahe', 17, 2, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (460, 'Zhangjiahe', 17, 3, 2, '2023-11-08 13:22:00', 1, 3);
INSERT INTO `abnormalities` VALUES (461, 'Zhangjiahe', 17, 4, 0, '2023-11-08 13:22:00', 0, 3);
INSERT INTO `abnormalities` VALUES (462, 'Zhangjiahe', 17, 5, 0, '2023-11-08 13:22:00', 0, 3);

-- ----------------------------
-- Table structure for form
-- ----------------------------
DROP TABLE IF EXISTS `form`;
CREATE TABLE `form`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` bigint NULL DEFAULT NULL,
  `abnormalid` int NULL DEFAULT NULL,
  `filepath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`userid` ASC) USING BTREE,
  INDEX `abnormalid`(`abnormalid` ASC) USING BTREE,
  CONSTRAINT `form_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `form_ibfk_2` FOREIGN KEY (`abnormalid`) REFERENCES `abnormalities` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form
-- ----------------------------
INSERT INTO `form` VALUES (1, 2, 359, 'E:\\studying\\2\\软创\\software\\springboot/public/files/fjw_2023-11-08_13_12_00_1.png', '2023-11-08 13:12:00');
INSERT INTO `form` VALUES (2, 2, 360, 'E:\\studying\\2\\软创\\software\\springboot/public/files/fjw_2023-11-08_13_12_01_1.png', '2023-11-08 13:12:01');
INSERT INTO `form` VALUES (3, 2, 361, 'E:\\studying\\2\\软创\\software\\springboot/public/files/fjw_2023-11-08_13_12_02_1.png', '2023-11-08 13:12:02');
INSERT INTO `form` VALUES (4, 2, 362, 'E:\\studying\\2\\软创\\software\\springboot/public/files/fjw_2023-11-08_13_12_03_1.png', '2023-11-08 13:12:03');
INSERT INTO `form` VALUES (5, 3, 363, 'E:\\studying\\2\\软创\\software\\springboot/public/files/admin_2023-11-08_13_12_05_1.png', '2023-11-08 13:12:05');
INSERT INTO `form` VALUES (6, 3, 364, 'E:\\studying\\2\\软创\\software\\springboot/public/files/admin_2023-11-08_13_12_06_1.png', '2023-11-08 13:12:06');
INSERT INTO `form` VALUES (7, 3, 32, 'E:\\studying\\2\\软创\\software\\springboot/public/files/admin_2023-11-08_13_12_06_1.png', '2023-11-08 13:12:06');

-- ----------------------------
-- Table structure for regions
-- ----------------------------
DROP TABLE IF EXISTS `regions`;
CREATE TABLE `regions`  (
  `region_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `leader` bigint NULL DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`region_name`) USING BTREE,
  INDEX `fk_leader`(`leader` ASC) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE,
  CONSTRAINT `fk_leader` FOREIGN KEY (`leader`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of regions
-- ----------------------------
INSERT INTO `regions` VALUES ('chunk', 2, 1);
INSERT INTO `regions` VALUES ('Default', 3, 2);
INSERT INTO `regions` VALUES ('Shanghai', 2, 3);
INSERT INTO `regions` VALUES ('Suzhou', 3, 4);
INSERT INTO `regions` VALUES ('test', 2, 5);
INSERT INTO `regions` VALUES ('Zhangjiahe', 3, 6);
INSERT INTO `regions` VALUES ('Zjhdsb', 2, 7);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'lyt', '98765432100', '$2a$10$8SoDKazwZgh9yNztekxgxudqTvfHhwI8e6cAbH/TzhlE9cU6oso7q', '检测员', '女', 'Zhangjiahe');
INSERT INTO `user` VALUES (3, 'admin', '1234567890', '$2a$10$ZjPgZMVqslHPMZWkbyJLF.LuUnJ731dfzC84oUfiMaQXFCSDPI4dK', 'admin', '男', 'string');

SET FOREIGN_KEY_CHECKS = 1;
