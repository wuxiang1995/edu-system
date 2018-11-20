package cn.gzsxt.edu.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.edu.mapper.ChapterMapper;
import cn.gzsxt.edu.mapper.DictionaryMapper;
import cn.gzsxt.edu.mapper.FeedbackMapper;
import cn.gzsxt.edu.mapper.StudentFeedbackMapper;
import cn.gzsxt.edu.mapper.StudentMapper;
import cn.gzsxt.edu.service.StudentFeedbackService;
import cn.gzsxt.edu.utils.Page;

@Service
public class StudentFeedbackServiceImpl implements StudentFeedbackService {
	private static final Logger logger=LogManager.getLogger(StudentFeedbackServiceImpl.class);
	@Autowired
	private StudentFeedbackMapper studentFeedbackMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private FeedbackMapper feedbackMapper;
	@Autowired
	private ChapterMapper chapterMapper;
	@Override
	public Page findStudentFeedbackTopage(Map<String, Object> entity, Integer index, int size) {
		int count = studentFeedbackMapper.countByCondition(entity);
		//studentFeedbackMapper.findByConditionToPage();
		int start=index*size;
		List<Map<String, Object>> studentFeedbacks = studentFeedbackMapper.findByConditionToPage(entity,start, size);
		for (Map<String, Object> studentFeedback : studentFeedbacks) {
			Object studentId = studentFeedback.get("student_id");
			Map<String, Object> student = studentMapper.findById(studentId);
			studentFeedback.put("student", student);
			Object feedbackId = studentFeedback.get("feedback_id");
			Map<String, Object> feedback = feedbackMapper.findById(feedbackId);
			Object chapterIds = feedback.get("feedback_chapters");
			List<Map<String, Object>> chapters = chapterMapper.findChapters(chapterIds);
			feedback.put("chapters", chapters);
			studentFeedback.put("feedback",feedback);
			
			//Map<String, Object> dic = dictionaryMapper.findByTypeCodeAndValue(studentFeedback.get("student_feedback_grade"), 1002);
			//studentFeedback.put("status",dic);
		}
		Page page=new Page(index, size, count, studentFeedbacks);
		return page;
	}
	@Override
	public Map<String, Object> addStudentFeedback(Map<String, Object> entity) {
		logger.debug(entity);
		try {
			Object feedback_id = entity.get("feedback_id");
			Map<String, Object> map2 = feedbackMapper.findById(feedback_id);
			String object = map2.get("feedback_chapters").toString();
			String[] s=object.split(",");
			StringBuilder sb=new StringBuilder();
			for (int i = 1; i <= s.length; i++) {
				String fString="feedbackGrade"+i;
				String feedbackGradeStr = entity.get(fString).toString();
				
				sb.append(feedbackGradeStr+",");
			}
			sb.delete(sb.length()-1, sb.length());
			String feedbackGrade=sb.toString();
			entity.put("feedbackGrade", feedbackGrade);
			if(entity.get("niming")==null){
				entity.put("niming", 0);
			}
			Map<String, Object> map=studentMapper.findNameById(entity);
			int i=0;
			if(map!=null){
				entity.put("student_id", map.get("student_id"));
				Date student_feedback_date=new Date(new java.util.Date().getTime());
				entity.put("student_feedback_date", student_feedback_date);
			}else{
				entity.put("add_msg", "无此学生，请核对学生名！！！");
				return entity;
			}
			Boolean bool=false;
			List<Map<String, Object>> studentIds=studentFeedbackMapper.findAll(feedback_id);
			if(studentIds!=null){
				for (Map<String, Object> map3 : studentIds) {
					if (map3.get("student_id")==map.get("student_id")) {
						bool=true;
					}
				}
			}
			if(bool){
				entity.put("add_msg", "您已填写，请勿重复填写！");
				return entity;
			}
			i=studentFeedbackMapper.insert(entity);
			if(i>0){
				return entity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Map<String, Object> toaddStudentFeedback(Object feedbackId) {
		Map<String, Object> map = feedbackMapper.findById(feedbackId);
		String object = map.get("feedback_chapters").toString();
		String[] s=object.split(",");
		List<Map<String, Object>> chapters=chapterMapper.findChapters(object);
		map.put("chapters", chapters);
		map.put("length", s.length);
		return map;
	}
	
	@Override
	public Map<String, Object> insertAdd(Map<String, Object> entity) {
		int count=studentFeedbackMapper.insertAdd(entity);
		if(count>0) {
			return entity;
		}
		return null;
	}

	




}
