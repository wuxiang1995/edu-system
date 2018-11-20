package cn.gzsxt.edu.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.edu.utils.Page;

public interface FeedbackService {

	Page findFeedbackToPage(Map<String, Object> entity, int index,int size);

	Map<String, Object> findByIds(Object feedbackId);
	
	List<Map<String, Object>> feedbackList();

}
