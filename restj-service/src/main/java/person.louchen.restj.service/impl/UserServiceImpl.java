package person.louchen.restj.service.impl;

import org.springframework.stereotype.Service;
import person.louchen.restj.framework.utils.BCryptUtil;
import person.louchen.restj.interfaces.UserService;
import person.louchen.restj.model.entity.UserEntity;
import person.louchen.restj.service.AbstractBusinessServiceImpl;

import java.util.List;

/**
 * Created by louchen on 2017/2/8.
 */
@Service
public class UserServiceImpl extends AbstractBusinessServiceImpl implements UserService {

    public UserEntity newOne(UserEntity userEntity) throws Exception {
        userEntity.setLoginPwd(BCryptUtil.hashpw(userEntity.getLoginPwd()));
        return userRepository.saveAndFlush(userEntity);
    }

    public List<UserEntity> getAll() throws Exception {
        return userRepository.findAll();
    }

}
