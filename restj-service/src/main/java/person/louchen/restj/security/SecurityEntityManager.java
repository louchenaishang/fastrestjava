package person.louchen.restj.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by louchen on 2017/2/9.
 */
public class SecurityEntityManager {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Map<String, SecurityEntity> map;

    public Map<String, SecurityEntity> getMap() {
        return map;
    }

    public void setMap(Map<String, SecurityEntity> map) {
        this.map = map;
    }

}
