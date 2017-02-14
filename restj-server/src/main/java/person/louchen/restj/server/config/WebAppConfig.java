package person.louchen.restj.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import person.louchen.restj.server.handler.ApiExcerptionHandler;
import person.louchen.restj.server.interceptor.AuthInterceptor;
import person.louchen.restj.server.interceptor.SessionInterceptor;
import person.louchen.restj.server.interceptor.SignInterceptor;
import person.louchen.restj.server.jackson.JacksonObjectMapper;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by louchen on 2017/2/13.
 */
@Configuration
@EnableWebMvc
@ComponentScan("person.louchen.restj.api")
public class WebAppConfig extends WebMvcConfigurerAdapter{

    @Bean
    public ApiExcerptionHandler apiExcerptionHandler(){
        return new ApiExcerptionHandler();
    }

    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter(){
        return new ByteArrayHttpMessageConverter();
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));

        return stringHttpMessageConverter;
    }

    @Bean
    public JacksonObjectMapper jacksonObjectMapper(){
        JacksonObjectMapper jacksonObjectMapper = new JacksonObjectMapper();
        jacksonObjectMapper.setIgnoreProperty("version,handler,fieldHandler,hibernateLazyInitializer,JavassistLazyInitializer,PersistentSet");
        jacksonObjectMapper.setDateFormatPattern("yyyy-MM-dd HH:mm:ss");
        jacksonObjectMapper.init();

        return jacksonObjectMapper;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(jacksonObjectMapper());

        return mappingJackson2HttpMessageConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(byteArrayHttpMessageConverter());
        converters.add(stringHttpMessageConverter());
        converters.add(mappingJackson2HttpMessageConverter());

        super.configureMessageConverters(converters);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");

        super.addCorsMappings(registry);
    }

    @Bean
    public SignInterceptor signInterceptor(){
        return new SignInterceptor();
    }

    @Bean
    public SessionInterceptor sessionInterceptor(){
        return new SessionInterceptor();
    }

    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(sessionInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**");

        super.addInterceptors(registry);
    }

}
