drop DATABASE if EXISTS fast ;
CREATE DATABASE `fast` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_bin';

use fast;

SET NAMES utf8mb4;

-- ----------------------------
-- Table structure for ytx_service_config_info
-- ----------------------------
DROP TABLE IF EXISTS `ytx_service_config_info`;
CREATE TABLE `ytx_service_config_info`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `service_code` varchar(100)  NULL DEFAULT NULL COMMENT '服务编码',
  `service_name` varchar(100)  NULL DEFAULT NULL COMMENT '服务名称',
  `service_config_key` varchar(100)  NULL DEFAULT NULL COMMENT '配置key值',
  `service_config_value` varchar(255)  NULL DEFAULT NULL COMMENT '配置value值',
  `service_status` tinyint(1) NULL DEFAULT 1 COMMENT '可用状态 1：可用 2：不可用',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_service_cofig_code`(`service_code`) USING BTREE
) ENGINE = InnoDB  COMMENT = '服务配置信息表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Records of ytx_service_config_info
-- ----------------------------
INSERT INTO `ytx_service_config_info` VALUES (2019111411, 'conf_info', '会议的appToken', 'appToken', '0f26f16e4a8d4680a586c6eb2a9f4e03', 1, NULL, NULL);
INSERT INTO `ytx_service_config_info` VALUES (2019111415, 'conf_info', '会议的AppKey', 'appId', '8a2af988536458c301537d7197320004', 1, NULL, NULL);
INSERT INTO `ytx_service_config_info` VALUES (2019111417, 'conf_info', '会议的accountSid', 'accountSid', '8a2af988536458c301537d7195fd0003', 1, NULL, NULL);
INSERT INTO `ytx_service_config_info` VALUES (2019111418, 'conf_info', '会议的SoftVersion', 'softVersion', '2013-12-26', 1, NULL, NULL);
INSERT INTO `ytx_service_config_info` VALUES (2019111419, 'conf_info', '会议的restIp', 'restIp', '47.97.231.84', 1, NULL, NULL);
INSERT INTO `ytx_service_config_info` VALUES (2019111420, 'conf_info', '会议的port', 'port', '8881', 1, NULL, NULL);

-- ----------------------------
-- Table structure for ytx_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `ytx_sys_menu`;
CREATE TABLE `ytx_sys_menu`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `menu_code` varchar(64)  NULL DEFAULT '' COMMENT '菜单代码',
  `menu_name` varchar(64)  NULL DEFAULT '' COMMENT '菜单名称',
  `menu_icon` varchar(255)  NULL DEFAULT '' COMMENT '菜单图标',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级菜单ID',
  `sort_num` int(11) NOT NULL DEFAULT 9999 COMMENT '排序号',
  `delete_status` smallint(2) NULL DEFAULT 1 COMMENT '删除状态 1-正常 0-已删除',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '系统权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ytx_sys_menu
-- ----------------------------
INSERT INTO `ytx_sys_menu` VALUES (13, '#', '系统功能', 'icon-yanjizhushou-shangchuan_xitong', NULL, 99, 1, sysdate(), sysdate());
INSERT INTO `ytx_sys_menu` VALUES (15, '/user/user', '用户管理', 'icon-yonghu', 13, 96,1, sysdate(), sysdate());
INSERT INTO `ytx_sys_menu` VALUES (16, '/role/role', '角色管理', 'icon-guanliyuan_jiaoseguanli', 13, 95,1, sysdate(), sysdate());
INSERT INTO `ytx_sys_menu` VALUES (17, '/authority/authority', '权限管理', 'icon-quanxianguanli', 13, 94,1, sysdate(), sysdate());
INSERT INTO `ytx_sys_menu` VALUES (18, '/menuManagement/menuManagement', '菜单管理', 'icon-caidanguanli', 13, 93, 1,sysdate(), sysdate());

-- ----------------------------
-- Table structure for ytx_sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `ytx_sys_operate_log`;
CREATE TABLE `ytx_sys_operate_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `module_name` varchar(50)  NULL DEFAULT NULL COMMENT '模块名称',
  `operate_type` varchar(10)  NULL DEFAULT NULL COMMENT '操作类型add、del、update、login、logout',
  `operate_desc` varchar(255)  NULL DEFAULT NULL COMMENT '操作详情',
  `create_user` varchar(100)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '系统操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ytx_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `ytx_sys_permission`;
