package ioc.factory;

import ioc.dao.UserDao;
import ioc.dao.impl.UserDaoImpl;

public class DynamicFactory {
    public  UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
