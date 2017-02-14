package person.louchen.restj.model.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import person.louchen.restj.model.entity.DeptEntity;

import java.io.Serializable;

/**
 * Created by louchen on 2017/2/12.
 */
@RedisHash("dept")
@Getter
@Setter
public class Dept extends DeptEntity{

    @Id
    private String redisId;

}
