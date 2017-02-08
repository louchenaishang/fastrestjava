package person.louchen.restj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.louchen.restj.interfaces.VersionService;
import person.louchen.restj.service.parameter.Parameter;

/**
 * Created by louchen on 2017/2/8.
 */
@Service
public class VersionServiceImpl implements VersionService {

    @Autowired
    private Parameter parameter;

    public String get() throws Exception {
        return parameter.getProjectVersion();
    }

}
