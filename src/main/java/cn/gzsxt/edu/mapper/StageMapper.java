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
import org.apache.ibatis.annotations.Update;

import cn.gzsxt.edu.mapper.provider.StageProvider;
@Mapper
public interface StageMapper {
	@Select(value="SELECT *	FROM tb_class_stage LIMIT #{start},#{size}")
	List<Map<String, Object>> findAllByPage(@Param("start") int start,@Param("size") int size);
	@Select(value="SELECT * FROM tb_edu_stage a JOIN tb_edu_subject b ON a.subject_id=b.subject_id WHERE a.stage_id=#{stageId}")
	Map<String, Object> findStageAndSubjectByStageId(Object stageId);
	@SelectProvider(type = StageProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	@DeleteProvider(type=StageProvider.class,method="deleteById")
	int deleteById(Object... classStageIds);
	
	
	@SelectProvider(type=StageProvider.class,method="selectById")
	List<Map<String, Object>> selectById(Object... stageIds);
	
	@Select(value="SELECT *	FROM tb_class_stage where class_stage_id=#{stageId}")
	Map<String, Object> findByClassStageId(Object stageId);
	@Update(value="UPDATE tb_class_stage SET class_stage_teacher=#{class_stage_teacher},stage_id=#{stage_id},class_id=#{class_id},class_stage_date=#{class_stage_date} WHERE class_stage_id=#{class_stage_id}")
	int updateClassStage(Map<String, Object> entity);
	@Update(value="UPDATE tb_edu_stage SET subject_id=#{subject_id} where stage_id=#{stage_id}")
	int updateStageAndSubject(Map<String, Object> entity);
	@Select(value="SELECT * FROM tb_edu_stage")
	List<Map<String, Object>> findStage();
	@Insert(value="insert into tb_class_stage (class_stage_teacher,class_id,stage_id,subject_id,class_stage_date) value(#{class_stage_teacher},#{class_id},#{stage_id},#{subject_id},#{class_stage_date}) ")
	@Options(useGeneratedKeys=true,keyColumn="class_stage_id",keyProperty="class_stage_id")
	int insertToClassStage(Map<String, Object> entity);
	@Insert(value="insert into tb_edu_stage (subject_id,stage_name) value(#{subject_id},#{stage_name})")
	@Options(useGeneratedKeys=true,keyColumn="stage_id",keyProperty="stage_id")
	int insertToStage(Map<String, Object> entity);
	@Select(value="SELECT *	FROM tb_class_stage where class_id=#{classId}")
	List<Map<String, Object>> findByClassId(Object classId);
	@Select(value="SELECT * FROM tb_edu_stage where subject_id=#{subjectId}")
	List<Map<String, Object>> findStageBySubjectId(Object subjectId);
	@Select(value="SELECT * FROM tb_class_stage where class_id=#{feedback_class_id} and stage_id=#{feedback_stage}")
	Map<String, Object> findStageClassId(Map<String, Object> entity);
}
