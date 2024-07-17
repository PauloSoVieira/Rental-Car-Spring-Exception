package mindswap.Car.Api.service;

import mindswap.Car.Api.converter.CarConverter;
import mindswap.Car.Api.dto.CarDto;
import mindswap.Car.Api.model.Car;
import mindswap.Car.Api.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository carRepository;
    private CarConverter carConverter;

    public CarService(CarRepository carRepository, CarConverter carConverter) {
        this.carRepository = carRepository;
        this.carConverter = carConverter;
    }


    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(carConverter::fromModelToDto)
                .collect(Collectors.toList());
    }

    public CarDto findUser(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Error"));
        return carConverter.fromModelToDto(car);
    }

    public CarDto createCar(CarDto carDto) {
        Car car = carConverter.fromDtoToModel(carDto);
        carRepository.save(car);
        return carConverter.fromModelToDto(car);
    }


    public CarDto deleteCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Error"));
        if (car != null) {
            carRepository.deleteById(id);
        }
        return carConverter.fromModelToDto(car);
    }

    public CarDto updateCar(CarDto carDto, Long id) {
        Car carExist = carRepository.findById(id).orElse(null);
        if (carExist != null) {
            Car updateCar = carConverter.fromDtoToModel(carDto);
            updateCar.setId(id);
            updateCar = carRepository.save(updateCar);
            return carConverter.fromModelToDto(updateCar);
        }
        return null;
    }
}
