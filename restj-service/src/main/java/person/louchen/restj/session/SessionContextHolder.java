package person.louchen.restj.session;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by louchen on 2017/2/9.
 */
public class SessionContextHolder {

    /**
     * httpsession存入自定义session上下文
     * @param context
     */
    public static void set(SessionContext context){
        getRequest().getSession().setAttribute(SessionConstant.SESSION_STORAGE_KEY, context);
    }

    /**
     * httpsession获取自定义session上下文
     * @return
     */
    public static SessionContext get(){
        return (SessionContext)getRequest().getSession().getAttribute(SessionConstant.SESSION_STORAGE_KEY);
    }

    /**
     * 获取request
     * @return
     */
    private static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

}
