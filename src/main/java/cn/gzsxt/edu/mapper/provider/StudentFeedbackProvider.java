package cn.gzsxt.edu.mapper.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StudentFeedbackProvider {
	private static final Logger logger=LogManager.getLogger(StudentFeedbackProvider.class);
	
	public String countByCondition(Map<String, Object> entity){
		String sql="SELECT COUNT(*) FROM tb_student_feedback WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		if (entity.get("student_feedback_id")!=null) {
			builder.append("AND feedback_id=#{student_feedback_grade}");
		}
		if (entity.get("student_feedback_comment")!=null) {
			builder.append("student_feedback_comment LIKE CONCAT('%',#{student_feedback_comment},'%')");
		}
		if (entity.get("student_id")!=null) {
			builder.append("AND  feedback_create_date=#{student_id}");
		}
		if (entity.get("feedback_id")!=null) {
			builder.append("AND  feedback_id=#{feedback_id}");
		}
		if (entity.get("student_feedback_date")!=null) {
			builder.append("AND student_feedback_date=#{student_feedback_date}");
		}
		
		if (entity.get("student_feedback_show")!=null) {
			builder.append("AND student_feedback_show=#{student_feedback_show}");
		}
		
		logger.debug(builder.toString());
		return builder.toString();

	}
	
	public String findByConditionToPage(@Param("entity")Map<String, Object> entity,@Param("start")int start, @Param("size")int size){
		String sql="SELECT * FROM tb_student_feedback WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		if (entity.get("student_feedback_id")!=null) {
			builder.append("AND feedback_id=#{entity.student_feedback_grade}");
		}
		if (entity.get("student_feedback_comment")!=null) {
			builder.append("student_feedback_comment LIKE CONCAT('%',#{entity.student_feedback_comment},'%')");
		}
		if (entity.get("student_id")!=null) {
			builder.append("AND  feedback_create_date=#{entity.student_id}");
		}
		if (entity.get("feedback_id")!=null) {
			builder.append("AND  feedback_id=#{entity.feedback_id}");
		}
		if (entity.get("student_feedback_date")!=null) {
			builder.append("AND student_feedback_date=#{entity.student_feedback_date}");
		}
		
		if (entity.get("student_feedback_show")!=null) {
			builder.append("AND student_feedback_show=#{entity.student_feedback_show}");
		}
		
		logger.debug(builder.toString());
		builder.append(" LIMIT #{start},#{size}");
		return builder.toString();
	}
}
