# Spring #
## Spring-Ioc
	Bean的实例化三种方式：
		方式一：使用getBean()
			<bean id="userDao" class="dao.impl.UserDaoImpl" scope="singleton" >
			UserDaoImpl userDao = (UserDaoImpl) ioc.getBean("userDao");
        	userDao.save();
		方式二：工厂静态方法实例化
			<bean id="userDao1" class="factory.StaticFactory" factory-method="getUserDao">
    		</bean>
			UserDaoImpl userDao = (UserDaoImpl) ioc.getBean("userDao1");
        	userDao.save();
		方式三：工厂实例方法实例化
			<bean id="factory" class="factory.DynamicFactory"></bean>
		    <bean id="userDao2" factory-bean="factory" factory-method="getUserDao"></bean>
			UserDaoImpl userDao = (UserDaoImpl) ioc.getBean("userDao2");
        	userDao.save();

----------
	Spring-jdbc:
		手动获取数据库连接池：
				@Test
				//使用配置文件获取数据源(c3p0)
				publicvoidtest5()throwsException{
				//读取配置文件
				ResourceBundlerb=ResourceBundle.getBundle("jdbc");
				Stringdriver=rb.getString("jdbc.driver");
				Stringurl=rb.getString("jdbc.url");
				Stringusername=rb.getString("jdbc.username");
				Stringpassword=rb.getString("jdbc.password");
				ComboPooledDataSourcedataSource=newComboPooledDataSource();
				dataSource.setDriverClass(driver);
				dataSource.setJdbcUrl(url);
				dataSource.setUser(username);
				dataSource.setPassword(password);
				Connectionconnection=dataSource.getConnection();
				System.out.println(connection);
				connection.close();
				
				}
			
				@Test
				//黑马手动获取数据源
				publicvoidtest4()throwsException{
				DruidDataSourcedataSource=newDruidDataSource();
				dataSource.setDriverClassName("com.mysql.jdbc.Driver");
				dataSource.setUrl("jdbc:mysql://localhost:3306/test");
				dataSource.setUsername("root");
				dataSource.setPassword("0316");
				DruidPooledConnectionconnection=dataSource.getConnection();
				System.out.println(connection);
				connection.close();
				}
			
				@Test
				//使用配置文件获取数据源(李志祥写的)
				publicvoidtest3()throwsException{
				InputStreamis=ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
				Propertiesproperties=newProperties();
				properties.load(is);
				DataSourcedataSource=DruidDataSourceFactory.createDataSource(properties);
				Connectionconnection=dataSource.getConnection();
				System.out.println(connection);
				connection.close();
				
				
				}
				
				@Test
				//测试手动创建Druid数据源
				publicvoidtest2()throwsException{
				DruidDataSourcedataSource=newDruidDataSource();
				dataSource.setDriverClassName("com.mysql.jdbc.Driver");
				dataSource.setUrl("jdbc:mysql://localhost:3306/test");
				dataSource.setUsername("root");
				dataSource.setPassword("0316");
				DruidPooledConnectionconnection=dataSource.getConnection();
				System.out.println(connection);
				connection.close();
				}
				
				@Test
				//测试手动创建c3p0数据源
				publicvoidtest1()throwsException{
				ComboPooledDataSourcedataSource=newComboPooledDataSource();
				dataSource.setDriverClass("com.mysql.jdbc.Driver");
				dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
				dataSource.setUser("root");
				dataSource.setPassword("0316");
				Connectionconnection=dataSource.getConnection();
				System.out.println(connection);
				connection.close();
				
				}
		
	使用spring-jdbcTemplate获取数据库连接池：
				xmlns:context="http://www.springframework.org/schema/context"
 				<!--加载外部的配置文件-->
				<context:property-placeholderlocation="classpath:jdbc.properties"></context:property-placeholder>
				<!--c3p0dataSource-->
				
				<beanid="c3p0"class="com.mchange.v2.c3p0.ComboPooledDataSource">
				<propertyname="driverClass"value="${jdbc.driver}"></property>
				<propertyname="jdbcUrl"value="${jdbc.url}"></property>
				<propertyname="user"value="${jdbc.username}"></property>
				<propertyname="password"value="${jdbc.password}"></property>
				</bean>
				<!--druiddataSource-->
				<beanid="druid"class="com.alibaba.druid.pool.DruidDataSource">
				<propertyname="driverClassName"value="com.mysql.jdbc.Driver"></property>
				<propertyname="url"value="jdbc:mysql://localhost:3306/test"></property>
				<propertyname="username"value="root"></property>
				<propertyname="password"value="0316"></property>
				</bean>

