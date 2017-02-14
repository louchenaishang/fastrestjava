package person.louchen.restj.config;

import org.springframework.context.annotation.*;
import person.louchen.restj.config.app.JpaAppConfig;
import person.louchen.restj.config.app.RedisAppConfig;
import person.louchen.restj.config.app.ServiceAppConfig;

/**
 * Created by louchen on 2017/2/13.
 */
@Configuration
@EnableAspectJAutoProxy
@PropertySource({"classpath:conf/config.properties",
        "classpath:conf/version.properties",
        "classpath:conf/jdbc.properties",
        "classpath:conf/redis.properties",
        "classpath:conf/rest.properties"})
@ComponentScan("person.louchen.restj.config.app")
public class AppConfig {

}
