package cn.gzsxt.edu.service;
import java.util.List;
import java.util.Map;
import cn.gzsxt.edu.utils.Page;

public interface ChapterService {
	
	/**
	 * 根据条件查询，分页返回数据
	 * @param condition 用于设置查询的条件
	 * @param index  当前索引
	 * @param size 每页记录数
	 * @return 返回分页对象
	 */
	 Page findChapterToPage(Map<String,Object> condition,int index,int size);
	
	/**
	 * 增加课程，
	 * @param entity
	 * @return 成功返回增加的记录，失败返回null
	 */
	 Map<String, Object> addChapter(Map<String, Object> entity);
	
	 /**
	  * 通过课程编号查询课程记录
	  * @param chapterId
	  * @return
	  */
	 Map<String, Object> findChapterById(Object chapterId);
	 
	 /**
	  * 编辑课程，如果编辑成功返回编辑后的数据
	  * @param entity
	  * @return
	  */
	 Map<String, Object> editChapter(Map<String, Object> entity);
	 
	 /**
	  * 通过课程编号删除课程，支持删除一个数组编号的课程
	  * @param chapterIds
	  * @return
	  */
	int deleteChapterByIds(Object... chapterIds);
	
	/**
	 * 查询所有的课程
	 * @return
	 */
	List<Map<String, Object>> findAllChapter();

	Map<String, Object> toChapterEdit(Long chapterId);
	
	List<Map<String, Object>> findByChapterId(Object chapterIds);

}
