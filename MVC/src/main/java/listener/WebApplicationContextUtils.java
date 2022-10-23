package listener;

import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;

public class WebApplicationContextUtils {
    public static ApplicationContext getWebApplicationContext(ServletContext servletContext){
        ApplicationContext app = (ApplicationContext) servletContext.getAttribute("app");
        return app;
    }
}
