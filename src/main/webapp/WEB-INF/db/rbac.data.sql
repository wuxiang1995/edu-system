-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.1.22-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 正在导出表  edu-system.tb_admin 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_admin` DISABLE KEYS */;
INSERT INTO `tb_admin` (`admin_id`, `admin_name`, `admin_account`, `admin_pwd`, `admin_status`, `role_id`) VALUES
	(1, '系统管理员', 'root', 'KjVa/8YMZHYa/hn+OAJzug==', 0, 1);
/*!40000 ALTER TABLE `tb_admin` ENABLE KEYS */;

-- 正在导出表  edu-system.tb_dictionary 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `tb_dictionary` DISABLE KEYS */;
INSERT INTO `tb_dictionary` (`dic_id`, `dic_name`, `dic_value`, `dic_type_code`, `dic_type_name`) VALUES
	(1, '可用', 0, 1000, '管理员使用状态'),
	(2, '禁用', 1, 1000, '管理员使用状态'),
	(3, '显示', 0, 1001, '权限显示状态'),
	(4, '隐藏', 1, 1001, '权限显示状态');
/*!40000 ALTER TABLE `tb_dictionary` ENABLE KEYS */;

-- 正在导出表  edu-system.tb_modular 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `tb_modular` DISABLE KEYS */;
INSERT INTO `tb_modular` (`modular_id`, `modular_name`) VALUES
	(1, '系统管理模块');
/*!40000 ALTER TABLE `tb_modular` ENABLE KEYS */;

-- 正在导出表  edu-system.tb_power 的数据：~30 rows (大约)
/*!40000 ALTER TABLE `tb_power` DISABLE KEYS */;
INSERT INTO `tb_power` (`power_id`, `power_name`, `power_action`, `power_parent`, `power_is_show`, `modular_id`) VALUES
	(1, '后台用户管理', '/admin/toAdminList.do', 0, 0, 1),
	(2, '角色管理', '/role/toRoleList.do', 0, 0, 1),
	(3, '权限管理', '/power/toPowerList.do', 0, 0, 1),
	(4, '模块管理', '/modular/toModularList.do', 0, 0, 1),
	(5, '系统功能-to修改密码', '/admin/toAdminSetting.do', 0, 0, 1),
	(6, '系统功能-修改密码', '/admin/setAdminPwd.do', 0, 0, 1);
	(7, '后台管理-To增加', '/admin/toAdminAdd.do', 1, 0, 1),
	(8, '后台用户管理-增加', '/admin/addAdmin.do', 1, 0, 1),
	(9, '后台用户管理-To编辑', '/admin/toAdminEdit.do', 1, 0, 1),
	(10, '后台用户管理-编辑', '/admin/editAdmin.do', 1, 0, 1),
	(11, '后台用户管理-删除', '/admin/deleteAdmin.do', 1, 0, 1),
	(12, '后台用户管理-批量删除', '/admin/deleteAdmins.do', 1, 0, 1),
	(13, '角色管理-To增加', '/role/toRoleAdd.do', 2, 0, 1),
	(14, '角色管理-增加', '/role/addRole.do', 2, 0, 1),
	(15, '角色管理-To编辑', '/role/toRoleEdit.do', 2, 0, 1),
	(16, '角色管理-编辑', '/role/editRole.do', 2, 0, 1),
	(17, '角色管理-删除', '/role/deleteRole.do', 2, 0, 1),
	(18, '角色管理-批量删除', '/role/deleteRoles.do', 2, 0, 1),
	(19, '权限管理-To增加', '/power/toPowerAdd.do', 3, 0, 1),
	(20, '权限管理-增加', '/power/addPower.do', 3, 0, 1),
	(21, '权限管理-To编辑', '/power/toPowerEdit.do', 3, 0, 1),
	(22, '权限管理-编辑', '/power/editPower.do', 3, 0, 1),
	(23, '权限管理-删除', '/power/deletePower.do', 3, 0, 1),
	(24, '权限管理-批量删除', '/power/deletePowers.do', 3, 0, 1),
	(25, '模块管理-To增加', '/modular/toModularAdd.do', 4, 0, 1),
	(26, '模块管理-增加', '/modular/addModular.do', 4, 0, 1),
	(27, '模块管理-To编辑', '/modular/toModularEdit.do', 4, 0, 1),
	(28, '模块管理-编辑', '/modular/editModular.do', 4, 0, 1),
	(29, '模块管理-删除', '/modular/deleteModular.do', 4, 0, 1),
	(30, '模块管理-批量删除', '/modular/deleteModulars.do', 4, 0, 1),
	
/*!40000 ALTER TABLE `tb_power` ENABLE KEYS */;

-- 正在导出表  edu-system.tb_role 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` (`role_id`, `role_name`, `role_powers`) VALUES
	(1, '超级管理员', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30');
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
