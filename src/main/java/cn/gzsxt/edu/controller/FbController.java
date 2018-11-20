package cn.gzsxt.edu.controller;


import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Finishings;
import javax.print.attribute.standard.Finishings;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.edu.annotation.TokenForm;
import cn.gzsxt.edu.service.AdminService;
import cn.gzsxt.edu.service.ClassService;
import cn.gzsxt.edu.service.FbService;
import cn.gzsxt.edu.service.StageService;
import cn.gzsxt.edu.utils.Global;
import cn.gzsxt.edu.utils.Page;

@Controller
@RequestMapping(value="/fb")
@Scope(value="request")
public class FbController {

	private static final Logger logger=LogManager.getLogger(FbController.class);
	
	private static Long feedbackId=null;
	
	@Autowired
	private FbService fbService;
	@Autowired
	private  StageService stageService;
	@Autowired
	private AdminService adminService;
	
	
	
	//select * from tb_edu_feedback order by feedback_create_date desc limit 0,5;
	@RequestMapping(value="/toFbList")
	public String toFbList(Integer index,HttpServletRequest request){
		logger.debug("跳转到每日反馈管理列表"+index);
		
		if(index==null){
			index=0;
		}
		
		Page page=fbService.findFbToPage(index,Global.PAGE_SIZE);
		
		//将信息放到作用域
		request.setAttribute("page", page);
		logger.debug(page.getData());
		logger.debug(page.getData().get(0).get("class_name"));
		
		return "manager/fbList";
	}
	
	@RequestMapping("/deleteFb")
	public String deleteFb(Long feedbackId,Integer index,HttpServletRequest request){
		
		fbService.deleteFbByIds(feedbackId);
	
		return this.toFbList(index,request);
		
	}
	
	@RequestMapping("/deleteFbs")
	public String deleteFbs(Long[] fbId,Integer index,HttpServletRequest request){
		logger.debug("批量删除页面");
		
		fbService.deleteFbByIds(fbId);
		
		return this.toFbList(index, request);
	}
	/**
	 * 跳转到编辑每天反馈页面
	 * path:${pageContext.request.contextPath }/fb/toFbAdd.do
	 * @return
	 */
	
