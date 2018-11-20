package cn.gzsxt.edu.service.impl;
import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.edu.mapper.SubjectMapper;
import cn.gzsxt.edu.service.SubjectService;
@Service
public class SubjectServiceImpl implements SubjectService{
@Autowired 
private SubjectMapper subjectMapper;
	@Override
	public List<Map<String, Object>> findAllSubject() {
		return subjectMapper.findAll();
	}
	@Override
	public Map<String, Object> findSubjectById(Object subjectId) {
		// TODO Auto-generated method stub
		return subjectMapper.findSubjectById(subjectId);
	}
   

 }