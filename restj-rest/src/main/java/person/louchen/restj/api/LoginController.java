package person.louchen.restj.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import person.louchen.restj.interfaces.UserService;
import person.louchen.restj.mvc.annotation.SkipAuth;
import person.louchen.restj.param.LoginParam;
import person.louchen.restj.result.ResultObject;
import person.louchen.restj.result.ResultStatus;

/**
 * Created by louchen on 2017/2/9.
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param loginParam
     * @return
     */
    @RequestMapping(value = "", method = {RequestMethod.POST})
    @SkipAuth
    public ResultObject login(@RequestBody LoginParam loginParam) {
        ResultObject resultObject = new ResultObject();
        try {
            resultObject.setBody(userService.login(loginParam.getUsername(), loginParam.getPassword()));
        } catch (Exception e) {
            resultObject.setStatus(ResultStatus.ERROR);
            resultObject.setBody(e.getMessage());
        }
        return resultObject;
    }

}
