package groceryProject.core.exceptionHandling;

import groceryProject.core.utilities.exceptions.BusinessException;
import groceryProject.core.utilities.result.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleBusinessExceptionsController {

    @ExceptionHandler(value = { BusinessException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)

    public ErrorResult handleBusinessException(BusinessException exception) {
        ErrorResult error = new ErrorResult(exception.getLocalizedMessage());
        return error;
    }



}