----------
	spring-anno
		Spring原始注解
	Spring原始注解主要是替代<Bean>的配置
	@Component使用在类上用于实例化Bean
	@Controller使用在Web层上用于实例化Bean
	@Service使用在Service层上用于实例化Bean
	@Repository使用在dao层上用于实例化Bean
	@Autowired使用在字段上用于根据类型依赖注入
	@Qualifiter结合@Autowired一起使用用于根据名称进行依赖注入
	@Resource相当于@Autowired+@Qualifiter，按照名称进行注入
	@Value注入普通属性
	@Scope注入Bean的作用范围
	@PostConstruct使用在方法上标注该方法时Bean的初始化方法
	@PreDestory使用在方法上标注该方法时Bean的销毁方法
	
	注意：使用注解实例化Bean之后要添加组件扫描
	<context:component-scan base-package="com.itheima"></context:component
	scan>
	
	Spring新注解：
	使用原始注解不能替代xml中的
	①非自定义的Bean配置<bena>例如数据库连接池。
	②加载properties文件的配置：<context:property-plcaeholder>MyBatis使用property属性引入。
	③组件扫描的配置：<context-component-scan>
	
	@Configeration用于指定当前类是一个Spring配置类，当创建容器时会从该类上加载注解对应问题对应applicatiojnContext
	@ComponentScan用于指定Spring在初始化容器时要扫描的包
	<!--配置组件扫描-->
	<!--用于读取注解-->
	通常情况下我们在创建spring项目的时候在xml配置文件中都会配置这个标签，配置完这个标签后，spring就会去自动扫描base-package对应的路径或者该路径的子包下面的java文件，如果扫描到文件中带有@Service,@Component,@Repository,@Controller等这些注解的类，则把这些类注册为bean
	@Bean使用在方法上，标注该方法的返回值存储到Spring容器中
	Spring新注解：
		使用原始注解不能替代xml中的
		①非自定义的Bean配置<bena>例如数据库连接池。
		②加载properties文件的配置：<context:property-plcaeholder>MyBatis使用property属性引入。
		③组件扫描的配置：<context-component-scan>
		
		@Configeration用于指定当前类是一个Spring配置类，当创建容器时会从该类上加载注解对应问题对应applicatiojnContext
		@ComponentScan用于指定Spring在初始化容器时要扫描的包
		<!--配置组件扫描-->
		<!--用于读取注解-->
		通常情况下我们在创建spring项目的时候在xml配置文件中都会配置这个标签，配置完这个标签后，spring就会去自动扫描base-package对应的路径或者该路径的子包下面的java文件，如果扫描到文件中带有@Service,@Component,@Repository,@Controller等这些注解的类，则把这些类注册为bean
		@Bean使用在方法上，标注该方法的返回值存储到Spring容器中
		@PropertySource用于加载properties文件中的配置
		@import用于导入其他配置类

----------
	spring整合Junit单元测试
	导入spring-test坐标
	导入junit坐标
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
##

## SpringMVC

	普通web项目
	<?xml version="1.0" encoding="UTF-8"?>
	<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--全局初始化参数-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>
    <servlet>
        <servlet-name>UserServlet</servlet-name>
        <servlet-class>web.UserServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>UserServlet</servlet-name>
        <url-pattern>/userServlet</url-pattern>
    </servlet-mapping>
    
    <!--配置监听器-->
    <listener>
        <listener-class>listener.ContextLoaderListener</listener-class>
    </listener>

	</web-app>

	
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


	public class WebApplicationContextUtils {
    public static ApplicationContext getWebApplicationContext(ServletContext servletContext){
        ApplicationContext app = (ApplicationContext) servletContext.getAttribute("app");
        return app;
	    }	
	}


	public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        UserService userService = app.getBean(UserService.class);
        userService.save();

    	}
	}	

