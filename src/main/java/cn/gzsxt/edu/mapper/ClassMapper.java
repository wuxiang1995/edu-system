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

import cn.gzsxt.edu.mapper.provider.ClassProvider;
import cn.gzsxt.edu.mapper.provider.ModularProvider;

@Mapper
public interface ClassMapper {
	
	
	@SelectProvider(type = ClassProvider.class, method = "findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") int start,@Param("size") int size);
	
	@SelectProvider(type = ClassProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);


	/**
	 * 添加班级
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_edu_class (class_name,class_student_number,class_status)	VALUES (#{class_name,},#{class_student_number},#{class_status})")
	@Options(useGeneratedKeys=true,keyProperty="class_id",keyColumn="class_id")
	int insert(Map<String, Object> entity);
	/**
	 * 删除班级
	 * @param modularIds
	 * @return
	 */
	@DeleteProvider(type=ClassProvider.class,method="deleteById")
	int deleteById(Object... classIds);
	
	/**
	 * 通过id查询班级信息
	 * @param modularId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_edu_class WHERE class_id=#{classId}")
	Map<String, Object> findById(Object classId);
	
	/**
	 * 更新非空字段
	 * @return
	 */
	@UpdateProvider(type=ClassProvider.class,method="updateForNotnull")
	int updateForNotnull(Map<String, Object> entity);
	
	/**
	 * 查询所有班级
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_edu_class")
	List<Map<String, Object>> findAll();
	
	

}
