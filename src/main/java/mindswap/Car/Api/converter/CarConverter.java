package mindswap.Car.Api.converter;

import mindswap.Car.Api.dto.CarDto;
import mindswap.Car.Api.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {


    public Car fromDtoToModel(CarDto carDto) {
        Car car = new Car();
        car.setId(carDto.getId());
        car.setModel(carDto.getModel());
        //   user.setLastName(messageDto.lastName());
        return car;
    }

    public CarDto fromModelToDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setModel(car.getModel());
        return carDto;

        //  message.getLastName()

    }
}
