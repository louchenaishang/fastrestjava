package person.louchen.restj.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by louchen on 2017/2/9.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginParam {

    String username;
    String password;

}
