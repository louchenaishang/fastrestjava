package person.louchen.restj.controller;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import person.louchen.restj.BaseControllerTests;
import person.louchen.restj.model.entity.UserEntity;
import person.louchen.restj.session.SessionConstant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by louchen on 2017/2/16.
 */

public class UserControllerTests extends BaseControllerTests {

    @Before
    public void setup() throws Exception {
        super.setup();
    }


    @Test
    public void getUsersTest() throws Exception {
        String mockUserId = "1";
        MockHttpServletRequestBuilder requestBuilder = get("/users")
                .contentType(MediaType.ALL)
                .sessionAttr(SessionConstant.SESSION_STORAGE_KEY, mockUserId)
                .param("name", "1");
        String responseString = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();

        String bodyString = this.checkResultObject(responseString);
        List<UserEntity> userEntities = JSON.parseArray(bodyString, UserEntity.class);
        assertThat(userEntities.size()).isGreaterThan(0);

    }

}
