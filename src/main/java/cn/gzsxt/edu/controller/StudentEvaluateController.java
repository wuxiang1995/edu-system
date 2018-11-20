package cn.gzsxt.edu.controller;

import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gzsxt.edu.service.StudentEvaluateService;
import cn.gzsxt.edu.utils.Global;
import cn.gzsxt.edu.utils.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="/studentevaluate")
public class StudentEvaluateController {
	private static final Logger logger=LogManager.getLogger(StudentEvaluateController.class);
	@Autowired
	private StudentEvaluateService studentEvaluateService;
	@RequestMapping(value="/toDetailsEvaluateList")
	public String toStudentEvaluateList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request,Integer evaluateId){
		logger.debug("跳转到教学评测-详细列表");
		try {
			if(index==null){
				index=0;
			}
			
			condition.put("evaluate_id", evaluateId);
			Page page = studentEvaluateService.findStudentEvaluateTopage(condition,index,Global.PAGE_SIZE);
			request.setAttribute("page", page);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "manager/teacher-detailsEvaluateList";
	}
}
