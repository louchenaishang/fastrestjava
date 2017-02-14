package person.louchen.restj.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by louchen on 2017/2/13.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("person.louchen.restj.server.config.app")
public class AppConfig {

}
