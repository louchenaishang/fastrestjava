package person.louchen.restj.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:conf/jdbc.properties"})
@EnableJpaRepositories(basePackages = "person.louchen.restj.model.repository.mysql")
public class JpaAppConfig {

    @Autowired
    public org.springframework.core.env.Environment env;

    @Bean
    public DruidDataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        dataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class));
        dataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class));
        dataSource.setMinIdle(env.getProperty("jdbc.minIdle", Integer.class));
        dataSource.setMaxWait(env.getProperty("jdbc.maxWait", Long.class));
        dataSource.setDefaultAutoCommit(env.getProperty("jdbc.defaultAutoCommit", Boolean.class));
        dataSource.setTimeBetweenEvictionRunsMillis(env.getProperty("jdbc.timeBetweenEvictionRunsMillis", Long.class));
        dataSource.setMinEvictableIdleTimeMillis(env.getProperty("jdbc.minEvictableIdleTimeMillis", Long.class));
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(env.getProperty("jdbc.testWhileIdle", Boolean.class));
        dataSource.setTestOnBorrow(env.getProperty("jdbc.testOnBorrow", Boolean.class));
        dataSource.setTestOnReturn(env.getProperty("jdbc.testOnReturn", Boolean.class));
        dataSource.setFilters(env.getProperty("jdbc.filters"));

        dataSource.init();

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(Environment.DRIVER, env.getProperty("hibernate.connection.driver_class"));
        jpaProperties.put(Environment.DIALECT, env.getProperty("hibernate.dialect"));
        jpaProperties.put(Environment.HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put(Environment.SHOW_SQL, env.getProperty("hibernate.show_sql"));
        jpaProperties.put(Environment.FORMAT_SQL, env.getProperty("hibernate.format_sql"));
        jpaProperties.put(Environment.MAX_FETCH_DEPTH, env.getProperty("hibernate.max_fetch_depth"));
        jpaProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, env.getProperty("hibernate.current_session_context_class"));
        jpaProperties.put(Environment.AUTOCOMMIT, env.getProperty("hibernate.connection.autocommit"));
        jpaProperties.put(Environment.ISOLATION, env.getProperty("hibernate.connection.isolation"));
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return jpaTransactionManager;
    }

}