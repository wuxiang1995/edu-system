package cn.gzsxt.edu.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.edu.mapper.EvaluateMapper;
import cn.gzsxt.edu.service.AdminService;
import cn.gzsxt.edu.service.ClassService;
import cn.gzsxt.edu.service.EvaluateService;
import cn.gzsxt.edu.service.FieldService;
import cn.gzsxt.edu.utils.Page;

@Service
public class EvaluateServiceImpl implements EvaluateService {


	@Autowired
	private EvaluateMapper evaluateMapper;

	@Autowired
	private AdminService adminService;
	@Autowired
	private ClassService classService;
	@Autowired
	private ClassStageServiceImpl classStageService;
	@Autowired
	private FieldService fieldService;
	@Override 
	public Page findEvaluateToPage(Map<String, Object> entity, int index,int size) {
		int count = evaluateMapper.countByCondition(entity);
		int start =index*size;
		List<Map<String, Object>> evaluates = evaluateMapper.findByConditionToPage(entity,start, size);
		for (Map<String, Object> evaluate : evaluates) {
			Object adminId = evaluate.get("evaluate_admin_id");
			Object classId = evaluate.get("evaluate_class_id");
			Object classStageId = evaluate.get("class_stage_id");
			Map<String, Object> classMap = classService.findClassById(classId);
			Map<String, Object> admin = adminService.findAdminById(adminId);
			Map<String, Object> classStage = classStageService.findClassStageById(classStageId);
			evaluate.put("admin", admin);
			evaluate.put("classMap", classMap);
			evaluate.put("classStage",classStage);
			
			
		}
		Page page=new Page(index, size, count, evaluates);
		return page;
	}
	@Override
	public Map<String, Object> findByIds(Object evaluateId) {
		Map<String, Object> evaluate = evaluateMapper.findById(evaluateId);
		Object adminId = evaluate.get("evaluate_admin_id");
		Object classId = evaluate.get("evaluate_class_id");
		
		//Object chapterId = evaluate.get("evaluate_chapter_id");
		Object classStageId = evaluate.get("class_stage_id");
		//Map<String, Object> chapter = chapterService.findChapterById(chapterId);
		
		Map<String, Object> classMap = classService.findClassById(classId);
		Map<String, Object> admin = adminService.findAdminById(adminId);
		Map<String, Object> classStage = classStageService.findClassStageById(classStageId);
		evaluate.put("admin", admin);
		evaluate.put("classMap", classMap);
		evaluate.put("classStage",classStage);
		
		//evaluate.put("chapter",chapter);
		return evaluate;
	}
	@Override
	public Map<String, Object> selectAll(Object evaluateId) {
		
		return evaluateMapper.selectAll(evaluateId);
	}

	@Override
	public Page findEvToPage(int index, int size) {
		// limit 0,5; 开始位置等于下标*每页记录数 (规定每页记录数为5)
		int start = index * size;
		// 查询总记录数
		int count = evaluateMapper.findCount();

		// 查询分页数据
		List<Map<String, Object>> data = evaluateMapper.findByToPage(start, size);

		// select count(*) from tb_student_feedback where feedback_id=7 and
		// student_feedback_date='2018-11-13'
		for (Map<String, Object> da : data) {
			Long evaluateId = (Long) da.get("evaluate_id");
			Date evaluateCreateDate = (Date) da.get("evaluate_create_date");

			int number = evaluateMapper.submitNumber(evaluateId, evaluateCreateDate);
			da.put("number", number);
		}

		// 将查询到的总记录数 还有当前索引需要的反馈记录信息 存到Page
		Page page = new Page(index, size, count, data);

		return page;
	}

	@Override
	public int deleteEvaluateByIds(Object... evaluateIds) {
		return evaluateMapper.deleteByIds(evaluateIds);
	}

	@Override
	public int updateVisit(Map<String,Object> entity) {
		return evaluateMapper.updateVisitById(entity);
	}

	@Override
	public Integer findVisitEvaluateById(Long evaluateId) {
		return evaluateMapper.findVisit(evaluateId);
	}

	@Override
	public int updateCloseVisit(Map<String,Object> entity) {
		return evaluateMapper.updateCloseVisitById(entity);
	}

}
