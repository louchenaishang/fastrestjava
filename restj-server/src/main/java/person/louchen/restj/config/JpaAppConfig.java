package person.louchen.restj.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "person.louchen.restj.model.repository.mysql")
public class JpaAppConfig {

    @Value("${jdbc.driver}")
    private String DB_DRIVER;

    @Value("${jdbc.url}")
    private String DB_URL;

    @Value("${jdbc.username}")
    private String DB_USERNAME;

    @Value("${jdbc.password}")
    private String DB_PASSWORD;

    @Value("${hibernate.packagesToScan}")
    private String ENTITYMANAGER_PACKAGES_TO_SCAN;

    @Value("${hibernate.connection.driver_class}")
    private String HIBERNATE_DRIVER;

    @Value("${hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HIBERNATE_HBM2DDL_AUTO;

    @Value("${hibernate.show_sql}")
    private String HIBERNATE_SHOW_SQL;

    @Value("${hibernate.format_sql}")
    private String HIBERNATE_FORMAT_SQL;

    @Value("${hibernate.max_fetch_depth}")
    private String HIBERNATE_MAX_FETCH_DEPTH;

    @Value("${hibernate.current_session_context_class}")
    private String HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS;

    @Value("${hibernate.connection.autocommit}")
    private String HIBERNATE_AUTOCOMMIT;

    @Value("${hibernate.connection.isolation}")
    private String HIBERNATE_ISOLATION;

    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(Environment.DRIVER, HIBERNATE_DRIVER);
        jpaProperties.put(Environment.DIALECT, HIBERNATE_DIALECT);
        jpaProperties.put(Environment.HBM2DDL_AUTO, HIBERNATE_HBM2DDL_AUTO);
        jpaProperties.put(Environment.SHOW_SQL, HIBERNATE_SHOW_SQL);
        jpaProperties.put(Environment.FORMAT_SQL, HIBERNATE_FORMAT_SQL);
        jpaProperties.put(Environment.MAX_FETCH_DEPTH, HIBERNATE_MAX_FETCH_DEPTH);
        jpaProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS);
        jpaProperties.put(Environment.AUTOCOMMIT, HIBERNATE_AUTOCOMMIT);
        jpaProperties.put(Environment.ISOLATION, HIBERNATE_ISOLATION);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();

        return jpaTransactionManager;
    }

}