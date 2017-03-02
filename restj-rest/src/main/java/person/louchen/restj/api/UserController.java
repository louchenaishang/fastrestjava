package person.louchen.restj.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import person.louchen.restj.interfaces.UserService;
import person.louchen.restj.model.entity.UserEntity;
import person.louchen.restj.result.ResultObject;
import person.louchen.restj.result.ResultStatus;
import person.louchen.restj.session.SessionContext;
import person.louchen.restj.session.SessionContextHolder;

import java.util.concurrent.Callable;

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
    public Callable<ResultObject> getUserList(String name, Integer page, Integer size) {
        return () -> {
            SessionContext context = SessionContextHolder.get();
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
        };
    }

    /**
     * 根据id得到一个用户
     *
     * @return
     */
    @RequestMapping(value = "/one", method = {RequestMethod.GET})
    public Callable<ResultObject> getOne(String id) {
        return () -> {
            ResultObject resultObject = new ResultObject();
            try {
                resultObject.setBody(userService.getOne(id));
            } catch (Exception e) {
                resultObject.setStatus(ResultStatus.ERROR);
                resultObject.setBody(e.getMessage());
            }
            return resultObject;
        };
    }

    /**
     * 新建用户
     *
     * @return
     */
    @RequestMapping(value = "/one", method = {RequestMethod.POST})
    public Callable<ResultObject> newOne(@RequestBody UserEntity one) {
        return () -> {
            ResultObject resultObject = new ResultObject();
            try {
                resultObject.setBody(userService.newOne(one));
            } catch (Exception e) {
                resultObject.setStatus(ResultStatus.ERROR);
                resultObject.setBody(e.getMessage());
            }
            return resultObject;
        };
    }

    /**
     * 编辑用户
     *
     * @return
     */
    @RequestMapping(value = "/one", method = {RequestMethod.PUT})
    public Callable<ResultObject> editOne(@RequestBody UserEntity one) {
        return () -> {
            ResultObject resultObject = new ResultObject();
            try {
                resultObject.setBody(userService.editOne(one));
            } catch (Exception e) {
                resultObject.setStatus(ResultStatus.ERROR);
                resultObject.setBody(e.getMessage());
            }
            return resultObject;
        };
    }

    /**
     * 删除用户
     *
     * @return
     */
    @RequestMapping(value = "/one", method = {RequestMethod.DELETE})
    public Callable<ResultObject> deleteOne(String id) {
        return () -> {
            ResultObject resultObject = new ResultObject();
            try {
                resultObject.setBody(userService.deleteOne(id));
            } catch (Exception e) {
                resultObject.setStatus(ResultStatus.ERROR);
                resultObject.setBody(e.getMessage());
            }
            return resultObject;
        };
    }

}
