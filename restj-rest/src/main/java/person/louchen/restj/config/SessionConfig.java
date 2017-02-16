package person.louchen.restj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@PropertySource({"classpath:conf/config.properties"})
@EnableRedisHttpSession
public class SessionConfig {

    @Autowired
    public Environment env;

    @Bean
    DefaultCookieSerializer defaultCookieSerializer() {
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        defaultCookieSerializer.setDomainName(env.getProperty("sys.domain"));
        defaultCookieSerializer.setUseBase64Encoding(true);

        return defaultCookieSerializer;
    }

    @Bean
    RedisHttpSessionConfiguration redisHttpSessionConfiguration() {
        RedisHttpSessionConfiguration redisHttpSessionConfiguration = new RedisHttpSessionConfiguration();
        redisHttpSessionConfiguration.setMaxInactiveIntervalInSeconds(env.getProperty("sys.maxInactiveIntervalInSeconds",Integer.class));
        redisHttpSessionConfiguration.setCookieSerializer(defaultCookieSerializer());

        return redisHttpSessionConfiguration;
    }

    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

}