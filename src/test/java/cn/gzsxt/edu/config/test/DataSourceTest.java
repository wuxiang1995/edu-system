package cn.gzsxt.edu.config.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.gzsxt.edu.config.DataConfig;

public class DataSourceTest {
	
	@Test
	public void getDataSource() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DataConfig.class);
		DataSource dataSource = context.getBean(DataSource.class);
	
		try {
			System.out.println(	dataSource.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		context.close();
		
	}

}
