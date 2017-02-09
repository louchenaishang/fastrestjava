package person.louchen.restj.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 删除一个用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteOne(String id) throws Exception;

    /**
     * 获取所有用户
     *
     * @return
     * @throws Exception
     */
    List<UserEntity> getAll(String name) throws Exception;

    /**
     * 获取所有用户
     *
     * @return
     * @throws Exception
     */
    Page<UserEntity> getAll(String name, Pageable pageable) throws Exception;

}
