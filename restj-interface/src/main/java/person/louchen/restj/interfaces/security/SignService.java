package person.louchen.restj.interfaces.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by louchen on 2017/2/9.
 */
public interface SignService {

    /**
     * 验证请求签名在服务端是否有效
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    boolean verify(final HttpServletRequest request, final HttpServletResponse response) throws Exception;

}
