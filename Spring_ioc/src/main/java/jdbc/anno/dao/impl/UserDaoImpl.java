package jdbc.anno.dao.impl;

import jdbc.anno.dao.UserDao;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Repository("UserDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("UserDao running ...");
    }
    @PostConstruct
    public void init(){
        System.out.println("初始化方法");
    }
    @PreDestroy
    public void destory(){
        System.out.println("销毁方法");
    }
}
