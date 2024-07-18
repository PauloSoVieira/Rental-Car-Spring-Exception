package mindswap.Car.Api.handler;

import mindswap.Car.Api.dto.ErrorDto;
import mindswap.Car.Api.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserExceptionHandler {

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(UserException.class)
    public ErrorDto handler(UserException exceptionHandler) {
        return new ErrorDto(
                exceptionHandler.getMessage()
        );
    }

}
