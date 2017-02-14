package person.louchen.restj.mvc.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import person.louchen.restj.interfaces.security.AuthService;
import person.louchen.restj.mvc.annotation.SkipAuth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 第三个拦截器 请求身份验证
 * 约定1.可有exclude
 * 约定2.必须覆盖所有路径
 * 约定3.被拦截的方法如果带有@SkipAuth,会跳过验证
 * Created by louchen on 2017/2/8.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws Exception {
        if(request.getMethod().toUpperCase().equals(RequestMethod.OPTIONS.toString())){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        SkipAuth skipAuth = handlerMethod
                .getMethodAnnotation(SkipAuth.class);
        if (skipAuth != null) {
            return true;
        } else {
            return authService.verify(request, response);
        }

    }

}
