package person.louchen.restj.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import person.louchen.restj.security.SecurityEntity;
import person.louchen.restj.security.SecurityEntityManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by louchen on 2017/2/13.
 */
@Configuration
@ComponentScan("person.louchen.restj.service.impl")
public class ServiceConfig {

    @Autowired
    public Environment env;

    @Bean
    public SecurityEntity securityEntity() {
        SecurityEntity se = new SecurityEntity();
        se.setAppId(env.getProperty("spring.sys.rest.app-id"));
        se.setAppSecret(env.getProperty("spring.sys.rest.app-secret"));

        return se;
    }

    @Bean
    public SecurityEntityManager securityEntityManager() {
        SecurityEntityManager sem = new SecurityEntityManager();
        Map<String, SecurityEntity> map = new HashMap<>();
        map.put(env.getProperty("spring.sys.rest.app-id"), securityEntity());
        sem.setMap(map);

        return sem;
    }

    @Bean
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        return new AutowiredAnnotationBeanPostProcessor();
    }

    @Bean(name = "transactionInterceptor")
    public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager) {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(platformTransactionManager);
        Properties transactionAttributes = new Properties();

        transactionAttributes.setProperty("*", "PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("select*", "PROPAGATION_SUPPORTS,-Throwable,readOnly");
        transactionAttributes.setProperty("get*", "PROPAGATION_SUPPORTS,-Throwable,readOnly");
        transactionAttributes.setProperty("find*", "PROPAGATION_SUPPORTS,-Throwable,readOnly");
        transactionAttributes.setProperty("query*", "PROPAGATION_SUPPORTS,-Throwable,readOnly");
        transactionAttributes.setProperty("load*", "PROPAGATION_SUPPORTS,-Throwable,readOnly");
        transactionAttributes.setProperty("search*", "PROPAGATION_SUPPORTS,-Throwable,readOnly");

        transactionInterceptor.setTransactionAttributes(transactionAttributes);

        return transactionInterceptor;
    }

    @Bean
    public BeanNameAutoProxyCreator transactionAutoProxy() {
        BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
        transactionAutoProxy.setProxyTargetClass(true);
        transactionAutoProxy.setBeanNames("*ServiceImpl");
        transactionAutoProxy.setInterceptorNames("transactionInterceptor");
        return transactionAutoProxy;
    }

}
