package person.louchen.restj.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import person.louchen.restj.config.JpaConfig;
import person.louchen.restj.config.RedisConfig;
import person.louchen.restj.config.ServiceConfig;
import person.louchen.restj.framework.spring.SpringProfilesActiveResolver;
import person.louchen.restj.interfaces.UserService;
import person.louchen.restj.model.entity.UserEntity;

import java.util.List;

/**
 * Created by louchen on 2017/2/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JpaConfig.class, RedisConfig.class, ServiceConfig.class})
@ActiveProfiles(resolver = SpringProfilesActiveResolver.class)
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Before
    public void setup() throws Exception {
        for(int i=0;i<100;i++){
            UserEntity newOne = new UserEntity();
            newOne.setLoginName("test"+i);
            newOne.setLoginPwd("123");
            newOne.setName("test"+i);
            userService.newOne(newOne);
        }

    }

    @Test
    public void getUsers() throws Exception {
        List<UserEntity> list = userService.getAll("");
        System.out.println("getUsers1");
        System.out.println(list.size());


    }





}
