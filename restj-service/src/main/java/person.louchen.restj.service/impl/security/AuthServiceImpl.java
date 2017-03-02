package person.louchen.restj.service.impl.security;

import org.springframework.stereotype.Service;
import person.louchen.restj.framework.utils.EmptyUtil;
import person.louchen.restj.interfaces.security.AuthService;
import person.louchen.restj.session.SessionContext;
import person.louchen.restj.session.SessionContextHolder;
import person.louchen.restj.service.AbstractBusinessServiceImpl;
import person.louchen.restj.service.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by louchen on 2017/2/8.
 */
@Service
public class AuthServiceImpl extends AbstractBusinessServiceImpl implements AuthService {

    @Override
    public boolean verify(final HttpServletRequest request,final HttpServletResponse response) throws Exception {
        final SessionContext context = SessionContextHolder.get();
        if(context==null){
            throw new BusinessException("身份验证失败");
        }
        final String userId = context.getUserId();
        if(EmptyUtil.isNotEmpty(userId)){
            return true;
        }else{
            throw new BusinessException("身份验证失败");
        }
    }

}
