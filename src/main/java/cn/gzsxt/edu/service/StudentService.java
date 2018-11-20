package cn.gzsxt.edu.service;

import java.util.Map;

import cn.gzsxt.edu.utils.Page;

public interface StudentService {

	Page findStudentToPage(Map<String,Object> condition,int index,int size);
	
	Map<String, Object> addStudent(Map<String, Object> entity);
	
	Map<String, Object> findClassById(Object studentId);
	
	Map<String, Object> editStudent(Map<String, Object> entity);
	
	int deleteStudentByIds(Object... studentIds);
	
	Map<String, Object> findNameById(Map<String, Object> entity);
	
}
