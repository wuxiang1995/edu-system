package cn.gzsxt.edu.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ChapterProvider {
	
	private static final Logger logger =LogManager.getLogger(ChapterProvider.class);
	
	/**
	 * 模糊查询并且分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") int start,@Param("size") int size) {
		String sql="SELECT * FROM tb_edu_chapter WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("chapter_id")!= null) {
				builder.append(" AND  chapter_id=#{chapter_id}");
			}
			
			if(entity.get("chapter_title")!= null) {
				builder.append(" AND  chapter_title like CONCAT('%',#{chapter_title},'%')");
			}
			if(entity.get("chapter_summary")!= null) {
				builder.append(" AND  chapter_summary like CONCAT('%',#{chapter_summary},'%')");
			}
			if(entity.get("subject_id")!= null) {
				builder.append(" AND  subject_id=#{subject_id}");
			}
		}
		builder.append(" LIMIT #{start},#{size}");
		
		
		logger.debug("课程模糊查询："+builder.toString());
		
		return builder.toString();
		
	}
	
	/**
	 * 根据条件统计记录数
	 * @param entity
	 * @return
	 */
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_edu_chapter WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("chapter_id")!= null) {
				builder.append(" AND  chapter_id=#{chapter_id}");
			}
			
			if(entity.get("chapter_title")!= null) {
				builder.append(" AND  chapter_title like CONCAT('%',#{chapter_title},'%')");
			}
			if(entity.get("chapter_summary")!= null) {
				builder.append(" AND  chapter_summary like CONCAT('%',#{chapter_summary},'%')");
			}
			if(entity.get("subject_id")!= null) {
				builder.append(" AND  subject_id=#{subject_id}");
			}
		}
		logger.debug("课程模糊查询："+builder.toString());
		
		return builder.toString();
	}
	
	/**
	 * 更新非空字段
	 * @param entity
	 * @return
	 */
	public String updateForNotnull(Map<String, Object> entity) {
		String sql = "UPDATE tb_edu_chapter SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity.get("chapter_title") != null) {
			builder.append("chapter_title=#{chapter_title},");
		}
		if (entity.get("chapter_summary") != null) {
			builder.append("chapter_summary=#{chapter_summary},");
		}
		builder.delete(builder.length() - 1, builder.length());
		builder.append(" WHERE chapter_id=#{chapter_id}");
		logger.debug("课程更新："+builder.toString());
		return builder.toString();
	}
	
	/**
	 * 批量删除
	 * @param chapterIdsMap
	 * @return
	 */
	public String deleteById(Map<String, Object[]> chapterIdsMap) {
		Object[] chapterIds = chapterIdsMap.get("array");
		String idsStr = Arrays.toString(chapterIds);
		String sql="DELETE FROM tb_edu_chapter WHERE chapter_id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
	
		return builder.toString();
	}
	public String findChapters(Object chapterIds) {
		String sql="SELECT * FROM tb_edu_chapter WHERE chapter_id in (";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(chapterIds);
		builder.append(")");
		//将[]修改为()
//		builder.setCharAt(builder.indexOf("["), '(');
//		builder.setCharAt(builder.indexOf("]"), ')');
		
		return builder.toString();
	}

}
