package cn.gzsxt.edu.controller;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.edu.service.DictionaryService;
import cn.gzsxt.edu.service.EvaluateService;
import cn.gzsxt.edu.service.FeedbackService;
import cn.gzsxt.edu.service.FieldService;
import cn.gzsxt.edu.service.StudentFeedbackService;
import cn.gzsxt.edu.service.StudentService;
import cn.gzsxt.edu.utils.Global;
import cn.gzsxt.edu.utils.ListAllSZ;
import cn.gzsxt.edu.utils.Page;

@Controller
@Scope(value="request")
@RequestMapping("/feedback")
public class FeedbackController {
	private static final Logger logger=LogManager.getLogger(FeedbackController.class);
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private FieldService fieldService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentFeedbackService studentFeedbackService; 
	
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private EvaluateService evaluateService;
	
	private static Long evId=null;
	
	@RequestMapping("/toFeedbackList")
	public String toFeedbackList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request,HttpSession session){
		logger.debug("跳转到每日反馈页面-");
		try {
			if (index==null) {
				index=0;
			}
			@SuppressWarnings("unchecked")
			Map<String, Object> admin = (Map<String, Object>) session.getAttribute("admin_info");
			Object adminId = admin.get("admin_id");
			if(Integer.valueOf(adminId.toString())!=1){
				condition.put("feedback_admin_id", adminId);
			}
			Page page = feedbackService.findFeedbackToPage(condition,index,Global.PAGE_SIZE);
			request.setAttribute("page", page);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/teacher-feedbackList";
	}
	@RequestMapping("/toDetailsFeedbackList")
	public String toDetailsFeedbackList(Integer feedbackId,HttpServletRequest request){
		logger.debug("跳转到教师页面-");
		try {
			
			Map<String, Object> feedback = feedbackService.findByIds(feedbackId);
			request.setAttribute("feedback",feedback);
			logger.debug(feedback.get("chapter"));
			logger.debug(feedback.get("classStage"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return "manager/teacher-detailsFeedbackList";
	}

	@ResponseBody
	@RequestMapping(value="/toFeedback")
	public ListAllSZ[] toFeedback() {
		ListAllSZ[] data;
		try {
			data = new ListAllSZ[2];
			List<Map<String, Object>> field=fieldService.fieldListAll();
			List<Map<String, Object>> dictionary =dictionaryService.findDictionaryByTypeCode(1003);
			data[0]=new ListAllSZ(field);
			data[1]=new ListAllSZ(dictionary);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="{evaluate_id}/toFeedbackListAll")
	public String toFeedbackListAll(@PathVariable(value="evaluate_id") Long evaluate_id ,HttpServletRequest request) {
		
		evId=evaluate_id;
		Integer visit=evaluateService.findVisitEvaluateById(evaluate_id);
		ServletContext context= request.getServletContext();
		//feedbackId  是静态属性
		context.setAttribute("evaluateVisit",visit);
		if(visit==1){
			request.setAttribute("admin_login_msg", "教务处未开放评测权限,请等通知");
			return "forward:/refuse.jsp";
		}
		
		Map<String, Object> evaluate=evaluateService.selectAll(evaluate_id);
		
		
		String fieldId=(String) evaluate.get("field_id");
		Object[] field_Id=fieldId.split(",");
		List<Map<String, Object>> field=fieldService.fieldListAll(field_Id);
		List<Map<String, Object>> dictionary =dictionaryService.findDictionaryByTypeCode(1003);
		
		request.setAttribute("evaluate", evaluate);
		request.setAttribute("field", field);
		request.setAttribute("dictionary", dictionary);
		
		//return "forward:/feedback.jsp";
		return "manager/feedback";
	}

	
	@RequestMapping("/feedback")
	public String toEvaluating(){
		logger.debug("恭喜你进入学生评测页面");
		
		return "manager/feedback";
	}
	
	
	@RequestMapping("feedbackFrom")
	public String feedbackFrom(@RequestParam Map<String, Object> entity,HttpServletRequest request) {

		/*logger.debug(entity.get("student_naem"));
		logger.debug(entity.get("student_feedback_comment"));*/
		

		try {
			
			Map<String, Object> evaluate=evaluateService.selectAll(entity.get("evaluate_id"));
			String fieldId=(String) evaluate.get("field_id");
			Object[] field_Id=fieldId.split(",");
			List<Map<String, Object>> field=fieldService.fieldListAll(field_Id);
			StringBuilder builder=new StringBuilder();
			for (int i = 0; i < field.size(); i++) {
				if(entity.get("dic_value"+field.get(i).get("field_id"))!=null) {
					builder.append(entity.get("dic_value"+field.get(i).get("field_id"))).append(",");
				}

			}
			if(builder.toString()!=null&&!("").equals(builder.toString())) {
				builder.delete(builder.length()-1, builder.length()).toString();
			}
			String dic_value=builder.toString();

			Map<String, Object> student=new HashMap<String, Object>();
			
			student.put("student_name", entity.get("student_name"));
			if(studentService.findNameById(student)!=null) {
				Map<String, Object> fieldMap=new HashMap<String,Object>();
				fieldMap.put("student_evaluate_grade",dic_value );
				fieldMap.put("student_evaluate_comment", entity.get("student_evaluate_comment"));
				fieldMap.put("student_id", studentService.findNameById(student).get("student_id"));
				fieldMap.put("student_evaluate_date", new Date());
				fieldMap.put("evaluate_id", entity.get("evaluate_id"));
				if(entity.get("student_evaluate_show")!=null) {
					fieldMap.put("student_evaluate_show", entity.get("student_evaluate_show"));
				}
				fieldMap.put("student_evaluate_show", 0);
				Map<String, Object> data=studentFeedbackService.insertAdd(fieldMap);
				if(data!=null) {
					request.setAttribute("student_name_age","提交成功！");
				}else {
					request.setAttribute("student_name_age","提交失败！");
				}
			}else {
				request.setAttribute("student_name_age", "对不起！你不是本校的学生，不能评分");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}	
		Long evaluate_id = Long.valueOf((String)entity.get("evaluate_id"));
		return this.toFeedbackListAll(evaluate_id, request);
	}
			
	
}

