package jdbc.anno;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@PropertySource("classpath:jdbc.properties")
public class JdbcConfig {
    @Value("${jdbc.driverClassName}")
    private String driver;
    @Value("{jdbc.Connectionurl}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean("dataSource")
    public DataSource getDataSource(){
        DruidDataSource druid = new DruidDataSource();
        druid.setDriverClassName(driver);
        druid.setUrl(url);
        druid.setUsername(username);
        druid.setPassword(password);
        return druid;
    }
}
