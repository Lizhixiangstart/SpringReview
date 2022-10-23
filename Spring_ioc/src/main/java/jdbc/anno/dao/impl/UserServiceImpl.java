package jdbc.anno.dao.impl;

import jdbc.anno.dao.UserDao;
import jdbc.anno.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("UserDao")
    UserDao userDao;
    @Override
    public void save() {
        System.out.println("userService running ...");
    }
}
