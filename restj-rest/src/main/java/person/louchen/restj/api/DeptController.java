package person.louchen.restj.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import person.louchen.restj.interfaces.DeptService;
import person.louchen.restj.model.entity.DeptEntity;
import person.louchen.restj.result.ResultObject;
import person.louchen.restj.result.ResultStatus;

import java.util.concurrent.Callable;

/**
 * Created by louchen on 2017/2/12.
 */
@RestController
@RequestMapping(value = "/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 根据条件获取部门
     *
     * @return
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public Callable<ResultObject> getUserList(String name, Integer page, Integer size) {
        return () -> {
            ResultObject resultObject = new ResultObject();
            try {
                if (page != null && size != null) {
                    resultObject.setBody(deptService.getAll(name, new PageRequest(page, size)));
                } else {
                    resultObject.setBody(deptService.getAll(name));
                }
            } catch (Exception e) {
                resultObject.setStatus(ResultStatus.ERROR);
                resultObject.setBody(e.getMessage());
            }
            return resultObject;
        };
    }

    /**
     * 根据id得到一个部门
     *
     * @return
     */
    @RequestMapping(value = "/one", method = {RequestMethod.GET})
    public Callable<ResultObject> getOne(String id) {
        return () -> {
            ResultObject resultObject = new ResultObject();
            try {
                resultObject.setBody(deptService.getOne(id));
            } catch (Exception e) {
                resultObject.setStatus(ResultStatus.ERROR);
                resultObject.setBody(e.getMessage());
            }
            return resultObject;
        };
    }

    /**
     * 新建部门
     *
     * @return
     */
    @RequestMapping(value = "/one", method = {RequestMethod.POST})
    public Callable<ResultObject> newOne(@RequestBody DeptEntity one) {
        return () -> {
            ResultObject resultObject = new ResultObject();
            try {
                resultObject.setBody(deptService.newOne(one));
            } catch (Exception e) {
                resultObject.setStatus(ResultStatus.ERROR);
                resultObject.setBody(e.getMessage());
            }
            return resultObject;
        };
    }

    /**
     * 编辑部门
     *
     * @return
     */
    @RequestMapping(value = "/one", method = {RequestMethod.PUT})
    public Callable<ResultObject> editOne(@RequestBody DeptEntity one) {
        return () -> {
            ResultObject resultObject = new ResultObject();
            try {
                resultObject.setBody(deptService.editOne(one));
            } catch (Exception e) {
                resultObject.setStatus(ResultStatus.ERROR);
                resultObject.setBody(e.getMessage());
            }
            return resultObject;
        };
    }

    /**
     * 删除部门
     *
     * @return
     */
    @RequestMapping(value = "/one", method = {RequestMethod.DELETE})
    public Callable<ResultObject> deleteOne(String id) {
        return () -> {
            ResultObject resultObject = new ResultObject();
            try {
                resultObject.setBody(deptService.deleteOne(id));
            } catch (Exception e) {
                resultObject.setStatus(ResultStatus.ERROR);
                resultObject.setBody(e.getMessage());
            }
            return resultObject;
        };
    }

}
