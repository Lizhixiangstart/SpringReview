package ioc.demo;

import ioc.dao.impl.UserDaoImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class demo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserDaoImpl userDao = (UserDaoImpl) ioc.getBean("userDao2");
        userDao.save();


    }
}
