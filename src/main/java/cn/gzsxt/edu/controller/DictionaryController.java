package cn.gzsxt.edu.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gzsxt.edu.annotation.TokenForm;
import cn.gzsxt.edu.service.DictionaryService;
import cn.gzsxt.edu.utils.Global;
import cn.gzsxt.edu.utils.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="/dictionary")
public class DictionaryController {
	
	private static final Logger logger =LogManager.getLogger(AdminController.class);
	
	@Autowired
	private  DictionaryService dictionaryService;
	
	/**
	 * 跳转到数据字典类别
	 * path：${pageContext.request.contextPath }/dictionary/toDictionaryList.do
	 * @return
	 */
	@RequestMapping(value="/toDictionaryList")
	public String toDictionaryList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		logger.debug("-跳转到数据字典列表-"+condition+"，索引："+index);
		try {
			if (index==null) {
				index=0;
			}
			
			Page page = dictionaryService.findDictionaryToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/dictionaryList";
	}
	
	/**
	 * 跳转到增加数据字典页面
	 * path:${pageContext.request.contextPath }/dictionary/toDictionaryAdd.do
	 * @return
	 */
	@TokenForm(create=true)
	@RequestMapping(value="/toDictionaryAdd")
	public String toDictionaryAdd() {
		logger.debug("跳转到增加数据字典页面--");
		return "manager/dictionaryAdd";
	}
	
	/**
	 * 增加数据字典
	 * path:${pageContext.request.contextPath }/dictionary/dictionaryAdd.do
	 * @return
	 */
	@TokenForm(remove=true)
	@RequestMapping(value="/addDictionary")
	public String addDictionary(@RequestParam Map<String,Object> entity,HttpServletRequest request) {
		logger.debug("增加数据字典："+entity);
		try {
			Map<String, Object> dictionary = dictionaryService.addDictionary(entity);
			if (dictionary!=null) {
				request.setAttribute("dictionary_add_msg", "增加数据字典成功！");
				return "forward:/dictionary/toDictionaryAdd.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("dictionary_add_msg", "增加数据字典失败");
		
		return "forward:/dictionary/toDictionaryAdd.do";
	}
	

	/**
	 * 跳转到编辑数据字典页面
	 * path:${pageContext.request.contextPath }/dictionary/toDictionaryEdit.do
	 * @return
	 */
	@RequestMapping(value="/toDictionaryEdit")
	@TokenForm(create=true)
	public String toDictionaryEdit(Long dictionaryId,HttpServletRequest request ) {
		logger.debug("跳转到增加数据字典页面--"+dictionaryId);
		//通过数据字典编号查询数据字典的信息
		try {
			Map<String, Object> dictionary = dictionaryService.findDictionaryById(dictionaryId);
			request.setAttribute("dictionary", dictionary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/dictionaryEdit";
	}
	
	/**
	 * 编辑数据字典
	 * path:${pageContext.request.contextPath }/dictionary/editDictionary.do
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/editDictionary")
	@TokenForm(remove=true)
	public String editDictionary(@RequestParam Map<String, Object> entity,HttpServletRequest request ) {
		logger.debug("-编辑数据字典-"+entity);
		try {
			Map<String, Object> dictionary = dictionaryService.editDictionary(entity);
			if (dictionary!=null) {
				request.setAttribute("dictionary_edit_msg", "更新数据字典成功");
				//更新成功，重设表单值
				request.setAttribute("dictionary", dictionary);
			}else {
				request.setAttribute("dictionary_edit_msg", "更新数据字典失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("dictionary_edit_msg", "更新数据字典失败");
		}
		
		return "forward:/dictionary/toDictionaryEdit.do";
	}
	/**
	 * 删除数据字典
	 * path:${pageContext.request.contextPath }/dictionary/deleteDictionary.do?dictionaryId=${dictionary.dictionary_id}
	 * @param dictionaryId
	 * @return
	 */
	@RequestMapping(value="/deleteDictionary")
	public String deleteDictionary(Long dictionaryId,HttpServletRequest request) {
		logger.debug("-删除数据字典:"+dictionaryId);
		dictionaryService.deleteDictionaryByIds(dictionaryId);
		return this.toDictionaryList(null, 0, request);
	}
	
	/**
	 * 批量删除数据字典
	 * path:${pageContext.request.contextPath }/dictionary/deleteDictionarys.do?dictionaryId=1&dictionaryId=2
	 * @param dictionaryId
	 * @return
	 */
	@RequestMapping(value="/deleteDictionarys")
	public String deleteDictionarys(Long[] dictionaryId,HttpServletRequest request) {
		logger.debug("-批量删除数据字典:"+dictionaryId);
		dictionaryService.deleteDictionaryByIds((Object[])dictionaryId);
		return this.toDictionaryList(null, 0, request);
	}
	
	

}
