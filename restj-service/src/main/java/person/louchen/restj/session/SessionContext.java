package person.louchen.restj.session;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by louchen on 2017/3/2.
 */
@Getter
@Setter
@ToString
public class SessionContext implements Serializable {

    private String userId;

    public SessionContext(String userId){
        this.userId = userId;
    }

}
