package person.louchen.restj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import person.louchen.restj.interfaces.VersionService;

/**
 * Created by louchen on 2017/2/8.
 */
@Service
public class VersionServiceImpl implements VersionService {

    @Autowired
    public Environment env;


    @Override
    public String get() throws Exception {
        return env.getProperty("project.version");
    }

}
