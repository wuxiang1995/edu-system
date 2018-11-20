SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS tb_admin;
DROP TABLE IF EXISTS tb_dictionary;
DROP TABLE IF EXISTS tb_power;
DROP TABLE IF EXISTS tb_modular;
DROP TABLE IF EXISTS tb_role;




/* Create Tables */

-- 管理员表
CREATE TABLE tb_admin
(
	admin_id bigint NOT NULL AUTO_INCREMENT COMMENT '管理编号',
	admin_name varchar(50) COMMENT '管理员名称',
	admin_account varchar(50) COMMENT '管理员账号',
	admin_pwd varchar(50) COMMENT '管理员密码',
	admin_status int unsigned COMMENT '管理员状态,0可用，1禁用',
	role_id bigint NOT NULL COMMENT '角色编号',
	PRIMARY KEY (admin_id)
) COMMENT = '管理员表';


-- 数据字典表
CREATE TABLE tb_dictionary
(
	dic_id bigint NOT NULL AUTO_INCREMENT COMMENT '数据字典编号',
	dic_value varchar(500) COMMENT '字典数据名',
	dic_type_code bigint COMMENT '字典类型编码',
	dic_type_name varchar(100) COMMENT '字典类型名称',
	dic_name varchar(200) COMMENT '字典名',
	PRIMARY KEY (dic_id)
) COMMENT = '数据字典表';


-- 模块表
CREATE TABLE tb_modular
(
	modular_id bigint NOT NULL AUTO_INCREMENT COMMENT '模块编号',
	modular_name varchar(200) COMMENT '模块名',
	PRIMARY KEY (modular_id)
) COMMENT = '模块表';


-- 权限表
CREATE TABLE tb_power
(
	power_id bigint NOT NULL AUTO_INCREMENT COMMENT '权限编号',
	power_name varchar(200) COMMENT '权限名',
	power_action varchar(500) COMMENT '权限路径',
	power_parent bigint COMMENT '父权限',
	power_is_show int COMMENT '是否显示，0显示，1隐藏',
	modular_id bigint NOT NULL COMMENT '模块编号',
	PRIMARY KEY (power_id)
) COMMENT = '权限表';


-- 角色表
CREATE TABLE tb_role
(
	role_id bigint NOT NULL AUTO_INCREMENT COMMENT '角色编号',
	role_name varchar(50) COMMENT '角色名',
	role_powers varchar(500) COMMENT '角色权限',
	PRIMARY KEY (role_id)
) COMMENT = '角色表';



/* Create Foreign Keys */

ALTER TABLE tb_power
	ADD FOREIGN KEY (modular_id)
	REFERENCES tb_modular (modular_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tb_admin
	ADD FOREIGN KEY (role_id)
	REFERENCES tb_role (role_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



