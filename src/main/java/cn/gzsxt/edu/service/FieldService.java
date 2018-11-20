
package cn.gzsxt.edu.service;

import java.util.List;
import java.util.Map;

public interface FieldService {

	List<Map<String, Object>> findByIds(Object fieldId);
	
	/**
	 * 查询全部
	 * @return
	 */
	List<Map<String, Object>> fieldListAll(Object... fieldId);


}
