package person.louchen.restj.interfaces.exception;

/**
 * Created by louchen on 2017/2/9.
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

}
