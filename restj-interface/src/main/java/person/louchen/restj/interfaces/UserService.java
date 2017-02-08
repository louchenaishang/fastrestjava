package person.louchen.restj.interfaces;

import person.louchen.restj.model.entity.UserEntity;

import java.util.List;

/**
 * Created by louchen on 2017/2/8.
 */
public interface UserService {

    UserEntity newOne(UserEntity userEntity) throws Exception;

    List<UserEntity> getAll() throws Exception;

}
