package mindswap.Car.Api.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Rentals")

public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Car pode ter muitos rentals
    @ManyToOne
    private Car car;

    //User pode ter muitos rentals
    @ManyToOne
    private User user;


}
