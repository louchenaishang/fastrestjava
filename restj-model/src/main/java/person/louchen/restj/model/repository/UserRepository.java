package person.louchen.restj.model.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import person.louchen.restj.model.entity.UserEntity;

import java.util.List;

/**
 * Created by louchen on 2017/2/8.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query("select u from UserEntity u where u.name like CONCAT('%',:name,'%')  order by u.createdttm")
    List<UserEntity> findByNameLike(@Param("name") String name);

    @Query("select u from UserEntity u where u.name like CONCAT('%',:name,'%')  order by u.createdttm")
    Page<UserEntity> findByNameLike(@Param("name") String name, Pageable pageable);

    @Query("select u from UserEntity u where u.loginName=?1")
    UserEntity findByLoginName(String loginName);
}
