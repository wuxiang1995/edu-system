package cn.gzsxt.edu.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.edu.utils.Page;

public interface FbService {

	/**
	 * 查询所有的反馈
	 * @return
	 */
	List<Map<String, Object>> findAllFb();
	
	/**
	 * 查询分页后的数据
	 * @return
	 */
	Page findFbToPage(int index,int size);
	
	/**
	 * 删除指定id的反馈记录  
	 * DELETE FROM tb_modular WHERE modular_id in (?,?,?)
	 */
	int deleteFbByIds(Object... ids);
	
	
	Integer findVisitFbById(Long feedbackId);
	
	int updateVisit(Map<String,Object> entity);
	
	
	int updateCloseVisit(Map<String,Object> entity);
	
	List<Map<String, Object>> findChapters();
	
	/**
	 * 增加每天反馈，
	 * @param entity
	 * @return 成功返回增加的记录，失败返回null
	 */
	 Map<String, Object> addFb(Map<String, Object> entity);

	 Map<String, Object> toFbEdit(Long feedbackId);

	int editFb(Map<String, Object> entity);
	
}
