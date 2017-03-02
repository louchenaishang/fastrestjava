package person.louchen.restj.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "person.louchen.restj.model.repository.mysql")
public class JpaConfig {

    @Autowired
    public org.springframework.core.env.Environment env;

    @Bean(initMethod = "init",destroyMethod = "close")
    public DruidDataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        dataSource.setInitialSize(env.getProperty("spring.datasource.initialSize", Integer.class));
        dataSource.setMaxActive(env.getProperty("spring.datasource.maxActive", Integer.class));
        dataSource.setMinIdle(env.getProperty("spring.datasource.minIdle", Integer.class));
        dataSource.setMaxWait(env.getProperty("spring.datasource.maxWait", Long.class));
        dataSource.setDefaultAutoCommit(env.getProperty("spring.datasource.defaultAutoCommit", Boolean.class));
        dataSource.setTimeBetweenEvictionRunsMillis(env.getProperty("spring.datasource.timeBetweenEvictionRunsMillis", Long.class));
        dataSource.setMinEvictableIdleTimeMillis(env.getProperty("spring.datasource.minEvictableIdleTimeMillis", Long.class));
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(env.getProperty("spring.datasource.testWhileIdle", Boolean.class));
        dataSource.setTestOnBorrow(env.getProperty("spring.datasource.testOnBorrow", Boolean.class));
        dataSource.setTestOnReturn(env.getProperty("spring.datasource.testOnReturn", Boolean.class));
        dataSource.setFilters(env.getProperty("spring.datasource.filters"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(env.getProperty("spring.jpa.properties.hibernate.packagesToScan"));
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(Environment.DRIVER, env.getProperty("spring.jpa.properties.hibernate.connection.driver_class"));
        jpaProperties.put(Environment.AUTOCOMMIT, env.getProperty("spring.jpa.properties.hibernate.connection.autocommit"));
        jpaProperties.put(Environment.ISOLATION, env.getProperty("spring.jpa.properties.hibernate.connection.isolation"));
        jpaProperties.put(Environment.DIALECT, env.getProperty("spring.jpa.properties.hibernate.dialect"));
        jpaProperties.put(Environment.HBM2DDL_AUTO, env.getProperty("spring.jpa.properties.hibernate.ddl-auto"));
        jpaProperties.put(Environment.SHOW_SQL, env.getProperty("spring.jpa.properties.hibernate.show_sql"));
        jpaProperties.put(Environment.FORMAT_SQL, env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        jpaProperties.put(Environment.MAX_FETCH_DEPTH, env.getProperty("spring.jpa.properties.hibernate.max_fetch_depth"));
        jpaProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));

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