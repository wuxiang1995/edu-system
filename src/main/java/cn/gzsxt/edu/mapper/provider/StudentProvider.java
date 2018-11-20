package cn.gzsxt.edu.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class StudentProvider{

	/**
	 * 按条件分页查询数据
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") int start,@Param("size") int size) {
		String sql="SELECT * FROM tb_edu_student WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("student_id")!= null) {
				builder.append(" AND  student_id=#{entity.student_id}");
			}
			
			if(entity.get("student_name")!= null) {
				builder.append(" AND  student_name like CONCAT('%',#{entity.student_name},'%')");
			}
			
			if (entity.get("student_phone") != null) {
				builder.append(" AND student_phone like CONCAT('%',#{entity.student_phone},'%')");
			}

			if (entity.get("student_age") != null) {
				builder.append(" AND student_age=#{entity.student_age}");
			}

			if (entity.get("student_sex") != null) {
				builder.append(" AND student_sex=#{entity.student_sex}");
			}

			if (entity.get("student_status") != null) {
				builder.append(" AND student_status = #{entity.student_status}");
			}
			
			if (entity.get("class_id") != null) {
				builder.append(" AND class_id = #{entity.class_id}");
			}
		}
		builder.append(" LIMIT #{start},#{size}");
		return builder.toString();
	}
	/**
	 * 查询总记录数
	 * @param entity
	 * @return
	 */
	public String countByCondition(Map<String, Object> entity) {
		
		String sql="SELECT count(*) FROM tb_edu_student WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("student_id")!= null) {
				builder.append(" AND  student_id=#{entity.student_id}");
			}
			
			if(entity.get("student_name")!= null) {
				builder.append(" AND  student_name like CONCAT('%',#{student_name},'%')");
			}
			
			if (entity.get("student_phone") != null) {
				builder.append(" AND student_phone like CONCAT('%',#{student_phone},'%')");
			}

			if (entity.get("student_age") != null) {
				builder.append(" AND student_age=#{student_age}");
			}

			if (entity.get("student_sex") != null) {
				builder.append(" AND student_sex=#{student_sex}");
			}

			if (entity.get("student_status") != null) {
				builder.append(" AND student_status=#{student_status}");
			}
			
			if (entity.get("class_id") != null) {
				builder.append(" AND class_id=#{class_id}");
			}
		}
		return builder.toString();
	}
	
	public String updateForNotnull(Map<String, Object> entity) {
		String sql="UPDATE tb_edu_student SET ";
		StringBuilder builder=new StringBuilder(sql);
		if(entity!=null) {
			if(entity.get("student_name")!= null) {
				builder.append(" student_name=#{student_name},");
			}
			
			if (entity.get("student_phone") != null) {
				builder.append(" student_phone=#{student_phone},");
			}

			if (entity.get("student_age") != null) {
				builder.append(" student_age=#{student_age},");
			}

			if (entity.get("student_sex") != null) {
				builder.append(" student_sex=#{student_sex},");
			}

			if (entity.get("student_status") != null) {
				builder.append(" student_status=#{student_status},");
			}
			if (entity.get("class_id") != null) {
				builder.append(" class_id=#{class_id},");
			}
		}
		builder.delete(builder.length() - 1, builder.length());
		builder.append(" WHERE student_id=#{student_id}");
		return builder.toString();
		
	}

	public String deleteById(Map<String, Object[]> classIdsMap ) {
		Object[] classIds= classIdsMap.get("array");
		String idsStr = Arrays.toString(classIds);
		String sql="DELETE FROM tb_edu_student WHERE student_id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
		System.out.println(builder.toString());
		return builder.toString();
		
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
}
