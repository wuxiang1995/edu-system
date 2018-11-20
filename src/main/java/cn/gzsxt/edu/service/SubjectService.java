package cn.gzsxt.edu.service;
import java.util.List;
import java.util.Map;
public interface SubjectService {

	 List<Map<String, Object>> findAllSubject();
	 
	 Map<String, Object> findSubjectById(Object subjectId);
}
