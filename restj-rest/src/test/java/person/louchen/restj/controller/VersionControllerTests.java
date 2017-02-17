package person.louchen.restj.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;
import person.louchen.restj.BaseControllerTests;
import person.louchen.restj.result.ResultObject;

/**
 * Created by louchen on 2017/2/16.
 */

public class VersionControllerTests extends BaseControllerTests {


    @Before
    public void setup() throws Exception {
        super.setup();
    }

    @Test
    public void getVersionTest() throws Exception {
        ResultObject result = this.restTemplate.getForObject("/version", ResultObject.class);

        Assert.notNull(result);
    }

}
