package person.louchen.restj.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import person.louchen.restj.mvc.annotation.SkipAuth;

/**
 * Created by louchen on 2017/3/1.
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloController {


    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String say() {
        return "hello ^-^";
    }


}
