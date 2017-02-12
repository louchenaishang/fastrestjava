package person.louchen.restj.model.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Access(AccessType.PROPERTY)
@MappedSuperclass
@Getter
@Setter
public abstract class BaseDateDomain extends BaseDomain {

    @Column(name = "createdtime")
    private Date createdtime;//创建时间

    @Column(name = "updatedtime")
    private Date updatedtime;//更新时间

    @PrePersist
    public void prePersist() {
        createdtime = updatedtime = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        updatedtime = new Date();
    }

}
