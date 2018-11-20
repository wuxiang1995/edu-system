package cn.gzsxt.edu.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class EvaluateProvider {
	private static final Logger logger=LogManager.getLogger(EvaluateProvider.class);
	public String countByCondition(Map<String, Object> entity){
		String sql="SELECT COUNT(*) FROM tb_edu_evaluate WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		if (entity.get("evaluate_id")!=null) {
			builder.append("AND evaluate_id=#{evaluate_id}");
		}
		if (entity.get("evaluate_title")!=null) {
			builder.append("AND evaluate_title LIKE CONCAT('%',#{evaluate_title},'%')");
		}
		if (entity.get(" evaluate_create_date")!=null) {
			builder.append("AND  evaluate_create_date=#{ evaluate_create_date}");
		}
		if (entity.get("evaluate_close_date")!=null) {
			builder.append("AND  evaluate_create_date=#{ evaluate_create_date}");
		}
		if (entity.get("evaluate_start_date")!=null) {
			builder.append("AND evaluate_start_date=#{evaluate_start_date}");
		}
		if (entity.get("evaluate_admin_id")!=null) {
			builder.append("AND evaluate_admin_id=#{evaluate_admin_id}");
		}
		
		if (entity.get("evaluate_class_id")!=null) {
			builder.append("AND  evaluate_class_id=#{evaluate_class_id}");
		}
		if (entity.get("field_id")!=null) {
			builder.append("AND field_id =#{field_id}");
		}
		if (entity.get("evaluate_student_total")!=null) {
			builder.append("AND evaluate_student_total=#{evaluate_student_total}");
		}
		if (entity.get("class_stage_id")!=null) {
			builder.append("AND class_stage_id=#{class_stage_id}");
		}
		logger.debug(builder.toString());
		return builder.toString();
	}
	
	public String findByConditionToPage(@Param("entity")Map<String, Object> entity,@Param("start")int start, @Param("size")int size){
		String sql="SELECT * FROM tb_edu_evaluate WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		if (entity.get("evaluate_id")!=null) {
			builder.append("AND evaluate_id=#{entity.evaluate_id}");
		}
		if (entity.get("evaluate_title")!=null) {
			builder.append("AND evaluate_title LIKE CONCAT('%',#{entity.evaluate_title},'%')");
		}
		if (entity.get(" evaluate_create_date")!=null) {
			builder.append("AND  evaluate_create_date=#{ entity.evaluate_create_date}");
		}
		if (entity.get("evaluate_close_date")!=null) {
			builder.append("AND  evaluate_create_date=#{ entity.evaluate_create_date}");
		}
		if (entity.get("evaluate_start_date")!=null) {
			builder.append("AND evaluate_start_date=#{entity.evaluate_start_date}");
		}
		if (entity.get("evaluate_admin_id")!=null) {
			builder.append("AND evaluate_admin_id=#{entity.evaluate_admin_id}");
		}
		
		if (entity.get("evaluate_class_id")!=null) {
			builder.append("AND  evaluate_class_id=#{entity.evaluate_class_id}");
		}
		if (entity.get("field_id")!=null) {
			builder.append("AND field_id =#{field_id}");
		}
		if (entity.get("evaluate_student_total")!=null) {
			builder.append("AND evaluate_student_total=#{entity.evaluate_student_total}");
		}
		if (entity.get("class_stage_id")!=null) {
			builder.append("AND class_stage_id=#{entity.class_stage_id}");
		}
		
		builder.append(" LIMIT #{start},#{size}");
		logger.debug(builder.toString());
		return builder.toString();
	}
	
	
	public String deleteByIds(Map<String, Object[]> evaluateIdsMap){
		Object[] evaluateIds=evaluateIdsMap.get("array");
		String idsStr= Arrays.toString(evaluateIds);
		
		String sql="DELETE FROM tb_edu_evaluate WHERE evaluate_id in ";
		StringBuilder builder=new StringBuilder(sql);

		builder.append(idsStr);
		
		builder.setCharAt(builder.indexOf("["),'(');
		builder.setCharAt(builder.indexOf("]"), ')');
		
		return builder.toString();
	}
}
