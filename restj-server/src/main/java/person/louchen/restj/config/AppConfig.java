package person.louchen.restj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by louchen on 2017/2/13.
 */
@Configuration
@PropertySource({"classpath:conf/config.properties",
        "classpath:conf/version.properties",
        "classpath:conf/jdbc.properties",
        "classpath:conf/redis.properties",
        "classpath:conf/rest.properties"})
public class AppConfig {

    @Bean
    public JpaAppConfig jpaAppConfig(){
        return new JpaAppConfig();
    }

    @Bean
    public RedisAppConfig redisAppConfig(){
        return new RedisAppConfig();
    }

    @Bean
    public ServiceAppConfig serviceAppConfig(){
        return new ServiceAppConfig();
    }

}
