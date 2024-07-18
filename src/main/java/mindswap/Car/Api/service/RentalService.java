package mindswap.Car.Api.service;

import mindswap.Car.Api.converter.RentalConverter;
import mindswap.Car.Api.dto.RentalCreationDto;
import mindswap.Car.Api.dto.RentalDto;
import mindswap.Car.Api.exception.RentalException;
import mindswap.Car.Api.message.Message;
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
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new RentalException(Message.INVALID_RENTAL_ID));
        return rentalConverter.fromModelToDto(rental);
    }

    /******************************  Validate Rental Null    ****************************************/
    private void ValidateRental(RentalCreationDto rentalDto) {
        if (rentalDto.getCarId() == null || rentalDto.getCarId() == 0) {
            throw new RentalException(Message.INVALID_CAR_ID);
        }


        if (rentalDto.getUserId() == null || rentalDto.getUserId() == 0) {
            throw new RentalException(Message.INVALID_USER_ID);
        }
    }

    /******************************  Validate Update Rental     ****************************************/
    private void ValidateRentalUpdate(RentalDto rentalDto) {
        if (rentalDto.getCar().getId() == null || rentalDto.getCar().getId() == 0) {
            throw new RentalException(Message.INVALID_CAR_ID);

        }
        if (!carRepository.existsById(rentalDto.getCar().getId())) {
            throw new RentalException(Message.CAR_DOES_NOT_EXIST);
        }
        if (rentalDto.getUser().getId() == null || rentalDto.getUser().getId() == 0) {
            throw new RentalException(Message.INVALID_USER_ID);
        }
        if (!userRepository.existsById(rentalDto.getUser().getId())) {
            throw new RentalException(Message.USER_DOES_NOT_EXIST);
        }

    }


    public RentalDto createRental(RentalCreationDto rentalDto) {
        ValidateRental(rentalDto);
        Rental rental = rentalConverter.fromDtoToModel(rentalDto);
        rentalRepository.save(rental);
        return rentalConverter.fromModelToDto(rental);
    }

    public RentalDto deleteRental(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new RentalException(Message.INVALID_RENTAL_DELETE));
        if (rental != null) {
            rentalRepository.deleteById(id);
        }
        return rentalConverter.fromModelToDto(rental);
    }

    public RentalDto updateRental(Long id, RentalDto rentalDto) {
        Rental rentalExist = rentalRepository.findById(id).orElseThrow(() -> new RentalException(Message.INVALID_RENTAL_UPDATE));
        if (rentalExist != null) {
            Rental updateRental = rentalConverter.fromDtoToModel(rentalDto);
            updateRental.setId(id);
            ValidateRentalUpdate(rentalDto);

            updateRental = rentalRepository.save(updateRental);

            return rentalConverter.fromModelToDto(updateRental);
        }
        return null;
    }
}
