package person.louchen.restj.service.impl;

import org.springframework.stereotype.Service;
import person.louchen.restj.framework.utils.BCryptUtil;
import person.louchen.restj.interfaces.UserService;
import person.louchen.restj.model.entity.UserEntity;
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
        UserEntity userEntity = userRepository.findByLoginName(loginName);
        if(userEntity==null){
            throw new BusinessException("无此登录账户");
        }
        if(!BCryptUtil.checkpw(loginPwd, userEntity.getLoginPwd())){
            throw new BusinessException("密码错误");
        }
        return userEntity;
    }

    @Override
    public UserEntity newOne(UserEntity userEntity) throws Exception {
        userEntity.setLoginPwd(BCryptUtil.hashpw(userEntity.getLoginPwd()));
        return userRepository.saveAndFlush(userEntity);
    }

    @Override
    public List<UserEntity> getAll() throws Exception {
        return userRepository.findAll();
    }

}
