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
import cn.gzsxt.edu.service.ChapterService;
import cn.gzsxt.edu.utils.Global;
import cn.gzsxt.edu.utils.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="/chapter")
public class ChapterController {
	
	private static final Logger logger =LogManager.getLogger(AdminController.class);
	
	@Autowired
	private  ChapterService chapterService;
	/**
	 * 跳转到课程类别
	 * path：${pageContext.request.contextPath }/chapter/toChapterList.do
	 * @return
	 */
	@RequestMapping(value="/toChapterList")
	public String toChapterList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		logger.debug("-跳转到课程列表-"+condition+"，索引："+index);
		try {
			if (index==null) {
				index=0;
			}
			
			Page page = chapterService.findChapterToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/chapterList";
	}
	
	/**
	 * 跳转到增加课程页面
	 * path:${pageContext.request.contextPath }/chapter/toChapterAdd.do
	 * @return
	 */
	@TokenForm(create=true)
	@RequestMapping(value="/toChapterAdd")
	public String toChapterAdd() {
		logger.debug("跳转到增加课程页面--");
		//1.模块数据
		
		return "manager/chapterAdd";
	}
	
	/**
	 * 增加课程
	 * path:${pageContext.request.contextPath }/chapter/chapterAdd.do
	 * @return
	 */
	@TokenForm(remove=true)
	@RequestMapping(value="/chapterAdd")
	public String addChapter(@RequestParam Map<String,Object> entity,HttpServletRequest request) {
		logger.debug("增加课程："+entity);
		try {
			Map<String, Object> chapter = chapterService.addChapter(entity);
			if (chapter!=null) {
				request.setAttribute("chapter_add_msg", "增加课程成功！");
				return "forward:/chapter/toChapterAdd.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("chapter_add_msg", "增加课程失败");
		//增加成功以后，使用toChapterAdd()跳转到增加页面
		//问题：为什么需要通过路径访问呢？
		//答：因为我们是在拦截器里面获得方法的注解的，如果直接调用本类的方法，那么拦截器就不生效
		//只有通过路径调用，拦截器才生效！！！！
		return "forward:/chapter/toChapterAdd.do";
	}
	

	/**
	 * 跳转到编辑课程页面
	 * path:${pageContext.request.contextPath }/chapter/toChapterEdit.do
	 * @return
	 */
	@RequestMapping(value="/toChapterEdit")
	@TokenForm(create=true)
	public String toChapterEdit(Long chapterId,HttpServletRequest request ) {
		logger.debug("跳转到编辑课程页面--"+chapterId);
		//通过课程编号查询课程的信息
		try {
			Map<String, Object> chapter = chapterService.toChapterEdit(chapterId);
			request.setAttribute("chapter", chapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/chapterEdit";
	}
	
	/**
	 * 编辑课程
	 * path:${pageContext.request.contextPath }/chapter/editChapter.do
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/editChapter")
	@TokenForm(remove=true)
	public String editChapter(@RequestParam Map<String, Object> entity,HttpServletRequest request ) {
		logger.debug("-编辑课程-"+entity);
		try {
			Map<String, Object> chapter = chapterService.editChapter(entity);
			if (chapter!=null) {
				request.setAttribute("chapter_edit_msg", "更新课程成功");
				//更新成功，重设表单值
				request.setAttribute("chapter", chapter);
			}else {
				request.setAttribute("chapter_edit_msg", "更新课程失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("chapter_edit_msg", "更新课程失败");
		}
		Long chapterId=Long.valueOf((String)entity.get("chapter_id"));
		return "forward:/chapter/toChapterEdit.do?chapterId="+chapterId;
	}
	/**
	 * 删除课程
	 * path:${pageContext.request.contextPath }/chapter/deleteChapter.do?chapterId=${chapter.chapter_id}
	 * @param chapterId
	 * @return
	 */
	@RequestMapping(value="/deleteChapter")
	public String deleteChapter(Long chapterId,HttpServletRequest request) {
		logger.debug("-删除课程:"+chapterId);
		chapterService.deleteChapterByIds(chapterId);
		return this.toChapterList(null, 0, request);
	}
	
	/**
	 * 批量删除课程
	 * path:${pageContext.request.contextPath }/chapter/deleteChapters.do?chapterId=1&chapterId=2
	 * @param chapterId
	 * @return
	 */
	@RequestMapping(value="/deleteChapters")
	public String deleteChapters(Long[] chapterId,HttpServletRequest request) {
		logger.debug("-批量删除课程:"+chapterId);
		chapterService.deleteChapterByIds((Object[])chapterId);
		return this.toChapterList(null, 0, request);
	}
	
	

}
