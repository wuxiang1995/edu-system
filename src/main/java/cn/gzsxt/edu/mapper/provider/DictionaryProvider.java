package cn.gzsxt.edu.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DictionaryProvider {

private static final Logger logger =LogManager.getLogger(DictionaryProvider.class);
	
	/**
	 * 模糊查询并且分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") int start,@Param("size") int size) {
		String sql="SELECT * FROM tb_dictionary WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("dic_id")!= null) {
				builder.append(" AND  dic_id=#{entity.dic_id}");
			}
			
			if(entity.get("dic_name")!= null) {
				builder.append(" AND  dic_name like CONCAT('%',#{entity.dic_name},'%')");
			}
			
			if(entity.get("dic_value")!= null) {
				builder.append(" AND  dic_value like CONCAT('%',#{entity.dic_value},'%')");
			}
			
			if(entity.get("dic_type_code")!= null) {
				builder.append(" AND  dic_type_code=#{entity.dic_type_code}");
			}
			
			if(entity.get("dic_type_name")!= null) {
				builder.append(" AND  dic_type_name like CONCAT('%',#{entity.dic_type_name},'%')");
			}
		}
		builder.append(" LIMIT #{start},#{size}");
		
		
		logger.debug("数据字典模糊查询："+builder.toString());
		
		return builder.toString();
		
	}
	
	/**
	 * 根据条件统计记录数
	 * @param entity
	 * @return
	 */
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_dictionary WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("dic_id")!= null) {
				builder.append(" AND  dic_id=#{dic_id}");
			}
			
			if(entity.get("dic_name")!= null) {
				builder.append(" AND  dic_name like CONCAT('%',#{entity.dic_name},'%')");
			}
			
			if(entity.get("dic_value")!= null) {
				builder.append(" AND  dic_value like CONCAT('%',#{entity.dic_value},'%')");
			}
			
			if(entity.get("dic_type_code")!= null) {
				builder.append(" AND  dic_type_code=#{entity.dic_type_code}");
			}
			
			if(entity.get("dic_type_name")!= null) {
				builder.append(" AND  dic_type_name like CONCAT('%',#{entity.dic_type_name},'%')");
			}
			
		}
		logger.debug("数据字典模糊查询："+builder.toString());
		
		return builder.toString();
	}
	
	/**
	 * 更新非空字段
	 * @param entity
	 * @return
	 */
	public String updateForNotnull(Map<String, Object> entity) {
		String sql = "UPDATE tb_dictionary SET ";
		StringBuilder builder = new StringBuilder(sql);

		
		if(entity.get("dic_name")!= null) {
			builder.append("dic_name=#{dic_name},");
		}
		
		if(entity.get("dic_value")!= null) {
			builder.append("dic_value=#{dic_value},");
		}
		
		if(entity.get("dic_type_code")!= null) {
			builder.append("dic_type_code=#{dic_type_code},");
		}
		
		if(entity.get("dic_type_name")!= null) {
			builder.append("dic_type_name=#{dic_type_name},");
		}
		
		builder.delete(builder.length() - 1, builder.length());
		builder.append(" WHERE dic_id= #{dic_id}");
		logger.debug("数据字典更新："+builder.toString());
		return builder.toString();
	}
	
	/**
	 * 批量删除
	 * @param dictionaryIdsMap
	 * @return
	 */
	public String deleteById(Map<String, Object[]> dictionaryIdsMap) {
		Object[] dictionaryIds = dictionaryIdsMap.get("array");
		String idsStr = Arrays.toString(dictionaryIds);
		String sql="DELETE FROM tb_dictionary WHERE dic_id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
	
		return builder.toString();
	}
	
	
}