----------

	概念：SpringMVC 是一种基于 Java 的实现 MVC 设计模型的请求驱动类型的轻量级 Web 框架，属于 
	SpringFrameWork 的后续产品，已经融合在 Spring Web Flow 中。 
	SpringMVC 已经成为目前最主流的MVC框架之一，并且随着Spring3.0 的发布，全面超越 Struts2，成为最优 
	秀的 MVC 框架。它通过一套注解，让一个简单的 Java 类成为处理请求的控制器，而无须实现任何接口。同时 
	它还支持 RESTful 编程风格的请求。

----------
	springmvc执行逻辑：
	SpringMVC组件:
		SpringMVC的相关组件 
		• 
		前端控制器：DispatcherServlet 
		• 
		处理器映射器：
		HandlerMapping 
		• 
		处理器适配器：
		HandlerAdapter 
		• 
		处理器：
		Handler 
		• 
		视图解析器：View Resolver 
		• 
		视图：View
		SpringMVC的注解和配置 
		• 
		请求映射注解：@RequestMapping 
		• 
		视图解析器配置： 
		REDIRECT_URL_PREFIX = "redirect:" 
		FORWARD_URL_PREFIX = "forward:" 
		prefix = ""; 
		suffix = ""; 
		
		
		MVC获取数据细节：
			中文乱码问题：
			<!--配置全局过滤的filter-->
			<filter>
			<filter-name>CharacterEncodingFilter</filter-name>
			<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
			<!--设置过滤器中的属性值指定字符集-->
			<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
			</init-param>
			<!--启动过滤器-->
			<init-param>
			<param-name>forceEncoding</param-name>
			<param-value>true</param-value>
			</init-param>
			</filter>
			<filter-mapping>
			<filter-name>CharacterEncodingFilter</filter-name>
			<url-pattern>/*</url-pattern>
			</filter-mapping>
			@RequestParam和@PathVariable
			自定义类类型转换器
			publicclassDateConverterimplementsConverter<String,Date>{
			@Override
			publicDateconvert(StringdateStr){
			//将日期字符串转换成日期对象返回
			SimpleDateFormatformat=newSimpleDateFormat("yyyy-MM-dd");
			Datedate=null;
			try{
			date=format.parse(dateStr);
			}catch(ParseExceptione){
			thrownewRuntimeException(e);
			}
			returndate;
			}
			<!--声明自定义转换器-->
			<beanid="conversionService"class="org.springframework.context.support.ConversionServiceFactoryBean">
			<propertyname="converters">
			<list>
			<beanclass="com.itheima.converter.DateConverter"/>
			</list>
			</property>
			</bean>
			<mvc:annotation-drivenconversion-service="conversionService"/>
			文件上传
			<!--配置文件上传解析器-->
			<beanid="multipartResolver"
			class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
			<!--上传文件总大小--><propertyname="maxUploadSize"value="5242800"/>
			<!--上传单个文件的大小--><propertyname="maxUploadSizePerFile"value="5242800"/>
			<!--上传文件的编码类型--><propertyname="defaultEncoding"value="UTF-8"/>
			</bean>

----------
	自定义异常处理器：
	实现HandlerExceptionResolver接口
	<!--配置拦截器-->
    <mvc:interceptors>
            <mvc:interceptor>
                <!--对哪些资源执行拦截操作-->
                <mvc:mapping path="/**"/>
                <bean class="com.itheima.interceptor.MyInterceptor"/>
            </mvc:interceptor>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <bean class="com.itheima.interceptor.MyInterceptor2"/>
        </mvc:interceptor>
    </mvc:interceptors>
	public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在目标方法执行之前  执行
        System.out.println("preHandle...");
        String param = request.getParameter("param");
        if ("yes".equalsIgnoreCase(param)){
            return true;
        }else{
            request.getRequestDispatcher("/error.jsp").forward(request,response);
            return false;
        }
        //return false;//返回true代表放行  返回false代表不放行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //目标方法执行之后  视图返回之前
        modelAndView.addObject("name","itheima");
        System.out.println("postHandle...");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //整个流程都执行完毕后
        System.out.println("afterHandle...");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    	}
	}
	public class MyInterceptor2 {
    public class MyInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            System.out.println("preHandle22222");
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            //目标方法执行之后  视图返回之前
            System.out.println("postHandle.222222222..");
            HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            //整个流程都执行完毕后
            System.out.println("afterHandle.22222222..");
            HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        	}
    	}
	}


----------
	spring异常处理机制：
		方式一：
			<!--配置简单映射异常处理器-->
    <bean
        class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
        <property name="defaultErrorView" value="error"/><!--默认错误视图-->
        <property name="exceptionMappings">
            <map>
                <entry key="com.itheima.exception.MyException" value="error2"/>
                <entry key="java.lang.ClassCastException" value="error1"/>
            </map>
        </property>
    </bean>
		方式二：
			<!--自定义异常处理器-->
    <bean class="com.itheima.resolver.MyExceptionResolver"></bean>
	public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
         /*
         参数Exception：异常对象
         返回值ModelAndView：跳转到错误视图信息
         */
        ModelAndView modelAndView = new ModelAndView();

        if ( ex instanceof MyException){
            modelAndView.addObject("info","自定义异常类");
        } else if (ex instanceof ClassCastException) {
            modelAndView.addObject("info","类型转换异常");
        }

        modelAndView.setViewName("error");
        return modelAndView;
    	}
	}
