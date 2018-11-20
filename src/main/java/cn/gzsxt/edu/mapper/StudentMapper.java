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

import cn.gzsxt.edu.mapper.provider.StudentProvider;



@Mapper
public	interface StudentMapper {
	
	/**
	 *按条件查询分页条件查询学生信息
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	
	@SelectProvider(type = StudentProvider.class, method = "findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") int start,@Param("size") int size);
	/**
	 * 查询总学生数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = StudentProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	/**
	 * 新增一条学生记录
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_edu_student (student_name,student_phone,student_age,student_sex,student_status,class_id)	VALUES (#{student_name},#{student_phone},#{student_age},#{student_sex},#{student_status},#{class_id})")
	@Options(useGeneratedKeys=true,keyProperty="student_id",keyColumn="student_id")
	int insert(Map<String, Object> entity);
	
	/**
	 * 通过id查询班级信息
	 * @param modularId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_edu_student WHERE student_id=#{studentId}")
	Map<String, Object> findById(Object studentId);
	
	/**
	 * 更新非空字段
	 * @param entity
	 * @return
	 */
	@UpdateProvider(type=StudentProvider.class,method="updateForNotnull")
	int updateForNotnull(Map<String, Object> entity);
	
	/**
	 * 根据id删除学生
	 * @param classIds
	 * @return
	 */
	@DeleteProvider(type=StudentProvider.class,method="deleteById")
	int deleteById(Object... classIds);
	
	@Select(value="SELECT *	FROM tb_edu_student WHERE student_name=#{student_name}")
	Map<String, Object> findNameById(Map<String, Object> entity);

}
