package cn.gzsxt.edu.service;

import java.util.List;
import java.util.Map;
import cn.gzsxt.edu.utils.Page;

public interface StageService {

	Page findStageToPage(Map<String, Object> condition, Integer index, int size);
	int deleteStage(Object...classStageIds);
	Map<String, Object> findClassStageById(Long stageId);
	Map<String, Object> toStageEdit(Long stageId);
	Map<String, Object> editStage(Map<String, Object> entity);
	Map<String, Object> toStageAdd();
	int addStage(Map<String, Object> entity);
	Map<String, Object> selectStageSubject(int mid);
	
	List<Map<String, Object>> selectById(Object... stageIds);
}
