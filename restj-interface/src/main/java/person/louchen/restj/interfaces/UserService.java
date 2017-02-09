package person.louchen.restj.interfaces;

import person.louchen.restj.model.entity.UserEntity;

import java.util.List;

/**
 * Created by louchen on 2017/2/8.
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param loginName
     * @param loginPwd
     * @return
     * @throws Exception
     */
    UserEntity login(String loginName, String loginPwd) throws Exception;

    /**
     * 新建一个用户
     *
     * @param userEntity
     * @return
     * @throws Exception
     */
    UserEntity newOne(UserEntity userEntity) throws Exception;

    /**
     * 获取所有用户
     *
     * @return
     * @throws Exception
     */
    List<UserEntity> getAll() throws Exception;

}