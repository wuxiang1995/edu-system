
package cn.gzsxt.edu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.edu.mapper.FieldMapper;
import cn.gzsxt.edu.service.FieldService;
@Service
public class FieldServiceImpl implements FieldService {
	@Autowired
	private FieldMapper fieldMapper;
	@Override
	public List<Map<String, Object>> fieldListAll(Object... fieldId) {
		return fieldMapper.fieldListAll(fieldId);
	}
	@Override
	public List<Map<String, Object>> findByIds(Object fieldId) {
		return fieldMapper.findByIds(fieldId);
	}


}

