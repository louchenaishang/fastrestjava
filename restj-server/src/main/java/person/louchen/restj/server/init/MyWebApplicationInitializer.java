package person.louchen.restj.server.init;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import person.louchen.restj.config.AppConfig;
import person.louchen.restj.config.WebConfig;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

/**
 * Created by louchen on 2017/2/13.
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        container.setInitParameter("logbackConfigLocation","classpath:conf/logback.xml");

        //logback监听器
        container.addListener(new LogbackConfigListener());

        //配置Spring提供的字符编码过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        javax.servlet.FilterRegistration.Dynamic filter1 = container.addFilter("encoding", characterEncodingFilter);
        //配置过滤器的过滤路径
        filter1.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

        //配置hibernate的session延长过滤器
        OpenEntityManagerInViewFilter openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
        javax.servlet.FilterRegistration.Dynamic filter2 = container.addFilter("openEntityManagerInViewFilter", openEntityManagerInViewFilter);
        filter2.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

        //基于注解配置的Spring容器上下文
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        //注册Spring容器配置类
        rootContext.register(AppConfig.class);
        container.addListener(new ContextLoaderListener(rootContext));

        //基于注解配置的Web容器上下文
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        //注册Web容器配置类
        context.register(WebConfig.class);
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(context));
        //配置映射路径
        servlet.addMapping("/*");
        //启动顺序
        servlet.setLoadOnStartup(1);

    }

}
