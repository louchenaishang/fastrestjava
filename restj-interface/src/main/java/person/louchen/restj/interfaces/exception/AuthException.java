package person.louchen.restj.interfaces.exception;

/**
 * Created by louchen on 2017/3/2.
 */
public class AuthException extends RuntimeException {

    public AuthException() {
    }

    public AuthException(String message) {
        super(message);
    }

}
