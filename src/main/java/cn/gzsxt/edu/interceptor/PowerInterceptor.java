package cn.gzsxt.edu.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PowerInterceptor implements HandlerInterceptor {
	
	private final static Logger logger=LogManager.getLogger(PowerInterceptor.class);

	@SuppressWarnings({"unchecked" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.获得请求路径
		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession();
		logger.debug("权限控制请求路径："+requestURI);
		session.setAttribute("path", requestURI);
		
		//2.获取当前用户拥有的权限	
		Map<String, Object> admin = (Map<String, Object>) session.getAttribute("admin_info");
		List<Map<String, Object>> powers = (List<Map<String, Object>>)admin.get("powers");
		
		if(requestURI.contains("/fb/studenttest.do")){
			ServletContext context= request.getServletContext();
			Integer visit= (Integer) context.getAttribute("visit");
			System.out.println(visit+"OKOKOK");
			if(visit==null||visit==1){
				request.setAttribute("admin_login_msg", "教务处未开放反馈权限,请等通知");
				request.getRequestDispatcher("/refuse.jsp").forward(request, response);
				return false;
			}
		}
		if(requestURI.contains("/studentfeedback")){
			ServletContext context= request.getServletContext();
			Integer visit= (Integer) context.getAttribute("visit");
			System.out.println(visit+"OKOKOKOKOK");
			if(visit==null||visit==1){
				request.setAttribute("admin_login_msg", "教务处未开放反馈权限,请等通知");
				request.getRequestDispatcher("/refuse.jsp").forward(request, response);
				return false;
			}
		}
		
		if(requestURI.contains("/toFeedbackListAll")){
			System.out.println(666);
			ServletContext context= request.getServletContext();
			Integer visit= (Integer) context.getAttribute("evaluateVisit");
			System.out.println(visit+"OKOKOK");
			if(visit==null||visit==1){
				request.setAttribute("admin_login_msg", "教务处未开放评测权限,请等通知");
				request.getRequestDispatcher("/refuse.jsp").forward(request, response);
				return false;
			}
		}
		
		if(requestURI.contains("/toDetailsFeedbackList")){
			ServletContext context= request.getServletContext();
			Integer visit= (Integer) context.getAttribute("visit");

			Long roleId=(Long) admin.get("role_id");
			System.out.println(admin.get("role_id")+"OKOKOKOKOKOKOKOKOKOKOKOKOKOKOK");
			if((visit==null||visit==0)&&(roleId==1)){
				request.setAttribute("admin_login_msg", "教务处目前正在开发发起反馈,老师暂时无法查看详情");
				request.getRequestDispatcher("/refuse.jsp").forward(request, response);
				return false;
			}
		}
		
		
		
		for (Map<String, Object> power : powers) {
			//3.判断是否有权限
			if(requestURI.contains((String)power.get("power_action"))) {
				return true;
			}
		}
		
		request.setAttribute("admin_login_msg", "你没有操作的权限，请先授权");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
