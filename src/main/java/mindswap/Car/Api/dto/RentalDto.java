package mindswap.Car.Api.dto;


import lombok.Data;

@Data
public class RentalDto {
    private Long id;
    private CarDto car;
    private UserDto user;
}
