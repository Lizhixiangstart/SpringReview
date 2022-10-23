package jdbc.demo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetDataSourceByHands {
    public static void main(String[] args) throws Exception {
        System.out.println(getc3p0());
        System.out.println("*****************");
        System.out.println(getdruid());
        System.out.println("*****************");
        System.out.println(getdruidByproperties());
    }

    public static DataSource getc3p0() throws PropertyVetoException {
        ComboPooledDataSource c3p0 = new ComboPooledDataSource();
        c3p0.setDriverClass("com.mysql.jdbc.Driver");
        c3p0.setJdbcUrl("jdbc:mysql://localhost:3306/test?");
        c3p0.setUser("root");
        c3p0.setPassword("0316");
        return c3p0;
    }
    public static DataSource getdruid(){
        DruidDataSource druid = new DruidDataSource();
        druid.setDriverClassName("com.mysql.jdbc.Driver");
        druid.setUrl("jdbc:mysql://localhost:3306/test?");
        druid.setUsername("root");
        druid.setPassword("0316");
        return druid;
    }
    
    public static DataSource getdruidByproperties() throws Exception {
        InputStream jdbcproperties = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(jdbcproperties);
        DataSource druid = DruidDataSourceFactory.createDataSource(properties);
        return druid;
    }
}
