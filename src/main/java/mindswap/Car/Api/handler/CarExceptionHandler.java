package mindswap.Car.Api.handler;


import mindswap.Car.Api.dto.ErrorDto;
import mindswap.Car.Api.exception.CarException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CarExceptionHandler {


    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(CarException.class)
    public ErrorDto handler(CarException exceptionHandler) {
        return new ErrorDto(
                exceptionHandler.getMessage()
        );
    }
}
