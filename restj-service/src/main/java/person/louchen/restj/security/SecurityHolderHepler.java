package person.louchen.restj.security;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by louchen on 2017/2/9.
 */
public class SecurityHolderHepler {

    /**
     * session中存入id
     * @param id
     */
    public static void set(String id){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        request.getSession().setAttribute(SecurityConstant.SESSION_STORAGE_KEY, id);
    }

}
