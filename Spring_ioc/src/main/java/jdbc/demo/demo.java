package jdbc.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class demo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jbt = ioc.getBean(JdbcTemplate.class);
        System.out.println(jbt);
    }
}
