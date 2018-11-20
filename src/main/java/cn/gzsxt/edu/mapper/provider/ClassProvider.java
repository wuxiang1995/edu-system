package cn.gzsxt.edu.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class ClassProvider {
	
	/**
	 * 按条件分页查询数据
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") int start,@Param("size") int size) {
		String sql="SELECT * FROM tb_edu_class WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("class_id")!= null) {
				builder.append(" AND  class_id=#{entity.class_id}");
			}
			
			if(entity.get("class_name")!= null) {
				builder.append(" AND  class_name like CONCAT('%',#{entity.class_name},'%')");
			}
			
			if (entity.get("class_student_number") != null) {
				builder.append(" AND class_student_number like CONCAT('%',#{entity.class_student_number},'%')");
			}

			if (entity.get("class_status") != null) {
				builder.append(" AND class_status=#{entity.class_status}");
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
		
		String sql="SELECT count(*) FROM tb_edu_class WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("class_id")!= null) {
				builder.append(" AND  class_id=#{entity.class_id}");
			}
			
			if(entity.get("class_name")!= null) {
				builder.append(" AND  class_name like CONCAT('%',#{class_name},'%')");
			}
			
			if (entity.get("class_student_number") != null) {
				builder.append(" AND class_student_number like CONCAT('%',#{class_student_number},'%')");
			}

			if (entity.get("class_status") != null) {
				builder.append(" AND class_status=#{class_status}");
			}

		}
		return builder.toString();
	}

	/**
	 * 批量删除
	 * @param classIdsMap
	 * @return
	 */
	public String deleteById(Map<String, Object[]> classIdsMap ) {
		Object[] classIds= classIdsMap.get("array");
		String idsStr = Arrays.toString(classIds);
		String sql="DELETE FROM tb_edu_class WHERE class_id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
		System.out.println(builder.toString());
		return builder.toString();
		
	}
	
	public String updateForNotnull(Map<String, Object> entity) {
		String sql="UPDATE tb_edu_class SET ";
		StringBuilder builder=new StringBuilder(sql);
		if(entity!=null) {
			if(entity.get("class_name")!= null) {
				builder.append(" class_name=#{class_name},");
			}
			
			if (entity.get("class_student_number") != null) {
				builder.append(" class_student_number=#{class_student_number},");
			}

			if (entity.get("class_status") != null) {
				builder.append(" class_status=#{class_status},");
			}
		}
		builder.delete(builder.length() - 1, builder.length());
		builder.append(" WHERE class_id=#{class_id}");
		return builder.toString();
		
	}
	

}
