package person.louchen.restj.service.impl.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.louchen.restj.framework.utils.MD5Util;
import person.louchen.restj.interfaces.security.SignService;
import person.louchen.restj.security.SecurityEntity;
import person.louchen.restj.security.SecurityEntityManager;
import person.louchen.restj.service.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by louchen on 2017/2/9.
 */
@Service
public class SignServiceImpl implements SignService {

    @Autowired
    private SecurityEntityManager sem;

    @Override
    public boolean verify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String appId = request.getParameter("ai");
        String appSign = request.getParameter("as");
        String appNonceStr = request.getParameter("ans");

        if (StringUtils.isBlank(appId)) {
            throw new BusinessException("签名验证失败");
        }
        if (StringUtils.isBlank(appSign)) {
            throw new BusinessException("签名验证失败");
        }
        if (StringUtils.isBlank(appNonceStr)) {
            throw new BusinessException("签名验证失败");
        }
        if(appNonceStr.length()!=32){
            throw new BusinessException("签名验证失败");
        }

        SecurityEntity se = sem.getMap().get(appId);
        if (se == null) {
            throw new BusinessException("签名验证失败");
        }

        String md5 = MD5Util.md5(se.getAppId() + se.getAppSecret() + appNonceStr);
        if (!md5.equals(appSign)) {
            throw new BusinessException("签名验证失败");
        }

        return true;
    }

}
