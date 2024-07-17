package mindswap.Car.Api.controller;

import mindswap.Car.Api.dto.CarDto;
import mindswap.Car.Api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/car")

public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping("/all")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.FOUND);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getById(@PathVariable Long id) {
        CarDto carDto = carService.findUser(id);
        return new ResponseEntity<>(carDto, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
        CarDto responseCar = carService.createCar(carDto);
        return new ResponseEntity<>(responseCar, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto, @PathVariable Long id) {
        CarDto responseCar = carService.updateCar(carDto, id);

        return new ResponseEntity<>(responseCar, HttpStatus.OK);

    }


}
