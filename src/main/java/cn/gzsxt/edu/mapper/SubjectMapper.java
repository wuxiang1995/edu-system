package cn.gzsxt.edu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface SubjectMapper {
	/**
	 * 通过编号查学科名称
	 * @param subjectId
	 * @return
	 */
	@Select("SELECT * FROM tb_edu_subject where subject_id=#{subjectId} ")
	Map<String, Object> findSubjectById(Object subjectId);
	
	
	/**
	 *  增加学科
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_edu_subject (subject_name)	VALUES (#{subject_name})")
	@Options(useGeneratedKeys=true,keyProperty="subject_id",keyColumn="subject_id")
	int insert(Map<String, Object> entity);

	@Select(value="select * from tb_edu_subject")
	List<Map<String, Object>> findAll();

	@Update(value="update tb_edu_subject set subject_name=#{subject_name} where subject_id=#{subject_id} ")
	int updateBysubjectId(Map<String, Object> entity);
}