##

## Soring-AOP
	什么是aop：
		AOP 为 Aspect Oriented Programming 的缩写，意思为面向切面编程，是通过预编译方式和运行期动态代理 
		实现程序功能的统一维护的一种技术。 
		AOP 是 OOP 的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍 
		生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序 
		的可重用性，同时提高了开发的效率。

----------

	Spring中aop相关概念
		Spring 的 AOP 实现底层就是对上面的动态代理的代码进行了封装，封装后我们只需要对需要关注的部分进行代码编 
		写，并通过配置的方式完成指定目标的方法增强。 
		在正式讲解 AOP 的操作之前，我们必须理解 AOP 的相关术语，常用的术语如下： 
		 Target（目标对象）：代理的目标对象 
		 Proxy （代理）：一个类被 AOP 织入增强后，就产生一个结果代理类 
		 Joinpoint（连接点）：所谓连接点是指那些被拦截到的点。在spring中,这些点指的是方法，因为spring只支持方 
		法类型的连接点 （可以被增强的方法）
		 Pointcut（切入点）：所谓切入点是指我们要对哪些 Joinpoint 进行拦截的定义 （被增强的方法）
		 Advice（通知/ 增强）：所谓通知是指拦截到 Joinpoint 之后所要做的事情就是通知 （）
		 Aspect（切面）：是切入点和通知（引介）的结合 
		 Weaving（织入）：是指把增强应用到目标对象来创建新的代理对象的过程。spring采用动态代理织入，而 
		AspectJ采用编译期织入和类装载期织入


----------
	基于AOP实现动态代理：
		方式u一：使用jdk内部动态代理
		publicstaticvoidmain(String[]args){
		//创建目标对象
		finalTargettarget=newTarget();
		
		//增强对象
		Adviceadvice=newAdvice();
		
		//返回值就是动态生成的代理对象
		TargerInterfaceproxy=(TargerInterface)Proxy.newProxyInstance(
		target.getClass().getClassLoader(),//目标对象的类加载器
		target.getClass().getInterfaces(),//目标对象的接口字节码对象数组
		newInvocationHandler(){
		//调用代理对象的任何方法，实质执行的都是invoke方法
		@Override
		publicObjectinvoke(Objectproxy,Methodmethod,Object[]args)throwsThrowable{
		advice.before();//前置增强
		Objectinvoke=method.invoke(target,args);//执行目标方法
		advice.after();//后置增强
		returninvoke;
		}
		}
		);
		//调用代理对象的方法
		proxy.save();
		}
		
		
		方式二：使用cglib实现动态代理
		//创建目标对象
		finalTargettarget=newTarget();
		
		//增强对象
		Adviceadvice=newAdvice();
		
		//返回值就是动态生成的代理对象基于cglib
		//1.创建增强器
		Enhancerenhancer=newEnhancer();
		//2.设置父类（目标）
		enhancer.setSuperclass(Target.class);
		//3.设置回调
		enhancer.setCallback(newMethodInterceptor(){
		@Override
		publicObjectintercept(Objecto,Methodmethod,Object[]objects,MethodProxymethodProxy)throwsThrowable{
		//执行前置
		advice.before();
		//执行目标
		Objectinvoke=method.invoke(target,args);
		//执行后置
		advice.after();
		returninvoke;
		}
		});
		//4.创建代理对象
		Targetproxy=(Target)enhancer.create();
		
		proxy.save();
		
		}

