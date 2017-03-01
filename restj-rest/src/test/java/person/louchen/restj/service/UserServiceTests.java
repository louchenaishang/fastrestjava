package person.louchen.restj.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public void getOne() throws Exception {
        List<UserEntity> list = userService.getAll("");
        assertThat(list.size()).isGreaterThan(0);
        UserEntity one = userService.getOne(list.get(0).getId());
        assertThat(one.getId()).isEqualTo(list.get(0).getId());
    }

    @Test
    public void getUsers() throws Exception {
        List<UserEntity> list = userService.getAll("");
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    public void getUsersPage() throws Exception {
        int page = 0;
        int size = 20;
        Page<UserEntity> pages = userService.getAll("",new PageRequest(page,size));
        assertThat(pages.getContent().size()).isLessThanOrEqualTo(size);
        pages = userService.getAll("test",new PageRequest(page+1,size));
        assertThat(pages.getContent().size()).isLessThanOrEqualTo(size);
    }



}
