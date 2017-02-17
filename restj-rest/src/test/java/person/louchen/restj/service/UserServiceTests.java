package person.louchen.restj.service;

import org.junit.Before;
import org.junit.Test;
import person.louchen.restj.BaseServiceTests;
import person.louchen.restj.model.entity.UserEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by louchen on 2017/2/16.
 */
public class UserServiceTests extends BaseServiceTests{


    @Before
    public void setup() throws Exception {
        super.setup();
    }

    @Test
    public void getUsers() throws Exception {
        List<UserEntity> list = userService.getAll("");
        assertThat(list.size()).isGreaterThan(0);
    }

}
