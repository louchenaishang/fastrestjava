package person.louchen.restj.framework.spring;

import org.springframework.test.context.ActiveProfilesResolver;

/**
 * Created by louchen on 2017/2/16.
 */
public class SpringProfilesActiveResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> testClass) {
        final String springProfilesActive = System.getProperty("spring.profiles.active");
        return new String[]{springProfilesActive == null ? "ci" : springProfilesActive};
    }

}