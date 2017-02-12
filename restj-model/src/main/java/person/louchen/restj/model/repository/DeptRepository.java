package person.louchen.restj.model.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import person.louchen.restj.model.entity.DeptEntity;

import java.util.List;

/**
 * Created by louchen on 2017/2/8.
 */
@Repository
public interface DeptRepository extends JpaRepository<DeptEntity, String> {

    @Query("select u from DeptEntity u where u.name like CONCAT('%',:name,'%')  order by u.createdtime")
    List<DeptEntity> findByNameLike(@Param("name") String name);

    @Query("select u from DeptEntity u where u.name like CONCAT('%',:name,'%')  order by u.createdtime")
    Page<DeptEntity> findByNameLike(@Param("name") String name, Pageable pageable);
    
}