CREATE TABLE `ytx_sys_permission`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `menu_code` varchar(32)  NULL DEFAULT '' COMMENT '归属菜单,前端判断并展示菜单使用',
  `menu_name` varchar(64)  NULL DEFAULT '' COMMENT '菜单的中文释义',
  `permission_code` varchar(255)  NULL DEFAULT '' COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(255)  NULL DEFAULT '' COMMENT '本权限的中文释义',
  `required_permission` smallint(1) NULL DEFAULT 2 COMMENT '是否本菜单必选权限, 1.必选 2非必选 通常是\"列表\"权限是必选',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '系统权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ytx_sys_permission
-- ----------------------------
INSERT INTO `ytx_sys_permission` VALUES (1835511102014496, 'all', '全部', '*:*', '全部', 2);

-- ----------------------------
-- Table structure for ytx_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `ytx_sys_role`;
CREATE TABLE `ytx_sys_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `role_code` varchar(64)  NULL DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(32)  NOT NULL COMMENT '角色名称',
  `delete_status` smallint(1) NULL DEFAULT 1 COMMENT '是否有效  1有效  2无效',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ytx_sys_role
-- ----------------------------
INSERT INTO `ytx_sys_role` VALUES (1908101222, 'manager', '普通管理员', 1, NULL, '2019-08-13 13:01:09');
INSERT INTO `ytx_sys_role` VALUES (1835512164680736, 'admin', '超级管理员', 1, NULL, '2019-08-11 15:49:04');
INSERT INTO `ytx_sys_role` VALUES (1835512595383328, 'normal', '普通用户', 1, NULL, '2019-08-10 16:42:03');

-- ----------------------------
-- Table structure for ytx_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ytx_sys_role_menu`;
CREATE TABLE `ytx_sys_role_menu`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单id',
  `delete_flag` smallint(2) NULL DEFAULT NULL COMMENT '删除状态：1-正常 2-已删除',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_menu_roleid`(`role_id`, `delete_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '系统角色菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ytx_sys_role_menu
-- ----------------------------
INSERT INTO `ytx_sys_role_menu` VALUES (1835513790366752, 1835512164680736, 13, 1, NULL);
INSERT INTO `ytx_sys_role_menu` VALUES (1835514225886240, 1835512164680736, 15, 1, NULL);
INSERT INTO `ytx_sys_role_menu` VALUES (1835514433274912, 1835512164680736, 16, 1, NULL);
INSERT INTO `ytx_sys_role_menu` VALUES (1835514653672480, 1835512164680736, 17, 1, NULL);
INSERT INTO `ytx_sys_role_menu` VALUES (1835514875380768, 1835512164680736, 18, 1, NULL);

-- ----------------------------
-- Table structure for ytx_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `ytx_sys_role_permission`;
CREATE TABLE `ytx_sys_role_permission`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `role_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '权限id',
  `delete_status` smallint(1) NULL DEFAULT 1 COMMENT '是否有效 1有效     2无效',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_permission_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '系统角色权限中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ytx_sys_role_permission
-- ----------------------------
INSERT INTO `ytx_sys_role_permission` VALUES (1835515107902496, 1835512164680736, 1835511102014496, 1, NULL, NULL);

-- ----------------------------
-- Table structure for ytx_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `ytx_sys_user`;
CREATE TABLE `ytx_sys_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `username` varchar(64)  NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(128)  NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(60)  NULL DEFAULT NULL COMMENT '加密盐值',
  `nickname` varchar(64)  NULL DEFAULT '' COMMENT '昵称',
  `phone` varchar(11)  NULL DEFAULT '' COMMENT '手机号',
  `dc_id` varchar(32)  NULL DEFAULT NULL COMMENT '数据中心编码',
  `delete_status` smallint(1) NULL DEFAULT 1 COMMENT '是否有效  1有效  2无效',
  `user_type` smallint(1) NULL DEFAULT 1 COMMENT '用户类型 1-管理员 2-普通用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_username`(`username`) USING BTREE
) ENGINE = InnoDB  COMMENT = '账户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ytx_sys_user
-- ----------------------------
INSERT INTO `ytx_sys_user` VALUES (1835511579640864, 'admin', '02306310b8f3808f9948bf42fabcb6b991bd4b96ba9c7dc3242ed8e0f632eadf', 'pDAh76ahWkkQblmeiK0beFP5ndtA9Z', '系统管理员', '', '', 1, 1, '2019-04-04 09:41:31', '2019-05-13 18:14:52');


-- ----------------------------
-- Table structure for ytx_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ytx_sys_user_role`;
CREATE TABLE `ytx_sys_user_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '角色ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '用户所属角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ytx_sys_user_role
-- ----------------------------
INSERT INTO `ytx_sys_user_role` VALUES (1835513217057824, 1835511579640864, 1835512164680736, NULL, NULL);
