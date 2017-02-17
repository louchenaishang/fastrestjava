package person.louchen.restj;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import person.louchen.restj.framework.spring.SpringProfilesActiveResolver;
import person.louchen.restj.interfaces.UserService;
import person.louchen.restj.model.entity.UserEntity;

/**
 * Created by louchen on 2017/2/17.
 */
@RunWith(SpringRunner.class)
@ActiveProfiles(resolver = SpringProfilesActiveResolver.class)
@EnableAspectJAutoProxy
@Transactional
@Rollback
public abstract class BaseTests {

    @Autowired
    protected UserService userService;

    @Before()
    public void setup() throws Exception {
        for(int i=0;i<100;i++){
            UserEntity newOne = new UserEntity();
            newOne.setLoginName("test"+i);
            newOne.setLoginPwd("123");
            newOne.setName("test"+i);
            userService.newOne(newOne);
        }

    }


}
