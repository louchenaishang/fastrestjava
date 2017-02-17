package person.louchen.restj;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import person.louchen.restj.result.ResultObject;
import person.louchen.restj.result.ResultStatus;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by louchen on 2017/2/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseControllerTests extends BaseTests {

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    protected TestRestTemplate restTemplate;


    protected MockMvc mockMvc;

    @Before()
    public void setup() throws Exception {
        super.setup();
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
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
