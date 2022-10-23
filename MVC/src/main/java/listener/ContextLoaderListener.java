package listener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    //上下文初始化方法
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //将应用上下文存储到ServletContext域中
        ServletContext servletContext = sce.getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        servletContext.setAttribute("app",app);
    }

    //上下文销毁方法
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
