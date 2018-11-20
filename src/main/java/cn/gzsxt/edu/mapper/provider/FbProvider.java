package cn.gzsxt.edu.mapper.provider;

import java.util.Arrays;
import java.util.Map;

public class FbProvider {

	public String deleteByIds(Map<String, Object[]> fbIdsMap){
		Object[] fbIds=fbIdsMap.get("array");
		String idsStr= Arrays.toString(fbIds);
		
		String sql="DELETE FROM tb_edu_feedback WHERE feedback_id in ";
		StringBuilder builder=new StringBuilder(sql);

		builder.append(idsStr);
		
		builder.setCharAt(builder.indexOf("["),'(');
		builder.setCharAt(builder.indexOf("]"), ')');
		
		return builder.toString();
	}
	
	
	
	
}
