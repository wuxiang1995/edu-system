package cn.gzsxt.edu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.edu.mapper.ClassMapper;
import cn.gzsxt.edu.mapper.StudentMapper;
import cn.gzsxt.edu.service.StudentService;
import cn.gzsxt.edu.utils.Page;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private ClassMapper classMapper;

	@Override
	public Page findStudentToPage(Map<String, Object> entity, int index, int size) {
		int count=studentMapper.countByCondition(entity);
		int start=index*size;
		List<Map<String, Object>> data=studentMapper.findByConditionToPage(entity, start, size);
		for (Map<String, Object> admin : data) {
			Object classId=admin.get("class_id");
			System.out.println(classId);
			Map<String, Object> classs=classMapper.findById(classId);
			admin.put("classs", classs);
		}
		
		Page page=new Page(index, size, count, data);
		return page;
	}

	@Override
	public Map<String, Object> addStudent(Map<String, Object> entity) {
		int count=studentMapper.insert(entity);
		if(count>0) {
			return entity;
		}
		return null;
	}

	@Override
	public Map<String, Object> findClassById(Object studentId) {
		return studentMapper.findById(studentId);
	}

	@Override
	public Map<String, Object> editStudent(Map<String, Object> entity) {
		int count=studentMapper.updateForNotnull(entity);
		if(count>0) {
			return entity;
		}
		return null;
	}

	@Override
	public int deleteStudentByIds(Object... studentIds) {
		
		return studentMapper.deleteById(studentIds);
	}
	public Map<String, Object> findNameById(Map<String, Object> entity){
		return studentMapper.findNameById(entity);
		
	}
	
	

}

