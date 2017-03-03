package person.louchen.restj.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by louchen on 2017/3/3.
 */
@RestController
public class HomeController {

    @RequestMapping(value = "")
    public String home(){
        return Thread.currentThread().getName();
    }

}
