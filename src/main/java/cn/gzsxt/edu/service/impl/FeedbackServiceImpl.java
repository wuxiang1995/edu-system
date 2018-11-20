package cn.gzsxt.edu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.edu.mapper.ChapterMapper;
import cn.gzsxt.edu.mapper.FeedbackMapper;
import cn.gzsxt.edu.service.AdminService;
import cn.gzsxt.edu.service.ChapterService;
import cn.gzsxt.edu.service.ClassService;
import cn.gzsxt.edu.service.FeedbackService;
import cn.gzsxt.edu.utils.Page;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackMapper feedbackMapper;
	@Autowired
	private AdminService adminService;
	@Autowired
	private ClassService classService;
	@Autowired
	private ClassStageServiceImpl classStageService;
	@Autowired
	private ChapterService chapterService;
	@Autowired
	private ChapterMapper chapterMapper;
	@Override 
	public Page findFeedbackToPage(Map<String, Object> entity, int index,int size) {
		int count = feedbackMapper.countByCondition(entity);
		int start =index*size;
		List<Map<String, Object>> feedbacks = feedbackMapper.findByConditionToPage(entity,start, size);
		for (Map<String, Object> feedback : feedbacks) {
			Object adminId = feedback.get("feedback_admin_id");
			Object classId = feedback.get("feedback_class_id");
			Object chapterId = feedback.get("feedback_chapter_id");
			Object classStageId = feedback.get("class_stage_id");
			Map<String, Object> chapter = chapterService.findChapterById(chapterId);
			Map<String, Object> classMap = classService.findClassById(classId);
			Map<String, Object> admin = adminService.findAdminById(adminId);
			Map<String, Object> classStage = classStageService.findClassStageById(classStageId);
			feedback.put("admin", admin);
			feedback.put("classMap", classMap);
			feedback.put("classStage",classStage);
			feedback.put("chapter",chapter);
		}
		Page page=new Page(index, size, count, feedbacks);
		return page;
	}
	@Override
	public Map<String, Object> findByIds(Object feedbackId) {
		Map<String, Object> feedback = feedbackMapper.findById(feedbackId);
		Object adminId = feedback.get("feedback_admin_id");
		Object classId = feedback.get("feedback_class_id");
//		Object chapterIds = feedback.get("feedback_chapters");
//		List<Map<String, Object>> chapters = chapterMapper.findChapters(chapterIds);
		Object classStageId = feedback.get("class_stage_id");
		//Map<String, Object> chapter = chapterService.findChapterById(chapterId);
		Map<String, Object> classMap = classService.findClassById(classId);
		Map<String, Object> admin = adminService.findAdminById(adminId);
		Map<String, Object> classStage = classStageService.findClassStageById(classStageId);
		feedback.put("admin", admin);
		feedback.put("classMap", classMap);
		feedback.put("classStage",classStage);
//		feedback.put("chapters",chapters);
		return feedback;
	}
	@Override
	public List<Map<String, Object>> feedbackList() {
		return feedbackMapper.feedbackList();
	}
	

}
