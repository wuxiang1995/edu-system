SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS tb_student_feedback;
DROP TABLE IF EXISTS tb_edu_feedback;
DROP TABLE IF EXISTS tb_class_stage;
DROP TABLE IF EXISTS tb_edu_chapter;
DROP TABLE IF EXISTS tb_edu_student;
DROP TABLE IF EXISTS tb_edu_class;
DROP TABLE IF EXISTS tb_edu_stage;
DROP TABLE IF EXISTS tb_edu_subject;




/* Create Tables */

CREATE TABLE tb_class_stage
(
	class_stage_id bigint NOT NULL AUTO_INCREMENT,
	class_grade_teacher bigint,
	class_stage_date datetime,
	class_id bigint NOT NULL,
	stage_id bigint NOT NULL,
	PRIMARY KEY (class_stage_id)
);


CREATE TABLE tb_edu_chapter
(
	chapter_id bigint NOT NULL AUTO_INCREMENT,
	chapter_title varchar(400),
	chapter_summary varchar(50),
	subject_id bigint NOT NULL,
	PRIMARY KEY (chapter_id)
);


CREATE TABLE tb_edu_class
(
	class_id bigint NOT NULL AUTO_INCREMENT,
	class_name varchar(50),
	class_student_number int,
	class_status int,
	PRIMARY KEY (class_id)
);


CREATE TABLE tb_edu_feedback
(
	feedback_id bigint NOT NULL AUTO_INCREMENT,
	feedback_title varchar(400),
	feedback_create_date datetime,
	feedback_close_date datetime,
	feedback_start_date datetime,
	feedback_admin_id bigint,
	feedback_class_id bigint,
	feedback_chapters varchar(500),
	feedback_student_total int,
	class_stage_id bigint NOT NULL,
	PRIMARY KEY (feedback_id)
);


CREATE TABLE tb_edu_stage
(
	stage_id bigint NOT NULL AUTO_INCREMENT,
	stage_name varchar(50),
	subject_id bigint NOT NULL,
	PRIMARY KEY (stage_id)
);


CREATE TABLE tb_edu_student
(
	student_id bigint NOT NULL AUTO_INCREMENT,
	student_name varchar(50),
	student_phone varchar(20),
	student_age int,
	student_sex char(2),
	student_status int,
	class_id bigint NOT NULL,
	PRIMARY KEY (student_id)
);


CREATE TABLE tb_edu_subject
(
	subject_id bigint NOT NULL AUTO_INCREMENT,
	subject_name varchar(50),
	PRIMARY KEY (subject_id)
);


CREATE TABLE tb_student_feedback
(
	student_feedback_id bigint NOT NULL AUTO_INCREMENT,
	-- 使用逗号分隔，跟章节顺序一一对应
	-- 
	student_feedback_grade int COMMENT '使用逗号分隔，跟章节顺序一一对应
',
	student_feedback_comment varchar(2000),
	student_id bigint NOT NULL,
	feedback_id bigint NOT NULL,
	student_feedback_date datetime,
	student_feedback_show int,
	PRIMARY KEY (student_feedback_id)
);






