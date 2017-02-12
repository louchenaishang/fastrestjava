package person.louchen.restj.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import person.louchen.restj.framework.utils.EmptyUtil;
import person.louchen.restj.interfaces.DeptService;
import person.louchen.restj.model.entity.DeptEntity;
import person.louchen.restj.service.AbstractBusinessServiceImpl;

import java.util.List;

/**
 * Created by louchen on 2017/2/12.
 */
public class DeptServiceImpl extends AbstractBusinessServiceImpl implements DeptService {

    @Override
    public DeptEntity getOne(String id) throws Exception {
        DeptEntity deptEntity = deptRepository.findOne(id);
        return deptEntity;
    }

    @Override
    public DeptEntity newOne(DeptEntity deptEntity) throws Exception {
        return deptRepository.saveAndFlush(deptEntity);
    }

    @Override
    public DeptEntity editOne(DeptEntity deptEntity) throws Exception {
        DeptEntity old = deptRepository.findOne(deptEntity.getId());

        old.setName(deptEntity.getName());

        return deptRepository.saveAndFlush(old);
    }

    @Override
    public boolean deleteOne(String id) throws Exception {
        DeptEntity deptEntity = deptRepository.findOne(id);
        deptRepository.delete(deptEntity);
        return true;
    }

    @Override
    public List<DeptEntity> getAll(String name) throws Exception {
        if(EmptyUtil.isEmpty(name)){
            return deptRepository.findAll();
        }else{
            return deptRepository.findByNameLike(name);
        }
    }

    @Override
    public Page<DeptEntity> getAll(String name, Pageable pageable) throws Exception {
        if(EmptyUtil.isEmpty(name)){
            return deptRepository.findAll(pageable);
        }else{
            return deptRepository.findByNameLike(name,pageable);
        }
    }
}