	@RequestMapping(value="/toFbEdit")
	@TokenForm(create=true)
	public String toFbEdit(Long feedbackId,HttpServletRequest request) {
		logger.debug("跳转到编辑每天反馈页面--");
		try {
			Map<String, Object> stages = stageService.toStageAdd();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String format = sdf.format(new Date(new java.util.Date().getTime()));
			logger.debug(format);
			stages.put("fbdate", format);
			request.setAttribute("stages", stages);
			Map<String, Object> fb = fbService.toFbEdit(feedbackId);
			request.setAttribute("fb", fb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/fbEdit";
	}
	@RequestMapping(value="/editFb")
	@TokenForm(remove=true)
	public String editFb(@RequestParam Map<String,Object> entity,String[] feedbackChapter,HttpServletRequest request) {
		logger.debug("编辑每天反馈页面--"+feedbackChapter.toString());
		Object feedback_id = entity.get("feedback_id");
		try {
			String chapterStr = Arrays.toString(feedbackChapter);
			//.去掉方括号
			chapterStr = chapterStr.replaceAll(" ", "");
			StringBuilder builder=new StringBuilder(chapterStr);
			builder.deleteCharAt(builder.indexOf("["));
			builder.deleteCharAt(builder.indexOf("]"));
			logger.debug("权限字符串："+builder.toString());
			entity.put("feedback_chapters", builder.toString());
			int fb = fbService.editFb(entity);
			if(fb>0){
				request.setAttribute("fb_edit_msg", "编辑成功！！！");
				return "forward:/fb/toFbEdit.do?feedbackId="+feedback_id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("fb_edit_msg", "编辑失败！！！");
		return "forward:/fb/toFbEdit.do?feedbackId="+feedback_id;
	}
	/**
	 * 跳转到增加每天反馈页面
	 * path:${pageContext.request.contextPath }/fb/toFbAdd.do
	 * @return
	 */

	@RequestMapping(value="/toFbAdd")
	@TokenForm(create=true)
	public String toFbAdd(HttpServletRequest request) {
		logger.debug("跳转到增加每天反馈页面--");
		try {
			Map<String, Object> stages = stageService.toStageAdd();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String format = sdf.format(new Date(new java.util.Date().getTime()));
			logger.debug(format);
			stages.put("fbdate", format);
			request.setAttribute("stages", stages);
			/*List<Map<String, Object>>  chapters= fbService.findChapters();
			stages.put("chapters", chapters);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/fbAdd";
	}
	
	

	/**
	 * 增加角色
	 * path:${pageContext.request.contextPath }/fb/fbAdd.do
	 * @return
	 */
	@TokenForm(remove=true)
	@RequestMapping(value="/addFb")
	public String addFeedback(@RequestParam Map<String,Object> entity,String[] subjectChapter,HttpServletRequest request) {
		logger.debug("增加每天反馈："+entity);
		
		String fbChapterStr = Arrays.toString(subjectChapter);
		//.去掉方括号
		fbChapterStr = fbChapterStr.replaceAll(" ", "");
		StringBuilder builder=new StringBuilder(fbChapterStr);
		builder.deleteCharAt(builder.indexOf("["));
		builder.deleteCharAt(builder.indexOf("]"));
		
		logger.debug("权限字符串："+builder.toString());
		entity.put("feedback_chapters", builder.toString());
		Object subjectName = entity.get("subject_id");
		Object adminId = entity.get("feedback_admin_id");
		Object teacherName = adminService.findAdminById(adminId).get("admin_name");
		String title=subjectName+"-每日反馈-"+teacherName;
		entity.put("feedback_title", title);
		entity.put("feedback_student_total", "0");
		entity.put("feedback_visit", "0");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date(new java.util.Date().getTime()));
		logger.debug(format);
		//entity.put("feedback_create_date", format);
		
		
		
		
		try {
			Map<String, Object> fb = fbService.addFb(entity);
			if (fb!=null) {
				request.setAttribute("fb_add_msg", "增加每天反馈成功！");
				return "forward:/fb/toFbAdd.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("fb_add_msg", "增加每天反馈");
		
		return "forward:/fb/toFbAdd.do";
	}
	
	
	
	/**
	 * 当学生提交反馈后 先访问数据库中的 feedback_visit 是不是0  
	 * 同时教务人员没有点击发起时,feedbackId静态属性默认为null,什么都查不到(然后提醒学生无法反馈)  教务点击发起会把该feedback_id赋值到该静态属性
	 * 这样做有一个缺点   就是只能有一个页面发起反馈
	 * @param request
	 * @return
	 */
	//select feedback_visit from tb_edu_feedback  where feedback_id=null    不报错,但什么都查不到 (返回null)
	@RequestMapping(value="/studenttest")
	public String test(HttpServletRequest request){
		
		
		return "manager/test";
	}
	
	

	@RequestMapping(value="/fbLaunch")
	@ResponseBody
	public String fbLaunch(Long fbId,HttpServletRequest request){
		logger.debug("发起反馈："+fbId);
		//当你发起反馈, 静态属性feedbackId将不为空
		feedbackId=fbId;
		Map<String,Object> entity=new HashMap<>();
		entity.put("feedback_id", fbId);
		entity.put("feedback_start_date", new Date(new java.util.Date().getTime()));
		fbService.updateVisit(entity);
		
		Integer visit=fbService.findVisitFbById(feedbackId);
		
		ServletContext context= request.getServletContext();
		//feedbackId  是静态属性
		context.setAttribute("visit",visit);
		
		return "";
	}
	
	@RequestMapping(value="/fbClose")
	@ResponseBody
	public String fbClose(Long fbId,HttpServletRequest request){
		logger.debug("关闭反馈："+fbId);
		Map<String,Object> entity=new HashMap<>();
		entity.put("feedback_id", fbId);
		entity.put("feedback_close_date", new Date(new java.util.Date().getTime()));
		fbService.updateCloseVisit(entity);
		Integer visit=fbService.findVisitFbById(feedbackId);
		
		ServletContext context= request.getServletContext();
		//feedbackId  是静态属性
		context.setAttribute("visit",visit);
		System.out.println(feedbackId);
		System.out.println(visit+"无滴滴滴滴滴滴滴");
		
		return "";
		
		
	}
	
	
	
}



















