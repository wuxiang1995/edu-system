package cn.gzsxt.edu.mapper.provider;

import java.util.Arrays;
import java.util.Map;

public class FieldProvider {
	public String fieldListAll(Map<String, Object[]> fieldIdsMap) {
		Object[] fieldIds = fieldIdsMap.get("array");
		String idsStr = Arrays.toString(fieldIds);
		String sql="SELECT * FROM tb_field WHERE  field_id IN  ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
	
		return builder.toString();
		
		
	}


	public String findByIds(Object fiedId) {
		String sql="SELECT * FROM tb_field WHERE field_id in (";
		StringBuilder builder=new StringBuilder(sql);
		builder.append(fiedId);
		builder.append(")");
		//logger.debug(builder.toString());
		
		return builder.toString();
	}
}

