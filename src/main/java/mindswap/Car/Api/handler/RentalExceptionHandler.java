package mindswap.Car.Api.handler;

import mindswap.Car.Api.dto.ErrorDto;
import mindswap.Car.Api.exception.RentalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class RentalExceptionHandler {


    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(RentalException.class)
    public ErrorDto handler(RentalException rentalException) {
        return new ErrorDto(
                rentalException.getMessage()
        );
    }
}
