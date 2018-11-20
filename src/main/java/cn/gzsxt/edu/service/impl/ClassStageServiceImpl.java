package cn.gzsxt.edu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.edu.mapper.StageMapper;
import cn.gzsxt.edu.service.ClassStageService;
@Service
public class ClassStageServiceImpl implements ClassStageService {
	@Autowired
	private StageMapper stageMapper;
	@Override
	public Map<String, Object> findClassStageById(Object classStageId) {
		
		return stageMapper.findByClassStageId(classStageId);
	}
	@Override
	public List<Map<String, Object>> calssIdAll(Object classId) {
		
		return stageMapper.findByClassId(classId);
	}


}
