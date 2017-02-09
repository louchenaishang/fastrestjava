package person.louchen.restj.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by louchen on 2017/2/9.
 */
public class SecurityEntity {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected String appId;

    protected String appSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

}
