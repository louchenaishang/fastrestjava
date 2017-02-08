package person.louchen.restj.service.parameter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by louchen on 2017/2/8.
 */
@Getter
@Setter
@Configuration
public class Parameter {

    @Value("${project.version}")
    private String projectVersion;

}
