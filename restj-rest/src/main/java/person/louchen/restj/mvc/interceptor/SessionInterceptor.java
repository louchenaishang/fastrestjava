package person.louchen.restj.mvc.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 第二个拦截器 把requestSession中的关键信息存入threadlocal
 * 约定1.不可有不拦截的路径
 * 约定2.必须覆盖所有路径
 * Created by louchen on 2017/2/8.
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public Environment env;


    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws Exception {
        if (request.getMethod().toUpperCase().equals(RequestMethod.OPTIONS.toString())) {
            return true;
        }
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(env.getProperty("spring.sys.session.max-inactive-interval-in-seconds", Integer.class));
        return true;
    }

}
