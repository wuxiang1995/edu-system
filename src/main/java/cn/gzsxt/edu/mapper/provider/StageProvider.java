package cn.gzsxt.edu.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/** 
* @author wuxiang
* @date  2018年11月13日 下午5:41:15 
* 
*/
public class StageProvider {
	private static final Logger logger =LogManager.getLogger(StageProvider.class);

	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_class_stage WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("class_stage_id")!= null) {
				builder.append(" AND  class_stage_id=#{class_stage_id}");
			}
			
			if(entity.get("class_stage_teacher")!= null) {
				builder.append(" AND  class_stage_teacher like CONCAT('%',#{class_stage_teacher},'%')");
			}
		}
		logger.debug("模块模糊查询："+builder.toString());
		
		return builder.toString();
	}
	
	public String deleteById(Map<String, Object[]> stageIdsMap) {
		Object[] stageIds = stageIdsMap.get("array");
		String idsStr = Arrays.toString(stageIds);
		String sql="DELETE FROM tb_class_stage WHERE class_stage_id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
	
		return builder.toString();
	}
	public String selectById(Map<String, Object[]> stageIdsMap) {
		Object[] stageIds = stageIdsMap.get("array");
		String idsStr = Arrays.toString(stageIds);
		String sql="SELECT * FROM tb_edu_stage WHERE stage_id in  ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
	
		return builder.toString();
	}
}
