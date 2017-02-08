package person.louchen.restj.result;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

/**
 *
 * Created by louchen on 2016/12/10.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonFilter(value = "default")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@jsonId")
public class ResultObject {

    private ResultStatus status = ResultStatus.SUCCESS;
    private Object body;

}
