package cn.gzsxt.edu.mapper.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FeedbackProvider {
	private static final Logger logger=LogManager.getLogger(FeedbackProvider.class);
	public String countByCondition(Map<String, Object> entity){
		String sql="SELECT COUNT(*) FROM tb_edu_feedback WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		if (entity.get("feedback_id")!=null) {
			builder.append("AND feedback_id=#{feedback_id}");
		}
		if (entity.get("feedback_title")!=null) {
			builder.append("AND feedback_title LIKE CONCAT('%',#{feedback_title},'%')");
		}
		if (entity.get(" feedback_create_date")!=null) {
			builder.append("AND  feedback_create_date=#{ feedback_create_date}");
		}
		if (entity.get("feedback_close_date")!=null) {
			builder.append("AND  feedback_create_date=#{ feedback_create_date}");
		}
		if (entity.get("feedback_start_date")!=null) {
			builder.append("AND feedback_start_date=#{feedback_start_date}");
		}
		if (entity.get("feedback_admin_id")!=null) {
			builder.append("AND feedback_admin_id=#{feedback_admin_id}");
		}
		
		if (entity.get("feedback_class_id")!=null) {
			builder.append("AND  feedback_class_id=#{feedback_class_id}");
		}
		if (entity.get("feedback_chapter_id")!=null) {
			builder.append("AND feedback_chapter_id =#{feedback_chapter_id}");
		}
		if (entity.get("feedback_student_total")!=null) {
			builder.append("AND feedback_student_total=#{feedback_student_total}");
		}
		if (entity.get("class_stage_id")!=null) {
			builder.append("AND class_stage_id=#{class_stage_id}");
		}
		logger.debug(builder.toString());
		return builder.toString();
	}
	
	public String findByConditionToPage(@Param("entity")Map<String, Object> entity,@Param("start")int start, @Param("size")int size){
		String sql="SELECT * FROM tb_edu_feedback WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		if (entity.get("feedback_id")!=null) {
			builder.append("AND feedback_id=#{entity.feedback_id}");
		}
		if (entity.get("feedback_title")!=null) {
			builder.append("AND feedback_title LIKE CONCAT('%',#{entity.feedback_title},'%')");
		}
		if (entity.get(" feedback_create_date")!=null) {
			builder.append("AND  feedback_create_date=#{ entity.feedback_create_date}");
		}
		if (entity.get("feedback_close_date")!=null) {
			builder.append("AND  feedback_create_date=#{ entity.feedback_create_date}");
		}
		if (entity.get("feedback_start_date")!=null) {
			builder.append("AND feedback_start_date=#{entity.feedback_start_date}");
		}
		if (entity.get("feedback_admin_id")!=null) {
			builder.append("AND feedback_admin_id=#{entity.feedback_admin_id}");
		}
		
		if (entity.get("feedback_class_id")!=null) {
			builder.append("AND  feedback_class_id=#{entity.feedback_class_id}");
		}
		if (entity.get("feedback_chapter_id")!=null) {
			builder.append("AND feedback_chapter_id =#{entity.feedback_chapter_id}");
		}
		if (entity.get("feedback_student_total")!=null) {
			builder.append("AND feedback_student_total=#{entity.feedback_student_total}");
		}
		if (entity.get("class_stage_id")!=null) {
			builder.append("AND class_stage_id=#{entity.class_stage_id}");
		}
		
		builder.append(" LIMIT #{start},#{size}");
		logger.debug(builder.toString());
		return builder.toString();
	}
}
