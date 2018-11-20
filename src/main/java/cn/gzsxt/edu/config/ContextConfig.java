package cn.gzsxt.edu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages="cn.gzsxt.edu")
@PropertySource(encoding="UTF-8",value="classpath:sys.properties")
public class ContextConfig {

}
