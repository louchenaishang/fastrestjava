package person.louchen.restj.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import person.louchen.restj.interfaces.UserService;
import person.louchen.restj.model.entity.UserEntity;
import person.louchen.restj.result.ResultObject;
import person.louchen.restj.result.ResultStatus;

import java.util.List;

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
    public ResultObject getUserList(String name, Integer page, Integer size) {
        ResultObject resultObject = new ResultObject();
        try {
            if (page != null && size != null) {
                resultObject.setBody(userService.getAll(name, new PageRequest(page, size)));
            } else {
                resultObject.setBody(userService.getAll(name));
            }
        } catch (Exception e) {
            resultObject.setStatus(ResultStatus.ERROR);
            resultObject.setBody(e.getMessage());
        }
        return resultObject;
    }

    /**
     * 根据id得到一个用户
     *
     * @return
     */
    @RequestMapping(value = "/one", method = {RequestMethod.GET})
    public ResultObject getOne(String id) {
        ResultObject resultObject = new ResultObject();
        try {
            resultObject.setBody(userService.getOne(id));
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

    /**
     * 删除用户
     *
     * @return
     */
    @RequestMapping(value = "/one", method = {RequestMethod.DELETE})
    public ResultObject deleteOne(String id) {
        ResultObject resultObject = new ResultObject();
        try {
            resultObject.setBody(userService.deleteOne(id));
        } catch (Exception e) {
            resultObject.setStatus(ResultStatus.ERROR);
            resultObject.setBody(e.getMessage());
        }
        return resultObject;
    }


}
