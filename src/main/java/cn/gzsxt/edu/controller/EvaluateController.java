package cn.gzsxt.edu.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.fabric.xmlrpc.base.Data;

import cn.gzsxt.edu.service.EvaluateService;
import cn.gzsxt.edu.utils.Global;
import cn.gzsxt.edu.utils.Page;

@Controller
@RequestMapping("/evaluate")
@Scope(value = "request")
public class EvaluateController {
	private static final Logger logger = LogManager.getLogger(EvaluateController.class);

	private static Long evId = null;

	@Autowired
	private EvaluateService evaluateService;

	@RequestMapping("/toEvaluateList")
	public String toEvaluateList(@RequestParam Map<String, Object> condition, Integer index, HttpServletRequest request,
			HttpSession session) {
		logger.debug("跳转到教师评测页面-");
		try {
			if (index == null) {
				index = 0;
			}
			@SuppressWarnings("unchecked")
			Map<String, Object> admin = (Map<String, Object>) session.getAttribute("admin_info");
			Object adminId = admin.get("admin_id");
			if(Integer.valueOf(adminId.toString())!=1){
				condition.put("evaluate_admin_id", adminId);
			}
			Page page = evaluateService.findEvaluateToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/teacher-evaluateList";
	}

	@RequestMapping("/toDetailsEvaluateList")
	public String toDetailsEvaluateList(Integer evaluateId, HttpServletRequest request) {
		logger.debug("跳转到教师页面-");
		try {

			Map<String, Object> evaluate = evaluateService.findByIds(evaluateId);
			request.setAttribute("evaluate", evaluate);
			logger.debug(evaluate.get("chapter"));
			logger.debug(evaluate.get("classStage"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/teacher-detailsEvaluateList";
	}


	@RequestMapping(value = "/feedback")
	public String feedback() {
		logger.debug("进入每日评测页面");

		return "manager/feedback";
	}

	@RequestMapping(value = "/toEvaluate")
	public String toEvaluatingList(Integer index, HttpServletRequest request) {
		logger.debug("进入每日评测管理控制器" + index);

		if (index == null) {
			index = 0;
		}

		Page page = evaluateService.findEvToPage(index, Global.PAGE_SIZE);

		// 将信息放到作用域
		request.setAttribute("page", page);
		logger.debug(page.getData());

		return "manager/evaluateList";

	}

	/**
	 * 单个删除
	 */
	@RequestMapping("/deleteEvaluate")
	public String deleteEvaluate(Long evaluateId, Integer index, HttpServletRequest request) {
		logger.debug("评测管理单个删除" + evaluateId);
		evaluateService.deleteEvaluateByIds(evaluateId);

		return this.toEvaluatingList(index, request);
	}

	@RequestMapping("/deleteEvaluates")
	public String deleteEvaluates(Long[] evaluateId, Integer index, HttpServletRequest request) {
		logger.debug("评测批量删除页面");

		evaluateService.deleteEvaluateByIds(evaluateId);

		return this.toEvaluatingList(index, request);
	}

	@RequestMapping(value = "/evaluateLaunch")
	@ResponseBody
	public String evaluateLaunch(Long evaluateId, HttpServletRequest request) {
		logger.debug("发起反馈：" + evaluateId);
		// 当你发起反馈, 静态属性feedbackId将不为空
		evId = evaluateId;
		Map<String,Object> entity=new HashMap<>();
		entity.put("evaluate_id", evaluateId);
		entity.put("evaluate_start_date", new Date(new java.util.Date().getTime()));
		evaluateService.updateVisit(entity);

		Integer visit = evaluateService.findVisitEvaluateById(evId);

		ServletContext context = request.getServletContext();
		// evId 是静态属性
		context.setAttribute("evaluateVisit", visit);

		return "";
	}

	@RequestMapping(value = "/evaluateClose")
	@ResponseBody
	public String evaluateClose(Long evaluateId, HttpServletRequest request) {
		logger.debug("关闭评测：" + evaluateId);
		Map<String,Object> entity=new HashMap<>();
		entity.put("feedback_id", evaluateId);
		entity.put("feedback_close_date", new Date(new java.util.Date().getTime()));
		evaluateService.updateCloseVisit(entity);
		Integer visit = evaluateService.findVisitEvaluateById(evaluateId);

		ServletContext context = request.getServletContext();
		// feedbackId 是静态属性
		context.setAttribute("evaluateVisit", visit);

		return "";
	}

}
