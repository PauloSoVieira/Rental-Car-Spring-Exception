package mindswap.Car.Api.exception;

import lombok.Data;
import mindswap.Car.Api.message.Message;

@Data
public class UserException extends RuntimeException {

    public UserException(Message message) {
        super(message.getMessage());

    }


}
