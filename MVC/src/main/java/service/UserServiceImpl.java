package service;

import dao.UserDao;
import dao.UserDaoImpl;

public class UserServiceImpl implements UserService{
    UserDao userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
    }

    @Override
    public void save() {
        System.out.println("userservice running ...");
    }
}
