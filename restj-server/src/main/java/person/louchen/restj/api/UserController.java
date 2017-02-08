package person.louchen.restj.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import person.louchen.restj.interfaces.UserService;
import person.louchen.restj.model.entity.UserEntity;
import person.louchen.restj.result.ResultObject;
import person.louchen.restj.result.ResultStatus;

/**
 * Created by louchen on 2017/2/8.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据条件获取用户
     *
     * @return
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ResultObject getUserList() {
        ResultObject resultObject = new ResultObject();
        try {
            resultObject.setBody(userService.getAll());
        } catch (Exception e) {
            resultObject.setStatus(ResultStatus.ERROR);
            resultObject.setBody(e.getMessage());
        }
        return resultObject;
    }

    /**
     * 新建用户
     *
     * @return
     */
    @RequestMapping(value = "/one", method = {RequestMethod.POST})
    public ResultObject newOne(@RequestBody UserEntity one) {
        ResultObject resultObject = new ResultObject();
        try {
            resultObject.setBody(userService.newOne(one));
        } catch (Exception e) {
            resultObject.setStatus(ResultStatus.ERROR);
            resultObject.setBody(e.getMessage());
        }
        return resultObject;
    }



}
