package cn.gzsxt.edu.mapper.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StudentEvaluateProvider {
	private static final Logger logger=LogManager.getLogger(StudentEvaluateProvider.class);
	
	public String countByCondition(Map<String, Object> entity){
		String sql="SELECT COUNT(*) FROM tb_student_evaluate WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		if (entity.get("student_evaluate_id")!=null) {
			builder.append("AND evaluate_id=#{student_evaluate_grade}");
		}
		if (entity.get("student_evaluate_comment")!=null) {
			builder.append("student_evaluate_comment LIKE CONCAT('%',#{student_evaluate_comment},'%')");
		}
		if (entity.get("student_id")!=null) {
			builder.append("AND  evaluate_create_date=#{student_id}");
		}
		if (entity.get("evaluate_id")!=null) {
			builder.append("AND  evaluate_id=#{evaluate_id}");
		}
		if (entity.get("student_evaluate_date")!=null) {
			builder.append("AND student_evaluate_date=#{student_evaluate_date}");
		}
		
		if (entity.get("student_evaluate_show")!=null) {
			builder.append("AND student_evaluate_show=#{student_evaluate_show}");
		}
		
		logger.debug(builder.toString());
		return builder.toString();

	}
	
	public String findByConditionToPage(@Param("entity")Map<String, Object> entity,@Param("start")int start, @Param("size")int size){
		String sql="SELECT * FROM tb_student_evaluate WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		if (entity.get("student_evaluate_id")!=null) {
			builder.append("AND evaluate_id=#{entity.student_evaluate_grade}");
		}
		if (entity.get("student_evaluate_comment")!=null) {
			builder.append("student_evaluate_comment LIKE CONCAT('%',#{entity.student_evaluate_comment},'%')");
		}
		if (entity.get("student_id")!=null) {
			builder.append("AND  evaluate_create_date=#{entity.student_id}");
		}
		if (entity.get("evaluate_id")!=null) {
			builder.append("AND  evaluate_id=#{entity.evaluate_id}");
		}
		if (entity.get("student_evaluate_date")!=null) {
			builder.append("AND student_evaluate_date=#{entity.student_evaluate_date}");
		}
		
		if (entity.get("student_evaluate_show")!=null) {
			builder.append("AND student_evaluate_show=#{entity.student_evaluate_show}");
		}
		
		logger.debug(builder.toString());
		builder.append(" LIMIT #{start},#{size}");
		return builder.toString();
	}
	
	
}
