package cn.gzsxt.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import cn.gzsxt.edu.interceptor.LoginStautsInterceptor;
import cn.gzsxt.edu.interceptor.PowerInterceptor;
import cn.gzsxt.edu.interceptor.TokenFormInterceptor;

@Configuration
@EnableWebMvc
//注意事项：SpringMVC注解是通过WebMvcConfigurerAdapter适配器来配置<mvc:xxxx>组件
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	//视图解释器的配置
	@Bean
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		//1.支持JSTL视图
		viewResolver.setViewClass(JstlView.class);
		//2.设置前缀
		viewResolver.setPrefix("/WEB-INF/views/");
		//3.设置后缀
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}

	//<mvc:default-servlet-hanlder>
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	


	//<mvc:interceptors>，配置拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LoginStautsInterceptor loginStauts=new LoginStautsInterceptor();
		PowerInterceptor powerInterceptor=new PowerInterceptor();
		//拦截器登记              
		InterceptorRegistration registration = registry.addInterceptor(loginStauts);
		//拦截所有的请求
		registration.addPathPatterns("/**");
		//排除登录不拦截
		registration.excludePathPatterns("/admin/loginAdmin.do");
		registration.excludePathPatterns("/studentfeedback/**");
		registration.excludePathPatterns("/feedback/**");
		//排除评测不拦截
		registration.excludePathPatterns("/evaluate/feedback.do");
		
		InterceptorRegistration powerRegistration = registry.addInterceptor(powerInterceptor);
		//拦截所有的请求
		powerRegistration.addPathPatterns("/**");
		//排除登录不拦截
		powerRegistration.excludePathPatterns("/admin/loginAdmin.do");
	     //判断注销不拦截
		powerRegistration.excludePathPatterns("/admin/undoAdmin.do");
		powerRegistration.excludePathPatterns("/admin/toIndex.do");
		powerRegistration.excludePathPatterns("/studentfeedback/**");
		
		
		powerRegistration.excludePathPatterns("/feedback/**");
		//判断学生提交反馈总数异步请求不拦截
		powerRegistration.excludePathPatterns("/fb/fbAsynchronous.do");
		
		//排除评测不拦截 
		//powerRegistration.excludePathPatterns("/evaluate/feedback.do");
		
		TokenFormInterceptor tokenFormInterceptor=new TokenFormInterceptor();
		InterceptorRegistration tokenFormRegistration = registry.addInterceptor(tokenFormInterceptor);
		tokenFormRegistration.addPathPatterns("/**");


		super.addInterceptors(registry);
	}
	

}
