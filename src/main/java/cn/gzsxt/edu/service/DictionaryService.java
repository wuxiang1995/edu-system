package cn.gzsxt.edu.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.edu.utils.Page;

/**
 *  数据字典服务
 * @author ranger
 *
 */
public interface DictionaryService {

	/**
	 * 通过类型编码获得数据字典的值列表
	 * 
	 * @param typeCode
	 * @return
	 */
	public List<Map<String, Object>> findDictionaryByTypeCode(Object typeCode);
	
	/**
	 * 根据条件查询，分页返回数据
	 * @param condition 用于设置查询的条件
	 * @param index  当前索引
	 * @param size 每页记录数
	 * @return 返回分页对象
	 */
	 Page findDictionaryToPage(Map<String,Object> condition,int index,int size);
	
	/**
	 * 增加模块，
	 * @param entity
	 * @return 成功返回增加的记录，失败返回null
	 */
	 Map<String, Object> addDictionary(Map<String, Object> entity);
	
	 /**
	  * 通过模块编号查询模块记录
	  * @param dictionaryId
	  * @return
	  */
	 Map<String, Object> findDictionaryById(Object dictionaryId);
	 
	 /**
	  * 编辑模块，如果编辑成功返回编辑后的数据
	  * @param entity
	  * @return
	  */
	 Map<String, Object> editDictionary(Map<String, Object> entity);
	 
	 /**
	  * 通过模块编号删除模块，支持删除一个数组编号的模块
	  * @param dictionaryIds
	  * @return
	  */
	int deleteDictionaryByIds(Object... dictionaryIds);
	
	/**
	 * 查询所有的模块
	 * @return
	 */
	List<Map<String, Object>> findAllDictionary();
	
	

}
