package person.louchen.restj.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import person.louchen.restj.interfaces.VersionService;
import person.louchen.restj.result.ResultObject;
import person.louchen.restj.result.ResultStatus;
import person.louchen.restj.mvc.annotation.SkipAuth;

/**
 * Created by louchen on 2017/2/8.
 */
@RestController
@RequestMapping(value = "/version")
public class VersionController {

    @Autowired
    private VersionService versionService;

    /**
     * 根据条件获取用户
     *
     * @return
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    @SkipAuth
    public ResultObject getVersion() {
        ResultObject resultObject = new ResultObject();
        try {
            resultObject.setBody(versionService.get());
        } catch (Exception e) {
            resultObject.setStatus(ResultStatus.ERROR);
            resultObject.setBody(e.getMessage());
        }
        return resultObject;
    }



}
