package person.louchen.restj.model.repository.redis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import person.louchen.restj.model.redis.Dept;

/**
 * Created by louchen on 2017/2/12.
 */
public interface DeptRedisRepository extends PagingAndSortingRepository<Dept, String> {

    Page<Dept> findByName(String name, Pageable page);

}
