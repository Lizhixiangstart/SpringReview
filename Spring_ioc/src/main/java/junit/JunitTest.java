package junit;


import jdbc.anno.SpringConfig;
import jdbc.anno.dao.UserDao;
import jdbc.anno.dao.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

@ContextConfiguration(classes = {SpringConfig.class})
//@ContextConfiguration("calsspath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JunitTest {
    @Resource(name = "UserService")
    private UserService userService;
    @Resource(name = "UserDao")
    private UserDao userDao;
    @Resource(name = "dataSource")
    private DataSource dataSource;
    @Test
    public   void test1(){
        userService.save();
        userDao.save();
        System.out.println(dataSource);
    }
}
