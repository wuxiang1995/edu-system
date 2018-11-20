package cn.gzsxt.edu.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.edu.utils.Page;

public interface ClassService {
	Page findClassToPage(Map<String,Object> condition,int index,int size);
	
	Map<String, Object> addClass(Map<String,Object> entity);
	
	int deleteClassByIds(Object... classIds);
	
	Map<String, Object> findClassById(Object classId);
	
	Map<String, Object> editClass(Map<String, Object> entity);
	
	List<Map<String, Object>> findAllClass();

}
