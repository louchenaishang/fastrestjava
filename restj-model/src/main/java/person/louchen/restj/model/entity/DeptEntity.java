package person.louchen.restj.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import person.louchen.restj.model.entity.base.BaseDateDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by louchen on 2017/2/11.
 */
@Entity
@Table(name = "t_dept")
@RedisHash("depts")
@Getter
@Setter
public class DeptEntity extends BaseDateDomain {

    @Column(name = "name", nullable = false)
    @Indexed
    private String name;//部门名称

}
