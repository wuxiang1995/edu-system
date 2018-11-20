package cn.gzsxt.edu.mapper.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.gzsxt.edu.config.DataConfig;
import cn.gzsxt.edu.mapper.AdminMapper;

public class AdminMapperTest {

	@Test
	public void insert() {
		try {
			
			AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DataConfig.class);
			AdminMapper adminMapper = context.getBean(AdminMapper.class);
			Map<String, Object> entity=new HashMap<String, Object>();
			entity.put("admin_name", "zhangsan");
			adminMapper.insert(entity);
			context.close();
		} catch (BeansException e) {
			e.printStackTrace();
		}
		
	}
}
