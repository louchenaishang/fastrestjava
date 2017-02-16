package person.louchen.restj.controller;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import person.louchen.restj.result.ResultObject;


/**
 * Created by louchen on 2017/2/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getVersionTest() {
        ResultObject result = this.restTemplate.getForObject("/version", ResultObject.class);
        Assert.notNull(result);
    }


}
