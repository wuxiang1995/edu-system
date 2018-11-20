package cn.gzsxt.edu.service;

import java.util.Map;

import cn.gzsxt.edu.utils.Page;

public interface StudentFeedbackService {

	Page findStudentFeedbackTopage(Map<String, Object> condition, Integer index, int pageSize);

	Map<String, Object> addStudentFeedback(Map<String, Object> entity);

	Map<String, Object> toaddStudentFeedback(Object feedbackId);
	
	Map<String, Object> insertAdd(Map<String, Object> entity);

}
