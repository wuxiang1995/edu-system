package cn.gzsxt.edu.service.test;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.gzsxt.edu.config.ContextConfig;
import cn.gzsxt.edu.config.DataConfig;
import cn.gzsxt.edu.service.ModularService;
import cn.gzsxt.edu.utils.Page;

public class ModularServiceTest {

	@Test
	public void findModularToPage() {
		try {
			Class<?>[] clases = { DataConfig.class, ContextConfig.class };
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(clases);
			ModularService modularService = context.getBean(ModularService.class);
			Page page = modularService.findModularToPage(null, 0, 3);
			System.out.println("当前索引：" + page.getIndex());
			System.out.println("总记录数:" + page.getCount());
			System.out.println("每页记录数:" + page.getPageSize());
			System.out.println("总页数:" + page.getPageNum());
			for (Map<String, Object> entity : page.getData()) {
				System.out.println("模块名：" + entity.get("modular_name"));
			}
			context.close();
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}

}
