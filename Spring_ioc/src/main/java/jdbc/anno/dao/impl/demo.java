package jdbc.anno.dao.impl;

import jdbc.anno.SpringConfig;
import jdbc.anno.dao.UserDao;
import jdbc.anno.dao.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class demo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService =  ioc.getBean(UserService.class);
        UserDao userDao = (UserDao) ioc.getBean(UserDao.class);
        DataSource druid = (DataSource) ioc.getBean("dataSource");
        userService.save();
        userDao.save();
        System.out.println(druid);
    }
}
