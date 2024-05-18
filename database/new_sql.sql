DROP TABLE IF EXISTS `rc_admin`;
CREATE TABLE `rc_admin` (
  `admin_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员Id',
  `admin_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `admin_password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `admin_privilege` int(11) NOT NULL COMMENT '角色\r\n二进制表示权限\r\n1-系管理\r\n2-专业管理\r\n4-班级管理\r\n8-学生管理\r\n16-教师管理\r\n32-课程管理\r\n64-选课管理\r\n128-管理员管理',
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE KEY `idx_admin_username` (`admin_username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of rc_admin
-- ----------------------------
INSERT INTO `rc_admin` VALUES ('1', 'admin', '81a5f5a9bfde4cdcb5b9fe1f8508df2a', '255');
INSERT INTO `rc_admin` VALUES ('2', 'azure99', '81a5f5a9bfde4cdcb5b9fe1f8508df2a', '96');


DROP TABLE IF EXISTS `rc_comment`;
CREATE TABLE `rc_comment` (
  `comment_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `course_id` int(10) DEFAULT NULL COMMENT '课程ID',
  `comment` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '评论内容',
  `comment_tm` datetime DEFAULT NULL COMMENT '评论时间',
  `comment_user_id` int(10) DEFAULT NULL COMMENT '评论用户ID',
  `comment_privilege` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '评论人角色',
  `relpy_id` int(10) DEFAULT NULL COMMENT '回复ID（如果是回复，否则为空）',
  `reply_tm` datetime DEFAULT NULL COMMENT '回复时间（如果是回复，否则为空）',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `rc_course_type`;
CREATE TABLE `rc_course_type` (
  `type_id` int(16) NOT NULL COMMENT '课程分类id',
  `parent_type_id` int(16) DEFAULT NULL COMMENT '父级分类id，顶层为空',
  `type_name` varchar(255) NOT NULL COMMENT '课程分类名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updat_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_course_type
-- ----------------------------
INSERT INTO `rc_course_type` VALUES ('13', null, '理工类', '2024-05-03 23:15:43', '2024-05-03 23:15:44');
INSERT INTO `rc_course_type` VALUES ('52', null, '文史类', '2024-05-03 23:17:09', '2024-05-03 23:17:09');

DROP TABLE IF EXISTS `rc_course`;
CREATE TABLE `rc_course` (
  `course_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程Id',
  `course_teacher_id` int(10) unsigned NOT NULL COMMENT '授课教师Id',
  `course_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程名称',
  `introduction` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '课程简介',
  `cover` longblob DEFAULT NULL COMMENT '课程封面',
  `course_selected_count` int(10) DEFAULT NULL,
  `course_exam_date` datetime DEFAULT NULL COMMENT '考试时间',
  `create_time` datetime DEFAULT NULL COMMENT '课程创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '课程更新时间',
  `free_type` int(11) DEFAULT NULL COMMENT '0表示免费1表示付费',
  `type_id` int(11) DEFAULT NULL COMMENT '课程分类id',
  `file` longblob DEFAULT NULL COMMENT '课程文件',
  `course_exam_frame` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '课程考试大纲',
  `course_exam_id` int(16) DEFAULT NULL,
  `course_start_time` datetime DEFAULT NULL,
  `course_end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of rc_course
-- ----------------------------
INSERT INTO `rc_course` VALUES ('1', '1', 'C语言程序设计', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `rc_course` VALUES ('2', '1', 'Java程序设计', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `rc_course` VALUES ('3', '1', '数据库实用技术', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `rc_course` VALUES ('4', '1', 'ASP.Net开发', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `rc_course` VALUES ('5', '1', 'Spring企业级开发', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `rc_course` VALUES ('6', '3', '数据库概论', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `rc_course` VALUES ('7', '3', 'Photoshop', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `rc_course` VALUES ('8', '4', '计算机网络', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `rc_course` VALUES ('9', '6', '测试', null, null, null, '2024-04-15 00:00:00', null, null, null, null, null, null, null, null, null);


DROP TABLE IF EXISTS `rc_exam`;
CREATE TABLE `rc_exam` (
  `exam_id` int(16) NOT NULL COMMENT '试卷id',
  `name` varchar(16) DEFAULT NULL COMMENT '考试名称',
  `content` longtext DEFAULT NULL COMMENT '考试内容',
  `start_time` datetime DEFAULT NULL COMMENT '考试开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '考试截止时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `rc_homework`;
CREATE TABLE `rc_homework` (
  `homework_id` int(16) NOT NULL COMMENT '作业id',
  `course_id` int(16) DEFAULT NULL COMMENT '课程id',
  `name` varchar(16) DEFAULT NULL COMMENT '作业名称',
  `content` longtext COMMENT '作业内容',
  `start_time` datetime DEFAULT NULL COMMENT '作业开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '作业截止时间',
  `end_status` varchar(16) DEFAULT NULL COMMENT '作业状态0,1',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`homework_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `rc_notice`;
CREATE TABLE `rc_notice` (
  `notice_id` int(16) NOT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '通知标题',
  `content` longtext COMMENT '通知内容',
  `teacher_id` int(16) DEFAULT NULL COMMENT '讲师id',
  `admin_id` int(16) DEFAULT NULL COMMENT '管理员id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `rc_process`;
CREATE TABLE `rc_process` (
  `process_id` int(16) NOT NULL COMMENT '进度id',
  `sc_id` int(16) DEFAULT NULL COMMENT '选课关系id',
  `precent` int(16) DEFAULT NULL COMMENT '学习进度百分比',
  `finish_charpter_num` int(16) DEFAULT NULL COMMENT '已完成章节数量',
  `left_charpter_num` int(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`process_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `rc_select_course`;
CREATE TABLE `rc_select_course` (
  `sc_id` int(16) NOT NULL COMMENT '选课Id',
  `sc_student_id` int(16) DEFAULT NULL COMMENT '学生id',
  `sc_course_id` int(16) DEFAULT NULL COMMENT '课程Id',
  `exam_id` int(16) DEFAULT NULL,
  `homework_id` int(16) DEFAULT NULL COMMENT '作业Id',
  `sc_exam_score` int(16) DEFAULT NULL COMMENT '期末考试分数',
  `exam_status` varchar(16) DEFAULT NULL COMMENT '学生考试状态',
  `sc_home_scores` varchar(255) DEFAULT NULL COMMENT '作业分数，逗号分隔',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `rc_student_course`;
CREATE TABLE `rc_student_course` (
  `sc_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '选课Id',
  `sc_student_id` int(10) unsigned NOT NULL COMMENT '学生Id',
  `sc_course_id` int(10) unsigned NOT NULL COMMENT '课程Id',
  `sc_daily_score` int(10) unsigned DEFAULT NULL COMMENT '日常表现分',
  `sc_exam_score` int(10) unsigned DEFAULT NULL COMMENT '期末测试分',
  `sc_score` int(10) unsigned DEFAULT NULL COMMENT '总成绩',
  `progress` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学习进度',
  PRIMARY KEY (`sc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `rc_student`;
CREATE TABLE `rc_student` (
  `student_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '学生Id',
  `student_number` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学号',
  `student_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `student_password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of rc_student
-- ----------------------------
INSERT INTO `rc_student` VALUES ('1', '201711010001', '李雨轩', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('2', '201711010002', '宋健', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('3', '201711010003', '李同学1', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('4', '201711010004', '李同学2', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('5', '201711010005', '李同学3', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('6', '201711010006', '李同学4', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('7', '201711010007', '李同学5', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('8', '201711010008', '李同学6', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('9', '201711010009', '李同学7', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('10', '201711010010', '李同学8', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('11', '201711010011', '李同学9', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('12', '201711010012', '张同学1', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('13', '201711010013', '张同学2', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('14', '201711010014', '张同学3', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('15', '201711010015', '张同学4', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('16', '201711010016', '张同学5', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('17', '201711010017', '张同学6', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('18', '201711010018', '张同学7', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('19', '201711020001', '王同学1', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('20', '201711020002', '王同学2', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('21', '201711020003', '王同学3', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('22', '201711020004', '王同学4', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('23', '201711020005', '王同学5', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('24', '201711020006', '王同学6', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('25', '201711020007', '王同学7', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('26', '201711020008', '王同学8', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('27', '201711020009', '王同学9', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('28', '201711020010', '王同学10', '81a5f5a9bfde4cdcb5b9fe1f8508df2a');
INSERT INTO `rc_student` VALUES ('29', '666888', '小明同学', '06c28ab4065397781bb7fa3da670336b');

DROP TABLE IF EXISTS `rc_teacher`;
CREATE TABLE `rc_teacher` (
  `teacher_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '教师Id',
  `teacher_number` int(16) NOT NULL,
  `teacher_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '教师姓名',
  `teacher_password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `major` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '专业领域',
  `experirence` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教学经验',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '职称',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教师简介',
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7472762 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of rc_teacher
-- ----------------------------
INSERT INTO `rc_teacher` VALUES ('1', '0', '张三', '81a5f5a9bfde4cdcb5b9fe1f8508df2a', null, null, null, null);
INSERT INTO `rc_teacher` VALUES ('2', '0', '宋老师', '81a5f5a9bfde4cdcb5b9fe1f8508df2a', null, null, null, null);
INSERT INTO `rc_teacher` VALUES ('3', '0', '宋老师', '81a5f5a9bfde4cdcb5b9fe1f8508df2a', null, null, null, null);
INSERT INTO `rc_teacher` VALUES ('4', '0', '张老师', '81a5f5a9bfde4cdcb5b9fe1f8508df2a', null, null, null, null);
INSERT INTO `rc_teacher` VALUES ('5', '0', '吕老师', '81a5f5a9bfde4cdcb5b9fe1f8508df2a', null, null, null, null);
INSERT INTO `rc_teacher` VALUES ('6', '0', '王老师', '81a5f5a9bfde4cdcb5b9fe1f8508df2a', null, null, null, null);
INSERT INTO `rc_teacher` VALUES ('7', '0', '丁老师', '81a5f5a9bfde4cdcb5b9fe1f8508df2a', null, null, null, null);
INSERT INTO `rc_teacher` VALUES ('8', '0', '高老师', '81a5f5a9bfde4cdcb5b9fe1f8508df2a', null, null, null, null);
INSERT INTO `rc_teacher` VALUES ('9', '0', '赵老师', '81a5f5a9bfde4cdcb5b9fe1f8508df2a', null, null, null, null);
INSERT INTO `rc_teacher` VALUES ('7472761', '20170313', '小张老师', '06c28ab4065397781bb7fa3da670336b', null, null, null, null);
