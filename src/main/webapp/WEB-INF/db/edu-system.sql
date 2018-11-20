/*
Navicat MySQL Data Transfer

Source Server         : 192.168.7.187_3306
Source Server Version : 50556
Source Host           : 192.168.7.187:3306
Source Database       : edu-system

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-11-19 15:32:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `admin_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理编号',
  `admin_name` varchar(50) DEFAULT NULL COMMENT '管理员名称',
  `admin_account` varchar(50) DEFAULT NULL COMMENT '管理员账号',
  `admin_pwd` varchar(50) DEFAULT NULL COMMENT '管理员密码',
  `admin_status` int(10) unsigned DEFAULT NULL COMMENT '管理员状态,0可用，1禁用',
  `role_id` bigint(20) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('1', '系统管理员', 'root', 'KjVa/8YMZHYa/hn+OAJzug==', '0', '1');
INSERT INTO `tb_admin` VALUES ('2', '朱老师', 'zhuyj', 'KjVa/8YMZHYa/hn+OAJzug==', '0', '2');
INSERT INTO `tb_admin` VALUES ('3', '熊老师', 'xiong', 'KjVa/8YMZHYa/hn+OAJzug==', '0', '2');
INSERT INTO `tb_admin` VALUES ('4', '房老师', 'fang', 'KjVa/8YMZHYa/hn+OAJzug==', '0', '2');
INSERT INTO `tb_admin` VALUES ('5', '李老师', 'li', 'KjVa/8YMZHYa/hn+OAJzug==', '0', '2');
INSERT INTO `tb_admin` VALUES ('6', '王老师', 'wang', 'KjVa/8YMZHYa/hn+OAJzug==', '0', '2');
INSERT INTO `tb_admin` VALUES ('7', '赵老师', 'zhao', 'KjVa/8YMZHYa/hn+OAJzug==', '0', '2');
INSERT INTO `tb_admin` VALUES ('8', '马校长', 'ma', 'KjVa/8YMZHYa/hn+OAJzug==', '0', '3');

-- ----------------------------
-- Table structure for tb_class_stage
-- ----------------------------
DROP TABLE IF EXISTS `tb_class_stage`;
CREATE TABLE `tb_class_stage` (
  `class_stage_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_stage_teacher` bigint(20) DEFAULT NULL,
  `class_stage_date` date DEFAULT NULL,
  `class_id` bigint(20) NOT NULL,
  `stage_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`class_stage_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_class_stage
-- ----------------------------
INSERT INTO `tb_class_stage` VALUES ('1', '2', '2018-07-18', '1', '1', '1');
INSERT INTO `tb_class_stage` VALUES ('4', '5', '2018-07-23', '2', '4', '2');
INSERT INTO `tb_class_stage` VALUES ('5', '6', '2018-08-23', '2', '5', '2');
INSERT INTO `tb_class_stage` VALUES ('6', '2', '2018-09-30', '1', '3', '2');
INSERT INTO `tb_class_stage` VALUES ('7', '2', '2018-10-19', '3', '1', '1');
INSERT INTO `tb_class_stage` VALUES ('8', '3', '2018-11-20', '3', '2', '1');
INSERT INTO `tb_class_stage` VALUES ('9', '4', '2018-12-15', '3', '3', '1');
INSERT INTO `tb_class_stage` VALUES ('10', '2', '2018-11-13', '1', '1', '1');
INSERT INTO `tb_class_stage` VALUES ('11', '2', '2018-10-31', '1', '1', '1');

-- ----------------------------
-- Table structure for tb_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `tb_dictionary`;
CREATE TABLE `tb_dictionary` (
  `dic_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据字典编号',
  `dic_value` varchar(500) DEFAULT NULL COMMENT '字典数据名',
  `dic_type_code` bigint(20) DEFAULT NULL COMMENT '字典类型编码',
  `dic_type_name` varchar(100) DEFAULT NULL COMMENT '字典类型名称',
  `dic_name` varchar(200) DEFAULT NULL COMMENT '字典名',
  `dic_percent` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Records of tb_dictionary
-- ----------------------------
INSERT INTO `tb_dictionary` VALUES ('1', '0', '1000', '管理员使用状态', '可用', null);
INSERT INTO `tb_dictionary` VALUES ('2', '1', '1000', '管理员使用状态', '禁用', null);
INSERT INTO `tb_dictionary` VALUES ('3', '0', '1001', '权限显示状态', '显示', null);
INSERT INTO `tb_dictionary` VALUES ('4', '1', '1001', '权限显示状态', '隐藏', null);
INSERT INTO `tb_dictionary` VALUES ('5', 'A', '1002', '理解程度', '完全理解', null);
INSERT INTO `tb_dictionary` VALUES ('6', 'B', '1002', '理解程度', '大部分理解', null);
INSERT INTO `tb_dictionary` VALUES ('7', 'C', '1002', '理解程度', '基本理解', null);
INSERT INTO `tb_dictionary` VALUES ('8', 'D', '1002', '理解程度', '不太理解', null);
INSERT INTO `tb_dictionary` VALUES ('9', 'E', '1002', '理解程度', '完全不懂', null);
INSERT INTO `tb_dictionary` VALUES ('10', 'A', '1003', '满意程度', '完全满意', '1.0');
INSERT INTO `tb_dictionary` VALUES ('11', 'B', '1003', '满意程度', '比较满意', '0.8');
INSERT INTO `tb_dictionary` VALUES ('12', 'C', '1003', '满意程度', '基本满意', '0.6');
INSERT INTO `tb_dictionary` VALUES ('13', 'D', '1003', '满意程度', '不太满意', '0');
INSERT INTO `tb_dictionary` VALUES ('14', 'E', '1003', '满意程度', '极不满意', null);
INSERT INTO `tb_dictionary` VALUES ('16', '0', '1004', '班级情况', '在读', null);
INSERT INTO `tb_dictionary` VALUES ('17', '1', '1004', '班级情况', '结束', null);

-- ----------------------------
-- Table structure for tb_edu_chapter
-- ----------------------------
DROP TABLE IF EXISTS `tb_edu_chapter`;
CREATE TABLE `tb_edu_chapter` (
  `chapter_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chapter_title` varchar(400) DEFAULT NULL,
  `chapter_summary` varchar(50) DEFAULT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`chapter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_edu_chapter
-- ----------------------------
INSERT INTO `tb_edu_chapter` VALUES ('1', 'spring概述', '什么是spring', '1');
INSERT INTO `tb_edu_chapter` VALUES ('2', '入门示例', 'spring的xml配置', '1');
INSERT INTO `tb_edu_chapter` VALUES ('3', 'STS安装', 'STS安装', '1');
INSERT INTO `tb_edu_chapter` VALUES ('4', 'Ioc理论', 'Ioc理论', '1');
INSERT INTO `tb_edu_chapter` VALUES ('5', 'mybatis', '什么mybatis', '1');
INSERT INTO `tb_edu_chapter` VALUES ('6', 'springMVC概述', '什么是springMVC', '1');
INSERT INTO `tb_edu_chapter` VALUES ('7', 'JavaScript概述', '什么是JavaScript', '1');
INSERT INTO `tb_edu_chapter` VALUES ('14', 'python概述', '什么叫python', '2');
INSERT INTO `tb_edu_chapter` VALUES ('15', 'html概述', '什么叫html', '3');

-- ----------------------------
-- Table structure for tb_edu_class
-- ----------------------------
DROP TABLE IF EXISTS `tb_edu_class`;
CREATE TABLE `tb_edu_class` (
  `class_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(50) DEFAULT NULL,
  `class_student_number` int(11) DEFAULT NULL,
  `class_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_edu_class
-- ----------------------------
INSERT INTO `tb_edu_class` VALUES ('1', 'java0719', '20', '0');
INSERT INTO `tb_edu_class` VALUES ('2', 'python0723', '25', '0');
INSERT INTO `tb_edu_class` VALUES ('3', 'java1021', '30', '0');

-- ----------------------------
-- Table structure for tb_edu_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `tb_edu_evaluate`;
CREATE TABLE `tb_edu_evaluate` (
  `evaluate_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `evaluate_title` varchar(400) DEFAULT NULL,
  `evaluate_create_date` date DEFAULT NULL,
  `evaluate_close_date` datetime DEFAULT NULL,
  `evaluate_start_date` datetime DEFAULT NULL,
  `evaluate_admin_id` bigint(20) DEFAULT NULL,
  `evaluate_class_id` bigint(20) DEFAULT NULL,
  `field_id` varchar(500) DEFAULT NULL,
  `evaluate_student_total` int(11) DEFAULT NULL,
  `class_stage_id` bigint(20) DEFAULT NULL,
  `evaluate_visit` int(11) DEFAULT NULL,
  PRIMARY KEY (`evaluate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_edu_evaluate
-- ----------------------------
INSERT INTO `tb_edu_evaluate` VALUES ('7', 'JavaEE-教学评测-朱老师', '2018-11-17', '2018-11-17 20:27:07', '2018-11-17 20:27:08', '2', '1', '1,2,3,4,5,6,7', '3', '1', '0');

-- ----------------------------
-- Table structure for tb_edu_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tb_edu_feedback`;
CREATE TABLE `tb_edu_feedback` (
  `feedback_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `feedback_title` varchar(400) DEFAULT NULL,
  `feedback_create_date` date DEFAULT NULL,
  `feedback_close_date` datetime DEFAULT NULL,
  `feedback_start_date` datetime DEFAULT NULL,
  `feedback_admin_id` bigint(20) DEFAULT NULL,
  `feedback_class_id` bigint(20) DEFAULT NULL,
  `feedback_chapters` varchar(5000) DEFAULT NULL,
  `feedback_student_total` int(11) DEFAULT NULL,
  `class_stage_id` bigint(20) DEFAULT NULL,
  `feedback_visit` int(11) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_edu_feedback
-- ----------------------------
INSERT INTO `tb_edu_feedback` VALUES ('1', 'JavaEE-每天反馈-朱老师', '2018-11-12', '2018-11-13 16:37:31', '2018-11-18 00:00:00', '1', '1', '1,2,3,4', '0', '1', '0');
INSERT INTO `tb_edu_feedback` VALUES ('3', 'JavaEE-每天反馈-朱老师', '2018-11-11', '2018-11-13 16:37:31', '2018-11-13 16:37:32', '1', '1', '1,2,3,4', '0', '1', '1');
INSERT INTO `tb_edu_feedback` VALUES ('4', 'JavaEE-每天反馈-朱老师', '2018-11-10', '2018-11-10 16:37:31', '2018-11-10 16:37:32', '1', '1', 'spring概述,入门示例,STS安装,Ioc理论,mybatis概述,springMVC概述,javas概述', '0', '4', '0');
INSERT INTO `tb_edu_feedback` VALUES ('5', 'JavaEE-每天反馈-朱老师', '2018-11-17', '2018-11-17 16:37:31', '2018-11-17 16:37:32', '1', '1', 'spring概述,入门示例,STS安装,Ioc理论,mybatis概述,springMVC概述', '0', '1', '1');
INSERT INTO `tb_edu_feedback` VALUES ('7', 'JavaEE-每天反馈-朱老师', '2018-11-13', '2018-11-13 16:37:31', '2018-11-13 16:37:32', '1', '1', '1,2,3,4', '0', '1', '1');
INSERT INTO `tb_edu_feedback` VALUES ('34', 'javaEE-每日反馈-朱老师', '2018-11-18', null, null, '2', '1', '封装(什么叫封装),接口(什么叫接口),继承(什么叫继承),html(什么叫html),spring(什么叫spring)', '0', '1', '1');
INSERT INTO `tb_edu_feedback` VALUES ('36', 'javaEE-每日反馈-朱老师', '2018-11-18', null, null, '2', '1', 'spring概述(什么叫spring),入门示例(spring示例),STS安装(STS安装),Ioc理论(Ioc是什么),mybatis(什么mybatis)', '0', '1', '1');
INSERT INTO `tb_edu_feedback` VALUES ('37', 'python-每日反馈-朱老师', '2018-11-18', '2018-11-18 00:00:00', '2018-11-18 00:00:00', '2', '2', 'python概述(什么叫python)', '0', '4', '1');
INSERT INTO `tb_edu_feedback` VALUES ('38', 'python-每日反馈-朱老师', '2018-11-18', '2018-11-18 00:00:00', '2018-11-18 00:00:00', '2', '2', 'python概述(什么叫python)', '0', '1', '1');

-- ----------------------------
-- Table structure for tb_edu_stage
-- ----------------------------
DROP TABLE IF EXISTS `tb_edu_stage`;
CREATE TABLE `tb_edu_stage` (
  `stage_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stage_name` varchar(50) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`stage_id`),
  UNIQUE KEY `stage_name` (`stage_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_edu_stage
-- ----------------------------
INSERT INTO `tb_edu_stage` VALUES ('1', 'javaEE初级', '1');
INSERT INTO `tb_edu_stage` VALUES ('2', 'javaEE中级', '1');
INSERT INTO `tb_edu_stage` VALUES ('3', 'javaEE高级', '1');
INSERT INTO `tb_edu_stage` VALUES ('4', 'python初级', '2');
INSERT INTO `tb_edu_stage` VALUES ('5', 'python中级', '2');
INSERT INTO `tb_edu_stage` VALUES ('6', 'python高级', '2');

-- ----------------------------
-- Table structure for tb_edu_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_edu_student`;
CREATE TABLE `tb_edu_student` (
  `student_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(50) DEFAULT NULL,
  `student_phone` varchar(20) DEFAULT NULL,
  `student_age` int(11) DEFAULT NULL,
  `student_sex` char(2) DEFAULT NULL,
  `student_status` int(11) DEFAULT NULL,
  `class_id` bigint(20) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_edu_student
-- ----------------------------
INSERT INTO `tb_edu_student` VALUES ('1', '张三', '1312431523', '18', 'M', '0', '1');
INSERT INTO `tb_edu_student` VALUES ('2', '赵六', '18421251524', '20', 'M', '0', '2');
INSERT INTO `tb_edu_student` VALUES ('3', '王五', '1244322131', '11', 'M', '0', '1');
INSERT INTO `tb_edu_student` VALUES ('8', '李四', '13888888888', '21', 'M', '0', '1');
INSERT INTO `tb_edu_student` VALUES ('9', '陈七', '13666666666', '21', 'M', '0', '1');
INSERT INTO `tb_edu_student` VALUES ('11', '何', '15512345678900', '21', 'M', '0', '1');

-- ----------------------------
-- Table structure for tb_edu_subject
-- ----------------------------
DROP TABLE IF EXISTS `tb_edu_subject`;
CREATE TABLE `tb_edu_subject` (
  `subject_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_edu_subject
-- ----------------------------
INSERT INTO `tb_edu_subject` VALUES ('1', 'javaEE');
INSERT INTO `tb_edu_subject` VALUES ('2', 'python');
INSERT INTO `tb_edu_subject` VALUES ('3', 'html11');
INSERT INTO `tb_edu_subject` VALUES ('4', 'java22');
INSERT INTO `tb_edu_subject` VALUES ('5', 'JavaScript1231');
INSERT INTO `tb_edu_subject` VALUES ('6', 'JavaScript1231');
INSERT INTO `tb_edu_subject` VALUES ('7', 'JavaScript3');
INSERT INTO `tb_edu_subject` VALUES ('8', 'JavaScript1231');
INSERT INTO `tb_edu_subject` VALUES ('9', 'JAVAee');
INSERT INTO `tb_edu_subject` VALUES ('10', 'JAVAEE');
INSERT INTO `tb_edu_subject` VALUES ('11', '');

-- ----------------------------
-- Table structure for tb_field
-- ----------------------------
DROP TABLE IF EXISTS `tb_field`;
CREATE TABLE `tb_field` (
  `field_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `field_name` varchar(400) DEFAULT NULL,
  `field_percent` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`field_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_field
-- ----------------------------
INSERT INTO `tb_field` VALUES ('1', '上课纪律', '0.05');
INSERT INTO `tb_field` VALUES ('2', '备课充分', '0.10');
INSERT INTO `tb_field` VALUES ('3', '思路清晰', '0.25');
INSERT INTO `tb_field` VALUES ('4', '内容充分', '0.10');
INSERT INTO `tb_field` VALUES ('5', '上课热情', '0.20');
INSERT INTO `tb_field` VALUES ('6', '内容吸收', '0.10');
INSERT INTO `tb_field` VALUES ('7', '激发力', '0.20');

-- ----------------------------
-- Table structure for tb_modular
-- ----------------------------
DROP TABLE IF EXISTS `tb_modular`;
CREATE TABLE `tb_modular` (
  `modular_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '模块编号',
  `modular_name` varchar(200) DEFAULT NULL COMMENT '模块名',
  PRIMARY KEY (`modular_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='模块表';

-- ----------------------------
-- Records of tb_modular
-- ----------------------------
INSERT INTO `tb_modular` VALUES ('1', '系统管理模块');
INSERT INTO `tb_modular` VALUES ('2', '教师模块');
INSERT INTO `tb_modular` VALUES ('3', '教务人员');

-- ----------------------------
-- Table structure for tb_power
-- ----------------------------
DROP TABLE IF EXISTS `tb_power`;
CREATE TABLE `tb_power` (
  `power_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `power_name` varchar(200) DEFAULT NULL COMMENT '权限名',
  `power_action` varchar(500) DEFAULT NULL COMMENT '权限路径',
  `power_parent` bigint(20) DEFAULT NULL COMMENT '父权限',
  `power_is_show` int(11) DEFAULT NULL COMMENT '是否显示，0显示，1隐藏',
  `modular_id` bigint(20) NOT NULL COMMENT '模块编号',
  PRIMARY KEY (`power_id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of tb_power
-- ----------------------------
INSERT INTO `tb_power` VALUES ('1', '后台用户管理', '/admin/toAdminList.do', '0', '0', '1');
INSERT INTO `tb_power` VALUES ('2', '角色管理', '/role/toRoleList.do', '0', '0', '1');
INSERT INTO `tb_power` VALUES ('3', '权限管理', '/power/toPowerList.do', '0', '0', '1');
INSERT INTO `tb_power` VALUES ('4', '模块管理', '/modular/toModularList.do', '0', '0', '1');
INSERT INTO `tb_power` VALUES ('5', '修改密码', '/admin/toAdminSetting.do', '0', '0', '1');
INSERT INTO `tb_power` VALUES ('6', '系统功能-to修改密码', '/admin/setAdminPwd.do', '0', '1', '1');
INSERT INTO `tb_power` VALUES ('7', '后台用户管理-to增加', '/admin/toAdminAdd.do', '1', '1', '1');
INSERT INTO `tb_power` VALUES ('8', '后台用户管理-增加', '/admin/addAdmin.do', '1', '1', '1');
INSERT INTO `tb_power` VALUES ('9', '后台用户管理-to编辑', '/admin/toAdminEdit.do', '1', '1', '1');
INSERT INTO `tb_power` VALUES ('10', '后台用户管理-编辑', '/admin/editAdmin.do', '1', '1', '1');
INSERT INTO `tb_power` VALUES ('11', '后台用户管理-删除', '/admin/deleteAdmin.do', '1', '1', '1');
INSERT INTO `tb_power` VALUES ('12', '后台用户管理-批量删除', '/admin/deleteAdmins.do', '1', '1', '1');
INSERT INTO `tb_power` VALUES ('13', '角色管理-to增加', '/role/toRoleAdd.do', '2', '1', '1');
INSERT INTO `tb_power` VALUES ('14', '角色管理-增加', '/role/addRole.do', '2', '1', '1');
INSERT INTO `tb_power` VALUES ('15', '角色管理-to编辑', '/role/toRoleEdit.do', '2', '1', '1');
INSERT INTO `tb_power` VALUES ('16', '角色管理-编辑', '/role/editRole.do', '2', '1', '1');
INSERT INTO `tb_power` VALUES ('17', '角色管理-删除', '/role/deleteRole.do', '2', '1', '1');
INSERT INTO `tb_power` VALUES ('18', '角色管理-批量删除', '/role/deleteRole.do', '2', '1', '1');
INSERT INTO `tb_power` VALUES ('19', '权限管理-to增加', '/power/toPowerAdd.do', '3', '1', '1');
INSERT INTO `tb_power` VALUES ('20', '权限管理-增加', '/power/addPower.do', '3', '1', '1');
INSERT INTO `tb_power` VALUES ('21', '权限管理-to编辑', '/power/toPowerEdit.do', '3', '1', '1');
INSERT INTO `tb_power` VALUES ('22', '权限管理-编辑', '/power/editPower.do', '3', '1', '1');
INSERT INTO `tb_power` VALUES ('23', '权限管理-删除', '/power/deletePower.do', '3', '1', '1');
INSERT INTO `tb_power` VALUES ('24', '权限管理-批量删除', '/power/deletePowers.do', '3', '1', '1');
INSERT INTO `tb_power` VALUES ('25', '模块管理-to增加', '/modular/toModularAdd.do', '4', '1', '1');
INSERT INTO `tb_power` VALUES ('26', '模块管理-增加', '/modular/addModular.do', '4', '1', '1');
INSERT INTO `tb_power` VALUES ('27', '模块管理-to编辑', '/modular/toModularEdit.do', '4', '1', '1');
INSERT INTO `tb_power` VALUES ('28', '模块管理-编辑', '/modular/editModular.do', '4', '1', '1');
INSERT INTO `tb_power` VALUES ('29', '模块管理-删除', '/modular/deleteModular.do', '4', '1', '1');
INSERT INTO `tb_power` VALUES ('30', '模块管理-批量删除', '/modular/deleteModulars.do', '4', '1', '1');
INSERT INTO `tb_power` VALUES ('31', '阶段管理', '/stage/toStageList.do', '0', '0', '3');
INSERT INTO `tb_power` VALUES ('32', '学生管理', '/student/toStudentList.do', '0', '0', '3');
INSERT INTO `tb_power` VALUES ('33', '班级管理', '/class/toClassList.do', '0', '0', '3');
INSERT INTO `tb_power` VALUES ('34', '每日反馈', '/feedback/toFeedbackList.do', '0', '0', '2');
INSERT INTO `tb_power` VALUES ('35', '课程管理', '/chapter/toChapterList.do', '0', '0', '3');
INSERT INTO `tb_power` VALUES ('36', '学生管理-to新增', '/student/toStudentAdd.do', '36', '0', '3');
INSERT INTO `tb_power` VALUES ('37', '每日反馈-to详细列表', '/studentfeedback/toDetailsFeedbackList.do', '34', '1', '2');
INSERT INTO `tb_power` VALUES ('38', '阶段管理-To编辑', '/stage/toStageEdit.do', '31', '0', '3');
INSERT INTO `tb_power` VALUES ('39', '阶段管理-编辑', '/stage/editStage.do', '31', '0', '3');
INSERT INTO `tb_power` VALUES ('40', '教学评测', '/evaluate/toEvaluateList.do', '0', '0', '2');
INSERT INTO `tb_power` VALUES ('41', '班级管理-to添加', '/class/toClassAdd.do', '33', '0', '3');
INSERT INTO `tb_power` VALUES ('42', '课程管理-to增加', '/chapter/toChapterAdd.do', '35', '1', '3');
INSERT INTO `tb_power` VALUES ('43', '课程管理-增加', '/chapter/chapterAdd.do', '35', '1', '3');
INSERT INTO `tb_power` VALUES ('44', '课程管理-to编辑', '/chapter/toChapterEdit.do', '35', '1', '3');
INSERT INTO `tb_power` VALUES ('45', '课程管理-编辑', '/chapter/editChapter.do', '35', '1', '3');
INSERT INTO `tb_power` VALUES ('46', '课程管理-删除', '/chapter/deleteChapter.do', '35', '1', '3');
INSERT INTO `tb_power` VALUES ('47', '课程管理-批量删除', '/chapter/deleteChapters.do', '35', '1', '3');
INSERT INTO `tb_power` VALUES ('48', '班级管理-添加', '/class/addClass.do', '33', '0', '3');
INSERT INTO `tb_power` VALUES ('49', '班级管理-删除', '/class/deleteClass.do', '33', '0', '3');
INSERT INTO `tb_power` VALUES ('50', '班级管理-批量删除', '/class/deleteClasss.do', '33', '0', '3');
INSERT INTO `tb_power` VALUES ('51', '班级管理-to编辑', '/class/toClassEdit.do', '33', '0', '3');
INSERT INTO `tb_power` VALUES ('52', '阶段管理-删除', '/stage/deleteClassStage.do', '52', '0', '3');
INSERT INTO `tb_power` VALUES ('53', '阶段管理-批量删除', '/stage/deleteClassStages.do', '53', '0', '3');
INSERT INTO `tb_power` VALUES ('54', '阶段管理-To增加', '/stage/toStageAdd.do', '31', '0', '3');
INSERT INTO `tb_power` VALUES ('55', '阶段管理-增加', '/stage/addStage.do', '31', '0', '3');
INSERT INTO `tb_power` VALUES ('56', '班级管理-编辑', '/class/editClass.do', '33', '0', '3');
INSERT INTO `tb_power` VALUES ('57', '每日反馈管理', '/fb/toFbList.do', '0', '0', '3');
INSERT INTO `tb_power` VALUES ('58', '每日反馈-删除', '/fb/deleteFb.do', '57', '1', '3');
INSERT INTO `tb_power` VALUES ('59', '每日反馈-批量删除', '/fb/deleteFbs.do', '57', '1', '3');
INSERT INTO `tb_power` VALUES ('60', '学生管理-添加', '/student/addStudent.do', '32', '0', '3');
INSERT INTO `tb_power` VALUES ('61', '学生管理-to编辑', '/student/toStudentEdit.do', '32', '0', '3');
INSERT INTO `tb_power` VALUES ('62', '学生管理-编辑', '/student/editStudent.do', '32', '0', '3');
INSERT INTO `tb_power` VALUES ('63', '每日反馈管理-增加', '/studentfeedback/addStudentFeedback.do', '57', '0', '3');
INSERT INTO `tb_power` VALUES ('64', '数据字典管理', '/dictionary/toDictionaryList.do', '0', '0', '1');
INSERT INTO `tb_power` VALUES ('65', '数据字典管理-to增加', '/dictionary/toDictionaryAdd.do', '65', '1', '1');
INSERT INTO `tb_power` VALUES ('66', '数据字典管理-增加', '/dictionary/addDictionary.do', '64', '1', '1');
INSERT INTO `tb_power` VALUES ('67', '数据字典管理-to编辑', '/dictionary/toDictionaryEdit.do', '64', '1', '1');
INSERT INTO `tb_power` VALUES ('68', '数据字典管理-编辑', '/dictionary/editDictionary.do', '64', '1', '1');
INSERT INTO `tb_power` VALUES ('69', '数据字典管理-删除', '/dictionary/deleteDictionary.do', '69', '1', '1');
INSERT INTO `tb_power` VALUES ('70', '数据字典管理-批量删除', '/dictionary/deleteDictionarys.do', '70', '1', '1');
INSERT INTO `tb_power` VALUES ('71', '学生管理-批量删除', '/student/deleteStudents.do', '32', '0', '3');
INSERT INTO `tb_power` VALUES ('72', '学生管理-删除', '/student/deleteStudent.do', '33', '0', '3');
INSERT INTO `tb_power` VALUES ('73', '测试显示是否能访问', '/fb/studenttest.do', '57', '1', '3');
INSERT INTO `tb_power` VALUES ('74', '教学评测-to详细列表', '/studentevaluate/toDetailsEvaluateList.do', '40', '1', '2');
INSERT INTO `tb_power` VALUES ('75', '每日反馈管理-To增加', '/fb/toFbAdd.do', '75', '0', '3');
INSERT INTO `tb_power` VALUES ('78', '每日反馈发起', '/fb/fbLaunch.do', '57', '1', '3');
INSERT INTO `tb_power` VALUES ('81', '每日反馈关闭', '/fb/fbClose.do', '57', '1', '3');
INSERT INTO `tb_power` VALUES ('82', '每日反馈学生界面', '/studentfeedback', '57', '1', '3');
INSERT INTO `tb_power` VALUES ('83', '阶段管理-选择', '/stage/selectStage.do', '31', '0', '3');
INSERT INTO `tb_power` VALUES ('84', '每日评测管理', '/evaluate/toEvaluate.do', '0', '0', '3');
INSERT INTO `tb_power` VALUES ('85', '每日评测-删除', '/evaluate/deleteEvaluate.do', '84', '1', '3');
INSERT INTO `tb_power` VALUES ('86', '每日反馈-批量删除', '/evaluate/deleteEvaluates.do', '84', '1', '3');
INSERT INTO `tb_power` VALUES ('87', '班级信息详情', '/class/selectClass.do', '33', '0', '3');
INSERT INTO `tb_power` VALUES ('88', '每日评测-发起', '/evaluate/evaluateLaunch.do', '84', '1', '3');
INSERT INTO `tb_power` VALUES ('89', '每日评测-关闭', '/evaluate/evaluateClose.do', '84', '1', '3');
INSERT INTO `tb_power` VALUES ('90', '教学评测-to增加', '/evaluate/toEvaluateAdd.do', '57', '1', '3');
INSERT INTO `tb_power` VALUES ('91', '每日反馈管理-更新', '/fb/toFbEdit.do', '57', '1', '3');
INSERT INTO `tb_power` VALUES ('92', '每日反馈管理-增加', '/fb/fbAdd.do', '57', '1', '3');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `role_powers` varchar(500) DEFAULT NULL COMMENT '角色权限',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '管理员', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,64,65,66,67,68,69,70,34,37,40,74,31,32,33,35,36,38,39,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,71,72,73,75,78,81,82,83,84,85,86,87,88,89,90,91,92');
INSERT INTO `tb_role` VALUES ('2', '教师', '34,37,40,74');
INSERT INTO `tb_role` VALUES ('3', '教务人员', '31,32,33,35,36,38,39,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,71,72,73,75,78,81,82,83');

-- ----------------------------
-- Table structure for tb_student_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `tb_student_evaluate`;
CREATE TABLE `tb_student_evaluate` (
  `student_evaluate_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_evaluate_grade` varchar(100) DEFAULT NULL COMMENT '使用逗号分隔，跟章节顺序一一对应\r\n',
  `student_evaluate_comment` varchar(2000) DEFAULT NULL,
  `student_id` bigint(20) NOT NULL,
  `evaluate_id` bigint(20) NOT NULL,
  `student_evaluate_date` date DEFAULT NULL,
  `student_evaluate_show` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_evaluate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_student_evaluate
-- ----------------------------
INSERT INTO `tb_student_evaluate` VALUES ('1', 'A,A,A,A,A,A,A', 'Nice', '1', '7', '2018-11-15', '0');
INSERT INTO `tb_student_evaluate` VALUES ('2', 'B,A,A,A,A,A,B', 'haoo', '2', '7', '2018-11-15', '0');
INSERT INTO `tb_student_evaluate` VALUES ('3', 'A,A,A,B,B,B,B', 'OKOKOK', '3', '7', '2018-11-17', '0');

-- ----------------------------
-- Table structure for tb_student_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tb_student_feedback`;
CREATE TABLE `tb_student_feedback` (
  `student_feedback_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_feedback_grade` varchar(50) DEFAULT NULL COMMENT '使用逗号分隔，跟章节顺序一一对应\\r\\n',
  `student_feedback_comment` varchar(2000) DEFAULT NULL,
  `student_id` bigint(20) NOT NULL,
  `feedback_id` bigint(20) DEFAULT NULL,
  `student_feedback_date` date DEFAULT NULL,
  `student_feedback_show` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_student_feedback
-- ----------------------------
INSERT INTO `tb_student_feedback` VALUES ('1', 'A,B,B,B', '很好', '1', '1', '2018-11-14', '0');
INSERT INTO `tb_student_feedback` VALUES ('2', 'B,A,D,C', '一般般', '2', '1', '2018-11-12', '1');
INSERT INTO `tb_student_feedback` VALUES ('3', 'C,D,D,D', '很好', '3', '7', '2018-11-13', '0');
INSERT INTO `tb_student_feedback` VALUES ('12', 'A,A,A,A', '', '2', '7', '2018-11-13', '0');
INSERT INTO `tb_student_feedback` VALUES ('14', 'A,B,C,B', '不好', '1', '7', '2018-11-16', '0');
INSERT INTO `tb_student_feedback` VALUES ('15', 'A,B,B,C', '不好！！！！！！！！11', '2', '2', '2018-11-16', '0');
INSERT INTO `tb_student_feedback` VALUES ('19', 'A,B,B,C', '不是很好', '4', '6', '2018-11-17', '1');
INSERT INTO `tb_student_feedback` VALUES ('20', 'A,B,C,B,C,C', '不太好', '2', '5', '2018-11-17', '1');
INSERT INTO `tb_student_feedback` VALUES ('21', 'A,B,C,B,C,D,B', 'shad', '3', '4', '2018-11-17', '1');
INSERT INTO `tb_student_feedback` VALUES ('22', 'A,B', '', '1', '2', '2018-11-17', '0');
INSERT INTO `tb_student_feedback` VALUES ('25', 'A,A,A,A,B,B', '不好', '8', '5', '2018-11-17', '1');
INSERT INTO `tb_student_feedback` VALUES ('26', 'A,A,E,A,C', 'okok', '2', null, '2018-11-18', '0');
INSERT INTO `tb_student_feedback` VALUES ('27', 'A,A,B,A,A', 'okok', '2', null, '2018-11-18', '0');
INSERT INTO `tb_student_feedback` VALUES ('28', 'A,E,A,E,A', 'ok', '2', '34', '2018-11-18', '0');
INSERT INTO `tb_student_feedback` VALUES ('29', 'A,A,A,A,A', '666666666', '9', '36', '2018-11-18', '1');
INSERT INTO `tb_student_feedback` VALUES ('30', 'A', '44444', '9', '37', '2018-11-18', '1');
