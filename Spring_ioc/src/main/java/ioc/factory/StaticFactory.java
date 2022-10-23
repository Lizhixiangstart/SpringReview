package ioc.factory;

import ioc.dao.UserDao;
import ioc.dao.impl.UserDaoImpl;

public class StaticFactory {
    public static UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
