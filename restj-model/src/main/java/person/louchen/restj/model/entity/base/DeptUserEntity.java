package person.louchen.restj.model.entity.base;

import lombok.Getter;
import lombok.Setter;
import person.louchen.restj.model.entity.DeptEntity;
import person.louchen.restj.model.entity.UserEntity;

import javax.persistence.*;

/**
 * Created by louchen on 2017/2/12.
 */
@Entity
@Table(name = "t_dept_user")
@Getter
@Setter
public class DeptUserEntity extends BaseDateDomain {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "dept_id", referencedColumnName = "id")
    private DeptEntity deptEntity;//部门

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id", referencedColumnName = "id")
    private UserEntity userEntity;//用户

}
