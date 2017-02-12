package person.louchen.restj.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import person.louchen.restj.model.entity.DeptEntity;

import java.util.List;

/**
 * Created by louchen on 2017/2/12.
 */
public interface DeptService {



    /**
     * 根据id得到一个部门
     *
     * @param id
     * @return
     * @throws Exception
     */
    DeptEntity getOne(String id) throws Exception;

    /**
     * 新建一个部门
     *
     * @param deptEntity
     * @return
     * @throws Exception
     */
    DeptEntity newOne(DeptEntity deptEntity) throws Exception;

    /**
     * 编辑一个部门
     *
     * @param deptEntity
     * @return
     * @throws Exception
     */
    DeptEntity editOne(DeptEntity deptEntity) throws Exception;

    /**
     * 删除一个部门
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteOne(String id) throws Exception;

    /**
     * 获取所有部门
     *
     * @return
     * @throws Exception
     */
    List<DeptEntity> getAll(String name) throws Exception;

    /**
     * 获取所有部门
     *
     * @return
     * @throws Exception
     */
    Page<DeptEntity> getAll(String name, Pageable pageable) throws Exception;

}
