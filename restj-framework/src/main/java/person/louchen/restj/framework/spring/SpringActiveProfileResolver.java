package person.louchen.restj.framework.spring;

import org.springframework.test.context.ActiveProfilesResolver;

/**
 * Created by louchen on 2017/2/16.
 */
public class SpringActiveProfileResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> testClass) {
        final String activeProfile = System.getProperty("spring.active.profile");
        return new String[]{activeProfile == null ? "ci" : activeProfile};
    }

}