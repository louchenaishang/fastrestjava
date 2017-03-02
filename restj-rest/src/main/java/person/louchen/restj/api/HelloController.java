package person.louchen.restj.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import person.louchen.restj.mvc.annotation.SkipAuth;

import java.util.concurrent.Callable;

/**
 * Created by louchen on 2017/3/1.
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloController {

//    @RequestMapping(value = "", method = {RequestMethod.GET})
//    @SkipAuth
//    public Callable<String> say() throws InterruptedException {
//        return () -> {
//            Thread.currentThread().sleep(1000);
//            return "hello ^-^"+Thread.currentThread().getName();
//        };
//    }

    @RequestMapping(value = "", method = {RequestMethod.GET})
    @SkipAuth
    public String say() throws InterruptedException {
        Thread.currentThread().sleep(1000);
        return "hello ^-^"+Thread.currentThread().getName();
    }

}
