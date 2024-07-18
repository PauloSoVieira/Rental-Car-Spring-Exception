package mindswap.Car.Api.exception;

import lombok.Data;
import mindswap.Car.Api.message.Message;

@Data
public class CarException extends RuntimeException {

    public CarException(Message message) {
        super(message.getMessage());
    }
}
