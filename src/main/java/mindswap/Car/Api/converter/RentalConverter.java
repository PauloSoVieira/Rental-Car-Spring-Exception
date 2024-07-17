package mindswap.Car.Api.converter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mindswap.Car.Api.dto.RentalCreationDto;
import mindswap.Car.Api.dto.RentalDto;
import mindswap.Car.Api.model.Car;
import mindswap.Car.Api.model.Rental;
import mindswap.Car.Api.model.User;
import org.springframework.stereotype.Component;

@Component
public class RentalConverter {

    @PersistenceContext
    private EntityManager entityManager;


    public Rental fromDtoToModel(RentalDto rentalDto) {
        Rental rental = new Rental();
        rental.setId(rentalDto.getId());
        rental.setUser(new UserConverter().fromDtoToModel(rentalDto.getUser()));
        rental.setCar(new CarConverter().fromDtoToModel(rentalDto.getCar()));
        return rental;
    }

    public RentalDto fromModelToDto(Rental rental) {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(rental.getId());
        rentalDto.setUser(new UserConverter().fromModelToDto(rental.getUser()));
        rentalDto.setCar(new CarConverter().fromModelToDto(rental.getCar()));
        return rentalDto;

    }

    public Rental fromDtoToModel(RentalCreationDto rentalDto) {
        Rental rental = new Rental();
        rental.setUser(entityManager.getReference(User.class, rentalDto.getUserId()));
        rental.setCar(entityManager.getReference(Car.class, rentalDto.getCarId()));
        
        return rental;
    }
}
