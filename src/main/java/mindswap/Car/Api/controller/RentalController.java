package mindswap.Car.Api.controller;


import jakarta.validation.Valid;
import mindswap.Car.Api.dto.RentalCreationDto;
import mindswap.Car.Api.dto.RentalDto;
import mindswap.Car.Api.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;


    @GetMapping("/all")
    public ResponseEntity<List<RentalDto>> getAllRentals() {
        List<RentalDto> rentalDto = rentalService.getAllRentals();
        return new ResponseEntity<>(rentalDto, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getById(@RequestParam("id") Long id) {
        RentalDto rentalDto = rentalService.getById(id);
        return new ResponseEntity<>(rentalDto, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalCreationDto rentalDto) {
        RentalDto rental = rentalService.createRental(rentalDto);
        return new ResponseEntity<>(rental, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RentalDto> deleteRental(@PathVariable("id") Long id) {
        RentalDto deletedRental = rentalService.deleteRental(id);
        return new ResponseEntity<>(deletedRental, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDto> updateRental(@Valid @PathVariable("id") Long id, @RequestBody RentalDto rentalDto) {
        RentalDto updatedRental = rentalService.updateRental(id, rentalDto);
        return new ResponseEntity<>(updatedRental, HttpStatus.OK);
    }


}
