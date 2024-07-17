package mindswap.Car.Api.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RentalCreationDto {
    private Long userId;
    private Long carId;
    
}
