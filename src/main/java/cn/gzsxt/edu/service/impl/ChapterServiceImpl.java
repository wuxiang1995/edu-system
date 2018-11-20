package cn.gzsxt.edu.service.impl;
 
import java.util.List;
 import java.util.Map;
 
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
 import cn.gzsxt.edu.mapper.ChapterMapper;
import cn.gzsxt.edu.mapper.SubjectMapper;
 import cn.gzsxt.edu.service.ChapterService;
import cn.gzsxt.edu.utils.Page;
 
 @Service
 public class ChapterServiceImpl implements ChapterService {
	
 	@Autowired
 	private ChapterMapper chapterMapper;	
	@Autowired
	private SubjectMapper subjectMapper;
	private static final Logger logger =LogManager.getLogger(ChapterServiceImpl.class);

 	@Override
	public Page findChapterToPage(Map<String, Object> condition, int index, int size) {
		logger.debug("分页查询");
		//1.通过条件查询记录数
		int count = chapterMapper.countByCondition(condition);
		//2.通过条件查询数据
		//注意：开始位置=索引*每页记录数
		int start=index*size;
		List<Map<String, Object>> chapters = chapterMapper.findByConditionToPage(condition, start, size);
		//拼接需要连接的数据
		for (Map<String, Object> chapter : chapters) {
			//显示学科名称
			Object subjectId= chapter.get("subject_id");
			Map<String, Object> subject= subjectMapper.findSubjectById(subjectId);
			chapter.put("subject", subject);
		}
		Page page=new Page(index, size, count, chapters);
		return page;
	}

	@Transactional
	@Override
	public Map<String, Object> addChapter(Map<String, Object> entity) {
		int subject=subjectMapper.insert(entity);
		/*Object id=entity.get("subject_id");
		entity.put("subject_id", id);*/
		int count = chapterMapper.insert(entity);
		if(count*subject>0) {
			return entity;
		}
		return null;
	}
	@Override
 	public Map<String, Object> findChapterById(Object chapterId) {
 		return chapterMapper.findById(chapterId);
		 
 	}
 
	@Transactional
	@Override
	public Map<String, Object> editChapter(Map<String, Object> entity) {
		int count = chapterMapper.updateForNotnull(entity);
		Long chapter_id = Long.valueOf(entity.get("chapter_id").toString());
		Map<String, Object> map = chapterMapper.findById(chapter_id);
		entity.put("subject_id", map.get("subject_id"));
		int subject=subjectMapper.updateBysubjectId(entity);
		if (count*subject>0) {
			return chapterMapper.findById(entity.get("chapter_id"));
		}
		return null;
	}

	@Transactional
	@Override
	public int deleteChapterByIds(Object... chapterIds) {
		return chapterMapper.deleteById(chapterIds);
	
	}

	@Override
	public List<Map<String, Object>> findAllChapter() {
		return chapterMapper.findAll();
	}

	@Override
	public Map<String, Object> toChapterEdit(Long chapterId) {
		Map<String, Object> map=chapterMapper.findById(chapterId);
		Object object = map.get("subject_id");
		Map<String, Object> entity = subjectMapper.findSubjectById(object);
		String name=entity.get("subject_name").toString();
		map.put("subject_name", name);
		return map;
	}

	@Override
	public List<Map<String, Object>> findByChapterId(Object chapterIds) {
		return chapterMapper.findBySubjectId(chapterIds);
	}
	

 }