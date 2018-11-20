package cn.gzsxt.edu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.gzsxt.edu.mapper.provider.AdminProvider;

/**
 * 管理员表，映射操作接口
 * @author ranger
 */
@Mapper
public interface AdminMapper {
	
	/**
	 * 插入管理员记录
	 * @param entity 数据实体
	 * @return 插入影响行号
	 */
	@Insert(value="INSERT INTO tb_admin	(admin_name, admin_account, admin_pwd, admin_status, role_id)	VALUES (#{admin_name}, #{admin_account}, #{admin_pwd}, #{admin_status}, #{role_id})")
	@Options(useGeneratedKeys=true,keyProperty="admin_id",keyColumn="admin_id")
	int insert(Map<String, Object> entity);
	
	/**
	 * 通过账号名查询记录
	 * @param accountName
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_admin  WHERE admin_account = #{accountName}")
	Map<String, Object> findByAccount(Object accountName);
	
	/**
	 * 通过管理员编号查询记录
	 * @param adminId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_admin  WHERE admin_id = #{adminId}")
	Map<String, Object> findById(Object adminId);
	
	
	/**
	 * 更新管理员非空的字段
	 * @param entity
	 * @return
	 */
	@UpdateProvider(type=AdminProvider.class,method="updateForNotnull")
	int updateForNotnull(Map<String, Object> entity);
	
	
	/**
	 * 条件分页的的实现
	 * 注意事项：如果多个参数，那么必须要所有的参数加上注解
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = AdminProvider.class, method = "findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") int start,@Param("size") int size);
	
	/**
	 * 通过条件统计记录数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = AdminProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	

	
	/**
	 * 查询全部记录
	 * @param accountName
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_admin where role_id=2")
	List<Map<String, Object>> findAllTeacher();

	

	
	/**
	 * 通过编号删除管理员
	 * @param adminId
	 * @return
	 */
	@DeleteProvider(type=AdminProvider.class,method="deleteById")
	int deleteById(Object... adminIds);
	
	@SelectProvider(type=AdminProvider.class,method="selectById")
	List<Map<String, Object>> selectById(Object... adminIds);

}
