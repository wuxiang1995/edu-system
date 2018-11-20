package cn.gzsxt.edu.service;

import java.util.Map;

import cn.gzsxt.edu.utils.Page;

public interface StudentEvaluateService {

	Page findStudentEvaluateTopage(Map<String, Object> condition, Integer index, int pageSize);

}
