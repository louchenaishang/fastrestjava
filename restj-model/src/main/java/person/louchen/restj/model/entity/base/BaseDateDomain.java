package person.louchen.restj.model.entity.base;

import javax.persistence.*;
import java.util.Date;

@Access(AccessType.PROPERTY)
@MappedSuperclass
public abstract class BaseDateDomain extends BaseDomain {

    @Column(name = "createdttm")
    private Date createdttm;//创建时间

    @Column(name = "updatedttm")
    private Date updatedttm;//更新时间

    @PrePersist
    public void prePersist() {
        createdttm = updatedttm = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        updatedttm = new Date();
    }

    public Date getCreatedttm() {
        return createdttm;
    }

    public void setCreatedttm(Date createdttm) {
        this.createdttm = createdttm;
    }

    public Date getUpdatedttm() {
        return updatedttm;
    }

    public void setUpdatedttm(Date updatedttm) {
        this.updatedttm = updatedttm;
    }
}
