package mindswap.Car.Api.service;

import mindswap.Car.Api.converter.RentalConverter;
import mindswap.Car.Api.dto.RentalCreationDto;
import mindswap.Car.Api.dto.RentalDto;
import mindswap.Car.Api.model.Rental;
import mindswap.Car.Api.repository.CarRepository;
import mindswap.Car.Api.repository.RentalRepository;
import mindswap.Car.Api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private RentalConverter rentalConverter;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    public List<RentalDto> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream().map(rentalConverter::fromModelToDto).collect(Collectors.toList());

    }


    public RentalDto getById(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Error"));
        return rentalConverter.fromModelToDto(rental);
    }


    public RentalDto createRental(RentalCreationDto rentalDto) {
        Rental rental = rentalConverter.fromDtoToModel(rentalDto);
        rentalRepository.save(rental);
        return rentalConverter.fromModelToDto(rental);
    }

    public RentalDto deleteRental(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Error"));
        if (rental != null) {
            rentalRepository.deleteById(id);
        }
        return rentalConverter.fromModelToDto(rental);
    }

    public RentalDto updateRental(Long id, RentalDto rentalDto) {
        Rental rentalExist = rentalRepository.findById(id).orElse(null);
        if (rentalExist != null) {
            Rental updateRental = rentalConverter.fromDtoToModel(rentalDto);
            updateRental.setId(id);
            updateRental = rentalRepository.save(updateRental);
            return rentalConverter.fromModelToDto(updateRental);
        }
        return null;
    }
}
