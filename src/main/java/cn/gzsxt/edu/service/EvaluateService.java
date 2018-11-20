package cn.gzsxt.edu.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.edu.utils.Page;

public interface EvaluateService {
	
	Map<String, Object> selectAll(Object evaluateId);


	Page findEvaluateToPage(Map<String, Object> entity, int index,int size);

	Map<String, Object> findByIds(Object feedbackId);

	

	/**
	 * 查询分页后的数据
	 * @return
	 */
	Page findEvToPage(int index,int size);
	
	
	/**
	 * 删除指定id的反馈记录  
	 * DELETE FROM tb_student_evaluate WHERE student_evaluate_id in (?,?,?)
	 */
	int deleteEvaluateByIds(Object... evaluateIds);
	
	int updateVisit(Map<String,Object> entity);
	
	Integer findVisitEvaluateById(Long evaluateId);
	
	int updateCloseVisit(Map<String,Object> entity);
	

}
