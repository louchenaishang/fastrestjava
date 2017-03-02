package person.louchen.restj.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import person.louchen.restj.framework.utils.BCryptUtil;
import person.louchen.restj.framework.utils.EmptyUtil;
import person.louchen.restj.interfaces.UserService;
import person.louchen.restj.model.entity.UserEntity;
import person.louchen.restj.session.SessionContext;
import person.louchen.restj.session.SessionContextHolder;
import person.louchen.restj.service.AbstractBusinessServiceImpl;
import person.louchen.restj.service.exception.BusinessException;

import java.util.List;

/**
 * Created by louchen on 2017/2/8.
 */
@Service
public class UserServiceImpl extends AbstractBusinessServiceImpl implements UserService {

    @Override
    public UserEntity login(String loginName, String loginPwd) throws Exception {
        UserEntity userEntity = userMysqlRepository.findByLoginName(loginName);
        if(userEntity==null){
            throw new BusinessException("账户错误");
        }
        if(!BCryptUtil.checkpw(loginPwd, userEntity.getLoginPwd())){
            throw new BusinessException("密码错误");
        }

        SessionContextHolder.set(new SessionContext(userEntity.getId()));

        return userEntity;
    }

    @Override
    public UserEntity getOne(String id) throws Exception {
        UserEntity userEntity = userMysqlRepository.findOne(id);
        return userEntity;
    }

    @Override
    public UserEntity newOne(UserEntity userEntity) throws Exception {
        if(EmptyUtil.isEmpty(userEntity.getLoginName())){
            userEntity.setLoginName(userEntity.getName());
        }
        if(EmptyUtil.isEmpty(userEntity.getLoginPwd())){
            userEntity.setLoginPwd("123456");
        }
        userEntity.setLoginPwd(BCryptUtil.hashpw(userEntity.getLoginPwd()));
        return userMysqlRepository.saveAndFlush(userEntity);
    }

    @Override
    public UserEntity editOne(UserEntity userEntity) throws Exception {
        UserEntity old = userMysqlRepository.findOne(userEntity.getId());

        old.setName(userEntity.getName());
        old.setSex(userEntity.getSex());
        old.setAge(userEntity.getAge());
        old.setBirth(userEntity.getBirth());
        old.setAddr(userEntity.getAddr());
        old.setPhone(userEntity.getPhone());

        return userMysqlRepository.saveAndFlush(old);
    }

    @Override
    public boolean deleteOne(String id) throws Exception {
        UserEntity userEntity = userMysqlRepository.findOne(id);
        userMysqlRepository.delete(userEntity);
        return true;
    }

    @Override
    public List<UserEntity> getAll(String name) throws Exception {
        if(EmptyUtil.isEmpty(name)){
            return userMysqlRepository.findAll();
        }else{
            return userMysqlRepository.findByNameLike(name);
        }
    }

    @Override
    public Page<UserEntity> getAll(String name, Pageable pageable) throws Exception {
        if(EmptyUtil.isEmpty(name)){
            return userMysqlRepository.findAll(pageable);
        }else{
            return userMysqlRepository.findByNameLike(name,pageable);
        }
    }

}
