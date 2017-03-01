package person.louchen.restj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by louchen on 2017/2/14.
 */
@SpringBootApplication
@EnableAsync
public class Application {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class,args);
    }

}
