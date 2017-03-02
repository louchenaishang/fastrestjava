package person.louchen.restj.mvc.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import person.louchen.restj.interfaces.exception.AuthException;
import person.louchen.restj.interfaces.exception.SignException;
import person.louchen.restj.result.ResultObject;
import person.louchen.restj.result.ResultStatus;

/**
 * 异常处理
 *
 * @author Eric.Lou
 */
@ControllerAdvice
public class ApiExcerptionHandler {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 身份验证失败,签名验证失败
     * @param ex
     * @return
     */
    @ExceptionHandler({AuthException.class, SignException.class})
    public ResponseEntity<Object> ignore(final Exception ex) {
        ResultObject resultObject = new ResultObject();
        resultObject.setStatus(ResultStatus.ERROR);
        resultObject.setBody(ex.getLocalizedMessage());
        return new ResponseEntity<>(resultObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(final Exception ex) {
        logger.error(ex.getMessage(), ex);
        ResultObject resultObject = new ResultObject();
        resultObject.setStatus(ResultStatus.ERROR);
        resultObject.setBody(ex.getLocalizedMessage());
        return new ResponseEntity<>(resultObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
