package cn.gzsxt.edu.service.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.fabric.xmlrpc.base.Value;

import cn.gzsxt.edu.mapper.DictionaryMapper;
import cn.gzsxt.edu.mapper.EvaluateMapper;
import cn.gzsxt.edu.mapper.StudentEvaluateMapper;
import cn.gzsxt.edu.mapper.StudentMapper;
import cn.gzsxt.edu.service.FieldService;
import cn.gzsxt.edu.service.StudentEvaluateService;
import cn.gzsxt.edu.utils.Page;

@Service
public class StudentEvaluateServiceImpl implements StudentEvaluateService {
	@Autowired
	private StudentEvaluateMapper studentEvaluateMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private EvaluateMapper evaluateMapper;
	@Autowired
	private DictionaryMapper dictionaryMapper;
	@Autowired
	private FieldService fieldService;
	@Override
	public Page findStudentEvaluateTopage(Map<String, Object> entity, Integer index, int size) {
		int count = studentEvaluateMapper.countByCondition(entity);
		//studentEvaluateMapper.findByConditionToPage();
		int start=index*size;
		List<Map<String, Object>> studentEvaluates = studentEvaluateMapper.findByConditionToPage(entity,start, size);
		for (Map<String, Object> studentEvaluate : studentEvaluates) {
			Object studentId = studentEvaluate.get("student_id");
			Map<String, Object> student = studentMapper.findById(studentId);
			studentEvaluate.put("student", student);
			Object evaluateId = studentEvaluate.get("evaluate_id");
			Map<String, Object> evaluate = evaluateMapper.findById(evaluateId);
			Object dicIds = studentEvaluate.get("student_evaluate_grade");
			String[] array = dicIds.toString().split(",");
			List<Map<String, Object>> dicList=new ArrayList<>();
			for (String dicId : array) {
				Map<String, Object> dic = dictionaryMapper.findByTypeCodeAndValue(dicId, 1003);
				dicList.add(dic);
				
			}
			studentEvaluate.put("dicList",dicList);
			studentEvaluate.put("evaluate",evaluate);
			Object fieldId = evaluate.get("field_id");
			List<Map<String, Object>> field = fieldService.findByIds(fieldId);
			studentEvaluate.put("field", field);
			int size2 = studentEvaluates.size();
			studentEvaluate.put("length",size2);
		}
		
		Page page=new Page(index, size, count, studentEvaluates);
		return page;
	}

	




}
