package cn.gzsxt.edu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import cn.gzsxt.edu.mapper.provider.StudentFeedbackProvider;

@Mapper
public interface StudentFeedbackMapper {
	
	@SelectProvider(type=StudentFeedbackProvider.class,method="countByCondition")
	int countByCondition(Map<String, Object> condition);
	
	@SelectProvider(type=StudentFeedbackProvider.class,method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity")Map<String, Object> entity,@Param("start")int start, @Param("size")int size);
	
	@Insert(value="insert into tb_student_feedback (student_feedback_grade,student_feedback_comment,student_id,student_feedback_date,student_feedback_show,feedback_id) value(#{feedbackGrade},#{comment},#{student_id},#{student_feedback_date},#{niming},#{feedback_id})")
	@Options(useGeneratedKeys=true,keyColumn="stage_id",keyProperty="stage_id")
	int insert(Map<String, Object> entity);
	
	@Insert(value="insert into tb_student_evaluate (student_evaluate_grade,student_evaluate_comment,student_id,evaluate_id,student_evaluate_date,student_evaluate_show) value(#{student_evaluate_grade},#{student_evaluate_comment},#{student_id},#{evaluate_id},#{student_evaluate_date},#{student_evaluate_show})")
	@Options(useGeneratedKeys=true,keyColumn="stage_id",keyProperty="stage_id")
	int insertAdd(Map<String, Object> entity );
	
	@Select(value="SELECT * FROM tb_student_feedback where feedback_id=#{feedback_id}")
	List<Map<String, Object>> findAll(Object feedback_id);
	
	

}

