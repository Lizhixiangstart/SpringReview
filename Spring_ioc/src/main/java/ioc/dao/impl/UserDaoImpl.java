package ioc.dao.impl;

import ioc.dao.UserDao;

public class UserDaoImpl implements UserDao {

    @Override
    public void save() {
        System.out.println("UserDao running ...");
    }
}
