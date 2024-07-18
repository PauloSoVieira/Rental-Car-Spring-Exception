package mindswap.Car.Api.exception;

import lombok.Data;
import mindswap.Car.Api.message.Message;

@Data
public class RentalException extends RuntimeException {

    public RentalException(Message message) {
        super(message.getMessage());
    }
}
