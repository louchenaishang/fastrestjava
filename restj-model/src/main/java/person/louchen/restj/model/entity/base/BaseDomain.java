package person.louchen.restj.model.entity.base;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@JsonFilter(value = "default")
public abstract class BaseDomain implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false)
    @Indexed
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
