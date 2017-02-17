package person.louchen.restj.controller;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import person.louchen.restj.framework.spring.SpringProfilesActiveResolver;
import person.louchen.restj.interfaces.UserService;
import person.louchen.restj.model.entity.UserEntity;
import person.louchen.restj.result.ResultObject;
import person.louchen.restj.result.ResultStatus;
import person.louchen.restj.security.SecurityConstant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by louchen on 2017/2/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(resolver = SpringProfilesActiveResolver.class)
public class ApiTests {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    @Before()
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        for(int i=0;i<100;i++){
            UserEntity newOne = new UserEntity();
            newOne.setLoginName("test"+i);
            newOne.setLoginPwd("123");
            newOne.setName("test"+i);
            userService.newOne(newOne);
        }

    }

    @Test
    public void getVersionTest() throws Exception {
        ResultObject result = this.restTemplate.getForObject("/version", ResultObject.class);

        Assert.notNull(result);
    }

    @Test
    public void getUsersTest() throws Exception {
        String mockUserId = "1";
        MockHttpServletRequestBuilder requestBuilder = get("/users")
                .contentType(MediaType.ALL)
                .sessionAttr(SecurityConstant.SESSION_STORAGE_KEY, mockUserId)
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

    protected String checkResultObject(String responseString) throws Exception{
        return checkResultObject(JSON.parseObject(responseString,ResultObject.class));
    }

    protected String checkResultObject(ResultObject resultObject) throws Exception{
        Assert.notNull(resultObject);
        assertThat(resultObject.getStatus()).isEqualTo(ResultStatus.SUCCESS);

        String bodyString = JSON.toJSONString(resultObject.getBody());
        return bodyString;
    }

}
