package person.louchen.restj;

import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import person.louchen.restj.config.JpaConfig;
import person.louchen.restj.config.RedisConfig;
import person.louchen.restj.config.ServiceConfig;

/**
 * Created by louchen on 2017/2/17.
 */
@SpringBootTest(classes = {JpaConfig.class, RedisConfig.class, ServiceConfig.class})
public abstract class BaseServiceTests extends BaseTests {

    @Before()
    public void setup() throws Exception {
        super.setup();
    }


}
