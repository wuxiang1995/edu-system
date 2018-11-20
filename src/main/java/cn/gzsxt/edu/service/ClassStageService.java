package cn.gzsxt.edu.service;

import java.util.List;
import java.util.Map;

public interface ClassStageService {

	Map<String, Object> findClassStageById(Object classStageId);
	List<Map<String, Object>> calssIdAll(Object classId);

}
