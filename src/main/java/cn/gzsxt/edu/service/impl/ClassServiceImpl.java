package cn.gzsxt.edu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.edu.mapper.ClassMapper;
import cn.gzsxt.edu.service.ClassService;
import cn.gzsxt.edu.utils.Page;

@Service
public class ClassServiceImpl  implements ClassService{
	
	@Autowired
	private ClassMapper classMapper;
	
	@Override
	public Page findClassToPage(Map<String, Object> entity, int index, int size) {
		int count=classMapper.countByCondition(entity);
		int start=index*size;
		List<Map<String, Object>> data=classMapper.findByConditionToPage(entity, start, size);
		Page page=new Page(index, size, count, data);
		return page;
	}

	@Override
	public Map<String, Object> addClass(Map<String, Object> entity) {
		int count=classMapper.insert(entity);
		if(count>0) {
			return entity;
		}
		return null;
	}

	@Override
	public int deleteClassByIds(Object... classIds) {
		return classMapper.deleteById(classIds);
	}

	@Override
	public Map<String, Object> findClassById(Object classId) {
		
		return classMapper.findById(classId);
	}

	@Override
	public Map<String, Object> editClass(Map<String, Object> entity) {
		int count=classMapper.updateForNotnull(entity);
		if(count>0) {
			return entity;
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> findAllClass() {
		// TODO Auto-generated method stub
		return classMapper.findAll();
	}
	
	

}
