package person.louchen.restj.server.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import person.louchen.restj.interfaces.security.SignService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 第一个拦截器 请求签名验证
 * 约定1.不可有exclude
 * 约定2.必须覆盖所有路径
 * Created by louchen on 2017/2/8.
 */
public class SignInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SignService signService;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws Exception {
        return signService.verify(request, response);
    }

}
