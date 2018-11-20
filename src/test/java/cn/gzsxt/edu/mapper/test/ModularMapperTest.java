package cn.gzsxt.edu.mapper.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.gzsxt.edu.config.ContextConfig;
import cn.gzsxt.edu.config.DataConfig;
import cn.gzsxt.edu.mapper.ModularMapper;

public class ModularMapperTest {

	@Test
	public void findByConditionToPage() {
		try {
			
			Class<?>[] clases= {DataConfig.class,ContextConfig.class};
			AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(clases);
			ModularMapper modularMapper = context.getBean(ModularMapper.class);
			Map<String, Object> admin=new HashMap<String, Object>();
			admin.put("modular_name", "模块");
			List<Map<String, Object>> modulars = modularMapper.findByConditionToPage(admin,0,2);
			for (Map<String, Object> modular : modulars) {
				System.out.println(modular.get("modular_name"));
			}
			context.close();
		} catch (BeansException e) {
			e.printStackTrace();
		}
		
	}
}