----------

	xml配置aop
		 1.切点表达式的写法 
		表达式语法： 
		execution([修饰符] 返回值类型 包名.类名.方法名(参数)) 
		 访问修饰符可以省略 
		 返回值类型、包名、类名、方法名可以使用星号* 代表任意 
		 包名与类名之间一个点 . 代表当前包下的类，两个点 .. 表示当前包及其子包下的类 
		 参数列表可以使用两个点 .. 表示任意个数，任意类型的参数列表 
		例如： 
		execution(public void com.itheima.aop.Target.method()) 
		execution(void com.itheima.aop.Target.*(..)) 
		execution(* com.itheima.aop.*.*(..)) 
		execution(* com.itheima.aop..*.*(..)) 
		execution(* *..*.*(..))
	
		 aop织入的配置 
		<aop:config> 
		<aop:aspect ref=“切面类”> 
		<aop:before method=“通知方法名称” pointcut=“切点表达式"></aop:before> 
		</aop:aspect> 
		</aop:config> 
		 通知的类型：前置通知、后置通知、环绕通知、异常抛出通知、最终通知 
		 切点表达式的写法： 
		execution([修饰符] 返回值类型 包名.类名.方法名(参数))


	 <!--配置目标对象-->
        <bean id="target" class="com.itheima.aop.Target"></bean>

        <!--配置切面对象-->
        <bean id="myAspect" class="com.itheima.aop.MyAspect"></bean>

        <!--配置织入，告诉Spring框架哪些方法(切点)需要进行哪些增强（前置、后置。。。）-->
        <aop:config>
                <!--声明切面-->
                <aop:aspect ref="myAspect">
                        <!--抽取切点表达式-->
                        <aop:pointcut id="myPoint" expression="execution(* com.itheima.aop.*.*(..))"></aop:pointcut>
                        <!--切面：切点+通知-->
                        <aop:before method="before" pointcut="execution(* com.itheima.aop.Target.save())"/>
		<!--                        <aop:before method="before" pointcut="execution(* com.itheima..*.*(..))"/>-->
		<!--                        <aop:after-returning method="afterReturning" pointcut="execution(* com.itheima.*.*(..))"/>-->
		<!--                        <aop:around method="around" pointcut="execution(* com.itheima.aop.*.*(..))"/>-->
		<!--                        <aop:after-throwing method="afterThrowing" pointcut="execution(* com.itheima.aop.*.*(..))"/>-->
		<!--                        <aop:after method="after" pointcut="execution(* com.itheima.aop.*.*(..))"/>-->
		<!--                        <aop:around method="around" pointcut-ref="myPoint"/>-->
                </aop:aspect>
        </aop:config>


----------
	基于aop的注解开发
		@Component("myAspect")
	@Aspect //标注当前MyAspect是一个切面类
	public class MyAspect {


    //配置前置通知
    //切点声明的第一个*代表任意返回值
    @Before("execution(* com.itheima.anno.*.*(..))")
    public void before(){
        System.out.println("前置增强......");
    }
    /*返回后增强*/
    @AfterReturning("pointcut()")
    public void afterReturning(){
        System.out.println("后置增强......");
    }

    //ProceedingJoinPoint:正在执行的连接点===切点
    /*环绕通知*/
	//    @Around("execution(* com.itheima.anno.Target.save())")
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕前增强....");
        Object proceed = pjp.proceed();//切点方法
        System.out.println("环绕后增强....");
        return proceed;
    }
    /*异常抛出通知*/
    public void afterThrowing(){
        System.out.println("异常抛出增强....");
    }
    /*最终通知*/
    //@After( "execution(* com.itheima.anno.*.*(..))")
    public void after(){
        System.out.println("最终增强....");
    }
    //定义切点表达式
    @Pointcut("execution(* com.itheima.anno.*.*(..))")
    //注意:此注解用于定义切点表达式用于标注哪些方法需要被增强因此该方法的方法体不会被执行
    public void pointcut(){
       // System.out.println("提取切点表达式");

    	}
	}




##