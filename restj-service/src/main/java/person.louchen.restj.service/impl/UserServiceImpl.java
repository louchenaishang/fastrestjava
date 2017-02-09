package person.louchen.restj.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import person.louchen.restj.framework.utils.BCryptUtil;
import person.louchen.restj.framework.utils.EmptyUtil;
import person.louchen.restj.interfaces.UserService;
import person.louchen.restj.model.entity.UserEntity;
import person.louchen.restj.security.SecurityConstant;
import person.louchen.restj.security.SecurityHolderHepler;
import person.louchen.restj.service.AbstractBusinessServiceImpl;
import person.louchen.restj.service.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
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
            throw new BusinessException("账户错误");
        }
        if(!BCryptUtil.checkpw(loginPwd, userEntity.getLoginPwd())){
            throw new BusinessException("密码错误");
        }

        SecurityHolderHepler.set(userEntity.getId());

        return userEntity;
    }

    @Override
    public UserEntity newOne(UserEntity userEntity) throws Exception {
        userEntity.setLoginPwd(BCryptUtil.hashpw(userEntity.getLoginPwd()));
        return userRepository.saveAndFlush(userEntity);
    }

    @Override
    public List<UserEntity> getAll(String name) throws Exception {
        if(EmptyUtil.isEmpty(name)){
            return userRepository.findAll();
        }else{
            return userRepository.findByNameLike(name);
        }
    }

    @Override
    public Page<UserEntity> getAll(String name, Pageable pageable) throws Exception {
        if(EmptyUtil.isEmpty(name)){
            return userRepository.findAll(pageable);
        }else{
            return userRepository.findByNameLike(name,pageable);
        }
    }

}
