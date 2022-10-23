package jdbc.anno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration //代表这是一个spring配置类
@ComponentScan("jdbc")//组件扫描，扫描注解
@Import(JdbcConfig.class)//添加其他配置类
public class SpringConfig {

}
