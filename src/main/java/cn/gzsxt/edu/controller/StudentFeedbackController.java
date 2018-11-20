package cn.gzsxt.edu.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.gzsxt.edu.service.FbService;
import cn.gzsxt.edu.service.StudentFeedbackService;
import cn.gzsxt.edu.utils.Global;
import cn.gzsxt.edu.utils.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="/studentfeedback")
public class StudentFeedbackController {
	private static final Logger logger=LogManager.getLogger(StudentFeedbackController.class);
	@Autowired
	private StudentFeedbackService studentFeedbackService;
	@Autowired
	private FbService fbService;
	
	private static Long fbId=null;
	
	@RequestMapping(value="/toDetailsFeedbackList")
	public String toStudentFeedbackList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request,Integer feedbackId){
		logger.debug("跳转到每日模块-详细列表");
		try {
			if(index==null){
				index=0;
			}
			
			condition.put("feedback_id", feedbackId);
			Page page = studentFeedbackService.findStudentFeedbackTopage(condition,index,Global.PAGE_SIZE);
			request.setAttribute("page", page);
			
			HttpSession session=request.getSession();
			Map<String, Object> admin = (Map<String, Object>) session.getAttribute("admin_info");
			Long roleId=(Long) admin.get("role_id");
			
			ServletContext context= request.getServletContext();
			Integer visit= (Integer) context.getAttribute("visit");
			
			System.out.println(visit+""+roleId);
			if(visit!=null){
				//roleId==2   就是登录用户是老师
				if(visit==0&&(roleId==2)){
					request.setAttribute("admin_login_msg", "教务处目前正在开发发起反馈,老师暂时无法查看详情");
					return "forward:/refuse.jsp";
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "manager/teacher-detailsFeedbackList";
	}
	@RequestMapping(value="{feedbackId}/student")
	//@TokenForm(create=true)
	public String toAddStudentFeedback(@PathVariable Long feedbackId,HttpServletRequest request){
		logger.debug("to增加学生每日反馈");
		
		try {
			fbId=feedbackId;
			Integer visit=fbService.findVisitFbById(feedbackId);
			ServletContext context= request.getServletContext();
			//feedbackId  是静态属性
			context.setAttribute("visit",visit);
			if(visit==1){
				request.setAttribute("admin_login_msg", "教务处未开放反馈权限,请等通知");
				return "forward:/refuse.jsp";
			}
			
			
			Map<String, Object> map = studentFeedbackService.toaddStudentFeedback(feedbackId);
			request.setAttribute("feedback", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "manager/student";
	}
	@RequestMapping(value="/addStudentFeedback",method=RequestMethod.POST)
//	@ResponseBody
	//@TokenForm(remove=true)
	public String addStudentFeedback(@RequestParam  Map<String, Object> entity,HttpServletRequest request){
		logger.debug("增加学生每日反馈");
		try { 
			int length=Integer.valueOf(request.getParameter("length"));
			for (int i = 1; i <=length; i++) {
				String[] feedbackAny= request.getParameterValues("feedbackGrade"+i);
				if(feedbackAny==null||feedbackAny.equals("")){
					request.setAttribute("add_msg",  "有选项为空，请重新填写!!!");
					return this.toAddStudentFeedback(Long.valueOf(entity.get("feedback_id").toString()), request);
				}
			}
			Map<String, Object> map = studentFeedbackService.addStudentFeedback(entity);
			if(map.get("add_msg")==null){
				request.setAttribute("add_msg", "提交成功");
				return this.toAddStudentFeedback(Long.valueOf(map.get("feedback_id").toString()), request);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("add_msg", "提交失败!!!");
		}
		request.setAttribute("add_msg", entity.get("add_msg"));
		return this.toAddStudentFeedback(Long.valueOf(entity.get("feedback_id").toString()), request);
	}
}
