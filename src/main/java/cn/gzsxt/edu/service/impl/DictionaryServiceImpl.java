package cn.gzsxt.edu.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gzsxt.edu.mapper.DictionaryMapper;
import cn.gzsxt.edu.service.DictionaryService;
import cn.gzsxt.edu.utils.Page;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	
	private static final Logger logger =LogManager.getLogger(DictionaryServiceImpl.class);
	
	@Autowired
	private DictionaryMapper dictionaryMapper;

	@Override
	public List<Map<String, Object>> findDictionaryByTypeCode(Object typeCode) {
		return dictionaryMapper.findByTypeCode(typeCode);
	}
	
	@Override
	public Page findDictionaryToPage(Map<String, Object> condition, int index, int size) {
		logger.debug("模块分页查询");
		//1.通过条件查询记录数
		int count = dictionaryMapper.countByCondition(condition);
		//2.通过条件查询数据
		//注意：开始位置=索引*每页记录数
		int start=index*size;
		List<Map<String, Object>> dictionarys = dictionaryMapper.findByConditionToPage(condition, start, size);
		
		Page page=new Page(index, size, count, dictionarys);
		return page;
	}

	@Transactional
	@Override
	public Map<String, Object> addDictionary(Map<String, Object> entity) {
		int count = dictionaryMapper.insert(entity);
		if(count>0) {
			return entity;
		}
		return null;
	}

	
	@Override
	public Map<String, Object> findDictionaryById(Object dictionaryId) {
		return dictionaryMapper.findById(dictionaryId);
		 
	}

	@Transactional
	@Override
	public Map<String, Object> editDictionary(Map<String, Object> entity) {
		int count = dictionaryMapper.updateForNotnull(entity);
		if (count>0) {
			return dictionaryMapper.findById(entity.get("dic_id"));
		}
		return null;
	}

	@Transactional
	@Override
	public int deleteDictionaryByIds(Object... dictionaryIds) {
		return dictionaryMapper.deleteById(dictionaryIds);
	
	}

	@Override
	public List<Map<String, Object>> findAllDictionary() {
		return dictionaryMapper.findAll();
	}
	

}
